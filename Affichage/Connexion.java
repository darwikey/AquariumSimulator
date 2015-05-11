import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Scanner; 


public class Connexion implements Runnable {
    
    private Socket socket = null;
    public static Thread t2;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private boolean connect = false;
    private String idClient;
    private String listFishString;
    private ReadCfg rc;
    private Window w;
    
    
    
    public Connexion(Socket s, ReadCfg rc, Window w){
    	this.w = w;
    	socket = s;
    	this.rc = rc;    	
    }
    
    public void run() {
	String consoleOutput;
	Parser p = new Parser();
	try {   
	    
	    out = new PrintWriter(socket.getOutputStream());
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
	    
	    out.println("getFishesContinuously");
	    out.flush();
	    Thread t1 = new Thread(new SendPing(rc.getControllerPort(), out));
	    Thread t2 = new Thread(new ReadInput(w, in, rc.getControllerPort()));
	    Thread t3 = new Thread(new ReadAndSendConsoleOutput(out));
	    t1.start();
	    t2.start();
	    t3.start();
	}
	

	    
	catch (UnknownHostException e) {
	    System.err.println("Can not connect to the address "+socket.getLocalAddress());
	} catch (IOException e) {

	    System.err.println("No server listening port "+socket.getLocalPort());
	}
    }
    
    public boolean noFishHasArrived (LinkedList<Fish> lf){
	for (int i =0; i < lf.size(); i++){
	    if (lf.get(i).getHasArrived()){
		System.out.println("FINIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII");
		return false;
	    }
	}
	return true;
    }
    
}
