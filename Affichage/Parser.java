import java.util.Scanner; 
import java.util.LinkedList;


	

public class Parser { 
    LinkedList<Fish> fishes = new LinkedList<Fish>();
    public Parser(){
	Scanner sc = new Scanner(System.in); 
	String str = sc.nextLine(); 

    	String fishType = "";
	int size_x = 0, size_y = 0, coord_x = 0, coord_y = 0, speed = 0;
	String[] parts = str.split("[, ]");
	for (int j=0 ; j < parts.length ; j++) {
	    parts[j] = parts[j].replace("[","");
	    parts[j] = parts[j].replace("]","");
	    System.out.println(parts[j] + "\n"); 
	}

	// stocking information into memory
	for (int j=1 ; j < parts.length ; j++) {
	 
	    switch (j % 5){
	    case 1:
		fishType = parts[j];
		break;

	    case 3:
		String[] stock = parts[j].split("x");
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
		fishes.add((j-1)/5, new Fish(fishType, size_x, size_y, coord_x, coord_y, speed));
		break;

	
	    }
	}

	
	
	// printing to be sure

	for (int i = 0 ; i < fishes.size() ; i++){
	    System.out.println(fishes.get(i).toString());
	}
		/*	while (Curseur < str.length()) { 
	    Curseur++;
	    } */
	System.out.println("FIN ! "); 
    }

    public Fish getFish(int index){
	return fishes.get(index);
    }

    public LinkedList<Fish> getFishes(){
	return fishes;
    }

}
