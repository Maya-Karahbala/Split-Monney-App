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
public class Group implements java.io.Serializable {
    public String name;
    public ArrayList<String> members ;

    public Group(String name) {
        this.name = name;
        members= new ArrayList<>();
    }
    
}
