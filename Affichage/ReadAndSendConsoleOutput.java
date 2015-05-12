import java.util.Scanner; 
import java.io.PrintWriter;
import java.util.LinkedList;



public class ReadAndSendConsoleOutput implements Runnable{

    private Scanner sc;
    private PrintWriter out;
    public static LinkedList <String> listCommands = null;
    
    
    public ReadAndSendConsoleOutput(PrintWriter pw){
    	sc = new Scanner(System.in);
	listCommands = new LinkedList <String> ();
	out = pw;
    }

    public void run(){
    	String stock;
    	
    	while(true){
	    stock = sc.nextLine();
	    listCommands.add(stock);
	
	    out.println(stock);
	    out.flush();
    	}
    }

    public static LinkedList<String> getListCommands (){
    	return listCommands;
    }
    

}

