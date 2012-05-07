package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Classe responsável por receber as mensagens do servidor
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 */
public class Receptor extends Observable implements Runnable {

    private Socket conexaoServidor;
    
    /**
     * Metodo estático que seta como true ou false se a conexao encontra-se
     * encerrada.
     */
    public static boolean conexaoEncerrada;
    private String mensagemServidor;

    /**
     * Metodo construtor da classe Receptor que recebe como parametro Um socket
     * @param conexaoServidor 
     */
    public Receptor(Socket conexaoServidor) {
        this.conexaoServidor = conexaoServidor;
    }

    /**
     * Metodo que faz a troca de mensagens entre o cliente e o servidor
     */
    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(conexaoServidor.getInputStream()));
            String linha;
            while (true) {
                // recebe a mensagem do servidor
                linha = entrada.readLine();
                // se está null pode ser que o servidor terminou inesperadamente a conexão
                if (linha == null) {
                    System.out.println("Conexão encerrada inesperadamente!");
                    break;
                }
                System.out.println(linha);
            }
        } catch (IOException e) {
            Logger.getLogger(Receptor.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            System.exit(1);
        }
        // sinaliza para o emissor que a conexão terminou
        Receptor.conexaoEncerrada = true;
    }

    /**
     * Metodo que retorna a mensagem do servidor
     * @return the mensagemServidor
     */
    public String getMensagemServidor() {
        return mensagemServidor;
    }

    /**
     * Metodo que seta a mensagem do Servidor
     * @param mensagemServidor the mensagemServidor to set
     */
    public void setMensagemServidor(String mensagemServidor) {
        this.mensagemServidor = mensagemServidor;
    }
}