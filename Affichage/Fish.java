public class Fish {

    private String fishType;
    private int size_x;
    private int size_y;
    private int coord_x;
    private int coord_y;
    private int speed;
    private boolean inversed;

    public Fish(String fishType, int size_x, int size_y, int coord_x, int coord_y, int speed){
	this.fishType = fishType;
	this.size_x = size_x;
	this.size_y = size_y;
	this.coord_x = coord_x;
	this.coord_y = coord_y;
	this.speed = speed;
	this.inversed = false;
    }
    
    public void setInversed(boolean b){
    	this.inversed = b;
    }
    
    public boolean getInversed(){
    	return this.inversed;
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
    public int move (int destX, int destY, float temps){

	int dx = Math.abs (destX-coord_x);
 	int dy = Math.abs (destY-coord_y);
	int maxDistance = 0;

	if(dx<dy)
	    maxDistance = dy;
	else 
	    maxDistance = dx;

	int timeRepaint = (int) ((temps/maxDistance) *1000);
	
	
	while (coord_x != destX && coord_y !=destY){
	    if (coord_x > destX)
		coord_x--;
	    if (coord_x < destX)
		coord_x++;
	    if (coord_y > destY)
		coord_y--;
	    if (coord_y < destY)
		coord_y++;
	    
	}
	return timeRepaint;	
	
    }


    public boolean compareFish (Fish f){
	return (fishType.equals(f.getFishType()));
    }

    public String getFishType(){
	return fishType;
    }

    public int getPosX(){
	return coord_x;
    }

    public int getPosY(){
	return coord_y;
    }

    public int getSizeX(){
	return size_x;
    }

    public int getSizeY(){
	return size_y;
    }


    public void setPosX(int a){
	coord_x = a;
    }

    public void setPosY(int a){
	 coord_y = a;
    }

    public void setSizeX(int a){
	 size_x = a;
    }

    public void setSizeY(int a){
	 size_y = a;
    }

    


}
