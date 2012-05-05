/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiago
 */
public class Servidor {
    
    private String ip;
    private int porta;

    public Servidor() {
    }

    public Servidor(String ip, int porta) {
        this.ip = ip;
        this.porta = porta;
    }
    
    public boolean conectar() {
        
        return true;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
    
}
