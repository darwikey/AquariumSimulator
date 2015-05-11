import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Scanner; 

public class SendPing implements Runnable {

    private PrintWriter out = null;
    private int port;
    private Scanner sc;

    public SendPing(int p, PrintWriter pw){
	port = p;
	out = pw;
	sc = new Scanner(System.in);
    }

    public void run() {
	

	while (true){
	    try {
		
		// thread to sleep for 1000 milliseconds
		Thread.sleep(1000);
	    } 
	    catch (Exception e) {
		System.out.println(e);
	    }
	   
	    out.println("ping "+port);
	    out.flush();
	}
	    	    
	
    		
	/*} catch (InterruptedException e) {
	  System.err.println("Interupted exception ");
    	   		
	  } catch (IOException e) {

	  System.err.println("No server listening port "+port);
	  }*/
    	
	
    }
}
