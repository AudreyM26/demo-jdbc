package fr.diginamic.jdbc;


public class TestUpdate {

	public static void main(String[] args) {
		
		new DbManager().executeUpdate("update fournisseur set nom='La Maison des Peintures' where id=4");
		
		
	}

}
