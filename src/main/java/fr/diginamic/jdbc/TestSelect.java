package fr.diginamic.jdbc;


import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {
		
		List<Fournisseur> fournisseurs = new ArrayList<>();
		
		//fournisseurs = new DbManager().executeQuery("select id,nom from fournisseur");
		
		FournisseurDaoJdbc fournisseurDaoJdbc = new FournisseurDaoJdbc();
		fournisseurs = fournisseurDaoJdbc.extraire();
		
		System.out.println("Affichage liste de fournisseurs");
		
		for(Fournisseur f : fournisseurs){
			System.out.println(f.getId()+" / "+f.getNom());
		}
	}
}
