package mainPackage;

 
public class MainClass{

	public static void main(String[] args) {
				
				StudentsDatabase stdb= new StudentsDatabase("students");
				Students student = new  Students("P142063053", "ANAS", "MAKOUAR", new Date(), "anasmakouar@gmail.com");
				stdb.ajouterEtudiant(student);
				InterfaceGraphique gui = new InterfaceGraphique();
				gui.getAccueil();
				gui.setVisible(true);
				

	
	}

}
