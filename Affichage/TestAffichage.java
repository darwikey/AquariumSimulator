
public class TestAffichage {
	
	public static void main(String[] args) {
	      Parser p = new Parser();
	      System.out.println(p.getFish(0).getFishType());
	      new Window(p.getFishes());      
	  }
	
}
