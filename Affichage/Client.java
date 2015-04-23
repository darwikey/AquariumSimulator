import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



public class Client {

	public static Socket socket = null;
	public static Thread t1;
	
	public static void main(String[] args) {
			
	try {
		/*Parser p = new Parser();
	      new Window(p.getFishes());*/
		ReadCfg rc = new ReadCfg("Affichage.cfg");
		rc.read();
				
		System.out.println("hello in as "+rc.getId());
		
		socket = new Socket(rc.getControllerAdress(),rc.getControllerPort());

		t1 = new Thread(new Connexion(socket, rc));
		t1.start();
						
		
	} catch (UnknownHostException e) {
	  System.err.println("Can not connect to the address "+socket.getLocalAddress());
	} catch (IOException e) {
	  System.err.println("No server listening port "+socket.getLocalPort());
	}
	
	}

}