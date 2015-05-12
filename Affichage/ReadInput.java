import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.HashMap;



public class ReadInput implements Runnable{

    private Socket socket = null;
    private BufferedReader in = null; 
    private Window window;
    private LinkedList <Fish> listFishes;

    public ReadInput(Window w, BufferedReader in, Socket s){
    	window = w;
    	this.in = in;
    	this.socket = s;
    }

   
    public void run() {
    	String listFishString;
    	String stock;    	
    	Parser p = new Parser (); 	
    	LinkedList <String> listCommands;
    	String command;
    	
    	try {	  	       		   	
	    while (true){
		stock = in.readLine();	
		if (stock.startsWith("list")){
		    listFishString = stock;
		    listFishes = p.parseFishList(listFishString); 
		    HashMap <String,Point> nameAndPoint = new HashMap <String,Point>();

		    for (int i = 0 ; i < listFishes.size() ; i++){
			nameAndPoint.put(listFishes.get(i).getFishType(), listFishes.get(i).getCoord());
		    }
		    p.fillPositionsHashMap(nameAndPoint);
		    window.updateFishList(listFishes);
		} else if (stock.startsWith("OK")){
		    if(Client.displayMessages)
			System.out.println("->"+stock);
			     
		    listCommands = ReadAndSendConsoleOutput.getListCommands();
		    command = listCommands.getFirst();
		    listCommands.removeFirst();
    				 
		} else if (stock.startsWith("NOK")){
		    listCommands = ReadAndSendConsoleOutput.getListCommands();
		    listCommands.removeFirst();
		    if(Client.displayMessages)
			System.out.println("->"+stock);
		} else if (stock.startsWith("bye")){
		    if(Client.displayMessages)
			System.out.println("->"+stock);
		    socket.close();
		    System.exit(0);
		} 		

			
	    }
	}
	      		
	catch (IOException e) {
	    System.err.println("No server listening port "+socket.getLocalPort());
	}   		
    }
        
}
