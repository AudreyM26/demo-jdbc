package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestUpdate {

	public static void main(String[] args) {
		
		//new DbManager().executeUpdate("update fournisseur set nom='La Maison des Peintures' where id=4");
		
		FournisseurDaoJdbc fournisseurDaoJdbc = new FournisseurDaoJdbc();
		Fournisseur fournisseur = new Fournisseur(4,"La Maison de la Peinture");

		int nbre_ligne = fournisseurDaoJdbc.update(fournisseur.getNom(),"La Maison des Peintures");
		
		System.out.println("Ligne modifiée : "+nbre_ligne);
	}

}
