/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author maya
 */
public class Message implements java.io.Serializable {
    //mesaj tipleri enum 
    public static enum Message_Type {Disconnected,None, Name, Draw,RivalConnected, ChangeColor, Selected, Bitis,Start,playAgain}
    //mesajın tipi
    public Message_Type type;
    //mesajın içeriği obje tipinde ki istenilen tip içerik yüklenebilsin
    public Object content;
    public String reciverName;
    
    public Message(Message_Type t)
    {
        this.type=t;
        reciverName="";
    }
 

    
    
}
  
 

    
    
