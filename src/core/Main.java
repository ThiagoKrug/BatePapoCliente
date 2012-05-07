package core;

/**
 * Classe principal que executa o cliente de bate papo.
 * 
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 */
public class Main {
    
    /**
     * Metodo main
     * 
     * 
     * @param args 
     */
    public static void main(String[] args) {
        Principal p = Principal.getInstance();
        p.iniciar();
    }
    
}
