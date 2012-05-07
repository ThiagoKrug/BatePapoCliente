package core;

/**
 * Classe que identifica um usuario
 *
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 */
public class Usuario {

    private String nome;

    /**
     * Metodo construtor da classe Usuario, que não recebe parametros
     */
    public Usuario() {
    }

    /**
     * Metodo construtor da classe Usuario e que recebe um nome como parametro.
     *
     * @param nome
     */
    public Usuario(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo que retorna um nome
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo que seta um nome
     *
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
