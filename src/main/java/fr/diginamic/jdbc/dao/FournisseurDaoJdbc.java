package fr.diginamic.jdbc.dao;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.DbManager;
import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	
	@Override
	public List<Fournisseur> extraire() {
		// TODO Auto-generated method stub
		List<Fournisseur> fournisseurs = new ArrayList<>();
		
		fournisseurs = new DbManager().executeQuery("select id,nom from fournisseur");
		
		return fournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		String nom = fournisseur.getNom().replace("'", "''");
		String requete = "insert ignore into fournisseur (ID,NOM) values("+fournisseur.getId()+",'"+nom+"')";
		//System.out.println(requete);
		new DbManager().executeUpdate(requete);
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		// TODO Auto-generated method stub
		String requete = "update fournisseur set nom='"+nouveauNom+"' where nom ='"+ancienNom+"'";
		//System.out.println(requete);
		return new DbManager().executeUpdate(requete);
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		Boolean suppr = false;
		String requete = "delete from fournisseur where id="+fournisseur.getId();
		//System.out.println(requete);
		int nb = new DbManager().executeUpdate(requete);
		if(nb > 0){
			suppr= true;
		}
		return suppr;
	}

}
