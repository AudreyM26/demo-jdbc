package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

/***
 * methode de connexion a la base de données
 * executionUpdate (insert, update et delete
 * executionQuery (select) retourne ResultSet
 * @author audrey
 *
 */

public class DbManager {
	
	public static String URL_DB = "database.urlcompta";
	ResourceBundle monFichierConf = ResourceBundle.getBundle("databases");
	private String urlDB;
	private String userDB;
	private String passwordDB;
	private String driverDB;
	public static boolean CLASS_DRIVER = false;
	
	Connection maConnection = null;

	public DbManager(){
		
		urlDB = monFichierConf.getString(URL_DB);
		userDB = monFichierConf.getString("database.user");
		passwordDB = monFichierConf.getString("database.password");
		driverDB = monFichierConf.getString("database.driver");
		
		try {
			Class.forName(driverDB);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		    
	}
	
	public Connection getConnection(){
		try {
			return DriverManager.getConnection(urlDB, userDB, passwordDB);
		} catch (SQLException e) {
			throw new RuntimeException("Impossible de récupérer une nouvelle connexion sur la base de données.");
		}
	}
	

	public int executeUpdate(String requete, List<Object> liste_attributs) {

		int nb = 0;

		//Statement stmt = null;
		PreparedStatement stmt =null;
		try {
			if(maConnection == null){
				maConnection = getConnection();
			}
			
			//stmt = maConnection.createStatement();
			
			//nb = stmt.executeUpdate(requete);
			int cpt = 1;
			stmt = maConnection.prepareStatement(requete);
			for (Object l :liste_attributs){
				stmt.setObject(cpt, l);
				//System.out.println(l);
				cpt +=1;
			}
			
			nb = stmt.executeUpdate();
		} catch (SQLException e) {
			// Traitement de l'exception si elle se produit
			e.printStackTrace();
			System.out.println(e.getMessage());

			// annuler transaction
			try {
				if(maConnection != null){
					maConnection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Erreur sur l annulation de la transaction");
			}
		} finally {
			// Fermeture des ressources: resultSet, statement, connexion.

			// valider transaction
			try {
				if(maConnection != null){
					maConnection.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erreur sur la validation de la transaction");
			}

			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Erreur fermeture de statement");
			}
			/*
			try {
				if(maConnection != null){
					maConnection.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
				System.out.println("Erreur fermeture de connexion");
			}
			*/
			//close();
		}
		
		return nb;

	}
	
	public void close(){
		try {
			if(maConnection != null){
				maConnection.close();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("Erreur fermeture de connexion");
		}	
	}
	
	
	public ResultSet executeQuery(String requete){	
		
		
		ResultSet curseur = null;
		Statement stmt = null;
		
		
		try {
			maConnection = getConnection();
			stmt = maConnection.createStatement();

			curseur = stmt.executeQuery(requete);
			
			/*
			while (curseur.next()){
				Integer id = curseur.getInt("ID");
				String nom = curseur.getString("NOM");
				
				Fournisseur four = new Fournisseur(id,nom);
				fournisseurs.add(four);
			}*/

		} catch (SQLException e) {
			// Traitement de l'exception si elle se produit
			e.printStackTrace();
			System.out.println(e.getMessage());

			// annuler transaction
			try {
				if(maConnection != null){
					maConnection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Erreur sur l annulation de la transaction");
			}
		} finally {
			// Fermeture des ressources: resultSet, statement, connexion.

			// valider transaction
			try {
				if(maConnection != null){
					maConnection.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erreur sur la validation de la transaction");
			}

			try {
				if(stmt != null){
					stmt.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Erreur fermeture de statement");
			}

			//close();

		}
		
	
		return curseur;
		
	}
}