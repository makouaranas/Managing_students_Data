package mainPackage;

import java.sql.SQLException;


public class MainClass {
	
	public static void main(String[] args) throws SQLException {
			StudentsDatabase data = new StudentsDatabase("students");
			//data.modifierEtudiant("P142063053", new Students("P142063053","anas","ezz",new Date(),"hi@gmail.com"));
			
			data.supprimerEtudiant("Y681871");
			
	}

}
