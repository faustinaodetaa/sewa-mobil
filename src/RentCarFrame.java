import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class RentCarFrame extends JInternalFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField idField;
    private JTable myTable;
    private JLabel titleLbl, idLbl, quantityLbl;
    private JButton atcButton, btmButton;
    private JSpinner spinner;
    private JPanel leftPanel, formPanel, buttonPanel, rightPanel, mainPanel;
    private DefaultTableModel dtm;
    private Object[] column = {"Product Id", "Product Name", "Product Price", "Product Rating"};
	private JScrollPane jsp;
	private Connect con;
	private String currFrame;
	private JDesktopPane desktop;
	private String currRole="Customer";
	Image bgImg;
	private ImageIcon background;
	private JLabel imageLabel;
	private String currUser = "";
	private String category = "";

    public RentCarFrame(String currUser, String category){
    	this.currUser = currUser;
    	this.category = category;
    	con = new Connect();
        this.setSize(400,400);
        this.setVisible(true);
        this.setTitle("Rent Car");

        getContentPane().setBackground(Color.ORANGE);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        
        JPanel leftPanel = new JPanel();
        getContentPane().add(leftPanel);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        
        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        headerPanel.setBackground(Color.ORANGE);
        leftPanel.add(headerPanel);
        
        if(category.equals("Minibus")) {
        	JLabel headerLabel = new JLabel("Minibus");
            headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
            headerPanel.add(headerLabel);
		}else if(category.equals("FamilyCar")) {
			JLabel headerLabel = new JLabel("Family Car");
	        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
	        headerPanel.add(headerLabel);
		}else if(category.equals("Sedan")) {
			JLabel headerLabel = new JLabel("Sedan");
	        headerLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
	        headerPanel.add(headerLabel);
		}
        
        
        
        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(new EmptyBorder(10, 10, 25, 10));
        tablePanel.setBackground(Color.ORANGE);
        leftPanel.add(tablePanel);
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));

       

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
				idField.setText("" + myTable.getValueAt(row, 0));

				
			}
		});
           
        tablePanel.add(jsp);
        
        select();

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.ORANGE);
        rightPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(rightPanel);
        rightPanel.setLayout(new GridLayout(2, 1, 0, 15));
        
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.setBackground(Color.ORANGE);
        rightPanel.add(textFieldPanel);
        textFieldPanel.setLayout(new GridLayout(2, 0, 0, 5));
        
        JPanel productIdPanel = new JPanel();
        productIdPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        productIdPanel.setBackground(Color.ORANGE);
        textFieldPanel.add(productIdPanel);
        productIdPanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel productIdLabel = new JLabel("Product Id:");
        productIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        productIdPanel.add(productIdLabel);
        
        idField = new JTextField();
        productIdPanel.add(idField);
        idField.setColumns(10);
        
        JPanel quantityPanel = new JPanel();
        quantityPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        quantityPanel.setBackground(Color.ORANGE);
        textFieldPanel.add(quantityPanel);
        quantityPanel.setLayout(new GridLayout(0, 2, 0, 0));
        
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        quantityPanel.add(quantityLabel);
        
        spinner = new JSpinner();
        quantityPanel.add(spinner);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.ORANGE);
        rightPanel.add(buttonPanel);
        buttonPanel.setLayout(new GridLayout(2, 1, 0, 0));
        
        JPanel atcPanel = new JPanel();
        atcPanel.setBackground(Color.ORANGE);
        atcPanel.setBorder(new EmptyBorder(20, 5, 20, 5));
        buttonPanel.add(atcPanel);
        atcPanel.setLayout(new BorderLayout(0, 0));
        
        atcButton = new JButton("Add to Cart");
        atcPanel.add(atcButton);
        atcButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        
        JPanel btmPanel = new JPanel();
        btmPanel.setBackground(Color.ORANGE);
        btmPanel.setBorder(new EmptyBorder(20, 5, 20, 5));
        buttonPanel.add(btmPanel);
        btmPanel.setLayout(new BorderLayout(0, 0));
        
        btmButton = new JButton("Back to Main Menu");
        btmPanel.add(btmButton);
        btmButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        setBounds(10, 100, 750, 400);
        
        atcButton.addActionListener(this);
        btmButton.addActionListener(this);

        this.setVisible(true);
    }
    
    private void select() {
		dtm = new DefaultTableModel(column,0);
		String querySelect = "";
		if(category.equals("Minibus")) {
			querySelect = "SELECT * FROM Product WHERE CategoryId = '" + 1 + "'";
			
		}else if(category.equals("FamilyCar")) {
			querySelect = "SELECT * FROM Product WHERE CategoryId = '" + 2 + "'";

		}else if(category.equals("Sedan")) {
			querySelect = "SELECT * FROM Product WHERE CategoryId = '" + 3 + "'";

		}
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
			myTable.setModel(dtm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == atcButton) {
			addToCart();
		}else if(e.getSource() == btmButton) {
			((JFrame)SwingUtilities.getAncestorOfClass(JFrame.class, btmButton)).dispose();
			
			new HomeFrame(currUser, "Customer").setVisible(true);
		}
	}
	
	private void addToCart() {
		String productId = idField.getText();
		
		if(productId.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Id must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
		}else {
			System.out.println(spinner.getValue().toString());
			int qty = Integer.parseInt(spinner.getValue().toString());
			if(qty < 1) {
				JOptionPane.showMessageDialog(this, "Quantity cannot be less than 1!", "Warning", JOptionPane.WARNING_MESSAGE);
			}else {
				String querySelect = "SELECT * FROM cart WHERE ProductId = '" + productId + "' AND UserId = '" + this.currUser + "'";
				Statement stat;
				try {
					stat = con.getCon().createStatement();
					ResultSet rs = stat.executeQuery(querySelect);
					if(rs.next()) {
						String tempProductId = rs.getString("ProductId");
						int tempQty = rs.getInt("Quantity");
						if(tempProductId.equals(productId)) {
							
							System.out.println(tempQty);
							String queryUpdate = "UPDATE cart SET Quantity = " + (tempQty+qty) + " WHERE ProductId = '" + productId + "'";
							
							stat.executeUpdate(queryUpdate);
							select();
							JOptionPane.showMessageDialog(this, "Cart Updated!");
						}
					}else {
						//insert new
						String queryInsert = "INSERT INTO cart VALUES('" + productId + "', '" + this.currUser + "', '" + qty + "')"; 
						
						try {
							
							stat.execute(queryInsert);
							select();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(this, "Added to Cart!");				
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}