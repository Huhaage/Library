/**
 * @author Haage - 2022
 * 
 */
package fr.fms.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Logger;

import fr.fms.entities.Book;

public interface Dao<L> {
	public Connection connection = BddConnection.getConnection();
	
	public static final Logger logger = Logger.getLogger( "SqlExceptions" );
	
	/**
	 * return all objects of the corresponding table 
	 * @return ArrayList<T> corresponding
	 */
	public ArrayList<L> readAll();
	public L read();
	/**
	 * reference of the objects corresponding to the category in base
	 * @param category ask
	 * @return the corresponding object, if not found, return null
	 */
	public ArrayList<L> readByCategory(long id);
	/**
	 * adding a object to the base
	 * @param request book id
	 * @return true if done
	 */
	public boolean add(L obj);
	/**
	 * delete a object from the base
	 * @param request book id
	 * @return true if done, otherwise false
	 */
	public boolean delete(L obj);
}

