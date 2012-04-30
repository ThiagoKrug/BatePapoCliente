
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiago
 */
public class Principal {
    
    private static Principal p;

    private Principal() {
    }
    
    public static Principal getInstance() {
        if (p == null) {
            p = new Principal();
        }
        return p;
    }
    
    public int iniciar() {
        System.out.println("Informe o número IP do servidor:\nIP Servidor: ");
        
        return -1;
    }
    
    public void testaConexao() {
        Servidor server = new Servidor("localhost", 5588);
        Mensagem m = new Mensagem("teste", TipoMensagem.publico);
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] msg = m.getMensagem().getBytes();
        InetAddress host = null;
        try {
            host = InetAddress.getByName(server.getIp());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        int porta = server.getPorta();
        
        DatagramPacket request = new DatagramPacket(msg, m.getMensagem().length(), host, porta);
        
        try {
            socket.send(request);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] buffer = new byte[1000];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(reply);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Reply: " + new String(reply.getData()));
        if (socket != null) {
            socket.close();
        }
    }
    
}
