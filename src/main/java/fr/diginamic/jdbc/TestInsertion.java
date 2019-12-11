package fr.diginamic.jdbc;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestInsertion {

	public static void main(String[] args) {
		
		//new DbManager().executeUpdate("insert ignore into fournisseur (ID,NOM) values(4,'La Maison de la Peinture')");

		FournisseurDaoJdbc fournisseurDaoJdbc = new FournisseurDaoJdbc();
		Fournisseur fournisseur = new Fournisseur(4,"La Maison de la Peinture");
		fournisseurDaoJdbc.insert(fournisseur);
		
		Fournisseur fournisseur2 = new Fournisseur(5,"L'Espace Création");
		fournisseurDaoJdbc.insert(fournisseur2);
	}
}
