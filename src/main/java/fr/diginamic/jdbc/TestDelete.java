package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestDelete {

	public static void main(String[] args) {

		//new DbManager().executeUpdate("delete from fournisseur where id=4");
		
		FournisseurDaoJdbc fournisseurDaoJdbc = new FournisseurDaoJdbc();
		Fournisseur fournisseur = new Fournisseur(4,"La Maison de la Peinture");

		boolean suppr = fournisseurDaoJdbc.delete(fournisseur);
		
		System.out.println("Ligne supprimée : "+suppr);
		
	}

}
