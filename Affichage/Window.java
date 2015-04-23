import javax.swing.JFrame;

import java.awt.Color;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Window extends JFrame {
    
    private final int refreshRate = 1; // milliseconds
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
	
	
	autoRepaint ();
	
    }  
    

    
    
    
    public void autoRepaint (){
	while (true){	
	    for (int i =0; i < listFish.size(); i++){
		listFish.get(i).move(refreshRate);
	    }
	    
	    pan.repaint();
	    try {
		Thread.sleep(refreshRate);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	
	
    }
    
}







