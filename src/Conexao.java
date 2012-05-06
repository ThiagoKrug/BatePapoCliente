/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiago
 */
public class Conexao {

    private String ip;
    private int porta;

    public Conexao(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
    }

    public void commandServer() {
        this.enviarReceber("SERVER " + ip + " " + porta);
    }

    public String[] commandUser(String username) {
        return this.enviarReceber("USER " + username);
    }

    public void commandMensagem(String text) {
        this.enviar("MSG " + text);
    }

    public String[] enviarReceber(String m) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] msg = m.getBytes();
        InetAddress host = null;
        try {
            host = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }

        DatagramPacket request = new DatagramPacket(msg, m.length(), host, porta);

        try {
            socket.send(request);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] buffer = new byte[1000];
        DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        try {
            socket.receive(reply);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Reply: " + new String(reply.getData()));
        if (socket != null) {
            socket.close();
        }
        return new Interpretador().interpretarMensagem(reply);
    }

    public void enviar(String m) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] msg = m.getBytes();
        InetAddress host = null;
        try {
            host = InetAddress.getByName(ip);
        } catch (UnknownHostException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }

        DatagramPacket request = new DatagramPacket(msg, m.length(), host, porta);

        try {
            socket.send(request);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (socket != null) {
            socket.close();
        }
    }

    class Receptora extends Thread {
        
        private ServerSocket serverSocket;
        private 

        public Receptora() {
        }

        public void run() {
            while (true) {
                try {
                    DatagramSocket conexao = serverSocket.accept();
                } catch (IOException ex) {
                    Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
                }
                DatagramSocket socket = null;
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                try {
                    socket.receive(reply);
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalView.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Reply: " + new String(reply.getData()));
                if (socket != null) {
                    socket.close();
                }
                String[] mensagem = new Interpretador().interpretarMensagem(reply);
            }
        }
    }
}
