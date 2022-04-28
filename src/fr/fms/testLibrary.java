package fr.fms;

import fr.fms.dao.BookDao;
import fr.fms.dao.CreateConfigFile;
import fr.fms.dao.OrderDao;
import fr.fms.dao.CustomerDao;

public class testLibrary {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//CreateConfigFile.main(args);
		System.out.println(new BookDao().readAll());
		System.out.println(new CustomerDao().readAll());
		//System.out.println(new OrderDao().readAll());
	}

}
