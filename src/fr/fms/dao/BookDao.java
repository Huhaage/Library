package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Book;

public class BookDao implements Dao<Book> {

	@Override
	public ArrayList<Book> readByCategory(long id) {
		ArrayList<Book> books = new ArrayList<Book>();
		String strSql = "SELECT * FROM T_BOOKS WHERE idTheme=" + id;									
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 
				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsTitel = resultSet.getString(2);
					String reAuthor = resultSet.getString(3);
					String rsPublishingHouse = resultSet.getString(4);
					boolean rsOccasions = resultSet.getBoolean(5);
					float rsPrice = resultSet.getFloat(6);
					books.add((new Book(rsId,rsTitel,reAuthor,rsPublishingHouse,rsOccasions,rsPrice)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books ;
	}

	@Override
	public boolean add(Book obj) {
		String str = "INSERT INTO T_BOOKS (Title,Author,PublishingHouse,Occasions,Price) "
				+ "VALUES (?,?,?,?,?,?)";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getTitel());
			ps.setString(2, obj.getAuthor());
			ps.setString(3, obj.getPublishingHouse());
			ps.setBoolean(4,obj.getOccasions());
			ps.setDouble(5, obj.getPrice());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Book obj) {
		try (Statement statement = connection.createStatement()){
			String str = "DELETE FROM T_Book where IdBook=" + obj.getId() + ";";									
			statement.executeUpdate(str);		
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return false;
	}

	@Override
	public ArrayList<Book> readAll() {
		ArrayList<Book> books = new ArrayList<Book>();
		String strSql = "SELECT * FROM T_BOOKS";
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 
				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsTitel = resultSet.getString(2);
					String reAuthor = resultSet.getString(3);
					String rsPublishingHouse = resultSet.getString(4);
					boolean rsOccasions = resultSet.getBoolean(5);
					float rsPrice = resultSet.getFloat(6);
					books.add((new Book(rsId,rsTitel,reAuthor,rsPublishingHouse,rsOccasions,rsPrice)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}

	@Override
	public Book read(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Books where IdBook=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) return new Book(rs.getInt(1) , rs.getString(2) , rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getFloat(6));
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return null;
	}

	public ArrayList<Book> readAllByTheme(int id) {
		ArrayList<Book> books = new ArrayList<Book>();
		String strSql = "SELECT * FROM T_Books where idTheme=" + id;		
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 			
				while(resultSet.next()) {
					int rsId = resultSet.getInt(1);	
					String rsTitel = resultSet.getString(2);
					String rsAuthor = resultSet.getString(3);
					String rsPublishingHouse = resultSet.getString(4);
					boolean rsOccation = resultSet.getBoolean(5);
					float rsPrice = resultSet.getFloat(6);		
					books.add((new Book(rsId,rsTitel,rsAuthor,rsPublishingHouse,rsOccation,rsPrice)));						
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}			
		return books;
	}
}
