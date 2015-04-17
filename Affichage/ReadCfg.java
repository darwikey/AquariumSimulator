import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
 
public class ReadCfg {
     
    private String file;
    private String controllerAdress;
    private String id;
    private int controllerPort;
    private int displayTimeOut;
    private String resources;
     
 
    public ReadCfg (String file){
        this.file = file;
        this.controllerAdress = "";
        this.id = "";
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
        id = parts[2];
 
        line=br.readLine();
        parts = line.split(" ");
        controllerPort = Integer.parseInt(parts[2]);
 
        line=br.readLine();
        parts = line.split(" ");
        displayTimeOut = Integer.parseInt(parts[2]);
 
        line=br.readLine();
        parts = line.split(" ");
        resources = parts[2];
 
        //System.out.println(controllerAdress+" "+id+" "+Integer.toString(controllerPort)+" "+Integer.toString(displayTimeOut)+" "+resources);
 
        br.close();
    }      
    catch (Exception e){
        System.out.println(e.toString());
    }
    }
     
    public String getControllerAdress(){
        return this.controllerAdress;
    }

     
    public String getId(){
        return this.id;
    }
     
    public int getControllerPort(){
        return this.controllerPort;
    }
     
    public int getDisplayTimeOut(){
        return this.displayTimeOut;
    }
     
    public String getResources(){
        return this.resources;
    }
     
     
}
