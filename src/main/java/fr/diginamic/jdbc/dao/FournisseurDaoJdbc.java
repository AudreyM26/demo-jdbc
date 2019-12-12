package fr.diginamic.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.DbManager;
import fr.diginamic.jdbc.entites.Fournisseur;

/***
 * classe permettant de traiter les requetes 
 * @author audrey
 *
 */

public class FournisseurDaoJdbc implements FournisseurDao {

	private static DbManager dbMan = new DbManager();
	List<Object> valeurAttribut = new ArrayList<>();
	
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
			resultat.close();
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
		//String requete = "insert ignore into fournisseur (ID,NOM) values("+fournisseur.getId()+",'"+nom+"')";
		
		String requete = "insert ignore into fournisseur(ID,NOM) values (?,?)";
		valeurAttribut.removeAll(valeurAttribut);
		valeurAttribut.add(fournisseur.getId());
		valeurAttribut.add(nom);
		
		//System.out.println(requete);
		dbMan.executeUpdate(requete,valeurAttribut);
	}

	@Override
	public int update(String ancienNom, String nouveauNom) {
		// TODO Auto-generated method stub
		//String requete = "update fournisseur set nom='"+nouveauNom+"' where nom ='"+ancienNom+"'";
		
		String requete = "update fournisseur set nom=? where nom =?";
		//System.out.println(requete);
		valeurAttribut.removeAll(valeurAttribut);
		valeurAttribut.add(nouveauNom);
		valeurAttribut.add(ancienNom);
		return dbMan.executeUpdate(requete,valeurAttribut);
	}

	@Override
	public boolean delete(Fournisseur fournisseur) {
		// TODO Auto-generated method stub
		Boolean suppr = false;
		//String requete = "delete from fournisseur where id="+fournisseur.getId();
		String requete = "delete from fournisseur where id=?";
		//System.out.println(requete);
		valeurAttribut.removeAll(valeurAttribut);
		valeurAttribut.add(fournisseur.getId());
		
		int nb = dbMan.executeUpdate(requete,valeurAttribut);
		if(nb > 0){
			suppr= true;
		}
		return suppr;
	}

	public void close(){
		dbMan.close();
	}
}
