import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class MainFrame extends JFrame implements ActionListener, MenuListener{
	private JMenuBar menuBar;
	private JMenu userMenu, categoryMenu, transactionMenu, manageMenu;
	private JMenuItem loginMI, registerMI, updateProfileMI, rentMI, viewCartMI, signOutMI, exitMI, minibusMI, familyMI, sedanMI, manageMI;
	private JLabel imageLabel;
	private JDesktopPane desktop;
	private ImageIcon background;
	private LoginFrame lf;
	private RegisterFrame rf;
	private RentCarFrame rcf;
	private UpdateFrame uf;
	private String currUser;
	Image bgImg;

	public MainFrame(){
		Connect con = new Connect();
		// MenuBar
		menuBar = new JMenuBar();

		// Menu
		userMenu = new JMenu("User");
		categoryMenu = new JMenu("Category");
		manageMenu = new JMenu("Manage");
		transactionMenu = new JMenu("Transaction");

		// MenuItem
		loginMI = new JMenuItem("Login");
		registerMI = new JMenuItem("Register");
		exitMI = new JMenuItem("Exit");

		updateProfileMI = new JMenuItem("Update Profile");
		signOutMI = new JMenuItem("Sign Out");

		minibusMI = new JMenuItem("Minibus");
		familyMI = new JMenuItem("Family Car");
		sedanMI = new JMenuItem("Sedan");

		rentMI = new JMenuItem("Rent Car"); 
		viewCartMI = new JMenuItem("View Cart");
		manageMI = new JMenuItem("Manage Vehicle"); 

		// Add MenuItem to Menu
		userMenu.add(loginMI);
		userMenu.add(registerMI);
		userMenu.add(exitMI);

		// Add Menu to MenuBar
		menuBar.add(userMenu);

		// Menu Listener
		userMenu.addMenuListener(this);

		// ActionListener
		loginMI.addActionListener(this);
		registerMI.addActionListener(this);
		exitMI.addActionListener(this);

		// Add MenuBar to Frame
		setJMenuBar(menuBar);

		// // Set image   
		background = new ImageIcon(this.getClass().getResource("/background.jpg"));
		imageLabel = new JLabel(background);
		imageLabel.setSize(getWidth(),getHeight());
		this.add(imageLabel);

		
//		bgImg = new ImageIcon("background.jpg").getImage();
		
		try {
			bgImg = new ImageIcon(new URL("https://images.unsplash.com/photo-1532587459811-f057563d1936?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=465&q=80")).getImage();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		setVisible(true);
		setSize(800, 600);
		setTitle("SewaMobil");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}



	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == loginMI){
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			
			setContentPane(desktop);
			lf = new LoginFrame();
			add(lf);
			
		}else if(e.getSource() == registerMI){
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new RegisterFrame());
		}else if(e.getSource() == exitMI){
			this.dispose();
		}else if(e.getSource() == signOutMI){

		}else if(e.getSource() == minibusMI){

		}else if(e.getSource() == familyMI){

		}else if(e.getSource() == sedanMI){

		}else if(e.getSource() == manageMI){

		}else if(e.getSource() == updateProfileMI){

		}else if(e.getSource() == rentMI){

		}else if(e.getSource() == viewCartMI){

		}
	}
}
