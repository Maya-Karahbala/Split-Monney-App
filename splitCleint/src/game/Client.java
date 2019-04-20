/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static game.Client.sInput;
import static game.app.thisApp;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INSECT
 */
// serverdan gelecek mesajları dinleyen thread
class Listen extends Thread {

    public void run() {
        //soket bağlı olduğu sürece dön
        Message received;
        while (Client.socket.isConnected()) {
            try {
                //mesaj gelmesini bloking olarak dinyelen komut
                received = (Message) (sInput.readObject());
                //mesaj gelirse bu satıra geçer
                //mesaj tipine göre yapılacak işlemi ayır.
                switch (received.type) {
                    //draw
                    case Draw:

                    case Disconnected:

                        break;
                    case Bitis:

                        break;
                    case clientsNames:
                        thisApp.addingEvent = true;
                        thisApp.cmbClients.removeAllItems();
                        thisApp.cmbAddtoGroup.removeAllItems();

                        for (Object object : received.content) {
                            // add other elemnt names except cleint itself
                            if (!thisApp.txtName.getText().equals((String) object)) {
                                if (!Client.otherCleints.contains(object)) {
                                    Client.otherCleints.add((String) object);
                                }
                                thisApp.cmbClients.addItem((String) object);
                                thisApp.cmbAddtoGroup.addItem((String) object);
                            }
                        }
                      
                        
                        thisApp.listModel.removeAllElements();
                        thisApp.cmbAddtoGroup.setSelectedIndex(-1);
                        thisApp.addingEvent = false;
                        break;

                    case Selected:
                  
                        // thisApp.jTextArea1.setText((String) received.content.get(0));
                        System.out.println(received.content);

                        break;
                    case playAgain:

                        break;

                }
            } catch (IOException ex) {
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}

public class Client {

    //her clientın bir soketi olmalı
    public static Socket socket;

    //verileri almak için gerekli nesne
    public static ObjectInputStream sInput;
    //verileri göndermek için gerekli nesne
    public static ObjectOutputStream sOutput;
    //serverı dinleme thredi 
    public static Listen listenMe;
    public static ArrayList<Group> groubs;
    public static ArrayList<String> otherCleints;
    public static ArrayList<Bill> sendedBills;
    public static ArrayList<Bill> recivedBills;

    public static void Start(String ip, int port) {
        try {

            // Client Soket nesnesi
            groubs = new ArrayList<Group>();
            sendedBills = new ArrayList<Bill>();
            recivedBills = new ArrayList<Bill>();
            otherCleints = new ArrayList<String>();
            Client.socket = new Socket(ip, port);
            Client.Display("Servera bağlandı");
            // input stream
            Client.sInput = new ObjectInputStream(Client.socket.getInputStream());
            // output stream
            Client.sOutput = new ObjectOutputStream(Client.socket.getOutputStream());
            Client.listenMe = new Listen();
            Client.listenMe.start();

            //ilk mesaj olarak isim gönderiyorum
            /* Message msg = new Message(Message.Message_Type.Name);
            msg.content = "deneme";
            Client.Send(msg);*/
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //client durdurma fonksiyonu
    public static void Stop() {
        try {
            if (Client.socket != null) {
                Client.listenMe.stop();
                Client.socket.close();
                Client.sOutput.flush();
                Client.sOutput.close();

                Client.sInput.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * **********************
     */
    public static void Display(String msg) {

        System.out.println(msg);

    }

    //mesaj gönderme fonksiyonu
    public static void Send(Message msg) {
        try {
            Client.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
