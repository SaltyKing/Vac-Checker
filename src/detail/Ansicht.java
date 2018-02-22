package detail;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Ansicht extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JLabel User = new JLabel();
	JButton Aktualisieren = new JButton();
	
	public Ansicht() {
		
		this.setTitle(getName());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		this.add(Aktualisieren);
		this.setVisible(true);
	}

}
