package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import fr.diginamic.jdbc.entites.Fournisseur;

public class TestSelect {

	public static void main(String[] args) {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		Connection maConnection = null;
		Statement stmt = null;
		ResultSet curseur = null;
		
		ArrayList<Fournisseur> fournisseurs = new ArrayList<>();
		
		try {

			ResourceBundle monFichierConf = ResourceBundle.getBundle("databases");
			String urlDB = monFichierConf.getString("database.url");
			String userDB = monFichierConf.getString("database.user");
			String passwordDB = monFichierConf.getString("database.password");

			maConnection = DriverManager.getConnection(urlDB, userDB, passwordDB);

			stmt = maConnection.createStatement();

			curseur = stmt.executeQuery("select id,nom from fournisseur");
			
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
				curseur.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Erreur fermeture de resultset");
			}
			
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
				System.out.println("Erreur fermeture de statement");
			}

			try {
				maConnection.close();
			} catch (SQLException e3) {
				e3.printStackTrace();
				System.out.println("Erreur fermeture de connexion");
			}
		}
		
		System.out.println("Affichage liste de fournisseurs");
		
		for(Fournisseur f : fournisseurs){
			System.out.println(f.getId()+" / "+f.getNom());
		}
	}
}
