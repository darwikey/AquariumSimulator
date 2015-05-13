import java.util.Scanner; 
import java.io.PrintWriter;
import java.util.LinkedList;

/*
  Reads the input from the console and stores each command into a list.

 */


public class ReadUserMessages implements Runnable{

    private Scanner sc;
    private PrintWriter out;
    public static LinkedList <String> listCommands = null;
    
    
    public ReadUserMessages(PrintWriter pw){
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

