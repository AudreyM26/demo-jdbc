import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnexionJdbcCleverCloud {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			//DriverManager.registerDriver(new Driver());
			Class.forName("org.mariadb.jdbc.Driver");
		
			ResourceBundle monFichierConf = ResourceBundle.getBundle("databaseclevercloud");
			String urlDB = monFichierConf.getString("database.url");
			String userDB = monFichierConf.getString("database.user");
			String passwordDB = monFichierConf.getString("database.password");
			
			Connection maConnection = DriverManager.getConnection(urlDB,userDB,passwordDB);
			System.out.println(maConnection.getCatalog());
			
		    System.out.println("Connection established!");
		    maConnection.close();
		    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
