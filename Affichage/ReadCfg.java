import java.io.*;

public class ReadCfg {
    
    private String file;
    private String controllerAdress;
    private int id;
    private int controllerPort;
    private int displayTimeOut;
    private String resources;
    

    public ReadCfg (String file){
	this.file = file;
	this.controllerAdress = "";
	this.id = 0;
	this.controllerPort = 0;
	this.displayTimeOut = 0;
	this.resources = "";
    }

    public void read(){	
	try{
	    InputStream ips=new FileInputStream(file); 
	    InputStreamReader ipsr=new InputStreamReader(ips);
	    BufferedReader br=new BufferedReader(ipsr);
	    String line;
	    String[] parts;
	    
	    line=br.readLine();
	    parts = line.split(" ");
	    controllerAdress = parts[2];

	    line=br.readLine();
	    parts = line.split(" ");
	    id = Integer.parseInt(parts[2]);

	    line=br.readLine();
	    parts = line.split(" ");
	    controllerPort = Integer.parseInt(parts[2]);

	    line=br.readLine();
	    parts = line.split(" ");
	    displayTimeOut = Integer.parseInt(parts[2]);

	    line=br.readLine();
	    parts = line.split(" ");
	    resources = parts[2];

	    System.out.println(controllerAdress+" "+Integer.toString(id)+" "+Integer.toString(controllerPort)+" "+Integer.toString(displayTimeOut)+" "+resources);

	    br.close(); 
	}		
	catch (Exception e){
	    System.out.println(e.toString());
	}
    }
    
}
