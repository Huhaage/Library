package fr.fms.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import fr.fms.dao.BookDao;
import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.entities.Book;
import fr.fms.entities.Order;
import fr.fms.entities.OrderBook;
import fr.fms.entities.Theme;
import fr.fms.entities.Customer;

public class IBusinessImpl implements IBusiness {
	private HashMap<Integer,Book> cart;
	private Dao<Book> bookDao = DaoFactory.getBookDao();
	private Dao<Customer> customerDao = DaoFactory.getCustomerDao();
	private Dao<Theme> themeDao = DaoFactory.getThemDao();
	private Dao<Order> orderDao = DaoFactory.getOrderDao();
	private Dao<OrderBook> orderBookDao = DaoFactory.getOrderBookDao();

	public IBusinessImpl() {
		this.cart = new HashMap<Integer,Book>();
	}
	
	@Override
	public void addToCart(Book book) {
		Book bouk = cart.get(book.getId());
		if(bouk != null) {
			bouk.setQuantity(bouk.getQuantity() + 1);
		}
		else cart.put(book.getId(), book);	
	}

	@Override
	public void removemFromCart(int id) {
		Book book = cart.get(id);
		if(book != null) {
			if(book.getQuantity() == 1)	cart.remove(id);
			else book.setQuantity(book.getQuantity() -1);
		}
	}

	@Override
	public ArrayList<Book> getCart() {
		return new ArrayList<Book> (cart.values());
	}

	@Override
	public int order(int id) {
		if(customerDao.read(id) != null) {
			double total = getTotal(); 
			Order order = new Order(total, new Date(), id);
			if(orderDao.add(order)) {	//ajout en base de la commande
				for(Book book : cart.values()) {	//ajout des commandes minifiées associées
					orderBookDao.add(new OrderBook(0, book.getId(), book.getQuantity(), book.getPrice(), order.getIdOrder()));
				}
				return order.getIdCustomer();
			}
		}
		return 0;
	}

	@Override
	public ArrayList<Book> readBook() {
		// TODO Auto-generated method stub
		return bookDao.readAll();
	}

	@Override
	public ArrayList<Theme> readTheme() {
		// TODO Auto-generated method stub
		return themeDao.readAll();
	}
	
	public Book readOneBook(int id) {
		return bookDao.read(id);
	}
	
	public ArrayList<Book> readBookByThemeId(int id) {
		return ((BookDao) bookDao).readAllByTheme(id);
	}
	/**
	 * renvoi le total de la commande en cours
	 * @return total
	 */
	public double getTotal() {
		double [] total = {0};
		cart.values().forEach((a) -> total[0] += a.getPrice() * a.getQuantity()); 	
		return total[0];
	}

	public Theme readOneTheme(int id) {
		return themeDao.read(id);
	}

	public boolean isCartEmpty() {
		return cart.isEmpty();
	}

	public void clearCart() {
		cart.clear();
		
	}

	public int existCustomer(String log, String pwd) {
		for(Customer customer : customerDao.readAll())
			if(customer.getLogin().equalsIgnoreCase(log) && customer.getPassword().equals(pwd))
				return customer.getId();
		return 0;
	}

}
