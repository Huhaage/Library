package fr.fms.dao;

import fr.fms.entities.Book;
import fr.fms.entities.Theme;
import fr.fms.entities.Order;
import fr.fms.entities.OrderBook;
import fr.fms.entities.Customer;

public class DaoFactory {
	
	public static Dao<Book> getBookDao() {
		return new BookDao();		
	}
	
	public static Dao<Customer> getCustomerDao() {
		return new CustomerDao();
	}
	
 	public static Dao<Theme> getThemDao() {
 		return new ThemeDao();
 	}
 	
 	public static Dao<Order> getOrderDao() {
 		return new OrderDao();
 	}
 	
 	public static Dao<OrderBook> getOrderBookDao() {
 		return new OrderBookDao();
 	}
}