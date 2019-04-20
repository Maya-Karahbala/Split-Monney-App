/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/**
 *
 * @author maya
 */
public class App {

    public static Thread control;

    public static void main(String[] args) {
      /*  Client.Start("127.0.0.1", 2000);
        // start measage reciving thread
        Thread t = new control();
        t.start();*/
      JFrame f=new JFrame("ComboBox Example");    
    String country[]={"India","Aus","U.S.A","England","Newzealand"};        
    JComboBox cb=new JComboBox(country);  
   cb.removeItem("India");
    cb.setBounds(50, 50,90,20);    
    f.add(cb);        
    f.setLayout(null);    
    f.setSize(400,500);    
    f.setVisible(true);     
    ArrayList<String> otherCleints=new ArrayList<>();
    otherCleints.add("dsd");
        otherCleints.add("dsd");
            otherCleints.add("maya");
            System.out.println(otherCleints);
        Bill b=new Bill("me", 40.0, "mmmm", new Group("ff"));
         Message m=new Message(Message.Message_Type.None) ;
        Object bb=b;
         Object mm=m;
        System.out.println(mm instanceof Bill); 
        System.out.println(mm instanceof Message); 
        
   }
    public static class control extends Thread {

    @Override
    public void run() {
        while (Client.socket.isConnected()) {
        }
    }
}
}


