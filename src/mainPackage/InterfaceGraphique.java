package mainPackage;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import java.awt.Font;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	

	String newCne;
	String newFirstName;
	String newLastName;
	String newBirthdate;
	String newEmail;
	
	
	private StudentsDatabase database;
	private ImageIcon appLogo;
	
	public InterfaceGraphique() {
		//Initiating database-------------------------------------------------
		database = new StudentsDatabase("students");
		//--------------------------------------------------------------------
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
		
		
		Color sideMenuColor = new Color(0xB6B6B6);
		JPanel homePanel = new JPanel(new BorderLayout());
		JPanel sideMenu = new JPanel(new BorderLayout());
//		JPanel dashboardPanel = new JPanel();
		JPanel emptyPanel = new JPanel();
		JPanel optionSideMenu = new JPanel(new GridLayout(8, 1));

		emptyPanel.setBackground(sideMenuColor);
		JPanel dashboardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,5,50));
		
//		JPanel optionSideMenu = new JPanel(new GridLayout(6, 1));
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
				
				  
			        ArrayList<Students> students;
					try {
						students = database.rechercherEtudiant(choose,value);
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
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				/*############################################################################*/
			       	
		}});
		
		dashboardPanel.add(searchBar);
		dashboardPanel.add(searchButton);
		dashboardPanel.add(choix);
		//dashboardPanel.add(table);
		
		sideMenu.setBackground(sideMenuColor);
		dashboardPanel.setBackground(new Color(0xCECECE));
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
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		}

	@Override
	public void getListEtudiants() {


		// TODO Auto-generated method stub
		
		this.setVisible(true);

	}

	@Override
	public void getAjouterEtudiant() {

		
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
        this.setContentPane(panel);
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
				
				int response = JOptionPane.showConfirmDialog(InterfaceGraphique.this, "Voulez-vous sauvegarder votre modification ?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
				if (response == JOptionPane.YES_OPTION) {
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
