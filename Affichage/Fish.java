public class Fish {

    private String fishType;
    private int size_x;
    private int size_y;
    private int coord_x;
    private int coord_y;
    private int speed;

    public Fish(String fishType, int size_x, int size_y, int coord_x, int coord_y, int speed){
	this.fishType = fishType;
	this.size_x = size_x;
	this.size_y = size_y;
	this.coord_x = coord_x;
	this.coord_y = coord_y;
	this.speed = speed;
    }

    public String toString(){
	String str = "";
	str += "Fish name: " + fishType + "\n";
	str += "Fish coords: " + coord_x + " " + coord_y + "\n";
	str += "Fish size: " + size_x + " " + size_y + "\n";
	str += "Fish speed: " + speed + "\n";
	
	return str;
	    
    }


    public String getFishType(){
	return fishType;
    }

}
