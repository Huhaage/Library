/**
 * Managing the database connection from the data in the config.properties file
 * To ensure that a single connection is established for all data access components,
 * we used here a Singleton with the help of the private builder
 * @author El babili - 2022
 */

package fr.fms.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.lang.Class;

public class BddConnection {
	private volatile static Connection connection;		//1st multi threading management lock
	private static String driver;
	private static String url;
	private static String login;
	private static String password;

	private BddConnection() {
		try {
			getConfigFile();	//First we open the config file to feed our attributes
								//nothing to do with the Singleton pattern, it’s a security + a ease of evolution
			Class.forName(driver);	//then load the driver
			connection = DriverManager.getConnection(url,login,password);	//and we open a connection
		}			
		catch (ClassNotFoundException | SQLException e) {
			System.out.println("problème de connexion !" + e.getMessage());
		}
		catch (FileNotFoundException e) {
			System.out.println("Il faut générer le fichier de config avant de pouvoir l'utiliser !");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method that returns a connection, if non-existent, it created it only once
	 * synchronized for the same multithreading management system
	 * @return Connection
	 */
	public static synchronized Connection getConnection() {
		if(connection == null) 	new BddConnection();
		else System.out.println("Connexion existe déjà  ! " + connection.toString());
		return connection;
	}
	
	/**
	 * method that opens the config file of a connection
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void getConfigFile() throws FileNotFoundException, IOException {
		Properties props = new Properties();		
		try (FileInputStream fis = new FileInputStream("config.properties")){
			props.load(fis);
		} catch (FileNotFoundException e1) {
			System.out.println("Fichier de config non trouvé !" + e1.getMessage());
		} catch (IOException e1) {
			System.out.println("Erreur lecture fichier config ! " + e1.getMessage());
		}
		
		driver = props.getProperty("db.driver");
		url = props.getProperty("db.url");
		login = props.getProperty("db.login");
		password = props.getProperty("db.password");
	}
}