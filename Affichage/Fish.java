public class Fish {

    private String fishType;
    private int size_x;
    private int size_y;
    private double coord_x;
    private double coord_y;
    private double speed;
    private double dest_x;
    private double dest_y;
    private boolean inversed;


    public Fish(String fishType, int size_x, int size_y, int coord_x, int coord_y){
	this.fishType = fishType;
	this.size_x = size_x;
	this.size_y = size_y;
	this.coord_x = coord_x;
	this.coord_y = coord_y;
	this.dest_x = coord_x;
	this.dest_y = coord_y;
	this.speed = 0.0;
	this.inversed = false;
    }
     
 
    public String toString(){
	String str = "";
	str += "Fish name: " + fishType + "\n";
	str += "Fish coords: " + coord_x + " " + coord_y + "\n";
	str += "Fish size: " + size_x + " " + size_y + "\n";
	str += "Fish speed: " + speed + "\n";
	
	return str;
	    
    }

    //Return time repaint
    public void move (double time_elapsed){

	double dx = dest_x - coord_x;
	double dy = dest_y - coord_y;

	double l = Math.sqrt(dx * dx + dy * dy);

	dx = (dx / l) * time_elapsed * speed;
	dy = (dy / l) * time_elapsed * speed;

	coord_x += dx;
	coord_y += dy;
    }


    /*private void bounce (){

		boolean backX = false;
		boolean backY = false;
		
		int tailleX = (int) (((double)(fish.getSizeX())/100) * pan.getWidth());
		int tailleY = (int) (((double)(fish.getSizeY())/100) * pan.getHeight());
		
		
		if (x < 1){
		    backX = false;
		    inversed = true;
		}
		if (x > pan.getWidth() - tailleX){
		    backX = true;
		    inversed = false;
		}
		if (y < 1)
		    backY = false;
		if (y > pan.getHeight() - tailleY)
		    backY = true;
		if (!backX)
		    coord_x++;
		else
		    coord_x--;
		if (!backY)
		    coord_y++;
		else
		    coord_y--;
		
		    }*/
  

    public void moveToDest (int destX, int destY, double temps){
	this.dest_x = (double)dest_x;
	this.dest_y = (double)dest_y;

	double dx = dest_x - coord_x;
	double dy = dest_x - coord_y;

	double l = Math.sqrt(dx * dx + dy * dy);
	
	this.speed = l / temps;
    }
  

    public boolean compareFish (Fish f){
	return (fishType.equals(f.getFishType()));
    }

    public String getFishType(){
	return fishType;
    }

    public int getPosX(){
	return (int)coord_x;
    }

    public int getPosY(){
	return (int)coord_y;
    }

    public int getSizeX(){
	return size_x;
    }

    public int getSizeY(){
	return size_y;
    }

    public Boolean getInversed(){
	return inversed;
    }

    public void setSizeX(int a){
	 size_x = a;
    }

    public void setSizeY(int a){
	 size_y = a;
    }


}
