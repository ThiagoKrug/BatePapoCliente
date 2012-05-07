package core;

import java.net.DatagramPacket;

/**
 * Classe que é responsável por receber os comandos, quebrar as strings e
 * interpretar os comandos padronizados do protocolo especificado.
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 */
public class Interpretador {

    /**
     * Metodo responsavel por interpretar a mensagem, utilizando como parametro
     * um datagrama recebido.
     * @param request
     * @return 
     */
    public String[] interpretarMensagem(DatagramPacket request) {
        byte[] buffer = request.getData();
        byte[] buffer2 = new byte[request.getLength()];
        for (int i = 0; i < request.getLength(); i++) {
            buffer2[i] = buffer[i];
        }
        String mensagem = new String(buffer2);
        return this.interpretarMensagem(mensagem);
    }
    
    /**
     * Método para fazer a quebra das strings das mensagens 
     * @param mensagem
     * @return 
     */
    public String[] interpretarMensagem(String mensagem) {
        String[] mensagens = mensagem.split("\\ ");
        return mensagens;
    }
    
    /**
     * Metodo responsável por concatenar as strings das mensagens
     * @param mensagem
     * @return 
     */
    
    public String juntarMensagem(String[] mensagem) {
        return this.juntarMensagem(mensagem, 1);
    }
    
    /**
     * Metodo responsavel por concaternar as strings das menagens privadas
     * @param mensagem
     * @return 
     */
    public String juntarMensagemPrivada(String[] mensagem) {
        return this.juntarMensagem(mensagem, 2);
    }
    
    /**
     * Metodo responsavel por concaternar uma mensagem, utilizando um index
     * @param mensagem
     * @param index
     * @return 
     */
    private String juntarMensagem(String[] mensagem, int index) {
        String m = "";
        if (mensagem.length > index) {
            for (int i = index; i < mensagem.length; i++) {
                m += mensagem[i] + " ";
            }
        }
        return m;
    }

}
