package core;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe principal do sistema, que utiliza o padrão de projeto observer.
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 */
public class Principal implements Observer {

    private static Principal principal;
    private Socket conexaoServidor;
    private static Properties configuracao;
    private Receptor receptor;

    
    /**
     * Método construtor da classe principal
     */
    private Principal() {
        lerConfiguracao();
    }

    /**
     * Método que instancia um objeto da classe principal, utilizando-se do
     * padrão de projeto singleton.
     * @return 
     */
    public static Principal getInstance() {
        if (principal == null) {
            principal = new Principal();
        }
        return principal;
    }

    @Deprecated
    @Override
    public void update(Observable o, Object arg) {
    }

    /**
     * Método que inicia a conexão com o servidor e que fica aguardando a ação
     * do usuário.
     */
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

    /**
     * Método que retorna a configuração
     * @return configuracao
     */
    public static Properties getConfiguracao() {
        return configuracao;
    }

    /**
     * Metodo que retorna a propriedade a partir de uma determinada chave.
     * @param chave
     * @return  
     */
    public static String getPropriedade(String chave) {
        return configuracao.getProperty(chave);
    }

    /**
     * Método que altera o valor de uma propriedade de uma dererminada chave.
     * @param chave
     * @param valor 
     */
    public static void setPropriedade(String chave, String valor) {
        configuracao.setProperty(chave, valor);
    }

    /**
     * Método estático que lê o arquivo de configuração
     */
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

    /**
     * Metodo estático que grava a configuração no arquivo.
     */
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
     * Metodo que reorna a conexão com o servidor
     * @return the conexaoServidor
     */
    public Socket getConexaoServidor() {
        return conexaoServidor;
    }

    /**
     * Metodo que seta o socket do servidor
     * @param conexaoServidor the conexaoServidor to set
     */
    public void setConexaoServidor(Socket conexaoServidor) {
        this.conexaoServidor = conexaoServidor;
    }

    /**
     * Metodo que retorna o receptor.
     * @return the receptor
     */
    public Receptor getReceptor() {
        return receptor;
    }

    /**
     * Método que seta um receptor.
     * @param aReceptor the receptor to set
     */
    public void setReceptor(Receptor aReceptor) {
        receptor = aReceptor;
    }
}
