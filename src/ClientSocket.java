
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
public class ClientSocket {
    
    private Socket socket;
    private PrintWriter out;
    
    public ClientSocket(Socket socket, PrintWriter out){
        this.socket = socket;
        this.out = out;
    }
    
    public void setSocket(Socket socket){
        this.socket = socket;
    }
    
    public void setOut(PrintWriter out){
        this.out = out;
    }
    
    public PrintWriter getOut(){
        return out;
    }
    
    public Socket getSocket(){
        return socket;
    }
}
