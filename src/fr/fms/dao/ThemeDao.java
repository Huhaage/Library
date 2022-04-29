package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Book;
import fr.fms.entities.Theme;

public class ThemeDao implements Dao<Theme> {

	@Override
	public ArrayList<Theme> readAll() {
		ArrayList<Theme> theme = new ArrayList<Theme>();
		String strSql = "SELECT * FROM T_Themes";
		try(Statement statement = connection.createStatement()){
			try(ResultSet resultSet = statement.executeQuery(strSql)){ 
				while (resultSet.next()) {
					int rsId = resultSet.getInt(1);
					String rsThemeName = resultSet.getString(2);;
					theme.add((new Theme(rsId,rsThemeName)));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theme;
	}

	@Override
	public ArrayList<Theme> readByCategory(long id) {
		return null;
	}

	@Override
	public boolean add(Theme obj) {
		String str = "INSERT INTO T_Themes (ThemeName) "
				+ "VALUES (?)";
		try (PreparedStatement ps = connection.prepareStatement(str)){
			ps.setString(1, obj.getThemeName());
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public boolean delete(Theme obj) {
		try (Statement statement = connection.createStatement()){
			String str = "DELETE FROM T_Themes where IdTheme=" + obj.getId() + ";";									
			statement.executeUpdate(str);		
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return false;
	}

	@Override
	public Theme read(int id) {
		try (Statement statement = connection.createStatement()){
			String str = "SELECT * FROM T_Themes where IdTheme=" + id + ";";									
			ResultSet rs = statement.executeQuery(str);
			if(rs.next()) return new Theme(rs.getInt(1) , rs.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return null;
	}

}
