/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package splitServer;

import game.Bill;
import game.Group;
import game.Message;
import static game.Message.Message_Type.Name;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SClient {

    int id;
    public String name = "NoName";
    Socket soket;
    ObjectOutputStream sOutput;
    ObjectInputStream sInput;
    Listen listenThread;

    SClient rival;

    public boolean paired = false;

    public SClient(Socket gelenSoket, int id) {
        this.soket = gelenSoket;
        this.id = id;
        try {
            this.sOutput = new ObjectOutputStream(this.soket.getOutputStream());
            this.sInput = new ObjectInputStream(this.soket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.listenThread = new Listen(this);


    }

    //client dinleme threadi
    //her clientin ayrı bir dinleme thredi var
    class Listen extends Thread {

        SClient TheClient;
        Object msg;
        Message received;
        Bill receivedBill;
        Group receivedGroup;
        SClient reciver = null;

        //thread nesne alması için yapıcı metod
        Listen(SClient TheClient) {
            this.TheClient = TheClient;
        }

        public void run() {
            //client bağlı olduğu sürece dönsün
            while (TheClient.soket.isConnected()) {

                try {
                    //mesajı bekleyen kod satırı
                    msg=(TheClient.sInput.readObject());
                    if(msg instanceof Bill){
                     // if msg type is bill         
                        receivedBill=(Bill)msg;
                        receivedBill.ammountToPaid= receivedBill.amount/(receivedBill.reciver.members.size()+1);
                        // send bill to group members
                         for (SClient Client : Server.Clients) {
                              for (String member : receivedBill.reciver.members) {
                                  // if clent name in bill groubs number send bill
                                 if(Client.name.equals(member)){
                                     
                                      Server.Send(Client, receivedBill);
                                 }
                             }
                            
                              }
                        
                    }
                    else if(msg instanceof Group){
                        receivedGroup=(Group)msg;
                          for (SClient Client : Server.Clients) {
                              for (String member : receivedGroup.members) {
                                  // if client name in bill groups number and not the sender who send bill
                                 if(Client.name.equals(member)&&
                                       !Client.name.equals(receivedGroup.members.get(receivedGroup.members.size()-1))){
                                     
                                      Server.Send(Client, receivedGroup);
                                 }
                             }
                            
                              }
                        
                    }
                    else{
                      
                    received = (Message) msg;

                    if (received.type == Name) {
                        // add clents with uniqu user name
                        TheClient.name = received.content.get(0).toString();
                        boolean containsSameName = false;
                        for (SClient Client : Server.Clients) {
                            if (Client.name.equals(TheClient.name)) {
                                containsSameName = true;
                            }
                           
                        }
                        if (!containsSameName) {
                            Server.Clients.add(TheClient);
                        }
                        
                           for (SClient Client : Server.Clients) {
                            
                            Server.Display(Client.name + " id = " + Client.id);
                        }
                           //send clents names to all clents
                            Message msg = new Message(Message.Message_Type.clientsNames);
                             for (SClient Client : Server.Clients) {
                             msg.content.add(Client.name);
                            
                              }
                              for (SClient Client : Server.Clients) {
                                 Server.Send(Client, msg);
                            
                              }
                            

                    } else {
                        for (SClient clnt : Server.Clients) {
                           
                            if (clnt.name.equals(received.reciverName)) {
                                reciver = clnt;
                            }
                        }
                        if (reciver != null) {
                            Server.Send(reciver, received);

                        }
                        reciver = null;
                    }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SClient.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }
}

