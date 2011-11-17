/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Dmitriy
 */
public class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private int client;
    private ServerGame Game;
    
    
    public ServerThread(Socket socket, ServerGame Game) throws IOException{
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket
                .getOutputStream())), true);
        
        //readPolicyRequest();
        
        client = Game.addClient(new ClientSocket(socket, out));
        this.Game = Game;
        //client = ServerGame.addClient(new ClientSocket(socket, out));        
        
    }

    public void run(){
        try{            
                        
            while(true){
                
                String command = in.readLine();
                
                if(command.equals("exit")){
                    break;
                }
                System.out.println(client);
                System.out.println("Command " + command);
                out.println(command);
            }
            System.out.println("Closing...................");
        }catch(Exception e){
            System.err.println("IO Exception................" + e.getMessage());
        }finally{
            try
            {
                Game.removeClient(client);
                this.socket.close();
            }
            catch (IOException e)
            {
                System.err.println("Socket not closed.............");
            }
        }
    } 
}
