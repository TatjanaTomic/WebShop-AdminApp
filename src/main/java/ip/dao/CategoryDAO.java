package ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ip.dto.Category;

public class CategoryDAO {

	private static final String SQL_SELECT_ALL = "SELECT * FROM category";
	private static final String SQL_SELECT_BY_NAME = "SELECT * FROM category WHERE name=?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM category WHERE id=?";
	private static final String SQL_INSERT_CATEGORY = "INSERT INTO category VALUES (?, ?, ?, ?)";
	private static final String SQL_DELETE_BY_ID = "UPDATE category SET is_deleted=true WHERE id=?";
	private static final String SQL_UPDATE_NAME = "UPDATE category SET name=? WHERE id=?";
	private static final String SQL_DELETE_ATTRIBUTE = "UPDATE attribute SET is_deleted=true WHERE id=?";
	
	public static List<Category> selectAll() {
		List<Category> categories = new ArrayList<>();
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { };
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_ALL, false, values);
			rs = ps.executeQuery();
			while(rs.next()) {
				Category category = new Category(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getInt(4));
				category.setAttributes(AttributeDAO.selectAttributesOfCategory(category.getId()));
				categories.add(category);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(rs, ps, c);
		}
		
		return categories;
	}
	
	public static boolean insertCategory(Category category) {
		boolean isInserted = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { null, category.getName(), category.isDeleted(), category.getIdParentCategory()};
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_INSERT_CATEGORY, false, values);
			ps.executeUpdate();
			isInserted = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(ps, c);
		}
		
		return isInserted;
	}
	
	public static boolean existsByName(String name) {
		boolean exists = false;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { name };
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_BY_NAME, false, values);
			rs = ps.executeQuery();
			if(rs.next()) {
				exists = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(ps, c);
		}
		
		return exists;
	}
	
	public static Category selectById(Integer id) {
		Category category = null;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { id };
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_BY_ID, false, values);
			rs = ps.executeQuery();
			if(rs.next()) {
				category = new Category(rs.getInt(1), rs.getString(2), rs.getBoolean(3), rs.getInt(4));
				category.setAttributes(AttributeDAO.selectAttributesOfCategory(category.getId()));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(ps, c);
		}
		
		return category;
	}

	public static boolean deleteById(Integer id) {
		if(selectById(id) == null) {
			return false;
		}
		
		boolean result = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { id };
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_DELETE_BY_ID, false, values);
			ps.executeUpdate();
			result = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(ps, c);
		}
		
		return result;
	}

	public static boolean updateName(Category category) {
		if(selectById(category.getId()) == null) {
			return false;
		}
		
		boolean result = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { category.getName(), category.getId() };
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_UPDATE_NAME, false, values);
			ps.executeUpdate();
			result = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(ps, c);
		}
		
		return result;
	}

	public static boolean deleteAttributeById(Integer id) {
		
		boolean result = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { id };
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_DELETE_ATTRIBUTE, false, values);
			ps.executeUpdate();
			result = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(ps, c);
		}
		
		return result;
	}
}
