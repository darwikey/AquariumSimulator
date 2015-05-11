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
	
    	//System.out.println("constructor");
    }
    
    public void run() {
	String consoleOutput;
	Parser p = new Parser();

	//System.out.println("je rentre dans run");
	try {
	    
	    //System.out.println("je rentre dans le try");
	    
	    out = new PrintWriter(socket.getOutputStream());
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
	    
	    //System.out.println("je suis iciiiiiiiii v");
	    out.println("getFishesContinuously");
	    Thread t1 = new Thread(new SendPing(rc.getControllerPort(), out));
	    Thread t2 = new Thread(new ReadInput(w, in, rc.getControllerPort()));
	    Thread t3 = new Thread(new ReadAndSendConsoleOutput(out));
	    t1.start();
	    t2.start();
	    t3.start();
	}
	/*if (rc.getId().equals(""))
	  out.println("hello");
	  else{
	  out.println("hello in as "+rc.getId());
	  }
	    
	  out.flush();*/
	//String stock;
	/* = in.readLine();
	   System.out.println(stock);	

	   if (stock.compareTo("no greeting") == 0){
	   System.out.println("The program will stop because of: "+stock);	
		
	   }	    
	   else{
	   System.out.println(stock);
	   idClient = p.parseGreeting(stock);
	   }*/

	//out.println("getFishesContinuously");
	//out.println("ping 12345");
	//out.flush();
	//stock = in.readLine();
		

	//System.out.println(stock);
	//listFishString = in.readLine();
	//System.out.println(listFishString);
	//LinkedList <Fish> listFishes = p.parseFishList(listFishString);  
	    
	    
	    
	//System.out.println("1 "+listFishes.size());
	//w.initListFish(listFishes);
	    
	/*	    int i = 1;
		    while (true){
		    consoleOutput = sc.nextLine();
		    out.println(consoleOutput);
		    out.flush();
		    System.out.println(consoleOutput);
		    i++;
		    listFishString = in.readLine();
		    System.out.println(listFishString);
		    try {
		    // thread to sleep for 1000 milliseconds
		    Thread.sleep(1000);
		    } catch (Exception e) {
		    System.out.println(e);
		    }
		    /*listFishes = p.parseFishList(listFishString); 
		    System.out.println(i+" "+listFishes.size());
		    w.updateFishList(listFishes);
		    }*/

	    
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
