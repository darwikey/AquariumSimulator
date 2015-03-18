import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.lang.Math;
import java.lang.Long;

public class Fenetre extends JFrame {

    private String fishName;

  public static void main(String[] args) {
      Parser p = new Parser();
      System.out.println(p.getFish(0).getFishType());
      new Fenetre(p.getFish(0).getFishType());
    
  }

  private Panneau pan = new Panneau(fishName);

  public Fenetre(String str) {
      fishName = str;
      this.setTitle("Aquarium");
      this.setSize(900, 600);
      this.setBackground(Color.WHITE);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      this.setContentPane(pan);
      //p.add(pan);
      this.setVisible(true);

      
      //go();
      move (400,400,2);
      //pan.invertImage();
  }


    
    
    

    //Va a l'endroit (destX, destY) a la vitesse vitesse
    public void move (int destX, int destY, float temps){
	int x = pan.getPosX(), y = pan.getPosY();
	int dx = Math.abs (destX-x);
	int dy = Math.abs (destY-y);
	int maxDistance = 0;

	if(dx<dy)
	    maxDistance = dy;
	else 
	    maxDistance = dx;

	int timeRepaint = (int) ((temps/maxDistance) *1000);
	
	
	while (x != destX && y !=destY){
	    if (x > destX)
		pan.setPosX(x--);
	    if (x < destX)
		pan.setPosX(x++);
	    if (y > destY)
		pan.setPosY(y--);;
	    if (y < destY)
		pan.setPosY(y++);

	    pan.repaint();
	    
	     try {
		Thread.sleep(timeRepaint);
	    } catch (InterruptedException e) {
		e.printStackTrace();
		}
	    
	}
	
	
    }


    
    

    
    
    
    
    private void go() {
	int x = pan.getPosX(), y = pan.getPosY();
	boolean backX = false;
	boolean backY = false;
	
	while (true) {
	    if (x < 1)
		backX = false;
	    if (x > pan.getWidth() - 50)
		backX = true;
	    if (y < 1)
		backY = false;
	    if (y > pan.getHeight() - 50)
		backY = true;
	    if (!backX)
		pan.setPosX(++x);
	    else
		pan.setPosX(--x);
	    if (!backY)
		pan.setPosY(++y);
	    else
		pan.setPosY(--y);

	    pan.repaint();
	    
	    try {
		Thread.sleep(3);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}




