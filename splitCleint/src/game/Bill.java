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
   
    public String sender,id;
   public Double amount, ammountToPaid;
   public String description;
    public Group reciver;
    public ArrayList<Boolean> payingStatues;

    


    public Bill(String id,String sender, Double amount, Double ammountToPaid, String description, Group group) {
        this.sender = sender;
        this.id = id;
        this.amount = amount;
        this.ammountToPaid = ammountToPaid;
        this.description = description;
        this.reciver = group;
        payingStatues=new ArrayList<>();
        // for controling other group members payment 
        // one  member pay his payingStatu will be false
        for (String member : group.members) {
            payingStatues.add(false);
        }
        
    }

  
    @Override
    public String toString() {
        return "sende "+sender+" amount "+amount+" dec "+description+" gr name  "+reciver.name +" mem "+reciver.members.size()
                +"amunt to paid "+ammountToPaid+"id "+id;
        //To change body of generated methods, choose Tools | Templates.
    }

  
     
}
