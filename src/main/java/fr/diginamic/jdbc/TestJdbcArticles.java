package fr.diginamic.jdbc;

import java.util.List;

import fr.diginamic.jdbc.dao.ArticleDaoJdbc;
import fr.diginamic.jdbc.dao.FournisseurDaoJdbc;
import fr.diginamic.jdbc.entites.Article;
import fr.diginamic.jdbc.entites.Fournisseur;

/***
 * ajout nouveau fournisseur
 * ajout 4 nouveaux articles
 * modifier le prix des articles contenant le mot mate
 * afficher la liste des articles
 * afficher la moyenne des prix des articles
 * supprimer le fournisseur
 * supprimer les articles contenant le mot Peinture
 * @author audrey
 *
 */
public class TestJdbcArticles {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DbManager.URL_DB = "database.urlcompta";
	
		long b = System.currentTimeMillis();
		FournisseurDaoJdbc fournisseurDaoJdbc = new FournisseurDaoJdbc();
		Fournisseur fournisseurPeinture = new Fournisseur(4,"La Maison de la Peinture");
		fournisseurDaoJdbc.insert(fournisseurPeinture);
		
		//fournisseurDaoJdbc.update("La Maison de la Peinture","La Maison des Peintures");
	
		ArticleDaoJdbc articleDaoJdbc = new ArticleDaoJdbc();
		
		Article peintureBlanche = new Article(11,"PB1","Peinture blanche 1L",12.50,fournisseurPeinture); 
		Article peintureRouge = new Article(12,"PRM1","Peinture rouge mate 1L",15.50,fournisseurPeinture); 
		Article peintureNoire = new Article(13,"PNL1","Peinture noire laquée 1L",17.80,fournisseurPeinture); 
		Article peintureBleue = new Article(14,"PBM1","Peinture bleue mate 1L",15.50,fournisseurPeinture); 
		
		articleDaoJdbc.insert(peintureBlanche);
		articleDaoJdbc.insert(peintureRouge);
		articleDaoJdbc.insert(peintureNoire);
		articleDaoJdbc.insert(peintureBleue);
		
		articleDaoJdbc.update(25);
		
		
		List<Article> articles = articleDaoJdbc.extraire();
		
		System.out.println("Affichage liste des articles");
		
		for(Article a : articles){
			System.out.println(a);
		}
		
		System.out.println("\nLa moyenne des prix des articles : "+articleDaoJdbc.moyennePrixArt()+"€ \n");
		
		boolean suppArt = articleDaoJdbc.delete();
		
		if(suppArt){
			System.out.println("Les articles contenant 'Peinture' ont bien été supprimé");	
		}
		
		String fournisseurNomSupp = fournisseurPeinture.getNom();
		boolean suppr = fournisseurDaoJdbc.delete(fournisseurPeinture);
		
		if(suppr){
			System.out.println("Le fournisseur '"+fournisseurNomSupp+"' a bien été supprimé");
		}
		
		long c = System.currentTimeMillis();
		System.out.println(c-b);
		fournisseurDaoJdbc.close();
		articleDaoJdbc.close();
	}

}
