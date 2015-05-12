import javax.swing.JFrame;
import java.awt.Color;
import java.util.LinkedList;
import java.awt.BorderLayout;


@SuppressWarnings("serial")
public class Window extends JFrame {  
    private final int refreshRate = 100; // milliseconds
    
    private Panel pan;	
    private LinkedList <Fish> listFishes;  
    private LinkedList<String>listStarted;

    public Window() {
    	listFishes = new LinkedList <Fish> ();
    	this.getContentPane().setLayout(new BorderLayout());
    	pan = new Panel();
    	this.add(pan, BorderLayout.CENTER);		    	    
    	this.setTitle("Aquarium");
    	this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    	this.setBackground(Color.WHITE);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	this.setLocationRelativeTo(null);
    	this.setVisible(true);
    	this.pack();
    }  
	    
    public void initListFish (LinkedList<Fish>lf){
    	listFishes = lf;
    	pan.initListFish(lf);
    }

    public void updateFishList(LinkedList<Fish> lf) {
	listFishes = lf;
	pan.updateListFish(listFishes);
    
    }
    
    
    
    private void repaintFish (){
    	Fish f;
    	for (int i =0; i < listFishes.size(); i++){
    		f = listFishes.get(i);
    		
    			if (!f.getHasArrived()){
    				f.move(refreshRate);
    			}
    		}
   		
    }
		
    
    //startFish Stylay
    
    
    public void autoRepaint (){
    	while (true){	
	    // listStarted = ReadAndSendConsoleOutput.getListStarted();
	    //System.out.println("listStarted " + listStarted);
	    //if (ReadAndSendConsoleOutput.isActivated){
	    	repaintFish();    
	    	pan.repaint();
		//}
	    try {
		Thread.sleep(refreshRate);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
		
    	}
	
	
    }
    

}
