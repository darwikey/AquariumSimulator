import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;



public class Client {

    public static Socket socket = null;
    public static Thread t1;
    
    //list [PoissonRouge at 90x4,20x20,5]    

    public static void main(String[] args) {

	Window w = new Window();

	ReadCfg rc = new ReadCfg("Affichage.cfg");
	rc.read();
	 
	try {
		socket = new Socket(rc.getControllerAdress(),rc.getControllerPort());
		System.out.println("je sors socket");
	
		t1 = new Thread(new Connexion(socket, rc, w));
		t1.start();

	} catch (UnknownHostException e) {
		System.err.println("Can not connect to the address "+socket.getLocalAddress());
	}catch (IOException e) {
	    
	    System.err.println("No server listening port "+socket.getLocalPort());
	}
	
	w.autoRepaint();
	
    }
    

}
