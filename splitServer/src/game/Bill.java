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
public class Bill implements java.io.Serializable {
    public String sender;
   public Double amount, ammountToPaid;
   public String description;
    public Group reciver;


    public Bill(String sender, Double amount, Double ammountToPaid, String description, Group group) {
        this.sender = sender;
        this.amount = amount;
        this.ammountToPaid = ammountToPaid;
        this.description = description;
        this.reciver = group;
    }

    @Override
      public String toString() {
        return "sende "+sender+" amount "+amount+" dec "+description+" gr name  "+reciver.name +" mem "+reciver.members.size()
                +"amunt to paid "+ammountToPaid;
        //To change body of generated methods, choose Tools | Templates.
    }

  
     
}

