import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
	
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*

  Thanks to the repaint method, displays the Fishes.

 */	
	
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

    private String pathToFish (LinkedList <String> list, String fishType){
	String fishPath = "res/";
	String fishPath2 ="";
	boolean found = false;

	for(String str: list){
	    if (fishType.contains(str)){
		fishPath2 = str;
		found = true;
	    }
	}
	if (found){
	    fishPath += fishPath2;
	    fishPath +=".png";
	} else {
	    fishPath += "Base.png";
	}
	return fishPath;
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
	boolean found = false;

	int listSize = 0;
	

	try {        	
	    img = ImageIO.read(new File("res/fond_ecran1.jpg"));
	    g.drawImage(img, 0, 0, tailleEcranX, tailleEcranY, this);
	        	
	    listSize = listFish.size();
	    for (int i =0; i < listFish.size(); i++){
		  		
		fishPath = pathToFish (fishEnum,listFish.get(i).getFishType());
		
		img = ImageIO.read(new File(fishPath));

		if(listSize == listFish.size() ){
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
	    }
	    
	} catch (IOException e) {
	    e.printStackTrace();
	}       
	        
    }
	
    public void updateListFish(LinkedList<Fish> lf) {
	listFish = lf;	  
    }
	    
	 
	    
}
	
