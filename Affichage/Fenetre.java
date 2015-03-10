import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

  public static void main(String[] args) {
    new Fenetre();
  }

  private Panneau pan = new Panneau();

  public Fenetre() {
    
      this.setTitle("Aquarium");
      this.setSize(900, 600);
      this.setBackground(Color.WHITE);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      this.setContentPane(pan);
      //p.add(pan);
      this.setVisible(true);

      
      go();
  }
    
    
    

    //Va a l'endroit (destX, destY) a la vitesse vitesse
    /*  private void move (int destX, int destY, int vitesse){
	int x = pan.getPosX(), y = pan.getPosY();
	while (x != destX){
	    if (x > destX)
		x--;

	}
	}*/


    
    

    
    
    
    
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




