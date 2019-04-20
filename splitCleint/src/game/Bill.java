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
public class Bill {
    String sender;
    Double amount;
    String description;
     Group group;

    public Bill(String sender, Double amount, String description, Group group) {
        this.sender = sender;
        this.amount = amount;
        this.description = description;
        this.group = group;
    }

    @Override
    public String toString() {
        return "sende "+sender+" amount "+amount+" dec "+description+" gr name  "+group.name +" mem "+group.members.size(); //To change body of generated methods, choose Tools | Templates.
    }

  
     
}
