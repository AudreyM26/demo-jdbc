package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TestDelete {

	public static void main(String[] args) {

		try {
			Class.forName("org.mariadb.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		Connection maConnection = null;
		Statement stmt = null;

		try {

			ResourceBundle monFichierConf = ResourceBundle.getBundle("databases");
			String urlDB = monFichierConf.getString("database.url");
			String userDB = monFichierConf.getString("database.user");
			String passwordDB = monFichierConf.getString("database.password");

			maConnection = DriverManager.getConnection(urlDB, userDB, passwordDB);

			stmt = maConnection.createStatement();

			stmt.executeUpdate("delete from fournisseur where id=4");

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
