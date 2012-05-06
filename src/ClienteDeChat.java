
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClienteDeChat extends Thread {
    // Flag que indica quando se deve terminar a execução.

    private static boolean done = false;

    public static void main(String args[]) {
        try {   
            // Para se conecta outras portas em outras máquinas.   
            Socket conexao = new Socket("localhost", 5588);
            // uma vez estabelecida a comunicação, deve-se obter os  
            // objetos que permitem controlar o fluxo de comunicação   
            PrintStream saida = new PrintStream(conexao.getOutputStream()); 
            // enviar antes de tudo o nome do usuário   
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Entre com o seu nome: ");
            String meuNome = teclado.readLine();
            saida.println(meuNome);

            // Uma vez que tudo está pronto, antes de iniciar o loop   
            // principal, executar a thread de recepção de mensagens.  
            Thread t = new ClienteDeChat(conexao);
            t.start();
            // loop principal: obtendo uma linha digitada no teclado e   
            // enviando-a para o servidor.   
            String linha;

            while (true) {
                // ler a linha digitada no teclado   
                System.out.print("> ");
                linha = teclado.readLine();
                // antes de enviar, verifica se a conexão não foi fechada   
                if (done) {
                    break;
                }
                // envia para o servidor    
                saida.println(linha);
            }
        } catch (IOException e) {
            // Caso ocorra alguma excessão de E/S, mostre qual foi.
            System.out.println("IOException: " + e);
        }
    }
    // parte que controla a recepção de mensagens deste cliente 
    private Socket conexao;
    // construtor que recebe o socket deste cliente

    public ClienteDeChat(Socket s) {
        conexao = s;
    }
    // execução da thread

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            while (true) {
                // pega o que o servidor enviou 
                linha = entrada.readLine();
                // verifica se é uma linha válida. Pode ser que a conexão    
                // foi interrompida. Neste caso, a linha é null. Se isso  
                // ocorrer, termina-se a execução saindo com break   
                if (linha == null) {
                    System.out.println("Conexão encerrada!");
                    break;
                }
                // caso a linha não seja nula, deve-se imprimi-la    
                System.out.println();
                System.out.println(linha);
                System.out.print("...> ");
            }
        } catch (IOException e) {
            // caso ocorra alguma exc
            // caso ocorra alguma exceção de E/S, mostre qual foi.  
            System.out.println("IOException: " + e);
        }
        // sinaliza para o main que a conexão encerrou.  
        done = true;
    }
}