	import java.awt.Color;
	import java.awt.Graphics;
	import java.awt.Image;
	import java.io.File;
	import java.io.IOException;
	import java.util.LinkedList;
	
	import javax.imageio.ImageIO;
	import javax.swing.JPanel;
	
	
	@SuppressWarnings("serial")
	 
	public class Panel extends JPanel {
	
	    //private LinkedList <String> listNameFish;
	    private LinkedList <Fish> listFish;
	
	
	    /*public Panel (LinkedList <Fish> lf){
	    	listFish = lf;
	    	listNameFish = new LinkedList <String> ();
	    	for (int i=0; i < lf.size();i++){
	    		listNameFish.add(listFish.get(i).getFishType());
	    	}
	
	    }*/
	    
	    public Panel (){
	    	listFish = new LinkedList<Fish>();
	    }
	    
	    public void initListFish (LinkedList<Fish>lf){
	    	listFish = lf;
	    }
	    
	    public LinkedList <Fish> getListFishes (){
	    	return listFish;
	    }
		
	
	    // list [PoissonRouge at 90x4,10x4,5] [Joli at 20x80,12x6,5]
	    // list [PoissonRouge at 90x4,20x20,5]
	    // list [PoissonRouge at 90x4,20x20,5] [PoissonRouge at 80x40,20x20,5] [PoissonRouge at 200x60,20x20,5]
	    
	    public void paintComponent(Graphics g){
	    	
	    	int tailleEcranX = this.getWidth();
	        int tailleEcranY = this.getHeight();
	            	
	        Image img;
	        int posX = -50;
	        int posY = -50;
		
	        int tailleX = 300;
	        int tailleY = 200;

	        try {        	
	        	img = ImageIO.read(new File("fond_ecran1.jpg"));
	        	g.drawImage(img, 0, 0, tailleEcranX, tailleEcranY, this);
	        	
	
	        	for (int i =0; i < listFish.size(); i++){
			    
	        		img = ImageIO.read(new File("res/"+listFish.get(i).getFishType()+".png"));
	        		posX = ((int)listFish.get(i).getCoord().x) * tailleEcranX / 100;
	        		posY = ((int)listFish.get(i).getCoord().y) * tailleEcranY / 100;
	        		
	        		//System.out.println("iiii "+posX+" "+posY);
	        		
	        		//System.out.println("iiii "+listFish.get(i).getCoord().x+" "+listFish.get(i).getCoord().y);
	        			
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
	
