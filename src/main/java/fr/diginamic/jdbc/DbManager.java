package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;


public class DbManager {

	ResourceBundle monFichierConf = ResourceBundle.getBundle("databases");
	String urlDB = monFichierConf.getString("database.url");
	String userDB = monFichierConf.getString("database.user");
	String passwordDB = monFichierConf.getString("database.password");
	String driverDB = monFichierConf.getString("database.driver");
	public static boolean CLASS_DRIVER = false;

	public Connection getConnection() {

		Connection maConnection = null;

		try {
			maConnection = DriverManager.getConnection(urlDB, userDB, passwordDB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return maConnection;

	}

	public boolean isClass() {
		if(CLASS_DRIVER == false){
			try {
				Class.forName(driverDB);
				CLASS_DRIVER = true;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				CLASS_DRIVER = false;
			}
		}
		return CLASS_DRIVER;
	}

	public void executeUpdate(String requete) {

		if (this.isClass()) {
			Connection maConnection = null;
			maConnection = this.getConnection();

			Statement stmt = null;

			try {
				maConnection = DriverManager.getConnection(urlDB, userDB, passwordDB);

				stmt = maConnection.createStatement();

				stmt.executeUpdate(requete);

			} catch (SQLException e) {
				// Traitement de l'exception si elle se produit
				e.printStackTrace();
				System.out.println(e.getMessage());

				// annuler transaction
				try {
					maConnection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
					System.out.println("Erreur sur l annulation de la transaction");
				}
			} finally {
				// Fermeture des ressources: resultSet, statement, connexion.

				// valider transaction
				try {
					maConnection.commit();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Erreur sur la validation de la transaction");
				}

				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
					System.out.println("Erreur fermeture de statement");
				}

				try {
					maConnection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
					System.out.println("Erreur fermeture de connexion");
				}
			}
		}

	}
	
	public ArrayList<Fournisseur> executeQuery(String requete) {
		
		ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
		
		if (this.isClass()) {
			Connection maConnection = null;
			maConnection = this.getConnection();

			Statement stmt = null;
			ResultSet curseur = null;
			
			try {
				stmt = maConnection.createStatement();

				curseur = stmt.executeQuery(requete);
				
				while (curseur.next()){
					Integer id = curseur.getInt("ID");
					String nom = curseur.getString("NOM");
					
					Fournisseur four = new Fournisseur(id,nom);
					fournisseurs.add(four);
				}

			} catch (SQLException e) {
				// Traitement de l'exception si elle se produit
				e.printStackTrace();
				System.out.println(e.getMessage());

				// annuler transaction
				try {
					maConnection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
					System.out.println("Erreur sur l annulation de la transaction");
				}
			} finally {
				// Fermeture des ressources: resultSet, statement, connexion.

				// valider transaction
				try {
					maConnection.commit();
				} catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Erreur sur la validation de la transaction");
				}

				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
					System.out.println("Erreur fermeture de statement");
				}

				try {
					maConnection.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
					System.out.println("Erreur fermeture de connexion");
				}
			}
		}
	
		return fournisseurs;
	}
}