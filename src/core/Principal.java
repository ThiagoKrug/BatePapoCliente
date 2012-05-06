package core;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
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
public class Principal implements Observer {

    private static Principal principal;
    private Socket conexaoServidor;
    private static Properties configuracao;
    private Receptor receptor;

    private Principal() {
        lerConfiguracao();
    }

    public static Principal getInstance() {
        if (principal == null) {
            principal = new Principal();
        }
        return principal;
    }

    @Override
    public void update(Observable o, Object arg) {
    }

    public void iniciar() {
        try {
            conexaoServidor = new Socket(configuracao.getProperty("ip"),
                    Integer.valueOf(configuracao.getProperty("porta")));
            PrintStream saida = new PrintStream(conexaoServidor.getOutputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            saida.println(Conexao.SERVER + " " + configuracao.getProperty("ip") + " "
                    + Integer.valueOf(configuracao.getProperty("porta")));
            System.out.println("Entre com o nome do usuário: ");
            String meuNome = teclado.readLine();
            saida.println(meuNome);
            this.receptor = new Receptor(conexaoServidor);
            Thread r = new Thread(this.receptor);
            r.start();

            String linha;
            while (true) {
                // ler a linha digitada no teclado   
                linha = teclado.readLine();
                
                if ("QUIT".equalsIgnoreCase(new Interpretador().interpretarMensagem(linha)[0])) {
                    break;
                }
                // envia para o servidor   
                saida.println(linha);
            }

        } catch (UnknownHostException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.exit(1);
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

    /**
     * @return the receptor
     */
    public Receptor getReceptor() {
        return receptor;
    }

    /**
     * @param aReceptor the receptor to set
     */
    public void setReceptor(Receptor aReceptor) {
        receptor = aReceptor;
    }
}
