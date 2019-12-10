package fr.diginamic.jdbc;



public class TestInsertion {

	public static void main(String[] args) {
		
		new DbManager().executeUpdate("insert ignore into fournisseur (ID,NOM) values(4,'La Maison de la Peinture')");
		
		
	}
}
