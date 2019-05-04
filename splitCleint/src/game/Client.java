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
        Object msg;
        Message received;
        Bill recivedBill;
        Group recivedGroup;
        while (Client.socket.isConnected()) {
            try {
                //mesaj gelmesini bloking olarak dinyelen komut
                msg = (sInput.readObject());
                if (msg instanceof Bill) {

                    recivedBill = (Bill) msg;
                    Client.recivedBills.add(recivedBill);
                    thisApp.tblHomeMsgsModel.addRow(new Object[]{"you recived new bill from "+recivedBill.sender});
                    thisApp.fillRecivedBilltable("All");
                   
                } else if (msg instanceof Group) {

                    recivedGroup = (Group) msg;

                    //delete client it self from members and add msg sender to members
                    recivedGroup.members.remove(thisApp.txtName.getText());

                    Client.groubs.add(recivedGroup);
                    thisApp.cmbGroups.addItem(recivedGroup.name);
                    // add to my groubsCombobox
                    thisApp.cmbMygroubs.addItem(recivedGroup.name);
                    thisApp.cmbMyBillsgroubs.addItem(recivedGroup.name);
                    thisApp.cmbRecivedBillsgroubs.addItem(recivedGroup.name);
                     thisApp.tblHomeMsgsModel.addRow(new Object[]{"you have new group called "+recivedGroup.name});
                    // }

                } else {
                    received = (Message) msg;
                    //mesaj gelirse bu satıra geçer
                    //mesaj tipine göre yapılacak işlemi ayır.
                    switch (received.type) {
                        //draw
                        case paid:
                            for (Bill recivedBill1 : Client.recivedBills) {
                               
                                if (recivedBill1.id.equals(received.content.get(0))) {
                                   thisApp.tblHomeMsgsModel.addRow(new Object[]{"your "+recivedBill1.id+" bill marked as paid"});
                                    recivedBill1.payingStatues.set(0, true);
                                    //reset recived Bill table
                                    thisApp.tblRecivedBillsModel.setRowCount(0);
                                    
                                   thisApp.fillRecivedBilltable(thisApp.cmbRecivedBillsgroubs.getItemAt(thisApp.cmbRecivedBillsgroubs.getSelectedIndex()));
                                 
                                }
                                
                            }
                        case clientsNames:
                            thisApp.addingEvent = true;
                            thisApp.cmbAddtoGroup.removeAllItems();

                            for (Object object : received.content) {
                                // add other elemnt names except cleint itself
                                if (!thisApp.txtName.getText().equals((String) object)) {
                                    if (!Client.otherCleints.contains(object)) {
                                        Client.otherCleints.add((String) object);
                                    }
                               
                                    thisApp.cmbAddtoGroup.addItem((String) object);
                                }
                            }

                            thisApp.listModel.removeAllElements();
                            thisApp.cmbAddtoGroup.setSelectedIndex(-1);
                            thisApp.addingEvent = false;
                            break;

                    }
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
    public static int billId;

    public static void Start(String ip, int port) {
        try {

            // Client Soket nesnesi
            billId = 0;
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
    public static void Send(Object msg) {
        try {
            Client.sOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
