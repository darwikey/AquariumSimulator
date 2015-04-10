public class RunImpl implements Runnable {
  private Fish fish;
  private int destX;
  private int destY;
  private float temps;
  private Panel pan;

  public RunImpl(Fish f, int destX, int destY, float temps, Panel panel){
    this.fish = f;
    this.destX = destX;
    this.destY = destY;
    this.temps = temps;
    this.pan = panel;
  }
  
  public void run() {
	  bounce ();
	  //moveToDest (destX, destY, temps);
  }
  
  private void moveToDest (int destX, int destY, float temps){
	  int coord_x = fish.getPosX();
	  int coord_y = fish.getPosY();
	  
	  int dx = Math.abs (destX-coord_x);
	  int dy = Math.abs (destY-coord_y);
	  int maxDistance = 0;

		if(dx<dy)
		    maxDistance = dy;
		else 
		    maxDistance = dx;

		int timeRepaint = (int) ((temps/maxDistance) *1000);
				
		while (coord_x != destX || coord_y != destY){
			
		    if (coord_x > destX)
		    	fish.setPosX(--coord_x);
		    if (coord_x < destX)
		    	fish.setPosX(++coord_x);
		    if (coord_y > destY)
		    	fish.setPosY(--coord_y);
		    if (coord_y < destY)
		    	fish.setPosY(++coord_y);
		    
		    try {
				Thread.sleep(timeRepaint);
			    } catch (InterruptedException e) {
				e.printStackTrace();
			    }
    }
		System.out.println(fish.getFishType()+" fini");
  }
  
  
  private void bounce (){
	  int x = fish.getPosX(), y = fish.getPosY();
		boolean backX = false;
		boolean backY = false;
		
		int tailleX = (int) (((float)(fish.getSizeX())/100) * pan.getWidth());
		int tailleY = (int) (((float)(fish.getSizeY())/100) * pan.getHeight());
		
		
		while (true) {

		    if (x < 1){
		    	backX = false;
		    	fish.setInversed(true);
		    }
		    if (x > pan.getWidth() - tailleX){
		    	backX = true;
		    	fish.setInversed(false);
		    }
		    if (y < 1)
		    	backY = false;
		    if (y > pan.getHeight() - tailleY)
		    	backY = true;
		    if (!backX)
		    	fish.setPosX(++x);
		    else
		    	fish.setPosX(--x);
		    if (!backY)
		    	fish.setPosY(++y);
		    else
		    	fish.setPosY(--y);
		    
		    try {
			Thread.sleep(3);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		}
	  
  }
  
  
  
}
