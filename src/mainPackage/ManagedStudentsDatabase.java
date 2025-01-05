package mainPackage;

import java.util.ArrayList;

public class ManagedStudentsDatabase implements DataBase{

	public ManagedStudentsDatabase() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ajouterEtudiant(StudentsClass etudiant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierEtudiant(String codeMassar, StudentsClass nouvelleInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void supprimerEtudiant(String codeMassar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<StudentsClass> afficherEtudiants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentsClass rechercherEtudiantParNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentsClass rechercherEtudiantParPrenom(String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentsClass rechercherEtudiantParEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStudentExist(String codeMassar) {
		// TODO Auto-generated method stub
		return false;
	}

}