import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



public class Client {

    public static Socket socket = null;
    // public static Thread t1;
    //private PrintWriter out = null;
    //private BufferedReader in = null;

    private String idClient;
    private String listFishString;
    
    
    public static void main(String[] args) {
	Parser p = new Parser();
	p.parse();
	Window w = new Window(p.getFishes());


		try {
		ReadCfg rc = new ReadCfg("Affichage.cfg");
		rc.read();
	    		
		socket = new Socket(rc.getControllerAdress(),rc.getControllerPort());
	    
		out = new PrintWriter(socket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
	    
		if (rc.getId().equals(""))
		out.println("hello");
		else
		out.println("hello in as "+rc.getId());

		out.flush();
	    
		if (in.readLine().compareTo("no greeting") == 0)
		System.out.println("fin de connexion");
		else{
		//Il faut prendre le nouvelle identifiant
		idClient = 0;
		}
	    
		out.println("getFishesContinuously");
		out.flush();
	    
		listFishString = in.readLine();
	    
	    
		//t2 = new Thread(new Chat_ClientServeur(socket));
		//t2.start();
	    
	    

		} catch (UnknownHostException e) {
		System.err.println("Can not connect to the address "+socket.getLocalAddress());
		} catch (IOException e) {

		System.err.println("No server listening port "+socket.getLocalPort());
		}
	
    }
    

}
