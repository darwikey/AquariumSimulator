import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;



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
    	System.out.println("constructor");
    }
    
    public void run() {
	Parser p = new Parser();

	System.out.println("je rentre dans run");
	try {
	    System.out.println("je rentre dans le try");
	    
	    out = new PrintWriter(socket.getOutputStream());
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
	    
	    System.out.println("je suis iciiiiiiiii v");
	    
	    if (rc.getId().equals(""))
	    	out.println("hello");
	    else{
	    	out.println("hello in as "+rc.getId());
	    }
	    
	    out.flush();
	    String stock = in.readLine();
	    
	    if (stock.compareTo("no greeting") == 0){
	    	System.out.println("connex1: "+stock);		
	    }	    
	    else{
	    	System.out.println("connex2: "+stock);
	    	idClient = p.parseGreeting(stock);
	    }

	    out.println("getFishesContinuously");
	    out.flush();
		
	    System.out.println("iii");
	    
	    listFishString = in.readLine();
	    LinkedList <Fish> listFishes = p.parseListFish(listFishString);  
	    System.out.println("1 "+listFishes.size());
	    w.initListFish(listFishes);
	    
	    
	    listFishString = in.readLine();
	    listFishes = p.parseListFish(listFishString); 
	    System.out.println("2 "+listFishes.size());
	    w.updateListFish(listFishes);
	    
	    listFishString = in.readLine();
	    listFishes = p.parseListFish(listFishString); 
	    System.out.println("3 "+listFishes.size());
	    w.updateListFish(listFishes);

	    
	} catch (UnknownHostException e) {
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
