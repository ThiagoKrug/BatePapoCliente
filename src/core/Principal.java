package core;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.PrincipalView;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author thiago
 */
public class Principal {

    private static Principal principal;
    private Socket conexaoServidor;
    private static Properties configuracao;

    private Principal() {
        lerConfiguracao();
    }

    public static Principal getInstance() {
        if (principal == null) {
            principal = new Principal();
        }
        return principal;
    }

    public void iniciar() {
        try {
            conexaoServidor = new Socket(configuracao.getProperty("ip"),
                     Integer.valueOf(configuracao.getProperty("porta")));
            //Emissor emissor = new Emissor(getConexaoServidor());
//            Thread receptor = new Thread(new Receptor(conexaoServidor, PrincipalView.getInstance()));
//            receptor.start();
            //emissor.start();
            
            PrintStream saida = new PrintStream(conexaoServidor.getOutputStream()); 
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Entre com o seu nome: ");
            String meuNome = "SERVER localhost 5588"; // teclado.readLine();
            saida.println(meuNome);
            Receptor r = new Receptor(conexaoServidor, PrincipalView.getInstance());
            Thread receptor = new Thread(r);
            receptor.start();
            
            String linha;

            while (true) {
                // ler a linha digitada no teclado   
                System.out.print("> ");
                linha = teclado.readLine();
                // antes de enviar, verifica se a conexão não foi fechada   
//                if (done) {
//                    break;
//                }
                // envia para o servidor   
                saida.println(linha);
            }
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Properties getConfiguracao() {
        return configuracao;
    }

    public static String getPropriedade(String chave) {
        return configuracao.getProperty(chave);
    }

    public static void setPropriedade(String chave, String valor) {
        configuracao.setProperty(chave, valor);
    }

    public static void lerConfiguracao() {
        configuracao = new Properties();
        FileReader fr;
        try {
            fr = new FileReader("configuracao.prop");
            configuracao.load(fr);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void gravarConfiguracao() {
        try {
            FileWriter fw = new FileWriter("configuracao.prop");
            configuracao.store(fw, "");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return the conexaoServidor
     */
    public Socket getConexaoServidor() {
        return conexaoServidor;
    }

    /**
     * @param conexaoServidor the conexaoServidor to set
     */
    public void setConexaoServidor(Socket conexaoServidor) {
        this.conexaoServidor = conexaoServidor;
    }
    
}
