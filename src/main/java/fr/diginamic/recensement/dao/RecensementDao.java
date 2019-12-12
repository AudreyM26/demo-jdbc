package fr.diginamic.recensement.dao;

import java.util.List;

import fr.diginamic.recensement.entites.*;


public interface RecensementDao {

	List<Ville> extraire(String requete);
	void insert(Ville ville);
	int update(int nouvelleValeur);
	boolean delete();
}
