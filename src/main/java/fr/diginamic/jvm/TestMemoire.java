package fr.diginamic.jvm;

import java.util.ArrayList;
import java.util.List;

public class TestMemoire {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> liste = new ArrayList<>();
		
		int i = 0;
		int cpt = 0;
		int cptAffichage = 10000;
		
		while (i < 10){
			
			if(cpt == 10000 || cpt-cptAffichage == 10000){
				System.out.println(liste.size());
			}
			
			liste.add(String.valueOf(cpt));
			cpt +=1;
		
		}
		
		//a 100 Ko Error occurred during initialization of VM Too small initial heap
		//10 Mo -> 20 000 apres error : Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
		//20 Mo -> 20 000 apres error : Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded

	}
}
