
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
public class Conexao {
    
    private static Conexao conexao = null;
    private DatagramSocket socket;

    private Conexao() {
        
    }
    
    public static Conexao getInstance() {
        if (conexao == null) {
            conexao = new Conexao();
        }
        return conexao;
    }
    
    public void enviar(Servidor server, Mensagem m) {
        DatagramSocket socket;
        socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] msg = m.getMensagem().getBytes();
        InetAddress host = null;
        try {
            host = InetAddress.getByName(server.getIp());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        int porta = server.getPorta();
        
        DatagramPacket request = new DatagramPacket(msg, m.getMensagem().length(), host, porta);
        try {
            socket.send(request);
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] buffer = new byte[1000];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        //System.out.println("1");
        try {
            //System.out.println("2");
            socket.receive(reply);
            System.out.println("3");
        } catch (IOException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("4");
        System.out.println("Reply: " + new String(reply.getData()));
        if (socket != null) {
            socket.close();
        }
    }
    
}
