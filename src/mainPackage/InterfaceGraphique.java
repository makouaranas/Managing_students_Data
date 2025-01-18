package mainPackage;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.AbstractBorder;
import javax.swing.table.DefaultTableModel;


public class InterfaceGraphique extends JFrame implements userGraphic{
	
	private ImageIcon appLogo;
	public InterfaceGraphique() {
		this.setTitle(" Gestion des Étudiants (beta)");
		appLogo = new ImageIcon("resources/AppLogo.png");
		this.setIconImage(appLogo.getImage());
		this.setSize(1000,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		  
	}
	/*----------------------------Cette class permet de cree rounde border------------------------------------*/
	

	class RoundedBorder extends AbstractBorder {
	    /**
		 * 
		 */
		private static final long serialVersionUID = -5260830134994952220L;
		private int radius;

	    public RoundedBorder(int radius) {
	        this.radius = radius;
	        
	    }

	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        Graphics2D g2d = (Graphics2D) g.create();
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setColor(c.getForeground());
	        g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, radius, radius));
	        g2d.dispose();
	        g2d.setColor(c.getForeground());
	   
	    }

	    @Override
	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
	    }

	    @Override
	    public Insets getBorderInsets(Component c, Insets insets) {
	        insets.left = insets.right = insets.top = insets.bottom = this.radius + 1;
	        return insets;
	    }
	    
	}
	
	/*------------------------------------------------------------------*/
	@Override
	public void getAccueil() {
		StudentsDatabase st =new StudentsDatabase();
		
		Color sideMenuColor = new Color(0xB6B6B6);
		JPanel homePanel = new JPanel(new BorderLayout());
		JPanel sideMenu = new JPanel(new BorderLayout());
		JPanel dashboardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,50));
		
		JPanel optionSideMenu = new JPanel(new GridLayout(6, 1));
		JTextField searchBar = new JTextField();
		JButton searchButton = new JButton("Search");
		JRadioButton choixCne = new  JRadioButton("Cne");
		JRadioButton choixNom = new  JRadioButton("Nom");
		JRadioButton choixPrenom = new  JRadioButton("Prenom");
		JRadioButton choixEmail = new  JRadioButton("E-mail");
		JPanel choix =new JPanel(new FlowLayout(FlowLayout.CENTER,40,0));
		ButtonGroup groupRadio = new ButtonGroup();
		
		String[] columnNames = {"Code Massar", "Penom", "Nom", "Date de Naissance", "E-mail"};

		
		
		groupRadio.add(choixCne);
		groupRadio.add(choixNom);
		groupRadio.add(choixPrenom);
		groupRadio.add(choixEmail);
		
		choix.add(choixCne);
		choix.add(choixPrenom);
		choix.add(choixNom);
		choix.add(choixEmail);
		choix.setOpaque(false);
		
		searchBar.setBorder(new RoundedBorder(10));
		searchBar.setFont(new Font("sans-serif", Font.BOLD,20));
		searchBar.setPreferredSize(new Dimension(500,50));
		searchBar.setOpaque(false);
		
		
		searchButton.setFocusable(false);
		searchButton.setOpaque(false);
		searchButton.setBackground(new Color(0x4c4c4c));
		searchButton.setBorder(new RoundedBorder(18));
		searchButton.setPreferredSize(new Dimension(80,40));
		searchButton.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				String value = searchBar.getText();
				String choose="";
				if(choixCne.isSelected()) {
					choose ="Code Massar";
				}else if(choixNom.isSelected()) {
					choose="nom";
				}else if(choixPrenom.isSelected()) {
					choose="prenom";
				}else if(choixEmail.isSelected()) {
					choose="E-mail";
									
				}else {
					JOptionPane.showMessageDialog(null, "Selectione un choix");
					return;
				}
				/*#########################################################################*/
				
				  StudentsDatabase dbManager = new StudentsDatabase();
			        ArrayList<Students> students = dbManager.rechercherEtudiant(choose,value);

			        // Convert ArrayList<Student> to a 2D array
			        Object[][] data = new Object[students.size()][5];
			        for (int i = 0; i < students.size(); i++) {
			            Students student = students.get(i);
			            data[i][0] = student.getCne();
			            data[i][1] = student.getFirstName();
			            data[i][2] = student.getLastName();
			            data[i][3] = student.getDate();
			            data[i][4] = student.getEmail();
			        }

			        // Create the table with the data and column names
			        JTable table = new JTable(data, columnNames);
			        JScrollPane scrollPane = new JScrollPane(table);
			        dashboardPanel.add(table);
			        table.setAutoCreateRowSorter(true); // Enable row sorting
			        // Add the scroll pane to the frame
			        add(scrollPane, BorderLayout.CENTER);
			    
				
				/*############################################################################*/
			       
				
		}});
		
		dashboardPanel.add(searchBar);
		dashboardPanel.add(searchButton);
		dashboardPanel.add(choix);
		//dashboardPanel.add(table);
		
		sideMenu.setBackground(sideMenuColor);
		dashboardPanel.setBackground(new Color(0xCECECE));
		optionSideMenu.setBackground(sideMenuColor);
		sideMenu.setPreferredSize(new Dimension(300, 1000));

		ImageIcon logoIcon = new ImageIcon("resources/logocompressed.png");
		Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
		sideMenu.add(logoLabel, BorderLayout.NORTH);

		ImageIcon homeIcon = new ImageIcon("resources/home.png");
		ImageIcon addIcon = new ImageIcon("resources/add-user.png");
		ImageIcon showIcon = new ImageIcon("resources/to-do-list.png");

		Image scaledHomeIcon = homeIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Image scaledAddIcon = addIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Image scaledShowIcon = showIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

		JLabel homeOption = new JLabel("Accueil", new ImageIcon(scaledHomeIcon), SwingConstants.CENTER);
		JLabel addStudentOption = new JLabel("Ajouter Etudiant", new ImageIcon(scaledAddIcon), SwingConstants.CENTER);
		JLabel showStudentsOption = new JLabel("Affichier Etudiant", new ImageIcon(scaledShowIcon), SwingConstants.CENTER);
		

		homeOption.setIconTextGap(25);
		addStudentOption.setIconTextGap(25);
		showStudentsOption.setIconTextGap(25);

		optionSideMenu.add(homeOption);
		optionSideMenu.add(addStudentOption);
		optionSideMenu.add(showStudentsOption);
		sideMenu.add(optionSideMenu, BorderLayout.CENTER);

		homePanel.add(sideMenu, BorderLayout.WEST);
		homePanel.add(dashboardPanel, BorderLayout.CENTER);
		// Set content pane
		this.setContentPane(homePanel);
		this.revalidate();
		this.repaint();
		
		
	}

	@Override
	public void getListEtudiants() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void getAjouterEtudiant() {
		StudentsDatabase studentdb = new StudentsDatabase();
		JTextField codeMassarField, prenomField, nomField, dateNaissanceField, emailField;
	    JButton submitButton;
	    JButton backButton;
	    JLabel vide;
	    backButton = new JButton("← Back");
	    backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getAccueil();
				
			}
		});
	  
	       	        // Create a panel with GridBagLayout
	    JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10)); // 6 rows, 2 columns, 10px horizontal and vertical gaps	
	      panel.add(backButton);
	      
	      panel.add(vide = new JLabel());
	      
        // Set a larger font
        Font largerFont = new Font("Arial", Font.PLAIN, 16);

        // Add labels and text fields
        JLabel codeMassarLabel = new JLabel("Code Massar:");
        codeMassarLabel.setFont(largerFont);
        panel.add(codeMassarLabel);

        codeMassarField = new JTextField();
        codeMassarField.setFont(largerFont);
        panel.add(codeMassarField);

        JLabel prenomLabel = new JLabel("Prenom:");
        prenomLabel.setFont(largerFont);
        panel.add(prenomLabel);

        prenomField = new JTextField();
        prenomField.setFont(largerFont);
        panel.add(prenomField);

        JLabel nomLabel = new JLabel("Nom:");
        nomLabel.setFont(largerFont);
        panel.add(nomLabel);

        nomField = new JTextField();
        nomField.setFont(largerFont);
        panel.add(nomField);

        JLabel dateNaissanceLabel = new JLabel("Date de Naissance:");
        dateNaissanceLabel.setFont(largerFont);
        panel.add(dateNaissanceLabel);

        dateNaissanceField = new JTextField();
        dateNaissanceField.setFont(largerFont);
        panel.add(dateNaissanceField);

        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setFont(largerFont);
        panel.add(emailLabel);

        emailField = new JTextField();
        emailField.setFont(largerFont);
        panel.add(emailField);

        // Add an empty label for spacing
        panel.add(new JLabel());

        // Add submit button
        submitButton = new JButton("Submit");
        submitButton.setFont(largerFont);
        panel.add(submitButton);
  

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);
	
	}

	@Override
	public void getModifierEtudiant() {
			
		
	}

}
