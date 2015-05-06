import java.awt.TextField;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputRaffin extends JPanel {
    
    private TextField t;

    public InputRaffin(){

	this.setPreferredSize(new Dimension(400, 40));
	t = new TextField("text field");
	this.add(t);
	this.setFocusable(true);
	t.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		    System.out.println(t.getText() + "\n");
		    t.setText("");
		}
	    });
	
    }
}