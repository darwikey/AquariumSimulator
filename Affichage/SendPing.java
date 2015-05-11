import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class SendPing implements Runnable {

    private PrintWriter out = null;
    private int port;

    public SendPing(int p, PrintWriter pw){
	port = p;
	out = pw;
    }

    public void run() {
	//try {
	while (true){
	    synchronized (out){
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
	    	    
	}
    		
	/*} catch (InterruptedException e) {
	  System.err.println("Interupted exception ");
    	   		
	  } catch (IOException e) {

	  System.err.println("No server listening port "+port);
	  }*/
    	
	
    }
}
