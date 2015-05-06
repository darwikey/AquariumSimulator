import javax.swing.JFrame;

import java.awt.Color;
import java.util.LinkedList;
import java.awt.TextField;
import java.awt.BorderLayout;



/*
 Cas ou la liste des prochaines dest est superieur a la liste courante
 */



@SuppressWarnings("serial")
public class Window extends JFrame {

    
    private final int refreshRate = 100; // milliseconds
    
    private Panel pan;	
    private InputRaffin textField;
    private LinkedList <Fish> listFishes; 

    public Window() {

    	listFishes = new LinkedList <Fish> ();
    	textField = new InputRaffin();
    	this.getContentPane().setLayout(new BorderLayout());

		pan = new Panel();
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
	    }  
	    
    public void initListFish (LinkedList<Fish>lf){
    	listFishes = lf;
    	pan.initListFish(lf);
    }

    public void updateListFish(LinkedList<Fish> lf) {
    	Fish f;
    	if (lf.size() == listFishes.size()){
    		for (int i =0; i < listFishes.size(); i++){
    		
    		
    			f = listFishes.get(i);
    			f.addNextDest(lf.get(i).getDestination());
    		}
    		pan.updateListFish(listFishes);
    	}
    }
    
    private void repaintFish (){
    	Fish f;
    	for (int i =0; i < listFishes.size(); i++){
    		f = listFishes.get(i);
    		if (f.getHasArrived() == false){   			
    			listFishes.get(i).move(refreshRate);
    		}
	    }
    }
    
    
    
    public void autoRepaint (){
    	while (true){	
		
    		repaintFish();    
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






