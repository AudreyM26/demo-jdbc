package fr.diginamic.jdbc.entites;

public class Article {

	private int id;
	private String reference;
	private String designation;
	private double prix;

	private Fournisseur fournisseur;

	public Article(int id, String reference, String designation, double prix, Fournisseur fournisseur) {
		super();
		this.id = id;
		this.reference = reference;
		this.designation = designation;
		this.prix = prix;
		this.fournisseur = fournisseur;
	}

	public String toString() {

		String texte = this.getId() + "  : " + this.getReference() + " : " + this.getDesignation() + " : " +this.getPrix()+"€ : "+this.getFournisseur().getNom();

		return texte;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 *            the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * @return the fournisseur
	 */
	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	/**
	 * @param fournisseur
	 *            the fournisseur to set
	 */
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

}
