import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class ManageVehicle extends JInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTable manageTable;
	private JTextField idTextField;
	private JTextField nameTextField;
	private JTextField priceTextField;
	private JTextField ratingTextField;
	private JTextField categoryIdTextField;
	private Connect con;
	private JButton insertButton, updateButton, deleteButton, backButton;
	private Object[] column = {"Product Id", "Product Name", "Product Price", "Product Rating"};
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	private JCheckBox jcb;
	private String currUser = "";
	private JComboBox categoryName;
	private Vector<String> category;

	public ManageVehicle(String currUser) {
		this.currUser = currUser;
		con = new Connect();

		setBounds(10, 10, 750, 500);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(Color.ORANGE);
		getContentPane().add(leftPanel);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		JPanel headerPanel = new JPanel();
		headerPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		headerPanel.setBackground(Color.ORANGE);
		leftPanel.add(headerPanel);

		JLabel headerLabel = new JLabel("Products");
		headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		headerPanel.add(headerLabel);

		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(new EmptyBorder(5, 10, 25, 10));
		tablePanel.setBackground(Color.ORANGE);
		leftPanel.add(tablePanel);
		tablePanel.setLayout(new BorderLayout(0, 0));

		dtm = new DefaultTableModel(column, 0);
		manageTable = new JTable(dtm);
		manageTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		jsp = new JScrollPane(manageTable);

		manageTable.getTableHeader().setResizingAllowed(false);
		manageTable.getTableHeader().setReorderingAllowed(false);
		manageTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = manageTable.getSelectedRow();
				idTextField.setText("" + manageTable.getValueAt(row, 0));
				nameTextField.setText("" + manageTable.getValueAt(row, 1));
				priceTextField.setText("" + manageTable.getValueAt(row, 2));
				ratingTextField.setText("" + manageTable.getValueAt(row, 3));

			}

		});
		tablePanel.add(jsp);

		select();

		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(Color.ORANGE);
		rightPanel.setBorder(new EmptyBorder(2, 0, 2, 0));
		getContentPane().add(rightPanel);
		rightPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setBackground(Color.ORANGE);
		textFieldPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		rightPanel.add(textFieldPanel);
		textFieldPanel.setLayout(new GridLayout(5, 5, 0, 0));

		JPanel idTextFieldPanel = new JPanel();
		idTextFieldPanel.setBackground(Color.ORANGE);
		idTextFieldPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		textFieldPanel.add(idTextFieldPanel);
		idTextFieldPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel idLabel = new JLabel("Id:");
		idLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		idTextFieldPanel.add(idLabel);

		idTextField = new JTextField();
		idTextFieldPanel.add(idTextField);
		idTextField.setColumns(10);
		idTextField.setEditable(false);

		JPanel categoryIdPanel = new JPanel();
		categoryIdPanel.setBackground(Color.ORANGE);
		categoryIdPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		textFieldPanel.add(categoryIdPanel);
		categoryIdPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		category = new Vector<String>();
		category.add("Minibus");
		category.add("Family Car");
		category.add("Sedan");
		
		categoryName = new JComboBox(category);

		JLabel categoryIdLabel = new JLabel("Category:");
		categoryIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		categoryIdPanel.add(categoryIdLabel);

//		categoryIdTextField = new JTextField();
		categoryIdPanel.add(categoryName);
//		categoryIdTextField.setColumns(10);


		JPanel nameTextfieldPanel = new JPanel();
		nameTextfieldPanel.setBackground(Color.ORANGE);
		nameTextfieldPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		textFieldPanel.add(nameTextfieldPanel);
		nameTextfieldPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		nameTextfieldPanel.add(nameLabel);

		nameTextField = new JTextField();
		nameTextfieldPanel.add(nameTextField);
		nameTextField.setColumns(10);

		JPanel priceTextfieldPanel = new JPanel();
		priceTextfieldPanel.setBackground(Color.ORANGE);
		priceTextfieldPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		textFieldPanel.add(priceTextfieldPanel);
		priceTextfieldPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel priceLabel = new JLabel("Price:");
		priceLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		priceTextfieldPanel.add(priceLabel);

		priceTextField = new JTextField();
		priceTextfieldPanel.add(priceTextField);
		priceTextField.setColumns(10);

		JPanel ratingTextfieldPanel = new JPanel();
		ratingTextfieldPanel.setBackground(Color.ORANGE);
		ratingTextfieldPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		textFieldPanel.add(ratingTextfieldPanel);
		ratingTextfieldPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel ratingLabel = new JLabel("Rating:");
		ratingLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		ratingTextfieldPanel.add(ratingLabel);

		ratingTextField = new JTextField();
		ratingTextfieldPanel.add(ratingTextField);
		ratingTextField.setColumns(10);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.ORANGE);
		buttonPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		rightPanel.add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel insertPanel = new JPanel();
		insertPanel.setBackground(Color.ORANGE);
		insertPanel.setForeground(Color.ORANGE);
		insertPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.add(insertPanel);
		insertPanel.setLayout(new BorderLayout(0, 0));

		insertButton = new JButton("Insert Product");
		insertButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		insertPanel.add(insertButton);

		JPanel updateButtonPanel = new JPanel();
		updateButtonPanel.setBackground(Color.ORANGE);
		updateButtonPanel.setForeground(Color.ORANGE);
		updateButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.add(updateButtonPanel);
		updateButtonPanel.setLayout(new BorderLayout(0, 0));

		updateButton = new JButton("Update Product");
		updateButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		updateButtonPanel.add(updateButton);

		JPanel deleteButtonPanel = new JPanel();
		deleteButtonPanel.setBackground(Color.ORANGE);
		deleteButtonPanel.setForeground(Color.ORANGE);
		deleteButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.add(deleteButtonPanel);
		deleteButtonPanel.setLayout(new BorderLayout(0, 0));

		deleteButton = new JButton("Delete Product");
		deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		deleteButtonPanel.add(deleteButton);

		JPanel backButtonPanel = new JPanel();
		backButtonPanel.setBackground(Color.ORANGE);
		backButtonPanel.setForeground(Color.ORANGE);
		backButtonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		buttonPanel.add(backButtonPanel);
		backButtonPanel.setLayout(new BorderLayout(0, 0));

		backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		backButtonPanel.add(backButton);

		insertButton.addActionListener(this);
		updateButton.addActionListener(this);
		deleteButton.addActionListener(this);
		backButton.addActionListener(this);

		this.setVisible(true);
		setTitle("Manage Vehicle");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == insertButton) {
			//insert
			insert();
		}else if(e.getSource() == updateButton) {
			//update
			update();
		}else if(e.getSource() == deleteButton) {
			//delete
			delete();
		}else if(e.getSource() == backButton) {
			//back
			back();
		}
	}

	private void back() {
		// TODO Auto-generated method stub
		((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, backButton)).dispose();

		new HomeFrame(currUser, "Admin").setVisible(true);
	}

	private void select() {
		dtm = new DefaultTableModel(column,0);
		String querySelect = "SELECT * FROM Product";
		Statement stat;

		try {
			stat = con.getCon().createStatement();
			ResultSet rs = stat.executeQuery(querySelect);

			while(rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(rs.getString("Id"));
				row.add(rs.getString("Name"));
				row.add(rs.getString("Price"));
				row.add(rs.getString("Rating"));
				dtm.addRow(row);

			}
			manageTable.setModel(dtm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isNumeric(String str) {
		String price = priceTextField.getText();
		boolean isNum = false;
		for(int i=0; i<price.length(); i++) {
			if(Character.isDigit(price.charAt(i))) {
				isNum = true;
			}else if(Character.isAlphabetic(price.charAt(i))) {
				isNum = false;
			}
		}
		return isNum;
	}
	
	private boolean isRatingNumeric(String str) {
		String rating = ratingTextField.getText();
		String arrRating[] = rating.split("/10");
		boolean isNum = false;
		for(int i=0; i<arrRating[0].length(); i++) {
			if(Character.isDigit(arrRating[0].charAt(i))) {
				isNum = true;
			}else if(Character.isAlphabetic(arrRating[0].charAt(i))) {
				isNum = false;
			}
		}
		return isNum;
	}

	private boolean isCheck(JCheckBox check) {
		
		boolean isCheck = false;
		if(check.isSelected()) {
			isCheck = true;
		}else {
			isCheck = false;
		}
		return isCheck;

	}

	private void insert() {
		String name = nameTextField.getText();
		String rating = ratingTextField.getText();
		String arrRating[] = rating.split("/10");
		boolean ratingNumeric = isRatingNumeric(arrRating[0]);
		int ratingTemp =0;
//		String categoryId = categoryIdTextField.getText();
		String productId = "";
		String priceStr = priceTextField.getText();
		boolean numeric = isNumeric(priceStr);
		JPanel msgPanel = new JPanel();
		JLabel msg = new JLabel("Are you sure you want to insert?");
		msgPanel.add(msg);

		jcb = new JCheckBox("Do not show this message again");
		boolean isCheck = isCheck(jcb);
		msgPanel.add(jcb);

		String category = categoryName.getSelectedItem().toString();
		String categoryId = "";
		System.out.println(arrRating[0]);
		System.out.println(ratingNumeric + "??");
		
		if(category.equals("Minibus")) {
			categoryId = "1";
		}else if(category.equals("Family Car")) {
			categoryId = "2";
		}else if(category.equals("Sedan")) {
			categoryId = "3";
		}

		if(name.isEmpty() || priceTextField.getText().isEmpty() || rating.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please input name, price, and rating field to insert product!", "Warning", JOptionPane.WARNING_MESSAGE);
		}else if(numeric == false) {
			JOptionPane.showMessageDialog(this, "Price must be numeric!", "Warning", JOptionPane.WARNING_MESSAGE);
		}else if(!rating.endsWith("/10")) {
			JOptionPane.showMessageDialog(this, "Rating must be between 1-10 and ends with '/10'!", "Warning", JOptionPane.WARNING_MESSAGE);
		}else if(ratingNumeric == false) {
			JOptionPane.showMessageDialog(this, "Rating must be between 1-10 and ends with '/10'!", "Warning", JOptionPane.WARNING_MESSAGE);

		} 
		else {
			ratingTemp = Integer.parseInt(arrRating[0]);
			if(ratingTemp < 1 || ratingTemp > 10) {
				JOptionPane.showMessageDialog(this, "Rating must be between 1-10 and ends with '/10'!", "Warning", JOptionPane.WARNING_MESSAGE);
				
			}else {
				int price = Integer.parseInt(priceTextField.getText());
				int option=0;
				System.out.println(isCheck);
				if(!isCheck) {
					option = JOptionPane.showConfirmDialog(null , msgPanel , "Confirmation Dialog" , JOptionPane.OK_CANCEL_OPTION);

				}
				

				String newProductId="";
				String querySelectId = "SELECT Id FROM product WHERE CategoryId = '" + categoryId + "'";
				Statement stat;
				try {
					stat = con.getCon().createStatement();
					ResultSet rs = stat.executeQuery(querySelectId);

					int newId = 1;
					while(rs.next()) {
						//generate new id 
						String id = rs.getString("Id");
						int lastId = Integer.parseInt(id.substring(2, 5));
						if(lastId == newId) {
							newId++;
						}else {
							break;
						}				
					}

					//generate id per category
					if(categoryId.equals("1")) {
						newProductId = "MB" + String.format("%03d", newId);					
					}else if(categoryId.equals("2")) {
						newProductId = "FC" + String.format("%03d", newId);
					}else if(categoryId.equals("3")) {
						newProductId = "SN" + String.format("%03d", newId);
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		

				if(option == JOptionPane.YES_OPTION) {
					System.out.println(newProductId);
					String queryInsert = "INSERT INTO Product VALUES('" + newProductId + "', '" + categoryId + "', '" + name + "', '" + price + "', '" + rating + "')";
					System.out.println(queryInsert);

					try {
						stat = con.getCon().createStatement();
						stat.execute(queryInsert);
						select();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(this, "Inserted Successfully!");
				}	
			}
			
			
								

		}
	}

	private void update() {
		String name = nameTextField.getText();
		String rating = ratingTextField.getText();
		String arrRating[] = rating.split("/10");
		boolean ratingNumeric = isRatingNumeric(arrRating[0]);
		int ratingTemp =0;
//		String categoryId = categoryIdTextField.getText();
		String productId = idTextField.getText();
		String priceStr = priceTextField.getText();
		boolean numeric = isNumeric(priceStr);

		JPanel msgPanel = new JPanel();
		JLabel msg = new JLabel("Are you sure you want to update?");
		msgPanel.add(msg);

		JCheckBox jcb = new JCheckBox("Do not show this message again");
		msgPanel.add(jcb);

		if(name.isEmpty() || priceTextField.getText().isEmpty() || rating.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please input all field to update product!", "Warning", JOptionPane.WARNING_MESSAGE);
		}else if(numeric == false) {
			JOptionPane.showMessageDialog(this, "Price must be numeric!", "Warning", JOptionPane.WARNING_MESSAGE);
		}else if(!rating.endsWith("/10")) {
			JOptionPane.showMessageDialog(this, "Rating must be between 1-10 and ends with '/10'!", "Warning", JOptionPane.WARNING_MESSAGE);
		}else if(ratingNumeric == false) {
			JOptionPane.showMessageDialog(this, "Rating must be between 1-10 and ends with '/10'!", "Warning", JOptionPane.WARNING_MESSAGE);

		} 
		else {
			ratingTemp = Integer.parseInt(arrRating[0]);
			if(ratingTemp < 1 || ratingTemp > 10) {
				JOptionPane.showMessageDialog(this, "Rating must be between 1-10 and ends with '/10'!", "Warning", JOptionPane.WARNING_MESSAGE);
			}else {
				int price = Integer.parseInt(priceTextField.getText());
				int option = JOptionPane.showConfirmDialog(null , msgPanel , "some title" , JOptionPane.OK_CANCEL_OPTION);
				
				if(option == JOptionPane.YES_OPTION) {
					String queryUpdate = "UPDATE product SET Name = '" + name + "', Price = '" + price + "', Rating = '" + rating +"' WHERE Id='" + productId +"'";
					System.out.println(queryUpdate);
					Statement stat;
					try {
						stat = con.getCon().createStatement();
						stat.executeUpdate(queryUpdate);
						select();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(this, "Updated Successfully!");
				}
				
			}
		}		
	}

	private void delete() {
		String productId = idTextField.getText();

		JPanel msgPanel = new JPanel();
		JLabel msg = new JLabel("Are you sure you want to delete?");
		msgPanel.add(msg);

		JCheckBox jcb = new JCheckBox("Do not show this message again");
		msgPanel.add(jcb);

		if(productId.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Id field must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);

		}else {
			int option = JOptionPane.showConfirmDialog(null , msgPanel , "some title" , JOptionPane.OK_CANCEL_OPTION);

			if(option == JOptionPane.YES_OPTION) {
				String queryDelete = "DELETE FROM Product WHERE Id = '" + productId + "'";
				Statement stat;
				try {
					stat = con.getCon().createStatement();
					stat.executeUpdate(queryDelete);
					select();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(this, "Deleted Successfully!");
			}
		}	
	}
}
