package fr.diginamic.props;
import java.util.ResourceBundle;
import java.util.Set;

public class TestConfiguration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ResourceBundle monFichierConf = ResourceBundle.getBundle("database");
		String driverName = monFichierConf.getString("database.driver");
		
		
		System.out.println("driverName "+driverName);

		Set<String> keySet = monFichierConf.keySet();
		
		for(String key : keySet){
			System.out.println(key+" = "+monFichierConf.getString(key));
		}
	}

}
