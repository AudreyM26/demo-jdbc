package fr.diginamic.recensement.entites;

public class Region {

	

	private String codeRegion;
	private String nomRegion;
	
	private int nbreHab;
	
	public Region(String codeRegion,String nomRegion) {
		super();
		this.codeRegion = codeRegion;
		this.nomRegion = nomRegion;
	}

	public String toString(){
		String texte = "Région : "+this.getCodeRegion()+" "+this.getNomRegion()+" - Population : "+this.getNbreHab()+" hab";
		return texte;
	}
	
	public String getCodeRegion() {
		return codeRegion;
	}

	public void setCodeRegion(String codeRegion) {
		this.codeRegion = codeRegion;
	}

	public int getNbreHab() {
		return nbreHab;
	}

	public void setNbreHab(int nbreHab) {
		this.nbreHab = nbreHab;
	}

	public String getNomRegion() {
		return nomRegion;
	}

	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}
}
