package fr.diginamic.jdbc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.diginamic.jdbc.DbManager;
import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

public class ArticleDaoJdbc implements ArticleDao {

	private static DbManager dbMan = new DbManager();
	
	List<Object> valeurAttribut = new ArrayList<>();
	
	@Override
	public List<Article> extraire() {
		// TODO Auto-generated method stub
		List<Article> articles = new ArrayList<>();
		ResultSet resultat = dbMan.executeQuery("select * from article a inner join fournisseur f on a.ID_FOURNISSEUR = f.ID order by a.ID");

		try {
			while (resultat.next()){
				Integer id = resultat.getInt("a.ID");
				String ref = resultat.getString("a.REF");
				String designation = resultat.getString("a.DESIGNATION");
				Fournisseur four = new Fournisseur(resultat.getInt("f.ID"),resultat.getString("f.NOM"));
				Article art = new Article(id,ref,designation,resultat.getDouble("PRIX"),four);
				articles.add(art);
			}
			resultat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return articles;
	}
	
	public Double moyennePrixArt (){
		
		Double result =0.0;
		
		ResultSet moy = dbMan.executeQuery("select ROUND(AVG(prix),2) as moyenne from article");
		try {
			while (moy.next()){
				result = moy.getDouble("moyenne");
			}
			moy.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void insert(Article article) {

		String designation = article.getDesignation().replace("'", "''");
		//String requete = "insert ignore into article (ID,REF,DESIGNATION,PRIX,ID_FOURNISSEUR) values("+article.getId()+",'"+article.getReference()+"','"+designation+"',"+article.getPrix()+","+article.getFournisseur().getId()+");";
		String requete = "insert ignore into article (ID,REF,DESIGNATION,PRIX,ID_FOURNISSEUR) values(?,?,?,?,?);";
		
		//System.out.println(requete);
		valeurAttribut.removeAll(valeurAttribut);
		
		valeurAttribut.add(article.getId());
		valeurAttribut.add(article.getReference());
		valeurAttribut.add(designation);
		valeurAttribut.add(article.getPrix());
		valeurAttribut.add(article.getFournisseur().getId());
		dbMan.executeUpdate(requete,valeurAttribut);

	}

	@Override
	public int update(int nouvelleValeur) {
		// TODO Auto-generated method stub
		//String requete = "update article set prix= prix-(prix*"+nouvelleValeur+"/100) where designation like '%mate%'";
		String requete = "update article set prix= prix-(prix*?/100) where designation like '%mate%'";
		
		valeurAttribut.removeAll(valeurAttribut);
		valeurAttribut.add(nouvelleValeur);
		//System.out.println(requete);
		return dbMan.executeUpdate(requete,valeurAttribut);
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		Boolean suppr = false;
		String requete = "delete from article where designation like '%Peinture%'";
		//System.out.println(requete);
		valeurAttribut.removeAll(valeurAttribut);
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
