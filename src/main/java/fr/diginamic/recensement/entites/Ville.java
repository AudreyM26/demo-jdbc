package fr.diginamic.recensement.entites;

public class Ville {

	private String codeCommune;
	private String nomCommune;
	
	private Departement departement;
	private Region region;
	
	private int population;
	
	public Ville(String codeCommune, String nomCommune, int population, Departement departement, Region region) {
		super();
		this.codeCommune = codeCommune;
		this.nomCommune = nomCommune;
		this.population = population;
		this.departement = departement;
		this.region = region;
	}
	
	public String toString(){
		String texte = "Région : "+this.getRegion().getNomRegion()+" - Département : "+this.getDepartement().getCodeDepartement()+" - Commune : "+this.getCodeCommune()+" "+this.getNomCommune()+" - Population : "+this.getPopulation()+" hab";
		return texte;
	}


	public String getCodeCommune() {
		return codeCommune;
	}

	public void setCodeCommune(String codeCommune) {
		this.codeCommune = codeCommune;
	}

	public String getNomCommune() {
		return nomCommune;
	}

	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	/**
	 * @return the departement
	 */
	public Departement getDepartement() {
		return departement;
	}

	/**
	 * @param departement the departement to set
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	/**
	 * @return the region
	 */
	public Region getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(Region region) {
		this.region = region;
	}

	
	
	
	
}
