
public class TestParseur {
	
	public static void main(String[] args) {
	    Parser p = new Parser();
	    String message = new String();
	    message = p.parse();
	    while(message.compareTo("quit") != 0 && message.compareTo("logout") != 0){
	      message = p.parse();
	      //System.out.println(message);
	    }

	   

	    
	            
	}
	
}
