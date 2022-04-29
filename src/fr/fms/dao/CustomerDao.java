package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Customer;

public class CustomerDao implements Dao<Customer>{

	@Override
	public ArrayList<Customer> readAll() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String strSql = "SELECT * FROM T_Customer";
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 
				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsLogin = resultSet.getString(2);
					String rsPassword = resultSet.getString(3);
					customers.add((new Customer(rsId,rsLogin,rsPassword)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public ArrayList<Customer> readByCategory(long id) {
		return null;
	}

	@Override
	public boolean add(Customer obj) {
		String str = "INSERT INTO T_Customer (Title,Author) "
				+ "VALUES (?,?)";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public boolean delete(Customer obj) {
		try (Statement statement = connection.createStatement()){
			String str = "DELETE FROM T_Customer where IdCustomer=" + obj.getId() + ";";									
			statement.executeUpdate(str);		
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return false;
	}

	@Override
	public Customer read(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Customer where IdCustomer=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) return new Customer(rs.getInt(1) , rs.getString(2) , rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return null;
	}

}
