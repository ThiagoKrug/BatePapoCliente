/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author thiago
 */
public class Receptor extends Observable implements Runnable {

    private Socket conexaoServidor;
    public static boolean conexaoEncerrada;

    public Receptor(Socket conexaoServidor, Observer observador) {
        this.conexaoServidor = conexaoServidor;
        this.addObserver(observador);
    }

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
        }
        // sinaliza para o emissor que a conexão terminou
        Receptor.conexaoEncerrada = true;
    }
}