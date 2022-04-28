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
		ArrayList<Customer> users = new ArrayList<Customer>();
		String strSql = "SELECT * FROM T_Users";
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 
				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsLogin = resultSet.getString(2);
					String rsPassword = resultSet.getString(3);
					users.add((new Customer(rsId,rsLogin,rsPassword)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public ArrayList<Customer> readByCategory(long id) {
		return null;
	}

	@Override
	public boolean add(Customer obj) {
		String str = "INSERT INTO T_Users (Title,Author) "
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
			String str = "DELETE FROM T_Book where IdUser=" + obj.getId() + ";";									
			statement.executeUpdate(str);		
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return false;
	}

	@Override
	public Customer read() {
		// TODO Auto-generated method stub
		return null;
	}



}
