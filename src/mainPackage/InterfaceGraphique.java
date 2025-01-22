package mainPackage;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.Font;


import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.PageAttributes.OrientationRequestedType;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;



import javax.swing.BorderFactory;


import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttribute;
import javax.print.attribute.PrintRequestAttributeSet;

import javax.swing.Box;
import javax.swing.BoxLayout;
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

import javax.swing.table.JTableHeader;




public class InterfaceGraphique extends JFrame implements userGraphic{
	

	private String newCne;
	private String newFirstName;
	private String newLastName;
	private String newBirthdate;
	private String newEmail;
	
	private Color Light = new Color(0xEEEEEE);
	private Color semiLight = new Color(0x76ABAE);
	private Color dark = new Color(0x222831);
	private Color semidark = new Color(0x878484);
	private Color placeHolder = new Color(0xB0AFAF);
	
	private StudentsDatabase database;
	private ImageIcon appLogo;

	



	public InterfaceGraphique() {
		//Initiating database-------------------------------------------------
		database = new StudentsDatabase("students_database");
		//--------------------------------------------------------------------
		this.setTitle(" Gestion des Étudiants (beta)");
		appLogo = new ImageIcon("resources/logo-USMBA.png");
		this.setIconImage(appLogo.getImage());
		this.setSize(1000,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	JButton returnButton = new JButton("<--");
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
		
//		Color Light = new Color(0xEEEEEE);
//		Color semiLight = new Color(0x76ABAE);
//		Color dark = new Color(0x222831);
//		Color semidark = new Color(0x31363F);
		
		Color sideMenuColor = dark;
		JPanel homePanel = new JPanel(new BorderLayout());
		JPanel sideMenu = new JPanel(new BorderLayout());

		JPanel emptyPanel = new JPanel();
		JPanel optionSideMenu = new JPanel(new GridLayout(8, 1));
		emptyPanel.setBackground(sideMenuColor);
		
		
		JPanel dashboardPanel = new JPanel(new BorderLayout());	
//		JPanel optionSideMenu = new JPanel(new GridLayout(6, 1));
		JTextField searchBar = new JTextField();
		searchBar.setBorder(new RoundedBorder(10));
		searchBar.setFont(new Font("sans-serif", Font.BOLD,20));
		searchBar.setPreferredSize(new Dimension(500,50));
		searchBar.setOpaque(false);
		searchBar.setVisible(true);
		JButton searchButton = new JButton("Search");
		searchButton.setVisible(true);
		JRadioButton choixCne = new  JRadioButton("Cne") ;
		choixCne.setOpaque(false);
		choixCne.setFocusable(false);
		JRadioButton choixNom = new  JRadioButton("Nom");
		choixNom.setOpaque(false);
		choixNom.setFocusable(false);
		JRadioButton choixPrenom = new  JRadioButton("Prenom");
		choixPrenom.setOpaque(false);
		choixPrenom.setFocusable(false);
		JRadioButton choixEmail = new  JRadioButton("E-mail");
		choixEmail.setOpaque(false);
		choixEmail.setFocusable(false);
		JPanel choix =new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		choix.add(choixCne);
		choix.add(choixPrenom);
		choix.add(choixNom);
		choix.add(choixEmail);
		choix.setOpaque(false);
		
		
		ButtonGroup groupRadio = new ButtonGroup();
		groupRadio.add(choixCne);
		groupRadio.add(choixNom);
		groupRadio.add(choixPrenom);
		groupRadio.add(choixEmail);
		
		String[] columnNames = {"Code Massar", "Penom", "Nom", "Date de Naissance", "E-mail","Remove","Edite"};
		
		
		searchButton.setFocusable(false);
		searchButton.setOpaque(false);
		searchButton.setBackground(new Color(0x4c4c4c));
		searchButton.setBorder(new RoundedBorder(18));
		searchButton.setPreferredSize(new Dimension(80,40));
		
		
		returnButton.setFocusable(false);
		returnButton.setBackground(new Color(0x4c4c4c));
		returnButton.setBorder(new RoundedBorder(20));
		returnButton.setOpaque(false);
		returnButton.setPreferredSize(new Dimension(40,30));
		returnButton.setVisible(false);
		returnButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getAccueil();
				
				
			}
		});
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
		
		JPanel searchPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,40,10));
		searchPanel.add(returnButton);
		searchPanel.add(searchBar);
		searchPanel.add(searchButton);
		topPanel.add(searchPanel);
		topPanel.add(choix);
		dashboardPanel.add(topPanel, BorderLayout.NORTH);
//		dashboardPanel.add(searchButton);
		dashboardPanel.add(choix);
		
		
		searchButton.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				returnButton.setVisible(true);
				searchBar.setVisible(false);
				searchButton.setVisible(false);
				choix.setVisible(false);
				JPanel tablePanel ;
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
			       
				tablePanel = new JPanel(new BorderLayout());
				ArrayList<Students> students;
				students = database.rechercherEtudiant(choose,value);
				
				Object[][] data = new Object[students.size()][7];
				for (int i = 0; i < students.size(); i++) {
				    Students student = students.get(i);
				    data[i][0] = student.getCne();
				    data[i][1] = student.getFirstName();
				    data[i][2] = student.getLastName();
				    data[i][3] = student.getDate();
				    data[i][4] = student.getEmail();
				    data[i][5] = "Remove";
				    data[i][6] = "EDITE";
				}

    // Create the table with the data and column names
				
				DefaultTableModel model =new DefaultTableModel(data,columnNames) {
					@Override
					public boolean isCellEditable(int row, int column) {
						// TODO Auto-generated method stub
						return column == 5 || column == 6;
						
					}
				};
				
				JTable table = new JTable(model);
				JScrollPane scrollPane = new JScrollPane(table);
				tablePanel.removeAll();
				tablePanel.add(scrollPane, BorderLayout.CENTER);
				table.setAutoCreateRowSorter(true); // Enable row sorting
				// Add the scroll pane to the frame


				dashboardPanel.add(tablePanel,BorderLayout.SOUTH);
				dashboardPanel.revalidate();
				dashboardPanel.repaint();
					
		

				/*############################################################################*/
			       	
		}});

		add(dashboardPanel);
		
		sideMenu.setBackground(sideMenuColor);
		dashboardPanel.setBackground(Light);
		optionSideMenu.setBackground(sideMenuColor);
		sideMenu.setPreferredSize(new Dimension(300, 700));

		ImageIcon logoIcon = new ImageIcon("resources/logo-USMBA.png");
		Image scaledLogo = logoIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
		sideMenu.add(logoLabel, BorderLayout.NORTH);

		ImageIcon homeIconBlack = new ImageIcon("resources/home.png");
		ImageIcon addIconBlack = new ImageIcon("resources/add-user-black.png");
		ImageIcon showIconBlack = new ImageIcon("resources/to-do-list-black.png");

		Image scaledHomeIconclicked = homeIconBlack.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Image scaledAddIconhovered = addIconBlack.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Image scaledShowIconhovered = showIconBlack.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		
		ImageIcon homeIconWhite = new ImageIcon("resources/home.png");
		ImageIcon addIconWhite= new ImageIcon("resources/add-user-white.png");
		ImageIcon showIconWhite = new ImageIcon("resources/to-do-list-white.png");

		Image scaledHomeIcon = homeIconWhite.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Image scaledAddIcon = addIconWhite.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		Image scaledShowIcon = showIconWhite.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);

		JLabel homeOption = new JLabel("Accueil", new ImageIcon(scaledHomeIcon), SwingConstants.CENTER);
		JLabel addStudentOption = new JLabel("Ajouter Etudiant", new ImageIcon(scaledAddIcon), SwingConstants.CENTER);
		JLabel showStudentsOption = new JLabel("Affichier Etudiant", new ImageIcon(scaledShowIcon), SwingConstants.CENTER);
				

		homeOption.setBackground(Light);
		homeOption.setOpaque(true);
		
		homeOption.setIconTextGap(25);
		addStudentOption.setIconTextGap(25);
		showStudentsOption.setIconTextGap(25);
		
		addStudentOption.setForeground(Light);
		showStudentsOption.setForeground(Light);
		
		addStudentOption.addMouseListener(new MouseAdapter() {
			 @Override
	            public void mouseEntered(MouseEvent e) {
	                addStudentOption.setBackground(semiLight);
	                addStudentOption.setForeground(Color.black);
	                addStudentOption.setIcon(new ImageIcon(scaledAddIconhovered));
	                addStudentOption.setOpaque(true);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	addStudentOption.setBackground(sideMenuColor);
	            	addStudentOption.setForeground(Color.WHITE);
	                addStudentOption.setIcon(new ImageIcon(scaledAddIcon));
	            	addStudentOption.setOpaque(true);
	            }
	            
	            @Override
	            public void mousePressed(MouseEvent e) {
	            	addStudentOption.setBackground(Light);
	            	addStudentOption.setForeground(Color.BLACK);
	                addStudentOption.setIcon(new ImageIcon(scaledAddIconhovered));
	            	addStudentOption.setOpaque(true);
	            	getAjouterEtudiant();
	            }
		});

		showStudentsOption.addMouseListener(new MouseAdapter() {
			
			 @Override
	            public void mouseEntered(MouseEvent e) {
					 showStudentsOption.setBackground(semiLight);
					 showStudentsOption.setIcon(new ImageIcon(scaledShowIconhovered));
					 showStudentsOption.setForeground(Color.BLACK);
					 showStudentsOption.setOpaque(true);
		        }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	showStudentsOption.setBackground(sideMenuColor);
					 showStudentsOption.setIcon(new ImageIcon(scaledShowIcon));
	            	showStudentsOption.setForeground(Color.WHITE);
	            	showStudentsOption.setOpaque(true);
	            }
	            
	            @Override
	            public void mousePressed(MouseEvent e) {
	            	showStudentsOption.setBackground(Light);
	            	showStudentsOption.setForeground(Color.BLACK);
					 showStudentsOption.setIcon(new ImageIcon(scaledShowIconhovered));
	            	showStudentsOption.setOpaque(true);
	            	getListEtudiants();
	            }
		});
		optionSideMenu.add(emptyPanel);
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
		this.setVisible(true);
		
		}

	@Override
	public void getListEtudiants() {



		// TODO Auto-generated method stub

		String headers[] = {"Code Massar","Nom","Prénom","Date de naissance","E-mail"}; 
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel centerPanel = new JPanel(new BorderLayout());
	    JPanel northPanel = new JPanel(new BorderLayout());
	    
	    northPanel.setPreferredSize(new Dimension(800,50));
	    northPanel.setBackground(Light);
	    
	    ArrayList<Students> rowData =  database.afficherEtudiants();
	    int rowDataSize = rowData.size();
	    String data[][] = new String[rowDataSize][5];
	    for(int i = 0; i<rowDataSize; i++) {
	    	data[i]= new String[]{
	    			rowData.get(i).getCne(),
	    			rowData.get(i).getLastName(),
	    			rowData.get(i).getFirstName(),
	    			rowData.get(i).getDate().toString(),
	    			rowData.get(i).getEmail()};
	    }
	    JTable table = new JTable(data,headers) {
	    	@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
	    };
	    
	    table.getTableHeader().setOpaque(false);
	    table.getTableHeader().setBackground(semiLight);
	    table.getTableHeader().setForeground(dark);
	    
	    table.getTableHeader().setReorderingAllowed(false);
	    
        JTableHeader tableHeader = table.getTableHeader();
        Font headerFont = tableHeader.getFont();
        tableHeader.setFont(headerFont.deriveFont(Font.BOLD));
        
	    JScrollPane scrollPlane = new JScrollPane(table);
	    centerPanel.add(scrollPlane);
	    
		ImageIcon goBackIcon = new ImageIcon("resources/back.png");
		Image scaledGoBackIcon = goBackIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		JLabel goBackIconLabel = new JLabel(new ImageIcon(scaledGoBackIcon));

		northPanel.add(goBackIconLabel,BorderLayout.WEST);
		

		goBackIconLabel.addMouseListener(new MouseAdapter() {
			@Override
            public void mousePressed(MouseEvent e) {
				getAccueil();
            }
		});
		
		ImageIcon printiIcon = new ImageIcon("resources/printer.png");
		Image scaledPrintiIcon = printiIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		JLabel printiIconLabel = new JLabel(new ImageIcon(scaledPrintiIcon));
		
		printiIconLabel.addMouseListener(new MouseAdapter() {
			@Override
            public void mousePressed(MouseEvent e) {
				MessageFormat header = new MessageFormat("List des Etudiants");
				
				try {
					PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
					table.print(JTable.PrintMode.FIT_WIDTH,header,null,true,set,true);
					JOptionPane.showMessageDialog(null, "\n"+"Le fichier a été imprimé avec succès");
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "\n"+"Une erreur s'est produite lors de l'impression");
					e1.printStackTrace();
				}
			}
		});
		
		northPanel.add(printiIconLabel,BorderLayout.EAST);
		
		
		mainPanel.add(northPanel,BorderLayout.NORTH);
		mainPanel.add(centerPanel,BorderLayout.CENTER);
		this.setContentPane(mainPanel);
		this.setVisible(true);

	}

	@Override
	public void getAjouterEtudiant() {
	    JTextField codeMassarField, prenomField, nomField, dateNaissanceField, emailField;
	    JButton submitButton;
	    JButton backButton;
	    

	   
	    JPanel panel = new JPanel(new GridBagLayout());
	    panel.setBackground(new Color(240, 240, 240)); 
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(10, 10, 10, 10);
	    // Load the image for the back button
	    ImageIcon backIcon = new ImageIcon("resources/back.png"); 
	    Image backImage = backIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); // Resize the image
	    backIcon = new ImageIcon(backImage); // Create a new ImageIcon with the resized image

	    // Back button with image and styling
	    backButton = new JButton(backIcon); // Set the image as the button's icon
	    backButton.setText(""); // Remove text (optional)
	    backButton.setOpaque(false);
	    backButton.setBorder(new RoundedBorder(15));
	    backButton.setBackground(new Color(0x4c4c4c)); // Dark gray background
	    backButton.setForeground(Color.WHITE); // White text
	    backButton.setFocusPainted(false); // Remove focus border
	    backButton.setFont(new Font("Segoe UI", Font.BOLD, 14)); // Modern font
	    backButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            getAccueil();
	        }
	    });

	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.WEST;
	    panel.add(backButton, gbc);

	    // Set a larger font for labels and text fields
	    Font largerFont = new Font("Segoe UI", Font.PLAIN, 16);

	    // Add labels and text fields
	    gbc.gridwidth = 1;
	    gbc.gridy++;
	    gbc.anchor = GridBagConstraints.EAST;
	    JLabel codeMassarLabel = new JLabel("Code Massar:");
	    codeMassarLabel.setFont(largerFont);
	    panel.add(codeMassarLabel, gbc);

	    gbc.gridx = 1;
	    gbc.anchor = GridBagConstraints.WEST;
	    codeMassarField = new JTextField(20);
	    codeMassarField.setFont(largerFont);
	    codeMassarField.setBorder(new RoundedBorder(10)); 
	    panel.add(codeMassarField, gbc);

	    gbc.gridx = 0;
	    gbc.gridy++;
	    gbc.anchor = GridBagConstraints.EAST;
	    JLabel prenomLabel = new JLabel("Prenom:");
	    prenomLabel.setFont(largerFont);
	    panel.add(prenomLabel, gbc);

	    gbc.gridx = 1;
	    gbc.anchor = GridBagConstraints.WEST;
	    prenomField = new JTextField(20);
	    prenomField.setFont(largerFont);
	    prenomField.setBorder(new RoundedBorder(10)); 
	    panel.add(prenomField, gbc);

	    gbc.gridx = 0;
	    gbc.gridy++;
	    gbc.anchor = GridBagConstraints.EAST;
	    JLabel nomLabel = new JLabel("Nom:");
	    nomLabel.setFont(largerFont);
	    panel.add(nomLabel, gbc);

	    gbc.gridx = 1;
	    gbc.anchor = GridBagConstraints.WEST;
	    nomField = new JTextField(20);
	    nomField.setFont(largerFont);
	    nomField.setBorder(new RoundedBorder(10)); 
	    panel.add(nomField, gbc);

	    gbc.gridx = 0;
	    gbc.gridy++;
	    gbc.anchor = GridBagConstraints.EAST;
	    JLabel dateNaissanceLabel = new JLabel("Date de Naissance:");
	    dateNaissanceLabel.setFont(largerFont);
	    panel.add(dateNaissanceLabel, gbc);

	    gbc.gridx = 1;
	    gbc.anchor = GridBagConstraints.WEST;
	    dateNaissanceField = new JTextField("jj/mm/aaaa",20);
	    dateNaissanceField.setForeground(placeHolder);
	    dateNaissanceField.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mousePressed(MouseEvent e) {
	    		if (dateNaissanceField.getText().equals("jj/mm/aaaa")) {
	    			dateNaissanceField.setText("");
	    			dateNaissanceField.setForeground(dark);
	    		}
	    	}
	    	
	    	@Override
	    	public void mouseExited(MouseEvent e) {
	    		if (dateNaissanceField.getText().isBlank()) {
	    			dateNaissanceField.setText("jj/mm/aaaa");
	    			dateNaissanceField.setForeground(placeHolder);
	    		}
	    	}
		});
	    dateNaissanceField.setFont(largerFont);
	    dateNaissanceField.setBorder(new RoundedBorder(10)); 
	    panel.add(dateNaissanceField, gbc);

	    gbc.gridx = 0;
	    gbc.gridy++;
	    gbc.anchor = GridBagConstraints.EAST;
	    JLabel emailLabel = new JLabel("E-mail:");
	    emailLabel.setFont(largerFont);
	    panel.add(emailLabel, gbc);

	    gbc.gridx = 1;
	    gbc.anchor = GridBagConstraints.WEST;
	    emailField = new JTextField(20);
	    emailField.setFont(largerFont);
	    emailField.setBorder(new RoundedBorder(10));
	    panel.add(emailField, gbc);
	    // Add submit button
	    gbc.gridx = 0;
	    gbc.gridy++;
	    gbc.gridwidth = 2;
	    gbc.anchor = GridBagConstraints.CENTER;
	    submitButton = new JButton("Submit");
	    submitButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
	    submitButton.setBackground(new Color(0x4CAF50)); 
	    submitButton.setForeground(Color.WHITE); 
	    submitButton.setFocusPainted(false); 
	    submitButton.setBorder(new RoundedBorder(15)); 
	    submitButton.setPreferredSize(new Dimension(120, 40)); 
	    panel.add(submitButton, gbc);
	    // Add action listener to the submit button
	    submitButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	 Date d = new Date();
	        	 
	        	 
	        	 if(!codeMassarField.getText().isBlank() && !prenomField.getText().isBlank()&& !nomField.getText().isBlank() && !emailField.getText().isBlank()  && d.parseDate(dateNaissanceField.getText()).validDate()) {
	        	Students  addedStudent = new Students(
	        			codeMassarField.getText(),
	        			prenomField.getText(),
	        			nomField.getText(),
	        			d.parseDate(dateNaissanceField.getText()),
	        			emailField.getText()
	        			);
	            database.ajouterEtudiant(addedStudent);
	            JOptionPane.showConfirmDialog(null, "L'etudient est ajoute sucessuf");
	        	 }else if( !d.validDate()) {
	        		 JOptionPane.showMessageDialog(null, "Le date est incorrect \n \n essay d'utilise la forma suivant jj/nn/aaaa.");
	        		
	        	 }else{
	        		 JOptionPane.showMessageDialog(null, "SVP remplie tout les informations");
	        	 }
	        }
	    });

	    // Add the panel to the frame
	    add(panel, BorderLayout.CENTER);
	    this.setContentPane(panel);
	    this.validate();
	    this.repaint();
	    this.setVisible(true);
	}


	@Override
	public void getModifierEtudiant(Students student) {

		
		Date bufferDate = new Date();
		JButton save = new JButton("Enregistrer");
		JButton cancel = new JButton("Annuler");
	
		save.setBackground(Light);
		save.setForeground(dark);
		
		cancel.setBackground(Light);
		cancel.setForeground(dark);
		
		save.setBorder(new RoundedBorder(10));
		cancel.setBorder(new RoundedBorder(10));
		
	    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
	    JPanel eastPanel = new JPanel();
	    JPanel westPanel = new JPanel();
	    JPanel southPanel = new JPanel(new BorderLayout());
	    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
	    
	    JPanel cnePanel = new JPanel(new BorderLayout());
	    JPanel firstNamePanel = new JPanel(new BorderLayout());
	    JPanel LastNamePanel = new JPanel(new BorderLayout());
	    JPanel birthdayPanel = new JPanel(new BorderLayout());
	    JPanel emailPanel = new JPanel(new BorderLayout());
	    
	    ((BorderLayout) cnePanel.getLayout()).setHgap(10);
        ((BorderLayout) cnePanel.getLayout()).setVgap(10);
        
        ((BorderLayout) firstNamePanel.getLayout()).setHgap(10);
        ((BorderLayout) firstNamePanel.getLayout()).setVgap(10);
        
        ((BorderLayout) LastNamePanel.getLayout()).setHgap(10);
        ((BorderLayout) LastNamePanel.getLayout()).setVgap(10);
        
        ((BorderLayout) birthdayPanel.getLayout()).setHgap(10);
        ((BorderLayout) birthdayPanel.getLayout()).setVgap(10);
        
        ((BorderLayout) emailPanel.getLayout()).setHgap(10);
        ((BorderLayout) emailPanel.getLayout()).setVgap(10);
	    
	    JPanel framePanel = new JPanel(new BorderLayout());
	    JPanel mainPanel = new JPanel(new BorderLayout());
	    JPanel labelsPanel = new JPanel(new GridLayout(5, 1, 20, 20));
	    JPanel inputsPanel = new JPanel(new GridLayout(5, 1, 20, 20));

	    northPanel.setPreferredSize(new Dimension(800,200));
	    southPanel.setPreferredSize(new Dimension(800,200));
	    westPanel.setPreferredSize(new Dimension(200, 700));
	    eastPanel.setPreferredSize(new Dimension(200, 700));

	    JLabel cneLabel = new JLabel("Code Massar 	", SwingConstants.RIGHT);
	    JLabel firstNameLabel = new JLabel("Prénom 	", SwingConstants.RIGHT);
	    JLabel lastNameLabel = new JLabel("Nom 	", SwingConstants.RIGHT);
	    JLabel birthdayLabel = new JLabel("Date de naissance 	", SwingConstants.RIGHT);
	    JLabel emailLabel = new JLabel("E-mail 	", SwingConstants.RIGHT);

	    JTextField cneInput = new JTextField(student.getCne(), 20);
	    JTextField firstNameInput = new JTextField(student.getFirstName(), 20);
	    JTextField lastNameInput = new JTextField(student.getLastName(), 20);
	    JTextField birthdayInput = new JTextField(student.getDate().toString(), 20);
	    JTextField emailInput = new JTextField(student.getEmail(), 20);
	    
	    cneInput.setBorder(new RoundedBorder(10));
	    firstNameInput.setBorder(new RoundedBorder(10));
	    lastNameInput.setBorder(new RoundedBorder(10));
	    birthdayInput.setBorder(new RoundedBorder(10));
	    emailInput.setBorder(new RoundedBorder(10));
	    
	    ImageIcon goBackIcon = new ImageIcon("resources/back.png");
		Image scaledGoBackIcon = goBackIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		JLabel goBackIconLabel = new JLabel(new ImageIcon(scaledGoBackIcon));
		
		goBackIconLabel.addMouseListener(new MouseAdapter() {
			@Override
            public void mousePressed(MouseEvent e) {
				getAccueil();
            }
		});
		
		northPanel.add(goBackIconLabel);
	    
	    ImageIcon editIcon = new ImageIcon("resources/edit-button.png");
		Image scaledEditIcon = editIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		
		JLabel editCneIconLabel = new JLabel(new ImageIcon(scaledEditIcon));
		JLabel editFirstNameIconLabel = new JLabel(new ImageIcon(scaledEditIcon));
		JLabel editLastNameIconLabel = new JLabel(new ImageIcon(scaledEditIcon));
		JLabel editBirthdayIconLabel = new JLabel(new ImageIcon(scaledEditIcon));
		JLabel editEmailIconLabel = new JLabel(new ImageIcon(scaledEditIcon));
		

	    cneInput.setEnabled(false);
	    firstNameInput.setEnabled(false);
	    lastNameInput.setEnabled(false);
	    birthdayInput.setEnabled(false);
	    emailInput.setEnabled(false);
	    
		editCneIconLabel.addMouseListener(new MouseAdapter() {
			@Override
	            public void mousePressed(MouseEvent e) {
	        	    cneInput.setEnabled(true);
	            }
		});
		
		editFirstNameIconLabel.addMouseListener(new MouseAdapter() {
			@Override
	            public void mousePressed(MouseEvent e) {
	            	firstNameInput.setEnabled(true); 
	            }
		});
	    
		editLastNameIconLabel.addMouseListener(new MouseAdapter() {
			@Override
	            public void mousePressed(MouseEvent e) {
	            	lastNameInput.setEnabled(true);
	            }
		});
		
		editBirthdayIconLabel.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	            	birthdayInput.setEnabled(true); 
	            }
		});
		
		editEmailIconLabel.addMouseListener(new MouseAdapter() {
			@Override
	            public void mousePressed(MouseEvent e) {
	            	emailInput.setEnabled(true);
	            }
		});
		
		save.addActionListener(new ActionListener() {
          
			@Override
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
		
				int response = JOptionPane.showConfirmDialog(InterfaceGraphique.this, "Voulez-vous sauvegarder votre modification ?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					if (!cneInput.getText().isBlank() && !firstNameInput.getText().isBlank() && !lastNameInput.getText().isBlank() && date.parseDate(birthdayInput.getText()).validDate()&&!emailInput.getText().isBlank()) {
						newCne = cneInput.getText();
						newFirstName = firstNameInput.getText();
						newLastName = lastNameInput.getText();
						newBirthdate = birthdayInput.getText();
						newEmail = emailInput.getText();
						database.modifierEtudiant(student.getCne(), new Students(newCne, newFirstName, newLastName,bufferDate.parseDate(newBirthdate), newEmail));
						cneInput.setEnabled(false);
					    firstNameInput.setEnabled(false);
					    lastNameInput.setEnabled(false);
					    birthdayInput.setEnabled(false);
					    emailInput.setEnabled(false);
					}else {
						JOptionPane.showMessageDialog(null, "Les données que vous avez saisies ne sont pas valides.");
					}
				} else if (response == JOptionPane.NO_OPTION) {
					cneInput.setText(student.getCne());
				    firstNameInput.setText(student.getFirstName());
				    lastNameInput.setText(student.getLastName());
				    birthdayInput.setText(student.getDate().toString());
				    emailInput.setText(student.getEmail());
				    
				    cneInput.setEnabled(false);
				    firstNameInput.setEnabled(false);
				    lastNameInput.setEnabled(false);
				    birthdayInput.setEnabled(false);
				    emailInput.setEnabled(false);
				    
				} else if (response == JOptionPane.CANCEL_OPTION) {
				}
			}
        });
		
		cancel.addActionListener(new ActionListener() {
	          
			@Override
			public void actionPerformed(ActionEvent e) {
				getAccueil();
			}
        });
		
		
	    labelsPanel.add(cneLabel);
	    labelsPanel.add(firstNameLabel);
	    labelsPanel.add(lastNameLabel);
	    labelsPanel.add(birthdayLabel);
	    labelsPanel.add(emailLabel);

	    cnePanel.add(cneInput,BorderLayout.CENTER);
	    cnePanel.add(editCneIconLabel,BorderLayout.EAST);
	    
	    firstNamePanel.add(firstNameInput,BorderLayout.CENTER);
	    firstNamePanel.add(editFirstNameIconLabel,BorderLayout.EAST);
	    
	    LastNamePanel.add(lastNameInput,BorderLayout.CENTER);
	    LastNamePanel.add(editLastNameIconLabel,BorderLayout.EAST);
	    
	    birthdayPanel.add(birthdayInput,BorderLayout.CENTER);
	    birthdayPanel.add(editBirthdayIconLabel,BorderLayout.EAST);
	    
	    emailPanel.add(emailInput,BorderLayout.CENTER);
	    emailPanel.add(editEmailIconLabel,BorderLayout.EAST);
	    
	    inputsPanel.add(cnePanel);
	    inputsPanel.add(firstNamePanel);
	    inputsPanel.add(LastNamePanel);
	    inputsPanel.add(birthdayPanel);
	    inputsPanel.add(emailPanel);

	    mainPanel.add(labelsPanel, BorderLayout.WEST);
	    mainPanel.add(inputsPanel, BorderLayout.CENTER);
	    
	    buttonsPanel.add(save);
	    buttonsPanel.add(cancel);
	    southPanel.add(buttonsPanel,BorderLayout.SOUTH);

	    framePanel.add(northPanel,BorderLayout.NORTH);
	    framePanel.add(westPanel, BorderLayout.WEST);
	    framePanel.add(mainPanel, BorderLayout.CENTER);
	    framePanel.add(eastPanel, BorderLayout.EAST);
	    framePanel.add(southPanel,BorderLayout.SOUTH);

	    this.setContentPane(framePanel);
		this.setVisible(true);

	}

}
