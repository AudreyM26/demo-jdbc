package fr.diginamic.jdbc.dao;

import java.util.List;

import fr.diginamic.jdbc.entites.Article;

/***
 * methodes a parir de la base de données
 * - lister les articles
 * - insertion d article
 * - mise a jour d article
 * - suppression d article
 * @author audrey
 *
 */

public interface ArticleDao {

	List<Article> extraire();
	void insert(Article article);
	int update(int nouvelleValeur);
	boolean delete();
}
