import java.util.Scanner; 
import java.util.LinkedList;


	

public class Parser { 

    private String fishType = "";
    LinkedList<Fish> fishes = new LinkedList<Fish>();
    String str = new String();
    Scanner sc;

    public Parser(){

    }

    public Fish getFish(int index){
	return fishes.get(index);
    }

    public String parse(){
	sc = new Scanner(System.in);
	str = sc.nextLine(); 
    	double time = 0.0;
	int size_x = 0, size_y = 0, coord_x = 0, coord_y = 0;
	String[] parts = str.split("[, ]");
	for (int j=0 ; j < parts.length ; j++) {
	    parts[j] = parts[j].replace("[","");
	    parts[j] = parts[j].replace("]","");
	}


	if (parts.length == 1 && parts[0].compareTo("status") == 0){
	    System.out.println("Demande de status à effectuer");
	    return "status";
	}

	
	else if(parts.length == 4 && parts[0].compareTo("hello") == 0 && parts[1].compareTo("in") == 0 && parts[2].compareTo("as") == 0){
	    System.out.println("greeting " + parts[3]);
	    return "hello in as ID";

	}

	else if(parts.length == 1 && parts[0].compareTo("hello") == 0){
	    System.out.println("greeting IDlibre");
	    return "hello";
	}

	else if(parts.length == 2 && parts[0].compareTo("log") == 0 && parts[1].compareTo("out") == 0){
	    System.out.println("bye");
	    sc.close();
	    return("logout");
	}

	else if(parts.length == 1 && parts[0].compareTo("getFishes") == 0){
	    System.out.println("Retourner liste des poissons");
	    return "getFishes";
	}


	else if(parts.length == 1 && parts[0].compareTo("getFishesContinuously") == 0){
	    System.out.println("Retourner liste des poissons de manière continue");
	    return "getFishesContinuaously";
	}

	
	else if(parts.length == 2 && parts[0].compareTo("ping") == 0){
	    System.out.println("pong " + parts[1]);
	    return "ping";
	}

	else if (parts[0].compareTo("addFish") == 0){

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
		    time = Integer.parseInt(parts[j]);
		    fishes.add((j-1)/5, new Fish(fishType, size_x, size_y, coord_x, coord_y, time));
		    break;
	
		}
	    }
	    for (int i = 0 ; i < fishes.size() ; i++){
	    System.out.println(fishes.get(i).toString());
	}

	    return "addFish";
	}


	else if (parts[0].compareTo("list") == 0){
	    System.out.println("coucou");
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
		    time = Double.parseDouble(parts[j]);
		    fishes.add((j-1)/5, new Fish(fishType, size_x, size_y, coord_x, coord_y, time));
		    break;
	
		}
	    }
	    for (int i = 0 ; i < fishes.size() ; i++){
	    System.out.println(fishes.get(i).toString());
	}

	    return "list";
	}

	else if (parts[0].compareTo("quit") == 0){
	    sc.close();
	    return "quit";
	}

	else {
	    System.out.println("-> NOK : commande introuvable");
	    return "NOK";
	}
      
    }

    public int  parseGreeting(String str){
	String[] parts = str.split(" ");
	return Integer.parseInt(parts[1]);
    }




     public void parseListFish(String str){
	String[] parts = str.split("[, ]");
	int size_x = 0, size_y = 0, coord_x = 0, coord_y = 0, speed = 0;
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
	
    }



    

    public LinkedList<Fish> getFishes(){ return fishes; }

}
