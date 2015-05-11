import java.util.Scanner; 
import java.util.LinkedList;


	

public class Parser { 

    //private String fishType = "";
    //LinkedList<Fish> fishes = new LinkedList<Fish>();
    //String str = new String();
    Scanner sc;
    private LinkedList <Juhnytg> fishPositions = null;

    public Parser(){
	sc = new Scanner(System.in);

    }

    /*public Fish getFish(int index){
	return fishes.get(index);
	}*/

    public String readInput(){
	String str = new String();
	//sc = new Scanner(System.in);
	str = sc.nextLine();
	return str;

    }   
    

    public String parseGreeting(String str){
    	String[] parts = str.split(" ");
    	System.out.println("paaart 1: "+ parts[1]);
	return parts[1];
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
		if (fishPositions.size() == 0){
		    System.out.println("coucou");
		    
		    fishes.add((j-1)/5, new Fish(fishType, size_x, size_y, coord_x, coord_y, speed, new Point(0,0)));
		}

		else {
		    System.out.println("PAS COUCOU");
		    boolean found = false;
		    for (int i = 0 ; i < fishPositions.size() ; i++){
			if (fishType.equals(fishPositions.get(i).getFishType())){
			    fishes.add((j-1)/5, new Fish(fishType, size_x, size_y, coord_x, coord_y, speed, fishPositions.get(i).getPos()));
			    fishPositions.remove(i);
			    found = true;
			    break;
			}
		    }

		    if (!found){
			// un poisson arrive, soit nouvellement créé, posi aléatoire ? sinon arrive d'un bord ? info à donner.
		    }

		    
		    
		    
		}
		
		break;
	
	    }
	}
	return fishes;
	
    }

    public void fillPositionsList(LinkedList<Juhnytg> list){
	
	fishPositions = list;

    }

    

    //public LinkedList<Fish> getFishes(){ return fishes; }

}
