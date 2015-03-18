import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


 
public class Panneau extends JPanel {

    private int posX = -50;
    private int posY = -50;

    private int tailleX = 300;
    private int tailleY = 200;

    private String imageName;

    //private BufferedImage image;

    public Panneau (String name){
	imageName = name;
    }
	

    
    public void paintComponent(Graphics g){
	try {
	    Image img = ImageIO.read(new File(imageName+".jpg"));
	    //image = new BufferedImage(img.getWidth(null), img.getHeight(null),BufferedImage.TYPE_BYTE_INDEXED);
	    g.drawImage(img, posX, posY, tailleX, tailleY, this);
	    //image.getGraphics().drawImage(img, posX, posY, tailleX, tailleY, this);
	} catch (IOException e) {
	    e.printStackTrace();
	}                
    }


    /*public void invertImage(){
	AffineTransform tx = AffineTransform.getScaleInstance(-1, -1);
	tx.translate(-image.getWidth(null), -image.getHeight(null));
	AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
	image = op.filter(image, null);
	//repaint();

	}*/

   
    
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

