package fr.diginamic.encodage;

public class TestEncodage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String chaineCyrillique = "ДОБРЫЙ ДЕНЬ, МЕНЯ ЗОВУТ";
		
		System.out.println(chaineCyrillique);
	}
	//en ligne de commande dans le repertoir demo-jdbc/target/classes/
	// java fr.diginamic.encodage.TestEncodage
	// affichage : ?????? ????, ???? ?????
	
	// java -Dfile.encoding=UTF-8  fr.diginamic.encodage.TestEncodage
	// affichage : ДОБРЫЙ ДЕНЬ, МЕНЯ ЗОВУТ

}
