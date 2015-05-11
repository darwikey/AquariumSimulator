import java.util.Scanner; 
import java.io.PrintWriter;

public class ReadAndSendConsoleOutput implements Runnable{

    private Scanner sc;
    private PrintWriter out;
    
	public ReadAndSendConsoleOutput(PrintWriter out){
	sc = new Scanner(System.in);
	out = out;
    }

    public void run(){
	while(true){
	    //System.out.println("on vient d'écrire : " + sc.nextLine());
	    out.println(sc.nextLine());
	    out.flush();
	}
    }
}
