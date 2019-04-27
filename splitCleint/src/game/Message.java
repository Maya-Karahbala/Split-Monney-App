/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author maya
 */
public class Message implements java.io.Serializable {
    //mesaj tipleri enum 
    public static enum Message_Type {Disconnected,None, Name, Draw,RivalConnected, clientsNames, Selected, Bitis,Start,AddGroup}
    //mesajın tipi
    public Message_Type type;
    public String reciverName;
    public ArrayList<Object> content ;
    
    public Message(Message_Type t)
    {
        this.type=t;
        reciverName="";
        content = new ArrayList<>();
    }
 

    
    
}
  
 

    
    
