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
public class App {

    public static Thread control;

    public static void main(String[] args) {
        Client.Start("127.0.0.1", 2000);
        // start measage reciving thread
        Thread t = new control();
        t.start();

    }
    public static class control extends Thread {

    @Override
    public void run() {
        while (Client.socket.isConnected()) {
        }
    }
}
}


