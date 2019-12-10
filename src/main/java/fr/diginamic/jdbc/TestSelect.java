package fr.diginamic.jdbc;

import java.util.ArrayList;
import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {
		
		ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
		
		fournisseurs = new DbManager().executeQuery("select id,nom from fournisseur");
		
		System.out.println("Affichage liste de fournisseurs");
		
		for(Fournisseur f : fournisseurs){
			System.out.println(f.getId()+" / "+f.getNom());
		}
	}
}
