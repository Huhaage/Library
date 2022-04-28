package fr.fms.dao;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * programme permettant de reg�n�rer le fichier de configuration
 * @author El babili - 2022
 * 
 */

public class CreateConfigFile {
	public static void main(String[] args) {
		try (OutputStream ops = new FileOutputStream("config.properties")) {		
			Properties properties = new Properties();	
			properties.setProperty("db.driver", "org.mariadb.jdbc.Driver");
			properties.setProperty("db.url", "jdbc:mariadb://localhost:3306/Library");
			properties.setProperty("db.login", "root");
			properties.setProperty("db.password", "fms2022");			
			properties.store(ops , "No comment !");
			System.out.println("fait");
		}
		 catch (IOException io) {
	            io.printStackTrace();
		}
	}

}

