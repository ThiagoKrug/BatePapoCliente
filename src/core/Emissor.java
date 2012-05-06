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
public class Emissor extends Thread implements Observer {

    private Socket conexaoServidor;
    private boolean conexaoTerminada;
    private String comando;
    private String mensagem;

    public Emissor(Socket conexaoServidor, String comando, String mensagem) {
        this.conexaoServidor = conexaoServidor;
        this.conexaoTerminada = false;
        this.comando = comando;
        this.mensagem = mensagem;
    }

    @Override
    public void run() {
        try {
            // cria o stream de saida com servidor
            PrintStream saida = new PrintStream(conexaoServidor.getOutputStream());
            // verifica se a conexao esta aberta
            if (conexaoTerminada) {
                return;
            }
            
            // faz o comando enviado
            if (comando.equalsIgnoreCase(Conexao.SERVER)) {
                saida.println(mensagem);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Emissor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if ("Conexão encerrada".equalsIgnoreCase(String.valueOf(arg))) {
            this.conexaoTerminada = true;
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
