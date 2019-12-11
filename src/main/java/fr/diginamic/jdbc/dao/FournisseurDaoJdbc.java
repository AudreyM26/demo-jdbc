package fr.diginamic.jdbc.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.DbManager;
import fr.diginamic.jdbc.entites.Fournisseur;

public class FournisseurDaoJdbc implements FournisseurDao {

	private static DbManager dbMan = new DbManager();
	
	@Override
	public List<Fournisseur> extraire() {
		// TODO Auto-generated method stub
		List<Fournisseur> fournisseurs = new ArrayList<>();
		
		ResultSet resultat = dbMan.executeQuery("select id,nom from fournisseur");
		//fournisseurs = new DbManager().executeQuery("select id,nom from fournisseur");
		try {
			while (resultat.next()){
				Integer id = resultat.getInt("ID");
				String nom = resultat.getString("NOM");
				
				Fournisseur four = new Fournisseur(id,nom);
				fournisseurs.add(four);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return fournisseurs;
	}

	@Override
	public void insert(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		String nom = fournisseur.getNom().replace("'", "''");
		String requete = "insert ignore into fournisseur (ID,NOM) values("+fournisseur.getId()+",'"+nom+"')";
		//System.out.println(requete);
		dbMan.executeUpdate(requete);
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		// TODO Auto-generated method stub
		String requete = "update fournisseur set nom='"+nouveauNom+"' where nom ='"+ancienNom+"'";
		//System.out.println(requete);
		return dbMan.executeUpdate(requete);
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		Boolean suppr = false;
		String requete = "delete from fournisseur where id="+fournisseur.getId();
		//System.out.println(requete);
		int nb = dbMan.executeUpdate(requete);
		if(nb > 0){
			suppr= true;
		}
		return suppr;
	}

}
