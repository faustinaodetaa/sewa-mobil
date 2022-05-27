import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.border.EmptyBorder;
public class LoginFrame extends JInternalFrame implements ActionListener{
	private JTextField usernameTextField;
	private JPasswordField passwordTextField;
	private JButton loginButton;
	private Connect con;
	private HomeFrame homeFrame;
	private MainFrame mainFrame;
	 private static final long serialVersionUID = 1L;


	public LoginFrame() {
		con = new Connect();


//        this.setSize();
//		try {
//			this.setMaximum(true);
//		} catch (PropertyVetoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        this.setVisible(true);
        this.setTitle("Login Page");

        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new GridLayout(4, 0, 0, 0));
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.ORANGE);
        getContentPane().add(headerPanel);
        headerPanel.setLayout(new GridLayout(0, 1, 0, 0));
        
        JLabel headerLabel = new JLabel("Login Form");
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
        headerPanel.add(headerLabel);
        
        JPanel usernamePanel = new JPanel();
        usernamePanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        usernamePanel.setBackground(Color.ORANGE);
        getContentPane().add(usernamePanel);
        usernamePanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        usernamePanel.add(usernameLabel);
        
        usernameTextField = new JTextField();
        usernamePanel.add(usernameTextField);
        usernameTextField.setColumns(10);
        
        JPanel passwordPanel = new JPanel();
        passwordPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        passwordPanel.setBackground(Color.ORANGE);
        getContentPane().add(passwordPanel);
        passwordPanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        passwordPanel.add(passwordLabel);
        
        passwordTextField = new JPasswordField();
        passwordPanel.add(passwordTextField);
        passwordTextField.setColumns(10);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.ORANGE);
        getContentPane().add(buttonPanel);
        buttonPanel.setLayout(new BorderLayout(0, 0));
        
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        buttonPanel.add(loginButton, BorderLayout.SOUTH);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 15, 10));
		
		loginButton.addActionListener(this);

        this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == loginButton){
			System.out.println("login");
			login();
		}
		
	}

	public void login(){
		System.out.println("login");
		try {
			String usernameInput = usernameTextField.getText();
			String passwordInput = String.valueOf(passwordTextField.getPassword());
			if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
				JOptionPane.showMessageDialog(this, "All field must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			boolean foundUsername = false;
			boolean foundPassword = false;
			String queryUser = "SELECT Username FROM user";
			Statement stat = con.getCon().createStatement();
			ResultSet rs = stat.executeQuery(queryUser);

			while (rs.next()) {
				String username = rs.getString("Username");
				if (usernameInput.equals(username)) {
					foundUsername = true;
					break;
				}
			}
			if (foundUsername) {
				String queryPass = "SELECT Password FROM user WHERE Username='%s'";
				queryPass = String.format(queryPass, usernameInput);
				stat = con.getCon().createStatement();
				rs = stat.executeQuery(queryPass);
				rs.next();
				String password = rs.getString("Password");
				if (passwordInput.equals(password)) {
					foundPassword = true;
				}
			}
			if (foundUsername && foundPassword) {
				String queryUsername = "SELECT Id, Name FROM user WHERE Username='%s'";
				queryUsername = String.format(queryUsername, usernameInput);
				stat = con.getCon().createStatement();
				rs = stat.executeQuery(queryUsername);
				rs.next();
				String currUser = rs.getString("Id");
				String username = rs.getString("Name");
				JOptionPane.showMessageDialog(this, "Welcome to SewaMobil, " + username);
				String role = getRole(usernameInput);
//				this.dispose();
				((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, loginButton)).dispose();
				
				new HomeFrame(currUser, role).setVisible(true);
			} else {
				JOptionPane.showMessageDialog(this, "Invalid Username or Password", "Warning", JOptionPane.WARNING_MESSAGE);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getRole(String username){
		String role="";
		try {
			String query = "SELECT Role FROM user WHERE Username='%s'";
			query = String.format(query, username);
			Statement stat = con.getCon().createStatement();
			ResultSet rs = stat.executeQuery(query);
			rs.next();
			role = rs.getString("Role");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	

}