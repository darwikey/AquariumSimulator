import javax.swing.JFrame;

import java.awt.Color;
import java.util.LinkedList;
import java.awt.TextField;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class Window extends JFrame {

    
   
 
	

    private final int refreshRate = 1; // milliseconds
    
    private Panel pan;	
    private LinkedList <Fish> listFish;
    private Input textField;

    public Window(LinkedList<Fish> lf) {

	textField = new Input();
	this.getContentPane().setLayout(new BorderLayout());

	listFish = lf;
	pan =  new Panel(listFish);
	this.add(pan, BorderLayout.CENTER);

	    
	this.add(textField, BorderLayout.SOUTH);
	    
	this.setTitle("Aquarium");
	this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	//this.setSize(900, 600);
	this.setBackground(Color.WHITE);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	//this.setContentPane(pan);
	this.setVisible(true);
	this.pack();
	//debug
	//Fish myFish = new Fish("poissonRouge", 7, 7, 20, 10);
	//myFish.moveToDest(70, 80, 3.0);
	//listFish.add(myFish);
	//autoRepaint();
    }  

    public void updateListFish(LinkedList<Fish> lf) {
	pan.updateListFish(lf);

    }
   
	
 
    

    
    
    
    public void autoRepaint (){
	while (true){	
	    for (int i =0; i < listFish.size(); i++){
		listFish.get(i).move(refreshRate);
	    }
	    

	    pan.repaint();
	    textField.repaint();
	    try {
		Thread.sleep(refreshRate);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	
	
    }
    

}






