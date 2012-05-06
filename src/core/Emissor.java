/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thiago
 */
public class Emissor extends Thread {

    private Socket conexaoServidor;
    private String comando;
    private String mensagem;
    private PrintStream saida;

    public Emissor(Socket conexaoServidor, String comando, String mensagem) {
        this.conexaoServidor = conexaoServidor;
        this.comando = comando;
        this.mensagem = mensagem;
    }

    public Emissor(Socket conexaoServidor, String comando) {
        this.conexaoServidor = conexaoServidor;
        this.comando = comando;
    }
    
    public void comandoServer() {
        saida.println(Conexao.SERVER + " " + Principal.getPropriedade("ip")
                + " " + Integer.valueOf(Principal.getPropriedade("porta")));
    }

    public void comandoUser() {
        saida.println(Conexao.USER + " " + Principal.getPropriedade("usuario"));
    }

    public void comandoNames() {
        saida.println(Conexao.NAMES);
    }

    @Override
    public void run() {
        try {
            // cria o stream de saida com servidor
            this.saida = new PrintStream(conexaoServidor.getOutputStream());
            // verifica se a conexao esta aberta
            if (Receptor.conexaoEncerrada) {
                return;
            } else if (comando.equalsIgnoreCase(Conexao.SERVER)) {
                this.comandoServer();
            } else if (comando.equalsIgnoreCase(Conexao.USER)) {
                this.comandoUser();
            }
        } catch (IOException ex) {
            Logger.getLogger(Emissor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the comando
     */
    public String getComando() {
        return comando;
    }

    /**
     * @param comando the comando to set
     */
    public void setComando(String comando) {
        this.comando = comando;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
