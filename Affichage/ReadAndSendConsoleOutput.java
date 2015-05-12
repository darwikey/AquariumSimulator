import java.util.Scanner; 
import java.io.PrintWriter;
import java.util.LinkedList;
public class ReadAndSendConsoleOutput implements Runnable{

    private Scanner sc;
    private PrintWriter out;
    //private static LinkedList <String> listStarted = null;
    //public static boolean isActivated = false;
    public static LinkedList <String> listCommands = null;
    
    
    public ReadAndSendConsoleOutput(PrintWriter pw){
    	sc = new Scanner(System.in);
	//listStarted = new LinkedList <String> ();
		listCommands = new LinkedList <String> ();
		out = pw;
    }

    public void run(){
    	String stock;
    	
    	while(true){
    		stock = sc.nextLine();
		//stock += "\n";
		//System.out.println("stock : '" + stock+"'");
    		listCommands.add(stock);
		/*for (int i = 0 ; i < listCommands.size() ; i++){
		    System.out.println("élément " + i + " : " + listCommands.get(i));
		    }*/
	
    		out.println(stock);
    		out.flush();
    	}
    }

    public static LinkedList<String> getListCommands (){
    	return listCommands;
    }
    
    /* public static LinkedList<String> getListStarted (){
    	return listStarted;
	}*/


}

