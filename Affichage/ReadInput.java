import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;


public class ReadInput implements Runnable{

    private Socket socket = null;
    private BufferedReader in = null; 
    private int port;
    private Window window;
    private LinkedList <Fish> listFishes;

    public ReadInput(Window w, BufferedReader in, int p){
    	window = w;
    	this.in = in;
    	port = p;
    }

   
    public void run() {
    	String listFishString;
    	String stock;
    	
    	Parser p = new Parser ();
    	
    	
    	try {
	  	       		   	
	    while (true){
	
		stock = in.readLine();	
		if (stock.startsWith("pong"))
		    {
			//System.out.println("pong " +port);
		    } else if (stock.startsWith("list")){		   
		    listFishString = stock;
		    listFishes = p.parseFishList(listFishString); 
		    LinkedList<Juhnytg> positions = new LinkedList<Juhnytg>();
		    for (int i = 0 ; i < listFishes.size() ; i++){
			positions.add(new Juhnytg(listFishes.get(i).getFishType(), listFishes.get(i).getCoord()));
		    }
		    p.fillPositionsList(positions);
		    window.updateFishList(listFishes);
		}


	
	    }
	}
	      		
	catch (IOException e) {

	    System.err.println("No server listening port "+socket.getLocalPort());
	}
    	
	
    }
    
    
}
