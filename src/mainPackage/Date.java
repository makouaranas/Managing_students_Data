package mainPackage;

public class Date {
	// Les attributes
	private int jour;
	private int mois;
	private int annee;
	
	
	// Les constructeurs
	public Date(int jour, int mois, int annee) {
		super();
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}
	public Date() {
		super();
		this.jour = 1;
		this.mois = 1;
		this.annee = 2000;
	}
	
	// Getters & Setters
	public int getJour() {
		return jour;
	}
	public void setJour(int jour) {
		this.jour = jour;
	}
	public int getMois() {
		return mois;
	}
	public void setMois(int mois) {
		this.mois = mois;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	
	// parsing Dates
	/***
	 * This method takes date as a String in the format {@code dd/mm/yyyy}
	 * and return an object of type Date.
	 * @param rawDate date as a String must be in this format {@code dd/mm/yyyy}.
	 * @return an object of type Date.
	 */
	public Date parseDate(String rawDate) {
		String[] parts = rawDate.split("/");
		this.jour = Integer.parseInt(parts[0]);
		this.mois = Integer.parseInt(parts[1]);
		this.annee = Integer.parseInt(parts[2]);
		return new Date(this.jour,this.mois,this.annee);
	}
	
	// Override toString
	@Override
	public String toString() {
		return this.jour+"/"+this.mois+"/"+this.annee;
	}
	
	
}
