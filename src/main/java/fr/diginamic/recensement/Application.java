package fr.diginamic.recensement;

import java.util.ArrayList;

import java.util.List;

import fr.diginamic.jdbc.DbManager;
import fr.diginamic.recensement.dao.RecensementDaoJdbc;
import fr.diginamic.recensement.entites.*;

/***
 * afficher les informations de la ville de Montpellier
 * afficher la population totale du departement 34
 * afficher la plus petite commune du departement 34
 * afficher les 10 plus grandes villes du departement 34
 * afficher les 10 plus petites villes du departement 34
 * afficher la population totale du la region occitanie
 * afficher les 10 plus grandes villes de la region occitanie
 * afficher le departement le plus peuple de l occitanie
 * Afficher les 10 régions les plus peuplés de France 
 * Afficher les 10 départements les plus peuplés de France 
 * Afficher les 10 villes les plus peuplées de France 
 * @author audrey
 *
 */


public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DbManager.URL_DB = "database.urlrecensement";
		
		RecensementDaoJdbc recensement = new RecensementDaoJdbc();
		
		List<Ville> villes = new ArrayList<>();
		
		villes = recensement.extraire("Select * from villes v inner join departements d on v.code_dept = d.code inner join regions r on d.code_region = r.code where v.nom = 'Montpellier'");
		
		for (Ville v : villes){
			System.out.println(v);
		}
		
		String populationTotale34 = recensement.select("Select sum(population) from villes where code_dept=34 group by code_dept");
		
		System.out.println("La population de l'Hérault est : "+populationTotale34+" hab\n");
	
		
		String populationMin34 = recensement.select("Select nom from villes where code_dept=34 order by population asc limit 1");
		
		System.out.println("La plus petite commune de l'Hérault est : "+populationMin34+"\n");
		
		System.out.println("Afficher les 10 plus grandes villes de l'Hérault :");
		
		villes = recensement.extraire("Select * from villes v inner join departements d on v.code_dept = d.code inner join regions r on d.code_region = r.code where v.CODE_DEPT = 34 order by population desc limit 10");
		
		for (Ville v : villes){	
			System.out.println(v);			
		}
		
		
		System.out.println("\nAfficher les 10 plus petites villes de l'Hérault :");
		
		villes = recensement.extraire("Select * from villes v inner join departements d on v.code_dept = d.code inner join regions r on d.code_region = r.code where v.CODE_DEPT = 34 order by population asc limit 10");
		
		for (Ville v : villes){
			System.out.println(v);
		}
		
		String populationTotaleOccitanie = recensement.select("Select sum(population) from villes v inner join departements d on v.code_dept = d.CODE inner join regions r on d.CODE_REGION = r.CODE where r.nom='Occitanie' group by r.nom");
		System.out.println("\nLa population totale de la région Occitanie : "+populationTotaleOccitanie+" hab\n");
		
		
		System.out.println("Afficher les 10 plus grandes villes de l'Occitanie :");
		
		villes = recensement.extraire("Select * from villes v inner join departements d on v.code_dept = d.code inner join regions r on d.code_region = r.code where r.nom='Occitanie' order by population desc limit 10");
		
		for (Ville v : villes){
			System.out.println(v);
		}
		
		String populationMaxDeptOccitanie = villes.get(0).getDepartement().getCodeDepartement();
		
		System.out.println("\nLe département le plus peuplé de l' Occitanie : "+populationMaxDeptOccitanie);
		
		
		System.out.println("\nAfficher les 10 régions les plus peuplées de France :");
		
	
		villes = recensement.extraire("Select CODE_VILLE,v.NOM,CODE_DEPT,r.CODE,r.NOM,sum(population) as POPULATION from villes v inner join departements d on v.code_dept = d.code inner join regions r on d.code_region = r.code group by r.code order by sum(population) desc limit 10");
		
		for (Ville v : villes){
			System.out.println("Région : "+v.getRegion().getNomRegion()+" : "+v.getPopulation()+" hab");
		}
		
		
		System.out.println("\nAfficher les 10 départements les plus peuplés de France :");
		
		villes = recensement.extraire("Select CODE_VILLE,v.NOM,CODE_DEPT,r.CODE,r.NOM,sum(population) as POPULATION from villes v inner join departements d on v.code_dept = d.code inner join regions r on d.code_region = r.code group by d.code order by sum(population) desc limit 10");
		
		for (Ville v : villes){
			System.out.println("Département : "+v.getDepartement().getCodeDepartement()+" : "+v.getPopulation()+" hab");
		}
		
		
		System.out.println("\nAfficher les 10 villes les plus peuplées de France :");
		
		villes = recensement.extraire("Select * from villes v inner join departements d on v.code_dept = d.code inner join regions r on d.code_region = r.code order by population desc limit 10");
		
		for (Ville v : villes){
			System.out.println(v);
		}
		
		recensement.close();
	}

}
