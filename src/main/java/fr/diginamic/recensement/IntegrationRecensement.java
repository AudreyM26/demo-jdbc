package fr.diginamic.recensement;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fr.diginamic.jdbc.DbManager;
import fr.diginamic.recensement.dao.RecensementDaoJdbc;
import fr.diginamic.recensement.entites.*;

/***
 * remplir la base de données recensement : tables villes, departements, regions
 * @author audrey
 *
 */
public class IntegrationRecensement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		DbManager.URL_DB = "database.urlrecensement";
		
		RecensementDaoJdbc recensement = new RecensementDaoJdbc();
	
		try {
			File file = new File("C:/Users/audrey/Documents/Developpement/recensement population 2016.csv");
			List<String> lignes = FileUtils.readLines(file, "UTF-8");

			lignes.remove(0);

			for (String ligne : lignes) {
				// System.out.println(ligne);

				String[] content = ligne.split(";");

				String codeRegion = content[0];
				String nomRegion = content[1];
				String codeDepartement = content[2];
				String codeCommune = content[3];
				String nomCommune = content[5];

				int population = Integer.parseInt(content[6].replace(" ", ""));

				Ville v = new Ville(codeCommune,nomCommune,population,new Departement(codeDepartement),new Region(codeRegion,nomRegion));
				
				recensement.insert(v);
			}

			recensement.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
