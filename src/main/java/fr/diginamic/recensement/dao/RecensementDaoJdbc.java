package fr.diginamic.recensement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.DbManager;
import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;
import fr.diginamic.recensement.entites.*;

public class RecensementDaoJdbc implements RecensementDao {

	private static DbManager dbMan = new DbManager();
	
	List<Object> valeurAttribut = new ArrayList<>();
	
	@Override
	public List<Ville> extraire(String requete) {
		// TODO Auto-generated method stub
		List<Ville> villes = new ArrayList<>();
		ResultSet resultat = dbMan.executeQuery(requete);
		
		try {
			while (resultat.next()){
				String codeVille = resultat.getString("CODE_VILLE");
				String nomCommune = resultat.getString("v.NOM");
				Integer population = resultat.getInt("POPULATION");
				String codeDept = resultat.getString("CODE_DEPT");
				String codeRegion = resultat.getString("CODE");
				String nomRegion = resultat.getString("r.NOM");
				
				Ville v = new Ville(codeVille,nomCommune,population,new Departement(codeDept),new Region(codeRegion,nomRegion));
				villes.add(v);
			}
			resultat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return villes;

	}

	public String select (String requete) {
		
		ResultSet resultat = dbMan.executeQuery(requete);
	
		String result="";
		
		try {
			while (resultat.next()){
			
				result = resultat.getString(1);
				
			}
			resultat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public void insert(Ville ville) {
		
		String requeteVilles = "insert ignore into villes (CODE_VILLE,NOM,POPULATION,CODE_DEPT) values(?,?,?,?);";
		
		valeurAttribut.removeAll(valeurAttribut);
		valeurAttribut.add(ville.getCodeCommune());
		valeurAttribut.add(ville.getNomCommune());
		valeurAttribut.add(ville.getPopulation());
		valeurAttribut.add(ville.getDepartement().getCodeDepartement());
		
		dbMan.executeUpdate(requeteVilles,valeurAttribut);
		
		String requeteDepts = "insert ignore into departements (CODE,CODE_REGION) values(?,?);";
		
		valeurAttribut.removeAll(valeurAttribut);
		valeurAttribut.add(ville.getDepartement().getCodeDepartement());
		valeurAttribut.add(ville.getRegion().getCodeRegion());
		
		dbMan.executeUpdate(requeteDepts,valeurAttribut);
		String requeteRegions = "insert ignore into regions (CODE,NOM) values(?,?);";
	
		valeurAttribut.removeAll(valeurAttribut);
		valeurAttribut.add(ville.getRegion().getCodeRegion());
		valeurAttribut.add(ville.getRegion().getNomRegion());
		
		
		dbMan.executeUpdate(requeteRegions,valeurAttribut);
		//dbMan.close();
	}

	@Override
	public int update(int nouvelleValeur) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public void close(){
		dbMan.close();
	}

}
