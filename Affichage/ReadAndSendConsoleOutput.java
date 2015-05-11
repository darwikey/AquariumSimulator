import java.util.Scanner; 
import java.io.PrintWriter;
import java.util.LinkedList;
public class ReadAndSendConsoleOutput implements Runnable{

    private Scanner sc;
    private PrintWriter out;
    private static LinkedList <String> listStarted;
    
    public ReadAndSendConsoleOutput(PrintWriter pw){
	sc = new Scanner(System.in);
	listStarted = new LinkedList <String> ();
	out = pw;
	System.out.println(out);
    }

    public void run(){
	String stock;
	while(true){
	    
	    //System.out.println("on vient d'écrire : " + sc.nextLine());
	    stock = sc.nextLine();

	    if (stock.startsWith("startFish")){
		String[] parts = stock.split(" ");
		listStarted.add(parts[1]);
	 
	    }
	    //System.out.println(stock);
	    //System.out.println(out);
	    out.println(stock);
	    out.flush();
	}
    }

    public static LinkedList<Fish> getListStarted (){
	return listStarted;
    }


}

