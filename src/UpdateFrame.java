import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;


public class UpdateFrame extends JInternalFrame implements ActionListener{
	 private static final long serialVersionUID = 1L;
	 
    private String currUser;
    private Connect con;
    private HomeFrame hf;
    private String prev;
    private JTextField usernameTextField;
    private JTextField nameTextField;
    private JPasswordField passwordField;
    private JTextField phoneNumberTextField;
    private JTextArea addressTextArea;
    private JButton btmButton, backButton, updateButton;
    private MainMenu mm;
    
    public UpdateFrame(String currUser, String prev){
    	con = new Connect();
    	this.currUser = currUser ;
    	this.prev = prev;
//    	System.out.println("di update " + this.currUser);
        this.setSize(400,400);
        this.setVisible(true);
        this.setTitle("Update Profile");

        getContentPane().setBackground(Color.ORANGE);
        getContentPane().setLayout(new GridLayout(7, 0, 0, 0));
        
        JPanel titlePanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) titlePanel.getLayout();
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);
        titlePanel.setBackground(Color.ORANGE);
        titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(titlePanel);
        
        JLabel titleLabel = new JLabel("Update Form");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel);
        
        JPanel usernamePanel = new JPanel();
        usernamePanel.setBorder(new EmptyBorder(6, 10, 6, 10));
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
        passwordPanel.setBorder(new EmptyBorder(6, 10, 6, 10));
        passwordPanel.setBackground(Color.ORANGE);
        getContentPane().add(passwordPanel);
        passwordPanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        passwordPanel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordPanel.add(passwordField);
        
        JPanel namePanel = new JPanel();
        namePanel.setBorder(new EmptyBorder(6, 10, 6, 10));
        namePanel.setBackground(Color.ORANGE);
        getContentPane().add(namePanel);
        namePanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        namePanel.add(nameLabel);
        
        nameTextField = new JTextField();
        namePanel.add(nameTextField);
        nameTextField.setColumns(10);
        
        JPanel phoneNumberPanel = new JPanel();
        phoneNumberPanel.setBorder(new EmptyBorder(6, 10, 6, 10));
        phoneNumberPanel.setBackground(Color.ORANGE);
        getContentPane().add(phoneNumberPanel);
        phoneNumberPanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        phoneNumberPanel.add(phoneNumberLabel);
        
        phoneNumberTextField = new JTextField();
        phoneNumberPanel.add(phoneNumberTextField);
        phoneNumberTextField.setColumns(10);
        
        JPanel addressPanel = new JPanel();
        addressPanel.setBorder(new EmptyBorder(6, 10, 6, 10));
        addressPanel.setBackground(Color.ORANGE);
        getContentPane().add(addressPanel);
        addressPanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        addressPanel.add(addressLabel);
        
        addressTextArea = new JTextArea();
        addressTextArea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        addressPanel.add(addressTextArea);
		// getContentPane().add(formPanel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.ORANGE);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(buttonPanel);
        buttonPanel.setLayout(new GridLayout(0, 3, 0, 0));
        
        btmButton = new JButton("Back to Main Menu");
        btmButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        buttonPanel.add(btmButton);
        
        backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        buttonPanel.add(backButton);
        
        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
        buttonPanel.add(updateButton);
        
        setBounds(100, 0, 550, 400);

        
        btmButton.addActionListener(this);
        backButton.addActionListener(this);
        updateButton.addActionListener(this);

    }

    private void updateData(){
        String username = usernameTextField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String name = nameTextField.getText();
        String phone = phoneNumberTextField.getText();
        String address = addressTextArea.getText();
        String id = this.currUser;
        
        if(username.isEmpty() || password.isEmpty() || name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
        	JOptionPane.showMessageDialog(this, "All field must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
        }else if(address.length() < 10 || address.length() > 25) {
        	JOptionPane.showMessageDialog(this, "Address must be between 10 and 25 characters!", "Warning", JOptionPane.WARNING_MESSAGE);
        }else {
        	String queryUpdate = "UPDATE user SET Username = '" + username +"', Password = '" + password + "', Name = '" + name + "', PhoneNumber = '" + phone + "', Address = '" + address + "' WHERE Id = '" + id + "'"; // kurang kondisi
        	try {
				Statement stat = con.getCon().createStatement();
				stat.execute(queryUpdate);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	JOptionPane.showMessageDialog(this, "Updated Successfully!");
        	((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, btmButton)).dispose();			
			new HomeFrame(currUser, "Customer").setVisible(true);
        }
        
        

    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btmButton) {
			//back to main menu		
			((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, btmButton)).dispose();			
			new HomeFrame(currUser, "Customer").setVisible(true);
		
		}else if(e.getSource() == backButton) {
			//back 
//			if(prev.equals("Minibus")) {
//    		((HomeFrame)SwingUtilities.getAncestorOfClass(HomeFrame.class, backButton)).desktop.setContentPane(new MinibusFrame());
//    	}
		}else if(e.getSource() == updateButton) {
			//update
			updateData();
		}
	}
}
