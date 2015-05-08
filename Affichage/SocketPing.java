import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.PrintWriter; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.LinkedList;

public class SocketPing implements Runnable {

    private Socket socket = null; 
    private PrintWriter out = null; 
    private BufferedReader in = null; 
    private int port;

    public SocketPing(Socket s, int p, PrintWriter pw ){ 
	socket = s; 
	port = p;
	out = pw; 
    }

    public void run() { 
	try{
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
	    while (true){ 
		synchronized (out){ 
		    try{
			Thread.sleep (1000); 
			out.println("ping "+port); 
			out.flush();
		    }

		    catch (InterruptedException e){
			System.out.println("error: " + e);
		    }
		} 
	    }
	}
	catch (UnknownHostException e) {
	    System.err.println("Can not connect to the address "+socket.getLocalAddress());
	} catch (IOException e) {

	    System.err.println("No server listening port "+socket.getLocalPort());
	}

    } 
}
