public class Fish {

    private String fishType;
    private int size_x;
    private int size_y;
    private double coord_x;
    private double coord_y;
    // private double speed;
    private double dest_x;
    private double dest_y;
    private double timeToDest;
    private boolean inversed;
    private double dist_x;
    private double dist_y;
    
    private boolean hasArrived = false; 

    public Fish(String fishType, int size_x, int size_y, int dest_x, int dest_y, double time){


	this.fishType = fishType;
	this.size_x = size_x;
	this.size_y = size_y;
	this.coord_x = 50;
	this.coord_y = 50;
	this.dest_x = dest_x;
	this.dest_y = dest_y;
	this.dist_x = dest_x - coord_x;
	this.dist_y =  dest_y - coord_y;
	//	this.speed = l/time; // pixels/seconde ?
	this.timeToDest = time;
	this.inversed = false;
    }
     
 
    public String toString(){
	String str = "";
	str += "Fish name: " + fishType + "\n";
	str += "Fish coords: " + coord_x + " " + coord_y + "\n";
	str += "Fish size: " + size_x + " " + size_y + "\n";
	//str += "Fish speed: " + speed + "\n";
	
	return str;
	    
    }

    //Return time repaint
    public void move (int timeElapsed){

	
	if (Math.abs(dest_x-coord_x) > 1.0 || Math.abs(dest_y-coord_y) > 1.0){

	    //System.out.println("move");
	//dx = dx / l) * time_elapsed * speed;
	//   dy = (dy / l) * time_elapsed * speed;

	    coord_x += dist_x/(timeToDest*1000/timeElapsed);
	    coord_y += dist_y/(timeToDest*1000/timeElapsed);
	} else {
	    hasArrived = true;
	}
    }


  

    /* public void moveToDest (int destX, int destY, double temps){
	this.dest_x = (double)destX;
	this.dest_y = (double)destY;

	double dx = dest_x - coord_x;
	double dy = dest_x - coord_y;

	double l = Math.sqrt(dx * dx + dy * dy);
	
	this.speed = l / temps;

	this.inversed = dx < 0.0;
	}*/
  

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

    public int getDestX(){
	return (int)dest_x;
    }
    
    public int getDestY(){
	return (int)dest_y;
    }

    public int getSizeX(){
	return size_x;
    }

    public int getSizeY(){
	return size_y;
    }
    public boolean getHasArrived(){
	return hasArrived;
    }

    public double getTimeToDest(){
	return this.timeToDest;
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

    public void sethasArrived(boolean b){
	 hasArrived = b;
    }
 public void setDest_x(double dx){
	 dest_x = dx;
    }
public void setDest_y(double dy){
	 dest_y = dy;
    }
 public void setTimeToDest(double t){
	 timeToDest = t;
    }

    public void updateFish(Fish f){
	this.hasArrived = false;
	this.dest_x = f.getDestX();
	this.dest_y = f.getDestY();
	this.timeToDest = f.getTimeToDest();
    }

}
