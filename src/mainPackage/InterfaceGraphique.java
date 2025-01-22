package mainPackage;

import java.awt.BorderLayout; 
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import javax.swing.table.JTableHeader;








public class InterfaceGraphique extends JFrame implements userGraphic{
	
	String newCne;
	String newFirstName;
	String newLastName;
	String newBirthdate;
	String newEmail;
	JPanel topPanel = new JPanel(new BorderLayout());
	JPanel dashboardPanel = new JPanel(new BorderLayout());
	String choose="";
	String value;
	Object[][] data =new Object[0][7];;
	JPanel tablePanel =new JPanel() ;
	String[] columnNames = {"Code Massar", "Penom", "Nom", "Date de Naissance", "E-mail","Remove","Edite"};		

	private StudentsDatabase database;
	private ImageIcon appLogo;
	
	CustomTable customTable;
	JDialog dialog;

	public InterfaceGraphique() {
		//Initiating database-------------------------------------------------
		database = new StudentsDatabase("students");
		//--------------------------------------------------------------------
		this.setTitle(" Gestion des Étudiants (beta)");
		appLogo = new ImageIcon("resources/AppLogo.png");
		this.setIconImage(appLogo.getImage());
		this.setSize(1100,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public void supprimerEtudiant(String cne) {
        database.supprimerEtudiant(cne); 
        refreshTable();
    }
	

    /**
     * Method to remove a student by CNE.
     */
    public void removeStudent(String cne) {
        // Call the method in the DatabaseStudent class to remove the student by CNE
        database.supprimerEtudiant(cne); 
        refreshTable();
    }

    /**
     * Method to modify a student.
     */
    public void modifyStudent(Students student) {
        // Your logic to modify the student
    	this.getModifierEtudiant(student);
        System.out.println("Modifying student: " + student.getCne());

        // Refresh the table after modification
        refreshTable();
    }

    /**
     * Method to refresh the table with updated data.
     */
    private void refreshTable(){
        // Fetch updated student data and update the table
        ArrayList<Students> students = database.rechercherEtudiant(choose,value);
        Object[][] data = new Object[students.size()][7];
        for (int i = 0; i < students.size(); i++) {
            Students student = students.get(i);
            data[i][0] = student.getCne();
            data[i][1] = student.getFirstName();
            data[i][2] = student.getLastName();
            data[i][3] = student.getDate(); 
            data[i][4] = student.getEmail();
            data[i][5] = "Remove";
            data[i][6] = "Modify";
        }

        // Update the table
        tablePanel.removeAll(); // Clear the table panel
        customTable = new CustomTable(columnNames, data, new int[]{5, 6}, this::removeStudent, this::modifyStudent);
        JScrollPane scrollPane = new JScrollPane(customTable);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.revalidate();
        tablePanel.repaint();
    }

	
	ImageIcon returnIcon = new ImageIcon("resources/back.png");
	Image scaledReturnIcon = returnIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	
	@Override
	public void getAccueil() {
		
		Color Light = new Color(0xEEEEEE);
		Color semiLight = new Color(0x76ABAE);
		Color dark = new Color(0x222831);
		Color semidark = new Color(0x31363F);
		
		Color sideMenuColor = semiLight;
		JPanel homePanel = new JPanel(new BorderLayout());
		JPanel sideMenu = new JPanel(new BorderLayout());

		JPanel emptyPanel = new JPanel();
		JPanel optionSideMenu = new JPanel(new GridLayout(8, 1));
		emptyPanel.setBackground(sideMenuColor);
		
		
			

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
		searchButton.setFocusable(false);
		searchButton.setOpaque(false);
		searchButton.setBackground(dark);
		searchButton.setBorder(new RoundedBorder(18));
		searchButton.setPreferredSize(new Dimension(80,40));

		JLabel backIcon =new JLabel(new ImageIcon(scaledReturnIcon));

		backIcon.setVisible(false);
		backIcon.addMouseListener(new MouseListener() {
			
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
				 dashboardPanel.removeAll();
			     dashboardPanel.add(topPanel, BorderLayout.NORTH);
			     dashboardPanel.add(tablePanel, BorderLayout.CENTER);
				getAccueil();
				refreshTable();
				dashboardPanel.revalidate();
		        dashboardPanel.repaint();
			}
		});
		topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel,BoxLayout.Y_AXIS));
		JPanel searchPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,40,10));
		searchPanel.add(backIcon);
		searchPanel.add(searchBar);
		searchPanel.add(searchButton);
		topPanel.add(searchPanel);
		topPanel.add(choix);
		dashboardPanel.add(topPanel, BorderLayout.NORTH);
		dashboardPanel.add(choix);
		
	    
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				backIcon.setVisible(true);
				searchBar.setVisible(false);
				searchButton.setVisible(false);
				choix.setVisible(false);
				
				value = searchBar.getText();
				
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
				
				/*----------------------------------------------------------------------*/
				
				/*-----------------------------------------------------------------------*/
				
				
				ArrayList<Students> students;
				students = database.rechercherEtudiant(choose,value);
				data = new Object[students.size()][7];
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
					 refreshTable();      	
		}
	    
			});
		
		customTable = new CustomTable(columnNames, data, new int[]{5, 6}, this::removeStudent, this::modifyStudent);
		
//		JTable table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(customTable);
		tablePanel.removeAll();
		tablePanel.add(scrollPane, BorderLayout.CENTER);
//		table.setAutoCreateRowSorter(true);
		dashboardPanel.add(tablePanel,BorderLayout.SOUTH);
		dashboardPanel.revalidate();
		dashboardPanel.repaint();	
		add(dashboardPanel);
		sideMenu.setBackground(sideMenuColor);
		dashboardPanel.setBackground(Light);
		optionSideMenu.setBackground(sideMenuColor);
		sideMenu.setPreferredSize(new Dimension(300, 700));
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
		
		homeOption.setBackground(new Color(0xE9E9E9));
		homeOption.setOpaque(true);
		
		homeOption.setIconTextGap(25);
		addStudentOption.setIconTextGap(25);
		showStudentsOption.setIconTextGap(25);
		
		addStudentOption.addMouseListener(new MouseAdapter() {
			 @Override
	            public void mouseEntered(MouseEvent e) {
	                addStudentOption.setBackground(new Color(0xE9E9E9));
	                addStudentOption.setForeground(Color.BLACK);
	                addStudentOption.setOpaque(true);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	addStudentOption.setBackground(sideMenuColor);
	            	addStudentOption.setForeground(Color.BLACK);
	            	addStudentOption.setOpaque(true);
	            }
	            
	            @Override
	            public void mousePressed(MouseEvent e) {
	            	addStudentOption.setBackground(new Color(0xE9E9E9));
	            	addStudentOption.setForeground(Color.BLACK);
	            	addStudentOption.setOpaque(true);
	            	getAjouterEtudiant();
	            }
		});

		showStudentsOption.addMouseListener(new MouseAdapter() {
			
			 @Override
	            public void mouseEntered(MouseEvent e) {
					 showStudentsOption.setBackground(new Color(0xE9E9E9));
					 showStudentsOption.setForeground(Color.BLACK);
					 showStudentsOption.setOpaque(true);
		        }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	showStudentsOption.setBackground(sideMenuColor);
	            	showStudentsOption.setForeground(Color.BLACK);
	            	showStudentsOption.setOpaque(true);
	            }
	            
	            @Override
	            public void mousePressed(MouseEvent e) {
	            	showStudentsOption.setBackground(new Color(0xE9E9E9));
	            	showStudentsOption.setForeground(Color.BLACK);
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
	    table.getTableHeader().setBackground(new Color(0x181B29));
	    table.getTableHeader().setForeground(new Color(0xFFFFFF));
	    
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
	    Image backImage = backIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH); 
	    backIcon = new ImageIcon(backImage); 

	    // Back button with image and styling
	    backButton = new JButton(backIcon); 
	    backButton.setText(""); 
	    backButton.setOpaque(false);
	    backButton.setBorder(new RoundedBorder(15));
	    backButton.setBackground(new Color(0x4c4c4c)); 
	    backButton.setForeground(Color.WHITE); 
	    backButton.setFocusPainted(false); 
	    backButton.setFont(new Font("Segoe UI", Font.BOLD, 14)); 
	    
	    backButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            getAccueil();
	        }
	    });
	    
	    
//	    gbc.gridx = 50;
//	    gbc.gridy = 0;
//	    gbc.gridwidth = 1;
//	    gbc.anchor = GridBagConstraints.WEST;
//	    panel.add(backButton, gbc);

	  
	    Font largerFont = new Font("Segoe UI", Font.PLAIN, 16);

	    
	    gbc.gridwidth = 1;
	    gbc.gridy =0;
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
	    dateNaissanceField.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				 if (dateNaissanceField.getText().equals("jj/mm/aaaa")) {
	                    dateNaissanceField.setText("");
	                    dateNaissanceField.setForeground(Color.BLACK); 
	                }
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				 if (dateNaissanceField.getText().isEmpty()) {
	                    dateNaissanceField.setText("jj/mm/aaaa");
	                    dateNaissanceField.setForeground(Color.GRAY); 
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
	        	int response =JOptionPane.showConfirmDialog(null, "are you sure you want to add "+nomField.getText()+" to list");
	        	if(response == JOptionPane.YES_OPTION) {
	            database.ajouterEtudiant(addedStudent);
	           
	            JOptionPane.showMessageDialog(null,"L'etudent a est ete ajoute a la list");
	        	}else {
	        		dialog= new JOptionPane("L'operation a ete annule",JOptionPane.INFORMATION_MESSAGE,JOptionPane.DEFAULT_OPTION).createDialog(null,"Auto-Close Dialog");
	        		
	        		Timer timer = new Timer(1000,new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							dialog.setVisible(false);
							dialog.dispose();
							
						}
					});
	        		timer.setRepeats(false);
	        		timer.start();
	        		dialog.setVisible(true);
	        		
	        	}
	        	
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
