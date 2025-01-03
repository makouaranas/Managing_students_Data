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
	
	
	// Override toString
	@Override
	public String toString() {
		return this.jour+"/"+this.mois+"/"+this.annee+".";
	}
	
	
}
