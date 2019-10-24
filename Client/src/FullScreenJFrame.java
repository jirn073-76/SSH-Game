import java.awt.FlowLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.DocFlavor.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;



import javax.swing.JComponent;

public class FullScreenJFrame extends JFrame {
	private GraphicsDevice vc;

	JFrame frame = new JFrame();
	

	public FullScreenJFrame() {
		super();

		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = e.getDefaultScreenDevice();

		JButton playbutton = new JButton("Play");

		playbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			

				// Hier methoden einfügen für einstellungen
				System.out.flush();  
				
			}
		});

		JButton settingsbutton = new JButton("Settings");
		settingsbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Hier methoden einfügen für spiel
				System.out.flush();  

				
			}
		});

		JButton exitbutton = new JButton("exit");
		exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);

			}
		});
		this.setLayout(new FlowLayout());
		this.add(playbutton);
		this.add(settingsbutton);
		this.add(exitbutton);
		setFullScreen(this);
		
	}

	public void setFullScreen(JFrame f) {

		f.setUndecorated(true);
		f.setResizable(false);
		

		vc.setFullScreenWindow(f);

	}

//	public static void main(String[] args) {
//		new FullScreenJFrame();
//	}
//	
}
