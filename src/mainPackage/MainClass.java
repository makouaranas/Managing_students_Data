package mainPackage;


import java.sql.SQLException;
import java.util.ArrayList;

public class MainClass{

	public static void main(String[] args) {

				
		
			InterfaceGraphique gui = new InterfaceGraphique();
//				gui.getAccueil();		
//				gui.getAjouterEtudiant();
		
		gui.getModifierEtudiant(new Students("Y131013551", "Ayoub", "EZZAOUYAH", new Date(), "exemple@domain.com"));
						
//				StudentsDatabase stdb= new StudentsDatabase("students_database");
//				Students student = new  Students("P142063053", "ANAS", "MAKOUAR", new Date(), "anasmakouar@gmail.com");
//				stdb.ajouterEtudiant(student);
//				gui.getAccueil();
//				gui.setVisible(true);

				
	}	

}
