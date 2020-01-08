import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
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
		JButton button1=new JButton("Singleplayer");
		JButton submit=new JButton("submit");
		
		JButton button2=new JButton("Multiplayer");
		JButton button3=new JButton("Settings");
		JButton button4=new JButton("Achievements");
		JButton button5=new JButton("Version");
		JButton button6=new JButton("Developers");
		
		
		JMenuBar mb=new JMenuBar();
		
		JMenu file=new JMenu("File...");
		JMenu open=new JMenu("Open...");
		JMenu team=new JMenu("Team...");
		
		JMenuItem submenu1=new JMenuItem("Open File...");
		JMenuItem submenu2=new JMenuItem("Open Something...");
		JMenuItem submenu3=new JMenuItem("See Team...");
		
		public menuType2() {
			
			JLabel background;
			f.setTitle("22-TRON");
			f.setSize(900,400);
			f.setLayout(new FlowLayout());
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			ImageIcon img=new ImageIcon(".//BILD.jpg");
			
			background=new JLabel("", img,JLabel.CENTER);
			background.setBounds(0,0,900,400);
			background.setIcon(img);
			add(background);
			f.setLocationRelativeTo(null);
			
			JPanel p=new JPanel();
			JPanel p2=new JPanel();
			JPanel p3=new JPanel();
			
			
			
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
			
			submit.setBackground(Color.BLUE);
			submit.setForeground(Color.WHITE);
			
			button1.setBackground(Color.GREEN);
			button2.setBackground(Color.YELLOW);
			button3.setBackground(Color.YELLOW);
			button4.setBackground(Color.YELLOW);
			button5.setBackground(Color.YELLOW);
			button6.setBackground(Color.YELLOW);

			
			
			HandlerClass handler1=new HandlerClass();
			handler1.kennzahl=1;
			HandlerClass handler2=new HandlerClass();
			handler2.kennzahl=2;
			HandlerClass handler3=new HandlerClass();
			handler3.kennzahl=3;
			HandlerClass handler4=new HandlerClass();
			handler4.kennzahl=4;
			HandlerClass handler5=new HandlerClass();
			handler5.kennzahl=5;
			HandlerClass handler6=new HandlerClass();
			handler6.kennzahl=6;
			
			
			button1.addActionListener(handler1);
			button2.addActionListener(handler2);
			button3.addActionListener(handler3);
			button4.addActionListener(handler4);
			button5.addActionListener(handler5);
			button6.addActionListener(handler6);
			
			setUndecorated(true);
			setExtendedState(Frame.MAXIMIZED_BOTH);
			JLabel imagelabel=new JLabel(new ImageIcon(".//BILD.jpg"));
			f.add(imagelabel);
			//add(f);
			
			/*imagelabel.setIcon(new ImageIcon(".//BILD.jpg"));
			p3.add(imagelabel);*/
			
			
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
			if(action.equals("Version")) {
				System.out.println("Der eingegebene Text ist "+txt.getText());
				String input =txt.getText();
				l2.setText("Version: 1.0.0");
			}
		}
	
		private class HandlerClass implements ActionListener{
			
			public int kennzahl=0;
			
			public void actionPerformed( ActionEvent event) {
				
				if (kennzahl==5) {
					JOptionPane.showMessageDialog(null, String.format("1.0.0", event.getActionCommand()));
				}
				if (kennzahl==6) {
					JOptionPane.showMessageDialog(null, String.format("Bayer Hugo,  Ramadani Dionis, Islamovic Armin, Doronenko Bronislav", event.getActionCommand()));
				}
				
				
			}
		}
}
