import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;


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
			System.out.println("on recoit : " + stock);
    			 if (stock.startsWith("list")){		   
    				 listFishString = stock;
    				 listFishes = p.parseFishList(listFishString); 
    				 LinkedList<Juhnytg> positions = new LinkedList<Juhnytg>();
    				 for (int i = 0 ; i < listFishes.size() ; i++){
    					 positions.addFirst(new Juhnytg(listFishes.get(i).getFishType(), listFishes.get(i).getCoord()));
					 //System.out.println("coord : " + listFishes.get(i).getCoord());
    				 }
				 System.out.println("positions : " + positions.get(0).getPos().x + " " + positions.get(0).getPos().y);
    				 p.fillPositionsList(positions);
    				 window.updateFishList(listFishes);
    			 } else if (stock.startsWith("OK")){
			     System.out.println(stock);
    				 listCommands = ReadAndSendConsoleOutput.getListCommands();
    				 command = listCommands.getFirst();
    				 if (command.startsWith("startFish")){
    					 String[] parts = command.split(" ");
    					 ReadAndSendConsoleOutput.getListStarted().add(parts[1]);	
    					 ReadAndSendConsoleOutput.isActivated = true;
    				 } else {
    					 System.out.println(stock);
    				 }
    				 listCommands.removeFirst();
    				 
    			 } else if (stock.startsWith("NOK")){
    				 listCommands = ReadAndSendConsoleOutput.getListCommands();
    				 listCommands.removeFirst();
    				 System.out.println(stock);
    			 } else if (stock.startsWith("bye")){
    				 socket.close();
				 exit();
    			 } 
    		}
    	}
	      		
    	catch (IOException e) {
	    System.err.println("No server listening port "+socket.getLocalPort());
    	}   		
    }
        
}
