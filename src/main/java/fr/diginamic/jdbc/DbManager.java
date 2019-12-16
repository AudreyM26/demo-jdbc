package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	//private static final Logger LOG = LoggerFactory.getLogger(DbManager.class);
	
	AppLogback logService = new AppLogback();
	

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
				logService.executer("Connexion : executeUpdate");
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
			
			logService.executer("executeUpdate : "+requete);
			
		} catch (SQLException e) {
			// Traitement de l'exception si elle se produit
			e.printStackTrace();
			System.out.println(e.getMessage());
			logService.executer("executeUpdate exception SQL : "+requete);
			
			// annuler transaction
			try {
				if(maConnection != null){
					maConnection.rollback();
					logService.executer("executeUpdate transaction annulée ");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Erreur sur l annulation de la transaction");
				logService.executer("executeUpdate : erreur sur annulation de la transaction");
			}
		} finally {
			// Fermeture des ressources: resultSet, statement, connexion.

			// valider transaction
			try {
				if(maConnection != null){
					maConnection.commit();
					logService.executer("executeUpdate : commit ");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erreur sur la validation de la transaction");
				logService.executer("executeUpdate : erreur sur validation de la transaction");
			}

			try {
				if(stmt != null){
					stmt.close();
					logService.executer("executeUpdate : statement fermé");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Erreur fermeture de statement");
				logService.executer("executeUpdate : erreur fermeture statement");
			}

			//close();
		}
		
		return nb;

	}
	
	public void close(){
		try {
			if(maConnection != null){
				maConnection.close();
				logService.executer("connexion fermée");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			System.out.println("Erreur fermeture de connexion");
			logService.executer("Erreur fermeture de connexion");
		}	
	}
	
	
	public ResultSet executeQuery(String requete){	
		
		
		ResultSet curseur = null;
		Statement stmt = null;
		
		try {
			
			if(maConnection == null){
				maConnection = getConnection();
				logService.executer("Connexion : executeQuery");
			}
			
			stmt = maConnection.createStatement();

			curseur = stmt.executeQuery(requete);
			
			logService.executer("executeQuery : "+requete);

		} catch (SQLException e) {
			// Traitement de l'exception si elle se produit
			e.printStackTrace();
			System.out.println(e.getMessage());
			logService.executer("executeQuery exception SQL : "+requete);
			
			// annuler transaction
			try {
				if(maConnection != null){
					maConnection.rollback();
					logService.executer("executeQuery transaction annulée");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Erreur sur l annulation de la transaction");
				logService.executer("executeQuery : erreur sur annulation de la transaction");
			}
		} finally {
			// Fermeture des ressources: resultSet, statement, connexion.

			// valider transaction
			try {
				if(maConnection != null){
					maConnection.commit();
					logService.executer("executeQuery : commit");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Erreur sur la validation de la transaction");
				logService.executer("executeQuery : erreur sur validation de la transaction");
			}

			try {
				if(stmt != null){
					stmt.close();
					logService.executer("executeQuery : statement fermé");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Erreur fermeture de statement");
				logService.executer("executeQuery : erreur fermeture statement");
			}

			//close();

		}
		
	
		return curseur;
		
	}
}