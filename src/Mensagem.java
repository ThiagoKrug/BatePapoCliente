/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author thiago
 */
public class Mensagem {
    
    private String mensagem;
    private TipoMensagem tipoMensagem;

    public Mensagem() {
        this.mensagem = "";
        this.tipoMensagem = TipoMensagem.publico;
    }

    public Mensagem(String mensagem, TipoMensagem tipoMensagem) {
        this.mensagem = mensagem;
        this.tipoMensagem = tipoMensagem;
    }
    
    public Mensagem(String mensagem) {
        this.mensagem = mensagem;
        this.tipoMensagem = TipoMensagem.publico;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public TipoMensagem getTipoMensagem() {
        return tipoMensagem;
    }

    public void setTipoMensagem(TipoMensagem tipoMensagem) {
        this.tipoMensagem = tipoMensagem;
    }
    
}
