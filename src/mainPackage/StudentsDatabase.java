package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentsDatabase implements DataBase{
	// les atributes
	private static String url = "jdbc:mysql://localhost:3306/";
    private final static String username = "root";
    private final static String password = "";
	private Connection myConnection;

	
	//Constructeur
	//============================================================================
	public StudentsDatabase(String databaseName) {
		// TODO Auto-generated constructor stub
		System.out.println("Connecting to database...");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("There is a problem with the Driver ....");
            throw new RuntimeException(e);
        }
        try {
        	url = url+databaseName;
            myConnection = DriverManager.getConnection(url,username,password);
            System.out.println("Connected to database...");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database...");
            throw new RuntimeException(e);
        }
	}
	//=============================================================================
	
	@Override
	public void ajouterEtudiant(Students etudiant) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifierEtudiant(String codeMassar, Students nouvelleInfo) {
		String query = "UPDATE students SET nome = ?, prenom = ?,`Date de naissance` = ?, `E-mail` = ? WHERE `Code Massar` = ? LIMIT 1";
			if (isStudentExist(codeMassar)){
				try {
				PreparedStatement statement = myConnection.prepareStatement(query);
				statement.setString(1, nouvelleInfo.getLastName());
				statement.setString(2, nouvelleInfo.getFirstName());
				statement.setString(3, nouvelleInfo.getDate().toString());
				statement.setString(4, nouvelleInfo.getEmail());
				statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Failed to Find student with this code Massar .....");
		}

	}

	@Override
	public void supprimerEtudiant(String codeMassar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Students> afficherEtudiants() {
		ArrayList<Students> studentsList = new ArrayList<Students>();
		String query = "select * from students";
		Statement statement = null;
		try {
			statement = myConnection.createStatement();
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				Students student = new Students("", "", "", null, "");
				Date dateNaissance = new Date();
				student.setCne(result.getString("Code Massar"));
				student.setFirstName(result.getString("	Prenom"));
				student.setLastName(result.getString("Nom"));
				student.setEmail(result.getString("E-mail"));
				student.setDate(dateNaissance.parseDate(result.getString("Date de naissance")));
				studentsList.add(student);
			}
			return studentsList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public Students rechercherEtudiantParNom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Students rechercherEtudiantParPrenom(String prenom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Students rechercherEtudiantParEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isStudentExist(String codeMassar) {
		// TODO Auto-generated method stub
		String query ="select * from students where `Code Massar` = ? LIMIT 1";
		PreparedStatement statement = null;
		try {
			statement = myConnection.prepareStatement(query);
			statement.setString(1, codeMassar);
			ResultSet result = statement.executeQuery();
			return result.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	
	
	
	
	
	
	
	
	
}