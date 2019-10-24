import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class menuType2 extends JFrame implements ActionListener {
	
		JFrame f=new JFrame();

		JLabel l1=new JLabel("Please Enter your name");
		JLabel l2=new JLabel();
		
		JTextField txt= new JTextField(30);
		JButton button1=new JButton("Play");
		JButton submit=new JButton("submit");
		
		JButton button2=new JButton("2");
		JButton button3=new JButton("3");
		JButton button4=new JButton("4");
		JButton button5=new JButton("5");
		JButton button6=new JButton("6");
		
		JMenuBar mb=new JMenuBar();
		
		JMenu file=new JMenu("File...");
		JMenu open=new JMenu("Open...");
		JMenu team=new JMenu("Team...");
		
		JMenuItem submenu1=new JMenuItem("Open File...");
		JMenuItem submenu2=new JMenuItem("Open Something...");
		JMenuItem submenu3=new JMenuItem("See Team...");
		
		public menuType2() {
			f.setTitle("22-TRON");
			f.setSize(900,400);
			f.setLayout(new FlowLayout());
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setLocationRelativeTo(null);
			
			JPanel p=new JPanel();
			JPanel p2=new JPanel();
			JPanel p3=new JPanel();
			JLabel imagelabel=new JLabel();
			
			p.add(l1);
			p.add(txt);
			
			p.add(submit);
			p.add(l2);
			
			
			p2.add(button1);
			p2.add(button2);
			p2.add(button3);
			p2.add(button4);
			p2.add(button5);
			p2.add(button6);
			
			
			imagelabel.setIcon(new ImageIcon(".//BILD.jpg"));
			p3.add(imagelabel);
			
			mb.add(file);
			mb.add(open);
			mb.add(team);
			
			file.add(submenu1);
			open.add(submenu2);
			team.add(submenu3);
			
			f.setJMenuBar(mb);
			
			p2.setLayout(new GridLayout(2,2,2,2));
			
			f.add(p);
			f.add(p2);
			f.add(p3);
			
			button1.addActionListener(this);
			submit.addActionListener(this);
			
			f.setVisible(true);
		}
		public void actionPerformed(ActionEvent e) {
			String action=e.getActionCommand();
			if(action.equals("submit")) {
				System.out.println("Der eingegebene Text ist "+txt.getText());
				String input =txt.getText();
				l2.setText("Hello "+input+ "! Please select your option!");
			}
		}
	
}
