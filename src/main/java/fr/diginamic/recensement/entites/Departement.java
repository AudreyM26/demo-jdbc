package fr.diginamic.recensement.entites;

public class Departement {

	private String codeDepartement;
	private int nbreHab;

	public Departement(String codeDepartement) {
		super();
		this.codeDepartement = codeDepartement;
	}

	public String toString(){
		String texte = "Département : "+this.getCodeDepartement()+" - Population : "+this.getNbreHab()+" hab";
		return texte;
	}
	
	public String getCodeDepartement() {
		return codeDepartement;
	}

	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	public int getNbreHab() {
		return nbreHab;
	}

	public void setNbreHab(int nbreHab) {
		this.nbreHab = nbreHab;
	}
	
	
}
