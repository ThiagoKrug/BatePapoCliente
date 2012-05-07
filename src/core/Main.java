package core;

/**
 * Classe principal que executa o cliente de bate papo, utilizando o padrão de
 * projeto singleton.
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
//        PrincipalView p = PrincipalView.getInstance();
//        p.setVisible(true);
        Principal p = Principal.getInstance();
        p.iniciar();
    }
    
}
