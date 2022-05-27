import javax.swing.BoxLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JInternalFrame {
	 private static final long serialVersionUID1 = 1L;
	private JTable mainMenuTable;
	private JScrollPane jsp;
	private String category;
	private String role;
	private int id;
	private Connect con;
	private DefaultTableModel dtm;
	private Object[] column = {"Product Id", "Product Name", "Product Price", "Product Rating"};
	private JLabel lblNewLabel_1;
	
	public MainMenu(String category, String currRole) {
		this.category = category;
		this.role = currRole;
		
		if(this.category.equals("Minibus")) {
			id = 1;
		}else if(this.category.equals("FamilyCar")) {
			id = 2;
		}else if(this.category.equals("Sedan")) {
			id = 3;
		}
		con = new Connect();
		  setBackground(Color.ORANGE);
		  getContentPane().setBackground(Color.ORANGE);
		  getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
//		  setBorder(new EmptyBorder(5, 5, 5, 5));
		  
		  JPanel labelPanel = new JPanel();
		  labelPanel.setBackground(Color.ORANGE);
		  labelPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		  getContentPane().add(labelPanel);
		  labelPanel.setLayout(new GridLayout(0, 1, 0, 5));
		  if(role.equals("Admin")) {
			  JLabel headerLabel = new JLabel("Welcome, Admin!");
			  headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
			  headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			  labelPanel.add(headerLabel);
			  setTitle("Admin Main Menu");
			  
		  }else if(role.equals("Customer")){
			  JLabel headerLabel = new JLabel("Welcome to SewaRumah!");
			  headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
			  headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
			  labelPanel.add(headerLabel);
			  setTitle("Main Menu");
		  }
		  
		  if(id == 1) {
			  lblNewLabel_1 = new JLabel("Minibus");
			  lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
			  lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			  labelPanel.add(lblNewLabel_1);			  
		  }else if(id == 2) {
			  lblNewLabel_1 = new JLabel("Family Car");
			  lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
			  lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			  labelPanel.add(lblNewLabel_1);
		  }else if(id == 3) {
			  lblNewLabel_1 = new JLabel("Sedan");
			  lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
			  lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			  labelPanel.add(lblNewLabel_1);
		  }
		
		JPanel mainMenuTablePanel = new JPanel();
		mainMenuTablePanel.setBackground(Color.ORANGE);
		mainMenuTablePanel.setBorder(new EmptyBorder(10, 15, 10, 15));
		getContentPane().add(mainMenuTablePanel);
		mainMenuTablePanel.setLayout(new BoxLayout(mainMenuTablePanel, BoxLayout.X_AXIS));
//		getContentPane().add(mainMenuTablePanel, BorderLayout.SOUTH);
		
		dtm = new DefaultTableModel(column, 0);
		mainMenuTable = new JTable(dtm);
		jsp = new JScrollPane(mainMenuTable);
		mainMenuTable.getTableHeader().setResizingAllowed(false);
		mainMenuTable.getTableHeader().setReorderingAllowed(false);
		  mainMenuTable.getColumnModel().getColumn(0).setResizable(false);
		  mainMenuTable.getColumnModel().getColumn(1).setResizable(false);
		  mainMenuTable.getColumnModel().getColumn(2).setResizable(false);
		  mainMenuTable.getColumnModel().getColumn(3).setResizable(false);
		  mainMenuTable.setBorder(new EmptyBorder(50, 50, 50, 50));
		  setBounds(100, 100, 550, 400);
		
		mainMenuTablePanel.add(jsp);
		
		add(mainMenuTablePanel);

		selectData();
		setBounds(100, 100, 450, 300);
		
		

		
		setVisible(true);
	}
	
	
	
	private void selectData() {
		String querySelect = "SELECT * FROM Product WHERE CategoryId =" + id;
		Statement stat;
		try {
			stat = con.getCon().createStatement();
			ResultSet rs = stat.executeQuery(querySelect);
			
			while(rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(rs.getString("Id"));
				row.add(rs.getString("Name"));
				row.add(rs.getInt("Price"));
				row.add(rs.getString("Rating"));
				dtm.addRow(row);
			}
			mainMenuTable.setModel(dtm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}