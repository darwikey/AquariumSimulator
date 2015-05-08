public class ReadAndSendConsoleOutput implements Runnable{

    private Scanner sc;
    private PrintWriter pw
    
	public ReadAndSendConsoleOutput(PrintWriter pw){
	sc = new Scanner(System.in);
	pw = pw;
    }

    public void run(){
	while(true){
	    out.println(sc.nextLine());
	    out.flush();
	}
    }

}
