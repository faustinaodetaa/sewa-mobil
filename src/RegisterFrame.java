import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class RegisterFrame extends JInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextField usernameTextField, phoneNumberTextField, nameTextField;
	private JPasswordField passwordField;
	private JRadioButton maleRadioButton, femaleRadioButton;
	private ButtonGroup genderGroup;
	private JComboBox dayComboBox,monthComboBox,yearComboBox, roleComboBox;
	private JButton registerButton, backButton;
	private JTextArea addressTextArea;
	private Vector<Integer> day;
	private Vector<Integer> month;
	private Vector<Integer> year;
	private Vector<String> role;
	private Connect con;
	
    JPanel panelTop = new JPanel();
	JPanel panelCent = new JPanel(new GridLayout(10,2));
	JPanel panelBot = new JPanel();

	public RegisterFrame() {
		con = new Connect();

//		try {
//			this.setMaximum(true);
//		} catch (PropertyVetoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        this.setVisible(true);
//        this.setSize(600, 1000);
        this.setTitle("Register Frame");

        getContentPane().setBackground(Color.ORANGE);
        getContentPane().setLayout(new GridLayout(10, 1, 0, 0));
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(6, 10, 6, 10));
        titlePanel.setBackground(Color.ORANGE);
        getContentPane().add(titlePanel);
        
        JLabel titleLabel = new JLabel("Register Form");
        titlePanel.add(titleLabel);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
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
        
        JPanel genderPanel = new JPanel();
        genderPanel.setBorder(new EmptyBorder(6, 10, 6, 10));
        genderPanel.setBackground(Color.ORANGE);
        getContentPane().add(genderPanel);
        genderPanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        genderPanel.add(genderLabel);
        
        JPanel genderChoicePanel = new JPanel();
        genderPanel.add(genderChoicePanel);
        genderChoicePanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        maleRadioButton = new JRadioButton("Male");
        maleRadioButton.setBackground(Color.ORANGE);
        maleRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        genderChoicePanel.add(maleRadioButton);
        
        femaleRadioButton = new JRadioButton("Female");
        femaleRadioButton.setBackground(Color.ORANGE);
        femaleRadioButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        genderChoicePanel.add(femaleRadioButton);
        
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        
        JPanel dobPanel = new JPanel();
        dobPanel.setBorder(new EmptyBorder(6, 10, 6, 10));
        dobPanel.setBackground(Color.ORANGE);
        getContentPane().add(dobPanel);
        dobPanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        dobPanel.add(dobLabel);
        
        JPanel dobChoicePanel = new JPanel();
        dobPanel.add(dobChoicePanel);
        dobChoicePanel.setLayout(new GridLayout(0, 3, 0, 0));
        
        day = new Vector<Integer>();
        for(int i = 1; i <= 31; i++) {
         day.add(i);
        }

        month = new Vector<Integer>();
        for(int i = 1; i <= 12; i++) {
         month.add(i);
        }
        
        int yearNow = Year.now().getValue();
        year = new Vector<Integer>();
        for(int i = 1900; i <= yearNow; i++) { 
         year.add(i);
        }
        
        dayComboBox = new JComboBox(day);
        dobChoicePanel.add(dayComboBox);
        
        monthComboBox = new JComboBox(month);
        dobChoicePanel.add(monthComboBox);
        
        yearComboBox = new JComboBox(year);
        dobChoicePanel.add(yearComboBox);
        
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
        
        JPanel rolePanel = new JPanel();
        rolePanel.setBorder(new EmptyBorder(6, 10, 6, 10));
        rolePanel.setBackground(Color.ORANGE);
        getContentPane().add(rolePanel);
        rolePanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        rolePanel.add(roleLabel);
        
        role = new Vector<String>();
        
        role.add("Customer");
        role.add("Admin");
        
        roleComboBox = new JComboBox(role);
        rolePanel.add(roleComboBox);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(6, 10, 6, 10));
        buttonPanel.setBackground(Color.ORANGE);
        getContentPane().add(buttonPanel);
        buttonPanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        backButton = new JButton("Back");
        backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        buttonPanel.add(backButton);
        
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        buttonPanel.add(registerButton);

        setBounds(100, 0, 450, 500);
		
		registerButton.addActionListener(this);
		backButton.addActionListener(this);

		
	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == registerButton) {
			register();
		}else if(e.getSource() == backButton) {
			this.dispose();
			this.setVisible(false);
			new MainFrame();
		}
		
	}
	
	public boolean isUnique(String username) {
		boolean usernameFound = false;
		String queryUsername = "SELECT Username FROM user";
		Statement stat;
		try {
			stat = con.getCon().createStatement();
			ResultSet rs = stat.executeQuery(queryUsername);
			while(rs.next()) {
				String usernameData = rs.getString("Username");
				if(username.equals(usernameData)) {
					usernameFound = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usernameFound;
	}
	
	public String getGender()
	{
	    if(maleRadioButton.isSelected())
	    {
	        return maleRadioButton.getText();
	    }
	    else if(femaleRadioButton.isSelected())
	    {
	        return femaleRadioButton.getText();
	    }
	    else
	    {
	        return null;
	    }
	}
	
	public void register(){
		String uname = usernameTextField.getText();
		String password = String.valueOf(passwordField.getPassword());
		String name = nameTextField.getText();
		boolean male = maleRadioButton.isSelected();
		boolean female = femaleRadioButton.isSelected();
		String phone = phoneNumberTextField.getText();
		String address = addressTextArea.getText();
		String role = roleComboBox.getSelectedItem().toString();
		String dayString = dayComboBox.getSelectedItem().toString();
		int day = Integer.parseInt(dayString);
		String monthString = monthComboBox.getSelectedItem().toString();
		int month = Integer.parseInt(monthString);
		String yearString = yearComboBox.getSelectedItem().toString();
		int year = Integer.parseInt(yearString);
		boolean usernameFound = isUnique(uname);

		LocalDate birthdate = LocalDate.of(year, month, day);
        LocalDate now = LocalDate.now();
        
        
        long age = ChronoUnit.YEARS.between(birthdate, now);
//        System.out.println(age);

        String gender = getGender();
        
        ZoneId systemTimeZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = birthdate.atStartOfDay(systemTimeZone);
        Date newBirthdate = Date.from(zonedDateTime.toInstant());
        
        String id="";
		try {
			String queryID = "SELECT Id FROM user ORDER BY Id DESC LIMIT 1";
			Statement stat;
			stat = con.getCon().createStatement();
			ResultSet rs = stat.executeQuery(queryID);
			rs.next();
			id = rs.getString("Id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int lastId;
		lastId = Integer.parseInt(id.substring(2, 5));
		String newId = "US"+String.format("%03d",lastId+1);
        
		if(uname.isEmpty() || password.isEmpty() || name.isEmpty() || (!male && !female) || phone.isEmpty() || address.isEmpty()){
			JOptionPane.showMessageDialog(this, "All field must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
		}else if(usernameFound) {
			JOptionPane.showMessageDialog(this, "Username are already taken, please insert another username!", "Warning", JOptionPane.WARNING_MESSAGE);
		}else if(address.length() < 10 || address.length() > 25) {
			JOptionPane.showMessageDialog(this, "Address must be between 10 and 25 characters", "Warning", JOptionPane.WARNING_MESSAGE);
		}else if(age < 17) {
			JOptionPane.showMessageDialog(this, "You must be 17 years old or older to create account! We're sorry for the inconvenience.", "Warning", JOptionPane.WARNING_MESSAGE);
			//dob 17 years old, bikin id nya
		}
		else {
			String queryInsert = "INSERT INTO user VALUES('" + newId +"', '" + name + "', '" + gender + "', '" + address + "', '" + birthdate + "', '" + phone + "', '" + uname + "', '" + password + "', '" + role +"')";
			System.out.println(queryInsert);
			try {
				Statement stat = con.getCon().createStatement();
				stat.execute(queryInsert);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(this, "Registered Successfully!");
//			this.dispose();
			((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, registerButton)).dispose();
			
			this.setVisible(false);
			new MainFrame();
			
		}
	}



	}
