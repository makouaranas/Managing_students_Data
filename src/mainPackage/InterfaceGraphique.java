package mainPackage;

import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InterfaceGraphique extends JFrame implements userGraphic{
	
	private ImageIcon appLogo;
	public InterfaceGraphique() {
		this.setTitle(" Gestion des Étudiants (beta)");
		appLogo = new ImageIcon("resources/AppLogo.png");
		this.setIconImage(appLogo.getImage());
		this.setSize(1000,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void getAccueil() {
		
		Color sideMenuColor = new Color(0xB6B6B6);
		JPanel homePanel = new JPanel(new BorderLayout());
		JPanel sideMenu = new JPanel(new BorderLayout());
		JPanel dashboardPanel = new JPanel();
		JPanel optionSideMenu = new JPanel(new GridLayout(6, 1));

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
	}

	@Override
	public void getListEtudiants() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getAjouterEtudiant() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getModifierEtudiant() {
		// TODO Auto-generated method stub
		
	}

}
