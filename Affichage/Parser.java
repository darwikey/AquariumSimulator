import java.util.Scanner; 
import java.util.LinkedList;
import java.util.HashMap;

	

public class Parser { 

    Scanner sc;
    private HashMap <String,Point> fishPositionsHashMap;

    public Parser(){
	sc = new Scanner(System.in);
	fishPositionsHashMap = new HashMap <String,Point> ();
    }
    

    public LinkedList<Fish> parseFishList(String str){
    	 
	String fishType = "";
	LinkedList<Fish> fishes = new LinkedList<Fish>();
	int size_x = 0, size_y = 0, coord_x = 0, coord_y = 0, speed = 0;
	String[] parts = str.split("[, ]"), stock;
    	 
	for (int j=0 ; j < parts.length ; j++) {
	    parts[j] = parts[j].replace("[","");
	    parts[j] = parts[j].replace("]","");
	}
   	   	   	
	for (int j=1 ; j < parts.length ; j++) {
	 
	    switch (j % 5){
	    case 1:
		fishType = parts[j];
		break;

	    case 3:
		stock = parts[j].split("x");
		coord_x = Integer.parseInt(stock[0]);
		coord_y = Integer.parseInt(stock[1]);
		break;

	    case 4:
		stock = parts[j].split("x");
		size_x = Integer.parseInt(stock[0]);

		size_y = Integer.parseInt(stock[1]);
		break;
		
	    case 0:
		speed = Integer.parseInt(parts[j]);
		
		// fishposition contains the former position and the fishType of all previous fishses
		// start a new fish at 0,0
		// keep going with a previous fish with its former position

		if (fishPositionsHashMap.size() == 0){  		    
		    fishes.add((j-1)/5, new Fish(fishType, size_x, size_y, coord_x, coord_y, speed, new Point(0,0)));
		}
		else {
		    boolean found = false;
		    if (fishPositionsHashMap.containsKey(fishType)){
			fishes.add((j-1)/5, new Fish(fishType, size_x, size_y, coord_x, coord_y, speed, (Point)fishPositionsHashMap.get(fishType)));
			    fishPositionsHashMap.remove(fishType);
			    found = true;
		    }
		    
		    if (!found){
			fishes.add((j-1)/5, new Fish(fishType, size_x, size_y, coord_x, coord_y, speed, new Point(0,0)));
			System.out.println("not found");
		        
		    }
		    
		}
					      		
		break;
	
	    }
	}
	return fishes;
	
    }
    
    public void fillPositionsHashMap(HashMap <String,Point> hm){	
    	fishPositionsHashMap = hm;
    }



}
