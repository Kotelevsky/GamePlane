/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
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
    private final String POLICY_REQUEST = "<policy-file-request/>";
    private  String POLICY_XML =
            "<?xml version=\"1.0\"?>"
            + "<cross-domain-policy>"
            + "<allow-access-from domain=\"*\" to-ports=\"*\" />"
            + "</cross-domain-policy>";
    
    public ServerThread(Socket socket, ServerGame Game) throws IOException{
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket
                .getOutputStream())), true);
        
        readPolicyRequest();
        
        client = Game.addClient(new ClientSocket(socket, out));
        this.Game = Game;
        //client = ServerGame.addClient(new ClientSocket(socket, out));        
        start();
    }

    protected void readPolicyRequest() {
           try {
               System.out.println("OK2");
                String request = read();
                System.out.println("OK1");
                System.out.println("client says '" + request + "'");
                System.out.println(request.equals(POLICY_REQUEST));
                if (request.equals(POLICY_REQUEST)) {
                    System.out.println("OK");
                   writePolicy();
                }
            }
            catch (Exception e) {
                System.out.println("Exception (readPolicyRequest): " + e.getMessage());
            }
            
    }
    
    protected void writePolicy() {
        try {
            this.out.println(POLICY_XML + "\u0000");
            this.out.close();
            System.out.println("policy sent to client");
        }
        catch (Exception e) {
            System.out.println("Exception (writePolicy): " + e.getMessage());
        }
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
            System.err.println("IO Exception................");
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
    
    protected String read() {
        StringBuffer buffer = new StringBuffer();
        int codePoint;
        boolean zeroByteRead = false;
        
        try {
            do {
                codePoint = this.in.read();

                if (codePoint == 0) {
                    zeroByteRead = true;
                }
                else if (Character.isValidCodePoint(codePoint)) {
                    buffer.appendCodePoint(codePoint);
                }
            }
            while (!zeroByteRead && buffer.length() < 200);
        }
        catch (Exception e) {
            System.out.println("Exception (read): " + e.getMessage());
        }
        
        return buffer.toString();
    } 
}
