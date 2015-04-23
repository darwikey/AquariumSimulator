import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;



public class Connexion implements Runnable {
    
    private Socket socket = null;
    public static Thread t2;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private boolean connect = false;
    private String listFishString;
    
    private ReadCfg rc;
    
    public Connexion(Socket s, ReadCfg r){
	rc = r;
	socket = s;
    }
    
    public void run() {
	
	try {
	    
	    out = new PrintWriter(socket.getOutputStream());
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
	    
	    out.println("hello in as "+rc.getId());
	    out.flush();
	    
	    if (in.readLine().compareTo("no greeting") == 0)
		connect = false;
	    else
		connect = true;
	    
	    out.println("getFishesContinuously");
	    out.flush();
	    
	    listFishString = in.readLine();
	    
	    
	    //t2 = new Thread(new Chat_ClientServeur(socket));
	    //t2.start();
	    
	} catch (IOException e) {
	    
	    System.err.println("Server down");
	}
    }
    
}
