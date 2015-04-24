import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.net.UnknownHostException;



public class Connexion implements Runnable {
    
    private Socket socket = null;
    public static Thread t2;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private boolean connect = false;
    private int idClient;
    private String listFishString;
    private ReadCfg rc;
    
    
    public Connexion(Socket s, ReadCfg rc){
	socket = s;
	this.rc = rc;
	System.out.println("constructor");
    }
    
    public void run() {
	Parser p = new Parser();

	System.out.println("je rentre dans run");
	try {
	    System.out.println("je rentre dans le try");
	    
	    out = new PrintWriter(socket.getOutputStream());
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
	    
	    if (rc.getId().equals(""))
		out.println("hello");
	    else
		out.println("hello in as "+rc.getId());

	    out.flush();
	    String stock = in.readLine();
	    if (stock.compareTo("no greeting") == 0){
		System.out.println("connex1: "+stock);
		socket.close();
	    }
	    
	    else{
		 idClient = p.parseGreeting(listFishString);
		 System.out.println("connex2: "+stock);
	    }

	    out.println("getFishesContinuously");
		
	    
	    listFishString = in.readLine();
	    
	    System.out.println("yolo:"+listFishString);
	    out.flush();
	    
	    //t2 = new Thread(new Chat_ClientServeur(socket));
	    //t2.start();
	    
	    

	} catch (UnknownHostException e) {
	    System.err.println("Can not connect to the address "+socket.getLocalAddress());
	} catch (IOException e) {

	    System.err.println("No server listening port "+socket.getLocalPort());
	}
    }
    
}
