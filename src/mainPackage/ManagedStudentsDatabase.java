package mainPackage;

import java.sql.*;

public class ManagedStudentsDatabase {
	private String JdbkDriver ="com.mysql.cj.jdbc.Driver";
	private String dbName = "here_where_i_will_test";
	private String tableName = "test1";
	private String myquery = "your query here";
	private String url = "jdbc:mysql://localhost:3306/"+dbName;
	private String username ="root";
	private String password ="";
	Connection con =null;
	Statement stm =null;
	public ManagedStudentsDatabase() {
		
		try {
			
			Class.forName(JdbkDriver);
			con = DriverManager.getConnection(url,username,password);
			
			stm = con.createStatement();
			stm.executeUpdate(myquery);
			System.out.println("done");
		} catch(ClassNotFoundException e) {	
			System.out.println("There is a problem maybe class not found");
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("There is a problem whith your daba conncetion");			
		}
	}
	public void addStudent(StudentsClass student) {
		
		
	}
	public void modifyStudent(StudentsClass student) {
		
		
	}
	public void removeStudent(StudentsClass student) {
		
		
	}
	public void displayAllStudents() {
		
		
	}
	public StudentsClass searchForStudent(StudentsClass student) {
		
		
		return null;
		
	}
	public String getJdbkDriver() {
		return JdbkDriver;
	}
	public void setJdbkDriver(String jdbkDriver) {
		JdbkDriver = jdbkDriver;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getMyquery() {
		return myquery;
	}
	public void setMyquery(String myquery) {
		this.myquery = myquery;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tablename) {
		tableName = tablename;
	}
	
	
	
}


