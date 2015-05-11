import java.util.LinkedList;

public class Fish {

    private String fishType;
    private int size_x;
    private int size_y;
    private Point coord;
    private double timeToDest;
    private boolean inversed;
    private double dist_x;
    private double dist_y;
    private boolean started = false;
    
    private Point nextDest;
     
    private boolean hasArrived;    

    public Fish(String fishType, int size_x, int size_y, int dest_x, int dest_y, double time, Point coord){
    	nextDest = new Point(dest_x,dest_y);
    	this.hasArrived = false;
    	this.fishType = fishType;
    	this.size_x = size_x;
	this.size_y = size_y;
	this.coord = coord;
	this.dist_x = nextDest.x - coord.x;
	this.dist_y =  nextDest.y - coord.y;
	this.timeToDest = time;
	this.inversed = false;
    }
     
    //Return time repaint
    public void move (int time_elapsed){
	/*	if (nextDest.size() == 0){   
    		hasArrived = true;
    		System.out.println("finished");
		} else { */   	
	/*if (Math.abs(nextDestx-coord.x) > 1.0 || Math.abs(nextDest.y-coord.y) > 1.0){		
	  coord.x += dist_x/(timeToDest*1000/timeElapsed);
	  coord.y += dist_y/(timeToDest*1000/timeElapsed);

	  }
	    			
	    		
	    	
	    		
	  this.dist_x = nextDest.x - coord.x;
	  this.dist_y =  nextDest.y - coord.y;
	*/
	
	if (started){
	    double dx = nextDest.x - coord.x;
	    double dy = nextDest.y - coord.y;
	    double l = Math.sqrt(dx * dx + dy * dy);
	    double speed = l/time_elapsed;
	    if (Math.abs(dx) > 1.0 || Math.abs(dy) > 1.0){
	   
 
		dx = (dx / l) * time_elapsed * speed;
		dy = (dy / l) * time_elapsed * speed;
		dx = (dx / l) * time_elapsed * speed;
		dy = (dy / l) * time_elapsed * speed;
 
		coord.x += dx;
		coord.y += dy;
		coord.x += dx;
		coord.y += dy;
	    }
	}
    }

    



    public boolean compareFish (Fish f){
	return (fishType.equals(f.getFishType()));
    }

    public String getFishType(){
	return fishType;
    }

    public Point getCoord(){
	return coord;
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
    
    public Point getDestination(){
	return this.nextDest;
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
   
    public void setTimeToDest(double t){
	timeToDest = t;
    }

    public void setDestination(Point p){
	coord = p;
    }

    public void startFish(){
	this.started = true;
    }
	 
}
