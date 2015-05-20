import java.util.Scanner; 
import java.util.LinkedList;
import java.util.HashMap;

/*
  Parses the answer to getFishesContinuously to a list of fishes.

 */	

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

                int min_dist = Math.min(Math.min(coord_x -(-size_x), 100-coord_x),Math.min(coord_y -(-size_y),100-coord_y));
                int pos_x = coord_x;// the position to apear
                int pos_y = coord_y;
                if (coord_x + size_x == min_dist)
                    pos_x = -size_x;
                if (100-coord_x == min_dist)
                    pos_x = 100;
                if (coord_y + size_y == min_dist)
                    pos_y = -size_y;
                if (100-coord_y == min_dist)
                    pos_y = 100;

		if (fishPositionsHashMap.size() == 0){
		    fishes.add((j-1)/5, new Fish(fishType, size_x, size_y, coord_x, coord_y, speed, new Point(pos_x,pos_y)));
		}
		else {
		    boolean found = false;
		    if (fishPositionsHashMap.containsKey(fishType)){
			fishes.add((j-1)/5, new Fish(fishType, size_x, size_y, coord_x, coord_y, speed, (Point)fishPositionsHashMap.get(fishType)));
			    fishPositionsHashMap.remove(fishType);
			    found = true;
		    }
		    
		    if (!found){
			fishes.add((j-1)/5, new Fish(fishType, size_x, size_y, coord_x, coord_y, speed, new Point(pos_x,pos_y)));
		       	        
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
