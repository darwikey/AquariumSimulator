import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



public class Client {

    public static Socket socket = null;
    public static Thread t1;
    //private PrintWriter out = null;
    //private BufferedReader in = null;

    private String idClient;
    private String listFishString;
    
    //list [PoissonRouge at 90x4,20x20,5]    

    public static void main(String[] args) {
	Parser p = new Parser();
	p.parse();
	Window w = new Window(p.getFishes());
	System.out.println("je sors de window");

	ReadCfg rc = new ReadCfg("Affichage.cfg");
	rc.read();
	 
	try {
	socket = new Socket(rc.getControllerAdress(),rc.getControllerPort());
	System.out.println("je sors socket");
	
	t1 = new Thread(new Connexion(socket, rc));
	t1.start();

	} catch (UnknownHostException e) {
		System.err.println("Can not connect to the address "+socket.getLocalAddress());
	}catch (IOException e) {
	    
	    System.err.println("No server listening port "+socket.getLocalPort());
	}

	w.autoRepaint();
	

	
    }
    

}
