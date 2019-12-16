package fr.diginamic.locale;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/***
 * affichage selon paramètres culturels par defaut : 
 * lundi 16 décembre 2019
 * fr_FR
 * 15,23
 * 
 * Réglages de la JVM : country : EN - language en (USA)
 * Monday, December 16, 2019
 * en_FR
 * 15.23
 * 
 * Réglages de la JVM : country : CH - language zh (Chine)
 * 2019年12月16日 星期一
 * zh_FR
 * 15.23
 * @author audrey
 *
 */
public class TestLocale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LocalDate date = LocalDate.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);

		System.out.println(date.format(formatter));
		System.out.println(Locale.getDefault());

		Double d = 15.23;

		DecimalFormat nbFormatter = new DecimalFormat();

		System.out.println(nbFormatter.format(d));

	}

}
