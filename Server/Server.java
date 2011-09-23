/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlane.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Dmitriy
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    static final int PORT = 5000;
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket SERVER = new ServerSocket(PORT);
        System.out.println("SERVER STARTED.....................");
        try {
            while(true){
                Socket SOCKET = SERVER.accept();
                try{
                    new ServerThread(SOCKET);
                } catch(IOException e) {
                    SOCKET.close();
                }
            }
        } finally {
            SERVER.close();
            //кордлопрб
        }
    }
}
