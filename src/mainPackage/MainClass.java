package mainPackage;
import java.sql.SQLException;


public class MainClass {
	
	public static void main(String[] args) throws SQLException {
			StudentsDatabase data = new StudentsDatabase("students");
		
			InterfaceGraphique gui = new InterfaceGraphique();
			gui.getAccueil();
			
	}
}



		