import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
 
public class Panneau extends JPanel {

    private int posX = -50;
    private int posY = -50;

    private int tailleX = 300;
    private int tailleY = 200;
    
    public void paintComponent(Graphics g){
	try {
	    Image img = ImageIO.read(new File("poisson-rouge.jpg"));
	    g.drawImage(img, posX, posY, tailleX, tailleY, this);
	} catch (IOException e) {
	    e.printStackTrace();
	}                
    }

    
    public int getTailleX() {
	return tailleX;
    }
    
    public void setTailleX(int tailleX) {
	this.tailleX = tailleX;
    }
    
    public int getTailleY() {
	return tailleY;
    }
    
    public void setTailleY(int tailleY) {
	this.tailleY = tailleY;
    } 

    
    public int getPosX() {
	return posX;
    }
    
    public void setPosX(int posX) {
	this.posX = posX;
    }
    
    public int getPosY() {
	return posY;
    }
    
    public void setPosY(int posY) {
	this.posY = posY;
    }    
    
    
}
