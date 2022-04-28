package fr.fms.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import fr.fms.dao.OrderDao;
import fr.fms.dao.CustomerDao;
import fr.fms.dao.Dao;
import fr.fms.dao.DaoFactory;
import fr.fms.dao.OrderBookDao;
import fr.fms.entities.Article;
import fr.fms.entities.Book;
import fr.fms.entities.Order;
import fr.fms.entities.OrderBook;
import fr.fms.entities.OrderItem;
import fr.fms.entities.Theme;
import fr.fms.entities.Customer;

public class IBusinessImpl implements IBusiness {
	private HashMap<Integer,Book> cart;
	private Dao<Book> articleDao = DaoFactory.getBookDao();
	private Dao<Customer> userDao = DaoFactory.getCustomerDao();
	private Dao<Theme> categoryDao = DaoFactory.getThemDao();
	private Dao<Order> orderDao = DaoFactory.getOrderDao();
	private Dao<OrderBook> orderItemDao = DaoFactory.getOrderBookDao();

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
	public int order(int idUser) {
		if(userDao.read(idUser) != null) {
			double total = getTotal(); 
			Order order = new Order(total, new Date(), idUser);
			if(orderDao.add(order)) {	//ajout en base de la commande
				for(Book book : cart.values()) {	//ajout des commandes minifiées associées
					orderItemDao.add(new OrderBook(0, book.getId(), book.getQuantity(), book.getPrice(), order.getIdOrder()));
				}
				return order.getIdOrder();
			}
		}
		return 0;
	}

	@Override
	public ArrayList<Book> readBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Theme> readTheme() {
		// TODO Auto-generated method stub
		return null;
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

}
