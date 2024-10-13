package poly.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import poly.entity.NEWS;
import poly.utils.Jdbc;

public class NEWSDAOImpl implements NEWSDAO {
	@Override
	public List<NEWS> findAll() {
		String sql = "SELECT * FROM NEWS";
		try {
		List<NEWS> entities = new ArrayList<>();
		Object[] values = {};
		ResultSet resultSet = Jdbc.executeQuery(sql, values);
		while (resultSet.next()) {
			NEWS entity = new NEWS();
			entity.setId(resultSet.getString("Id"));
			entity.setTitle(resultSet.getString("Title"));
			entity.setContent(resultSet.getString("Content"));
			entity.setImage(resultSet.getString("Image"));
			entity.setPostedDate(resultSet.getDate("PostedDate"));
			entity.setAuthor(resultSet.getString("Author"));
			entity.setViewCount(resultSet.getInt("ViewCount"));
			entity.setCategoryId(resultSet.getString("CategoryId"));
			entity.setHome(resultSet.getBoolean("Home"));
			entities.add(entity);
		}
		return entities;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
@Override
	public NEWS findById(String id) {
		String sql = "SELECT * FROM NEWS WHERE Id=?";
		try {
			Object[] values = { id };
			ResultSet resultSet = Jdbc.executeQuery(sql, values);
			if (resultSet.next()) {
				NEWS entity = new NEWS();
				entity.setId(resultSet.getString("Id"));
				entity.setTitle(resultSet.getString("Title"));
				entity.setContent(resultSet.getString("Content"));
				entity.setImage(resultSet.getString("Image"));
				entity.setPostedDate(resultSet.getDate("PostedDate"));
				entity.setAuthor(resultSet.getString("Author"));
				entity.setViewCount(resultSet.getInt("ViewCount"));
				entity.setCategoryId(resultSet.getString("CategoryId"));
				entity.setHome(resultSet.getBoolean("Home"));
				return entity;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		throw new RuntimeException("Not found");
	}
@Override
	public void create(NEWS entity) {
		String sql = "INSERT INTO NEWS(Id, Name, Description) VALUES(?, ?, ?)";
		try {
			Object[] values = { 
			entity.getId(), 
			entity.getTitle(), 
			entity.getContent(),
			entity.getImage(),
			entity.getPostedDate(),
			entity.getAuthor(),
			entity.getViewCount(),
			entity.getCategoryId(),
			entity.getHome()
		};
		Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
@Override
	public void update(NEWS entity) {
		String sql = "UPDATE NEWS SET Name=?, Description=? WHERE Id=?";
		try {
			Object[] values = { 
			entity.getTitle(), 
			entity.getContent(),
			entity.getImage(),
			entity.getPostedDate(),
			entity.getAuthor(),
			entity.getViewCount(),
			entity.getCategoryId(),
			entity.getHome(),
			entity.getId() 
		};
		Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
@Override
	public void deleteById(String id) {
		String sql = "DELETE FROM NEWS WHERE Id=?";
		try {
			Object[] values = { id };
			Jdbc.executeUpdate(sql, values);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
