package mainPackage;


public class MainClass {

	public static void main(String[] args) {
		InterfaceGraphique gui = new InterfaceGraphique();
		gui.getModifierEtudiant(new Students("Y131013551", "Ayoub", "EZZAOUYAH", new Date(), "exemple@domain.com"));
		
}
}