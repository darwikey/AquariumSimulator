import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

    public static Socket socket = null;
    public static boolean displayMessages = false;

    public static void main(String[] args) {

    	PrintWriter out;
    	BufferedReader in;
    	String idClient;
    	String idInitial;
    	
    	Window w = new Window();


	if (args.length == 1 && args[0].equals("-v")){
	    displayMessages = true;
	}
    	ReadCfg rc = new ReadCfg("Affichage.cfg");
    	rc.read();
	 
    	try {
	    socket = new Socket(rc.getControllerAdress(),rc.getControllerPort());
	    out = new PrintWriter(socket.getOutputStream());
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
	    
	    //Connection
	    if (rc.getId().equals("")){
		out.println("hello");
		idInitial = "";
	    }
	    else{
		out.println("hello in as "+rc.getId());
		idInitial = rc.getId();
	    }
	    
	    out.flush();
	    String stock = in.readLine();
	    
	    if (stock.compareTo("no greeting") == 0){
		System.out.println(stock);	
		socket.close();
	    }
		    
	    else{
		String[] parts = stock.split(" ");
		idClient = parts[1];
		if (!idClient.equals(idInitial))
		    System.out.println("Nouvel id: "+idClient);
	    }
	
	    //Command line ???
	    //out.println("getFishesContinuously");
	    //  out.flush();
		    
	    Thread t1 = new Thread(new SendPing(rc.getControllerPort(), out));
	    Thread t2 = new Thread(new ReadInput(w, in, socket));
	    Thread t3 = new Thread(new ReadAndSendConsoleOutput(out));
		    
	    t1.start();
	    t2.start();
	    t3.start();
	
	} catch (UnknownHostException e) {
	    System.out.println("Can not connect to the address "+socket.getLocalAddress());
	    System.exit(0);
	}catch (IOException e) {
	    System.out.println("No server listening port");
	    System.exit(0);
	}
		
	w.autoRepaint();
		
    }
    

}
