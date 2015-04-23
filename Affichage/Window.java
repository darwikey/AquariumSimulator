import javax.swing.JFrame;

import java.awt.Color;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Window extends JFrame {


	private Panel pan;	
	private LinkedList <Fish> listFish;

	public Window(LinkedList<Fish> lf) {
	    listFish = lf;
	    pan =  new Panel(listFish);
	    
	    this.setTitle("Aquarium");
	    this.setSize(900, 600);
	    this.setBackground(Color.WHITE);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setContentPane(pan);
	    this.setVisible(true);
	    
	    //debug
	    Fish myFish = new Fish("poissonRouge", 7, 7, 20, 10);
	    myFish.moveToDest(70, 80, 3.0);
	    listFish.add(myFish);
	    
	}  
	
	public void autoRepaint (){
	    while (true){		
		pan.repaint();
		
		for (int i =0; i < listFish.size(); i++){
		    listFish.get(i).move(0.01);
		}
		
		try {
		    Thread.sleep(10);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	    
			
    }
        
}




