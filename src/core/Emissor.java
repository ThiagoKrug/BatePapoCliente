package core;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe emissor e que extende a classe Thread
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 */
public class Emissor extends Thread {

    private Socket conexaoServidor;
    private String comando;
    private String mensagem;
    private PrintStream saida;

    /**
     * Metodo construtor da classe Emissor, que recebe como parametro um socket,
     * um comando e uma mensagem.
     * @param conexaoServidor
     * @param comando
     * @param mensagem 
     */
    public Emissor(Socket conexaoServidor, String comando, String mensagem) {
        this.conexaoServidor = conexaoServidor;
        this.comando = comando;
        this.mensagem = mensagem;
    }

    /**
     * Metodo construtor da classe Emissor, que recebe como parametro um socket
     * e um comando.
     * @param conexaoServidor
     * @param comando 
     */
    public Emissor(Socket conexaoServidor, String comando) {
        this.conexaoServidor = conexaoServidor;
        this.comando = comando;
    }
    
    /**
     * 
     */
    public void comandoServer() {
        saida.println(Conexao.SERVER + " " + Principal.getPropriedade("ip")
                + " " + Integer.valueOf(Principal.getPropriedade("porta")));
    }

    /**
     * Comando responsavel por informar o nome do usuario.
     */
    public void comandoUser() {
        saida.println(Conexao.USER + " " + Principal.getPropriedade("usuario"));
    }
    /**
     * Comando responsavel por verificar os clientes conectados.
     */
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
     * Metodo que retorna o comando
     * @return the comando
     */
    public String getComando() {
        return comando;
    }

    /**
     * Metodo que seta um comando
     * @param comando the comando to set
     */
    public void setComando(String comando) {
        this.comando = comando;
    }

    /**
     * Metodo que retorna uma mensagem
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * Metodo que seta uma mensagem
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
