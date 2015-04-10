import javax.swing.JFrame;

import java.awt.Color;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Window extends JFrame {

	private Panel pan;	
	private LinkedList <Fish> listFish;

	public Window(LinkedList<Fish> lf) {
      listFish = new LinkedList <Fish> (lf);
      pan =  new Panel(listFish);

      this.setTitle("Aquarium");
      this.setSize(900, 600);
      this.setBackground(Color.WHITE);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      this.setContentPane(pan);
      this.setVisible(true);
      
      Thread t;
      for (int i =0; i < listFish.size(); i++){
    	  t = new Thread(new RunImpl(listFish.get(i), 0, 0, 5, pan));
    	  t.start();
      }
      autoRepaint ();
      
  }  
	
	public void autoRepaint (){
		while (true){		
			pan.repaint();
			try {
				Thread.sleep(3);
			    } catch (InterruptedException e) {
				e.printStackTrace();
			    }
		}
			
			
	}
        
}




