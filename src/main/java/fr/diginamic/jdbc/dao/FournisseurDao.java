package fr.diginamic.jdbc.dao;

import java.util.List;
import fr.diginamic.jdbc.entites.Fournisseur;

/***
 * methodes a parir de la base de données
 * - lister les fournisseurs
 * - insertion d un fournisseurs
 * - mise a jour d un fournisseurs
 * - suppression d un fournisseurs
 * @author audrey
 *
 */

public interface FournisseurDao {

	List<Fournisseur> extraire();
	void insert(Fournisseur fournisseur);
	int update(String ancienNom, String nouveauNom);
	boolean delete(Fournisseur fournisseur);
	
}
