
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
public class ServerRequest extends Thread{
    
    private ServerSocket SERVER;
    private ServerGame Game;
    private int port;
    
    public ServerRequest(int PORT){
       port = PORT;
    }
        
    public void run(){
        try{
            SERVER = new ServerSocket(port);
            Game = new ServerGame();
            System.out.println("Start server REQUEST..................");
            
            while(true){
                Socket SOCKET = SERVER.accept();
                
                    ServerThread my = new ServerThread(SOCKET, Game);
                    my.start();
               
            }

        }catch(Exception e){
            System.out.println("Request "+ e.getMessage());
        }finally{
            try{
            SERVER.close();
            }catch(Exception e){
                
            }
        }
    }
}
