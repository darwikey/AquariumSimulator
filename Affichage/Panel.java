import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
	
import javax.imageio.ImageIO;
import javax.swing.JPanel;
	
	
@SuppressWarnings("serial")
	 
public class Panel extends JPanel {
	
    private LinkedList <Fish> listFish;
    private LinkedList <String> fishEnum;
    private String defaultPath = "res/Base.png";
	    
    public Panel (){
	listFish = new LinkedList<Fish>();
	fishEnum = new LinkedList<String>();
	fishEnum.add("Clown");
	fishEnum.add("Blob");
	fishEnum.add("Empereur");
	fishEnum.add("PoissonRouge");
	fishEnum.add("Stylay");
    }
	    
    public void initListFish (LinkedList<Fish>lf){
	listFish = lf;
    }
	    
    public LinkedList <Fish> getListFishes (){
	return listFish;
    }
		
	    
    public void paintComponent(Graphics g){
	    	
	int tailleEcranX = this.getWidth();
	int tailleEcranY = this.getHeight();
	            	
	Image img;
	int posX = -50;
	int posY = -50;
		
	int tailleX = 300;
	int tailleY = 200;

	String fishPath;

	try {        	
	    img = ImageIO.read(new File("res/fond_ecran1.jpg"));
	    g.drawImage(img, 0, 0, tailleEcranX, tailleEcranY, this);
	        		
	    for (int i =0; i < listFish.size(); i++){
		
		if (fishEnum.contains(listFish.get(i).getFishType()))
		    fishPath = "res/"+listFish.get(i).getFishType()+".png";
		else
		    fishPath = defaultPath;
			    
		img = ImageIO.read(new File(fishPath));
		//	System.out.println(listFish);
		posX = ((int)listFish.get(i).getCoord().x) * tailleEcranX / 100;
		posY = ((int)listFish.get(i).getCoord().y) * tailleEcranY / 100;
	        		
	        			
		tailleX = listFish.get(i).getSizeX() * tailleEcranX / 100;
		tailleY = listFish.get(i).getSizeY() * tailleEcranY / 100;    		
	        			
		if (listFish.get(i).getInversed()){
		    g.drawImage(img, posX+tailleX, posY, -tailleX, tailleY, this);
		}
		else{
		    g.drawImage(img, posX, posY, tailleX, tailleY, this);
		}
	        		
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}       
	        
    }
	
    public void updateListFish(LinkedList<Fish> lf) {
	listFish = lf;	  
    }
	    
	 
	    
}
	
