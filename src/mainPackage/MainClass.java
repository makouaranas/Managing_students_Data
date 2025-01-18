package mainPackage;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainClass{

	public static void main(String[] args) {
				
//				StudentsDatabase stdb= new StudentsDatabase("students");
//				Students student = new  Students("P12345678", "ANAS", "SAYD", new Date(), "HELLOSAYD@gmail.com");
				
			InterfaceGraphique gui = new InterfaceGraphique();
//				gui.getAccueil();		
				gui.getAjouterEtudiant();
			gui.setVisible(true);
				
				

	
	}

}
