package planegame;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dmitriy
 */
public class ServerResponseThread extends Thread{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
        
    private final String POLICY_REQUEST = "<policy-file-request/>";
    private  String POLICY_XML =
            "<cross-domain-policy>"
                +"<allow-access-from domain=\"*\" to-ports=\"*\"/>"
            +"</cross-domain-policy>";

    
    public ServerResponseThread(Socket socket) throws IOException{
        this.socket = socket;
        //start();
    }
    
    public void run(){
        try{            
            
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket
                    .getOutputStream())), true);
            readPolicyRequest();            
            
        }catch(Exception e){
            System.err.println("IO Exception................" + e.getMessage());
        }finally{
            try
            {
                //System.out.println("DMK");
                //Game.removeClient(client);
                this.socket.close();
            }
            catch (IOException e)
            {
                System.err.println("Socket not closed.............");
            }
        }
    }
    
    private void readPolicyRequest() {
           try {
               System.out.println("OK2");
                String request = read();
                System.out.println("client says '" + request + "'");
                System.out.println("flag "+request.equals(POLICY_REQUEST));
                if (request.equals(POLICY_REQUEST)) {
                    System.out.println("OK");
                     writePolicy();
                }
            }
            catch (Exception e) {
                System.out.println("Exception (readPolicyRequest): " + e.getMessage());
            }
            
    }
    
    private void writePolicy() {
        try {
            this.out.print(POLICY_XML + "\u0000");
            this.out.close();
            System.out.println("policy sent to client");
        }
        catch (Exception e) {
            System.out.println("Exception (writePolicy): " + e.getMessage());
        }
    }
    
    private String read() {
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
