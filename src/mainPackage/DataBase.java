package mainPackage;

import java.util.ArrayList;

public interface DataBase {
	
	/***
	 * This method can add a student info to the database by taking its information
	 * from the object of type StudentClass and assign those information to the respective columns in the database 
	 * @param etudiant an object of type StudentClass that holds the Student info
	 */
	public void ajouterEtudiant(Students etudiant);
	
	/***
	 * This method modify the student info in the database, it takes a String that holds the "codeMassar" for the student "its a unique identifier that used to search for the student in the database",
	 *  and an object of type StudentClass that holds the new data the we want to change.
	 * @param codeMassar a String that holds the unique identifier for each student, used to search for the student in the database.
	 * @param nouvelleInfo an object of type StudentClass that holds the new data.
	 */
	public void modifierEtudiant(String codeMassar , Students nouvelleInfo);
	
	/***
	 * This method delete student data from the database, it takes a unique identifier of type String to search for the student that we want.
	 * @param codeMassar a String thats hold the unique identifier for each student, used to search for the student in the database.
	 */
	public void supprimerEtudiant(String codeMassar);
	
	/***
	 * This method return an ArrayList of type StudentClass that holds all that students data in the database. 
	 * @return of type {@code ArrayList<StudentClass;} that holds all the student data that we have in the database
	 */
	public ArrayList<Students> afficherEtudiants();
	
	/***
	 * This method search the entire database looking for Student with the same last name, and returns an object of type StudentClass of that student.
	 * @param nom String that holds the last name of the Student that we search for.
	 * @return an object of type StudentClass that holds the data of the student that we search for.
	 */
	public Students rechercherEtudiantParNom(String nom);
	
	/***
	 * This method search the entire database looking for Student with the same first name, and returns an object of type StudentClass of that student.
	 * @param prenom String that holds the first name of the Student that we search for.
	 * @return an object of type StudentClass that holds the data of the student that we search for.
	 */
	public Students rechercherEtudiantParPrenom(String prenom);
	
	/***
	 * This method search the entire database looking for Student with the same e-mail, and returns an object of type StudentClass of that student.
	 * @param email String that holds the e-mail of the Student that we search for.
	 * @return an object of type StudentClass that holds the data of the student that we search for.
	 */
	public Students rechercherEtudiantParEmail(String email);
	
	/***
	 * This method search the entire database looking for the student with the same unique identifier "codeMassar", and return true if the student exist. 
	 * @param codeMassar a String thats hold the unique identifier for each student, used to search for the student in the database.
	 * @return of type boolean that return true if the student exist.
	 */
	public boolean isStudentExist(String codeMassar);
}
