package planegame;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dmitriy
 */
public class ServerRespons extends Thread{
    
    private ServerSocket SERVER;
    private int port;
        
    public ServerRespons(int PORT){
        port = PORT;
    }
       
    public void run(){
        try{
            SERVER = new ServerSocket(port);
            System.out.println("Start server RESPONSE..................");
            
            while(true){
                Socket SOCKET = SERVER.accept();
                try{
                    ServerResponseThread my = new ServerResponseThread(SOCKET);
                    my.start();
                }catch(IOException e){
                    System.out.println(e);
                }
            }
        }catch(Exception e){
            System.out.println("Response server " + e.getMessage());
        }
    }
    
}
