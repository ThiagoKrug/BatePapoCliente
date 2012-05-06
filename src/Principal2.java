
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author thiago
 */
public class Principal2 {

    private static Principal2 p;
    private Conexao2 conexao;
    private Servidor servidor;
    private BufferedReader br;

    private Principal2() {
        conexao = Conexao2.getInstance();
        servidor = new Servidor();
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public static Principal2 getInstance() {
        if (p == null) {
            p = new Principal2();
        }
        return p;
    }

    public int iniciar() {
        return this.conectar();
    }
    
    public int conectar() {
        String ip = "";
        int porta = 0;
        try {
            System.out.println("Informe o número IP do servidor:");
            ip = br.readLine();
            System.out.println("Informe o número da porta do servidor:");
            porta = Integer.valueOf(br.readLine());
        } catch (IOException ex) {
            Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        servidor.setIp(ip);
        servidor.setPorta(porta);
        this.enviarReceber(servidor, "SERVER");
        return -1;
    }

    public Mensagem enviarReceber(Servidor server, String m) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] msg = m.getBytes();
        InetAddress host = null;
        try {
            host = InetAddress.getByName(server.getIp());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        int porta = server.getPorta();

        DatagramPacket request = new DatagramPacket(msg, m.length(), host, porta);

        try {
            socket.send(request);
        } catch (IOException ex) {
            Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] buffer = new byte[1000];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(reply);
        } catch (IOException ex) {
            Logger.getLogger(Principal2.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Reply: " + new String(reply.getData()));
        if (socket != null) {
            socket.close();
        }
        return new Mensagem(new String(reply.getData()));
    }
}
