import java.io.*;
import java.net.*;

public class Server {
    public static ServerSocket ss = null;
    private static PrintWriter out = null;
    private static BufferedReader in = null;
    
    
    public static void main(String[] args) {
	String stock;	
	Socket socket;
	try {
	    ss = new ServerSocket(12345);
	    System.out.println("Le serveur est à l'écoute du port "+ss.getLocalPort());
	    
	    while(true){		
		socket = ss.accept();

		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		
		//out.println("Entrez votre mot de passe :");

		stock = in.readLine();
		System.out.println(stock);
		if (stock == null){
		    out.println("stock est null");
		}
		else{
		    if(stock.compareTo("getFishesContinuously") == 0){
			out.println("Envoi de listes de poissons");
		    }
		    
		    else if (stock.compareTo("hello in as 4") == 0){
			out.println("hello 4");
		    }
		    
		    else if(stock.compareTo("getFish") == 0){
			out.println("Envoi d'un poisson");
		    }
		    
		    else if(stock.compareTo("ping 12345") == 0){
			out.println("pong 12345");
		    }
		    else {
			out.println("commande non reconnue");
		    }
		}
		out.flush();
		
	    }
	    
	    
	    
	} catch (IOException e) {
	    System.err.println("Le port "+ss.getLocalPort()+" est déjà utilisé !");
	}
	
    }
    
    
}
