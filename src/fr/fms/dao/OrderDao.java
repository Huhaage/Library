package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import fr.fms.entities.Order;

public class OrderDao implements Dao<Order> {

	@Override
	public ArrayList<Order> readAll() {
		ArrayList<Order> orders = new ArrayList<Order>();
		String strSql = "SELECT * FROM T_Users";
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 
				while (resultSet.next()) {
					int rsIdOrder = resultSet.getInt(1);
					double rsAmount = resultSet.getDouble(2);
					Date rsDate = resultSet.getDate(3);
					int rsIdUser = resultSet.getInt(4);
					orders.add((new Order(rsIdOrder,rsAmount,rsDate,rsIdUser)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;	}

	@Override
	public ArrayList<Order> readByCategory(long id) {
		return null;
	}

	@Override
	public boolean add(Order obj) {
		String str = "INSERT INTO T_Users (Amount,Date,IdUsers) "
				+ "VALUES (?,?,?)";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setDouble(1, obj.getAmount());
			ps.setDate(2, (java.sql.Date) obj.getDate());
			ps.setInt(3, obj.getIdUser());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Order obj) {
		return false;
	}

	@Override
	public Order read() {
		// TODO Auto-generated method stub
		return null;
	}

}
