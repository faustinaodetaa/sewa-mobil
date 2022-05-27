import javax.swing.JInternalFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class ViewCartFrame extends JInternalFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTable myTable;
	private JTextField textField;
	private DefaultTableModel dtm;
	private Object[] column = {"Product Id", "Product Name", "Product Price", "Product Rating", "Quantity"};
	private JScrollPane jsp;
	private Connect con;
	private String currUser = "";
	private JButton rentAllProductButton, deleteProductButton, clearCartButton, backButton;

	public ViewCartFrame(String currUser) {
		this.currUser = currUser;
		con = new Connect();


		getContentPane().setBackground(Color.ORANGE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
		leftPanel.setBackground(Color.ORANGE);
		getContentPane().add(leftPanel);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		this.setTitle("View Cart");

		dtm = new DefaultTableModel(column, 0);
		myTable = new JTable(dtm);
		myTable.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		jsp = new JScrollPane(myTable);

		myTable.getTableHeader().setResizingAllowed(false);
		myTable.getTableHeader().setReorderingAllowed(false);
		myTable.addMouseListener(new MouseListener() {

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
				int row = myTable.getSelectedRow();
				textField.setText("" + myTable.getValueAt(row, 0));
			}
		});
		leftPanel.add(jsp);
		select();



		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		rightPanel.setBackground(Color.ORANGE);
		getContentPane().add(rightPanel);
		rightPanel.setLayout(new GridLayout(5, 0, 0, 0));

		JPanel productIdPanel = new JPanel();
		productIdPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		productIdPanel.setBackground(Color.ORANGE);
		rightPanel.add(productIdPanel);
		productIdPanel.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel producIdLabel = new JLabel("Product Id:");
		producIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		productIdPanel.add(producIdLabel);

		textField = new JTextField();
		productIdPanel.add(textField);
		textField.setColumns(10);
		textField.setEditable(false);

		JPanel rentAllProductPanel = new JPanel();
		rentAllProductPanel.setBorder(new EmptyBorder(15, 10, 10, 10));
		rentAllProductPanel.setBackground(Color.ORANGE);
		rightPanel.add(rentAllProductPanel);
		rentAllProductPanel.setLayout(new BorderLayout(0, 0));

		rentAllProductButton = new JButton("Rent All Product");
		rentAllProductButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		rentAllProductPanel.add(rentAllProductButton);

		JPanel deleteProductPanel = new JPanel();
		deleteProductPanel.setBorder(new EmptyBorder(15, 10, 10, 10));
		deleteProductPanel.setBackground(Color.ORANGE);
		rightPanel.add(deleteProductPanel);
		deleteProductPanel.setLayout(new BorderLayout(0, 0));

		deleteProductButton = new JButton("Delete Product");
		deleteProductButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		deleteProductPanel.add(deleteProductButton);

		JPanel clearCartPanel = new JPanel();
		clearCartPanel.setBorder(new EmptyBorder(15, 10, 10, 10));
		clearCartPanel.setBackground(Color.ORANGE);
		rightPanel.add(clearCartPanel);
		clearCartPanel.setLayout(new BorderLayout(0, 0));

		clearCartButton = new JButton("Clear Cart");
		clearCartButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		clearCartPanel.add(clearCartButton);

		JPanel backPanel = new JPanel();
		backPanel.setBorder(new EmptyBorder(15, 10, 10, 10));
		backPanel.setBackground(Color.ORANGE);
		rightPanel.add(backPanel);
		backPanel.setLayout(new BorderLayout(0, 0));

		backButton = new JButton("Back to Main Menu");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		backPanel.add(backButton);
		setBounds(10, 100, 750, 400);

		rentAllProductButton.addActionListener(this);
		deleteProductButton.addActionListener(this);
		clearCartButton.addActionListener(this);
		backButton.addActionListener(this);


		this.setVisible(true);

	}


	private void select() {
		dtm = new DefaultTableModel(column,0);
		String querySelect = "SELECT * FROM cart c, product p WHERE c.ProductId = p.Id";
		Statement stat;

		try {
			stat = con.getCon().createStatement();
			ResultSet rs = stat.executeQuery(querySelect);

			while(rs.next()) {
				Vector<Object> row = new Vector<>();
				row.add(rs.getString("ProductId"));
				row.add(rs.getString("Name"));
				row.add(rs.getString("Price"));
				row.add(rs.getString("Rating"));
				row.add(rs.getString("Quantity"));
				dtm.addRow(row);

			}
			myTable.setModel(dtm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == rentAllProductButton) {
			//rent all product
			rentAllProduct();
		}else if(e.getSource() == deleteProductButton) {
			//delete product
			deleteProduct();
		}else if(e.getSource() == clearCartButton) {
			//clear cart
			clearCart();
		}else if(e.getSource() == backButton) {
			//back button
			back();
		}
	}

	private void rentAllProduct() {
		String querySelect = "SELECT * FROM cart c, product p WHERE c.ProductId = p.Id";
		Statement stat;

		try {
			stat = con.getCon().createStatement();
			ResultSet rs = stat.executeQuery(querySelect);

			if(!rs.next()) {
				JOptionPane.showMessageDialog(this, "Cart must not be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			}else {
				int tempTotal=0;
				do {
					int tempPrice = rs.getInt("Price");
					int tempQty = rs.getInt("Quantity");
					tempTotal += tempPrice * tempQty;
					System.out.println(tempPrice);
					System.out.println(tempQty);
				}while(rs.next());

				int option = JOptionPane.showConfirmDialog(this, "Dear User, the total price is " + tempTotal + ". Would you want to continue to rent all products?");

				if(option == JOptionPane.YES_OPTION) {
					String queryDelete = "DELETE FROM cart WHERE UserId = '" + this.currUser + "'";
					stat = con.getCon().createStatement();
					stat.executeUpdate(queryDelete);
					select();
					JOptionPane.showMessageDialog(this, "Successfully rent all product!");
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteProduct() {
		String id = textField.getText();
		String querySelect = "SELECT * FROM cart c, product p WHERE c.ProductId = p.Id";
		Statement stat;

		try {
			stat = con.getCon().createStatement();
			ResultSet rs = stat.executeQuery(querySelect);

			if(!rs.next()) {
				JOptionPane.showMessageDialog(this, "Cart must not be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			}else if(id.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Id cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);			
			}else {

				int option = JOptionPane.showConfirmDialog(this, "Do you want to delete the product?");
				if(option == JOptionPane.YES_OPTION) {
					String queryDelete = "DELETE FROM cart WHERE ProductId = '" + id + "'";

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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	private void clearCart(){
		String querySelect = "SELECT * FROM cart c, product p WHERE c.ProductId = p.Id";
		Statement stat;

		try {
			stat = con.getCon().createStatement();
			ResultSet rs = stat.executeQuery(querySelect);

			if(!rs.next()) {
				JOptionPane.showMessageDialog(this, "Cart must not be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			}else {

				int option = JOptionPane.showConfirmDialog(this, "Do you want to clear all products?");

				if(option == JOptionPane.YES_OPTION) {
					String queryDelete = "DELETE FROM cart WHERE UserId = '" + this.currUser + "'";
					stat = con.getCon().createStatement();
					stat.executeUpdate(queryDelete);
					select();
					JOptionPane.showMessageDialog(this, "Successfully clear all product!");
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void back() {
		((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, backButton)).dispose();

		new HomeFrame(currUser, "Customer").setVisible(true);
	}

}
