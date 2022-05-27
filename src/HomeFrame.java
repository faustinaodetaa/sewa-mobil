import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeFrame extends JFrame implements ActionListener, MenuListener{
	private JMenuBar menuBar;
	private JMenu userMenu, categoryMenu, transactionMenu, manageMenu, rentMenu;
	private JMenuItem loginMI, registerMI, updateProfileMI, viewCartMI, signOutMI, exitMI, minibusMI, familyMI, sedanMI, manageMI, rentMinibus, rentFamilyCar, rentSedan;
	private JLabel imageLabel;
	public JDesktopPane desktop;
	private ImageIcon background;
	private LoginFrame lf;
	private RegisterFrame rf;
	private RentCarFrame rcf;
	private UpdateFrame uf;
	private String currUser="";
	private String currRole="";
	Image bgImg;
	private String currFrame;
	private String category;

	public HomeFrame(String currUser, String role){
		this.currUser = currUser;
		this.currRole = role;
		//    	System.out.println(currUser);
		currFrame = "Home";
		Connect con = new Connect();
		// MenuBar
		menuBar = new JMenuBar();

		// Menu
		userMenu = new JMenu("User");
		categoryMenu = new JMenu("Category");
		manageMenu = new JMenu("Manage");
		transactionMenu = new JMenu("Transaction");
		rentMenu = new JMenu("Rent Car"); 

		// MenuItem
		exitMI = new JMenuItem("Exit");

		updateProfileMI = new JMenuItem("Update Profile");
		signOutMI = new JMenuItem("Sign Out");

		minibusMI = new JMenuItem("Minibus");
		familyMI = new JMenuItem("Family Car");
		sedanMI = new JMenuItem("Sedan");

		viewCartMI = new JMenuItem("View Cart");
		manageMI = new JMenuItem("Manage Vehicle"); 
		
		rentMinibus = new JMenuItem("Rent Minibus");
		rentFamilyCar = new JMenuItem("Rent Family Car");
		rentSedan = new JMenuItem("Rent Sedan");




		// Roles
		if(role.equals("Customer")){
			// User Menu
			userMenu.add(updateProfileMI);
			userMenu.add(signOutMI);
			userMenu.add(exitMI);
			// Category Menu
			categoryMenu.add(minibusMI);
			categoryMenu.add(familyMI);
			categoryMenu.add(sedanMI);
			// Transaction Menu
			rentMenu.add(rentMinibus);
			rentMenu.add(rentFamilyCar);
			rentMenu.add(rentSedan);
			transactionMenu.add(rentMenu);
			transactionMenu.add(viewCartMI);

			// Add Menu to MenuBar
			menuBar.add(userMenu);
			menuBar.add(categoryMenu);
			menuBar.add(transactionMenu);
			

			currFrame = "Minibus";
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new MainMenu(currFrame, this.currRole));
		}else if(role.equals("Admin")){
			// User Menu
			userMenu.add(signOutMI);
			userMenu.add(exitMI);
			// Category Menu
			categoryMenu.add(minibusMI);
			categoryMenu.add(familyMI);
			categoryMenu.add(sedanMI);
			// Manage Menu
			manageMenu.add(manageMI);

			// Add Menu to MenuBar
			menuBar.add(userMenu);
			menuBar.add(categoryMenu);
			menuBar.add(manageMenu);

			currFrame = "Minibus";
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new MainMenu(currFrame, this.currRole));
		}

		// Menu Listener
		userMenu.addMenuListener(this);
		categoryMenu.addMenuListener(this);
		manageMenu.addMenuListener(this);
		transactionMenu.addMenuListener(this);
		rentMenu.addMenuListener(this);

		// ActionListener
		exitMI.addActionListener(this);
		updateProfileMI.addActionListener(this);
		signOutMI.addActionListener(this);
		minibusMI.addActionListener(this);
		familyMI.addActionListener(this);
		sedanMI.addActionListener(this);
		rentMinibus.addActionListener(this);
		rentFamilyCar.addActionListener(this);
		rentSedan.addActionListener(this);
		viewCartMI.addActionListener(this);
		manageMI.addActionListener(this);


		// Add MenuBar to Frame
		setJMenuBar(menuBar);

		// // Set image   
		background = new ImageIcon(this.getClass().getResource("/background.jpg"));
		imageLabel = new JLabel(background);
		imageLabel.setSize(600,500);
		this.add(imageLabel);


		try {
			bgImg = new ImageIcon(new URL("https://images.unsplash.com/photo-1532587459811-f057563d1936?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=465&q=80")).getImage();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		this.currUser = currUser;
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
		if(e.getSource() == exitMI){
			this.dispose();

		}else if(e.getSource() == signOutMI){
			this.dispose();
			new MainFrame();
		}else if(e.getSource() == minibusMI){
			currFrame = "Minibus";
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new MainMenu(currFrame, this.currRole));

		}else if(e.getSource() == familyMI){
			currFrame = "FamilyCar";
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new MainMenu(currFrame,this.currRole));
		}else if(e.getSource() == sedanMI){
			currFrame = "Sedan";
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new MainMenu(currFrame,this.currRole));
		}else if(e.getSource() == manageMI){
			currFrame = "Manage";
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new ManageVehicle(this.currUser));
		}else if(e.getSource() == updateProfileMI){
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new UpdateFrame(this.currUser, this.currFrame));

		}else if(e.getSource() == rentMinibus){
			currFrame = "Rent";
			category = "Minibus";
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new RentCarFrame(this.currUser, category));
		}else if(e.getSource() == rentFamilyCar){
			currFrame = "Rent";
			category = "FamilyCar";
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new RentCarFrame(this.currUser, category));
		}else if(e.getSource() == rentSedan){
			currFrame = "Rent";
			category = "Sedan";
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new RentCarFrame(this.currUser, category));
		}else if(e.getSource() == viewCartMI){
			currFrame = "Cart";
			desktop = new JDesktopPane() {
				@Override
				protected void paintComponent(Graphics g) {
					// TODO Auto-generated method stub
					super.paintComponent(g);
					g.drawImage(bgImg, 0, 0, getWidth(), getHeight(),null);
				}
			};
			setContentPane(desktop);
			add(new ViewCartFrame(this.currUser));
		}
	}
}
