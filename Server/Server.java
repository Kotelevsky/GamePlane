package planegame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;

/**
 *
 * @author Dmitriy
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    static final int PORT_REQUEST = 5000;
    static final int PORT_RESPONSE = 843;
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerRespons ServerRes = new ServerRespons(PORT_RESPONSE);
        ServerRes.start();
        ServerRequest ServerReq = new ServerRequest(PORT_REQUEST);
        ServerReq.start();
        
    }
}
