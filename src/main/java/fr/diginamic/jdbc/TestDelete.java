package fr.diginamic.jdbc;


public class TestDelete {

	public static void main(String[] args) {

		new DbManager().executeUpdate("delete from fournisseur where id=4");
		

	}

}
