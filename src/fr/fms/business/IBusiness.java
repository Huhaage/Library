package fr.fms.business;

import fr.fms.entities.Book;
import fr.fms.entities.Theme;
import java.util.ArrayList;

public interface IBusiness {	
	/**
	 * method that adds an book to the cart
	 * @param Book to add
	 */
	public void addToCart(Book book);		
	
	/**
	 * method that removes an book from the cart if it is in
	 * @param Book ID to be removed
	 */
	public void removemFromCart(int id);		
	
	/**
	 * method that returns in the form of a list all the elements of the cart (management in memory)
	 * @return Shopping Cart book List
	 */
	public ArrayList<Book> getCart();	
	
	/**
	 * method that performs the base order with the idUser + total of the current order + current date + contents of the cart:
	 * - the method will see a base command -> idOrder + amount + date + idUser
	 * - then add as many associated minified commands: orderItem -> idOrderItem + idArticle + Quantity + Price + idOrder
	 * @param idUser is the identifier of the customer who placed the order
	 * @return 1 if all is ok 0 if pb 
	 */
	public int order(int idUser);		
	
	/**
	 * method that returns all items in the table t_Books in bdd
	 * @return List of book in base
	 */
	public ArrayList<Book> readBook();	
		
	
	/**
	 * method that returns all categories of the table t_Theme in bdd
	 * @return List of theme in base
	 */
	public ArrayList<Theme> readTheme();
	
}
