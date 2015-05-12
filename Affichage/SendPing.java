import java.io.PrintWriter;

public class SendPing implements Runnable {

    private PrintWriter out = null;
    private int port;

    public SendPing(int p, PrintWriter pw ){
    	port = p;
    	out = pw;
    }

    public void run() {
    	
    	try {
    		
    		while (true){
    			synchronized (out){
    				Thread.sleep (1000);
    				out.println("ping "+port);
    				out.flush();
    			}
	    	    
    		}
    		
    	} catch (InterruptedException e) {
    	    System.err.println("Interupted exception ");
    	}
    	   			
    	}
    
    
}
