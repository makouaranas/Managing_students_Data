package mainPackage;

public class StudentsClass {
	private String cne;
	private String firstName;
	private String lastName;
	private String email;
	private Date date;
	

	public StudentsClass(String cne,String firstName,String lastName,Date date,String email) {
		this.cne = cne;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.date = date;
		
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getCne() {
		return cne;
	}


	public void setCne(String cne) {
		this.cne = cne;
	}


	@Override
	public String toString() {
		return String.format("""
				First name : %s	
				Last name : %s
				Birthday : %s
				Email: %s
				""",firstName,lastName,date.toString(),email);
	}
	
	

}
