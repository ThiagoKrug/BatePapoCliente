
package core;

/**
 * Classe abstrata Conexao. Estabelece os atributos estáticos do protocolo.
 * 
 * @author Bruno Vicelli
 * @author Mateus Henrique Dal Forno
 * @author Thiago Cassio Krug
 */
public abstract class Conexao {
    
    public static String SERVER = "SERVER";
    public static String USER = "USER";
    public static String OK_USERNAME = "OK_USERNAME";
    public static String ERR_INVALIDUSERNAME = "ERR_INVALIDUSERNAME";
    public static String ERR_NEEDMOREPARAMS = "ERR_NEEDMOREPARAMS";
    public static String ERR_ALREADYREGISTRED = "ERR_ALREADYREGISTRED";
    public static String MSG = "MSG";
    public static String MSG_SENDED = "MSG_SENDED";
    public static String PRIVMSG = "PRIVMSG";
    public static String ERR_NOSUCHNICK = "ERR_NOSUCHNICK";
    public static String NOTEXTTOSEND = "NOTEXTTOSEND";
    public static String PRIVMSG_SENDED = "PRIVMSG_SENDED";
    public static String NAMES = "NAMES";
    public static String QUIT = "QUIT";
    
}
