package ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ip.dto.User;

public class UserDAO {

	private static final String SQL_SELECT_ALL = "SELECT * FROM user_account";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM user_account WHERE id=?";
	private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM user_account WHERE username=?";
	private static final String SQL_INSERT_USER = "INSERT INTO user_account VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String SQL_DELETE_USER = "UPDATE user_account SET is_deleted=true WHERE id=?";
	 
	
	public static List<User> selectAll() {
		List<User> users = new ArrayList<>();
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { };
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_ALL, false, values);
			rs = ps.executeQuery();
			while(rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6), rs.getString(7), rs.getString(8), rs.getBoolean(10), rs.getBoolean(11)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(rs, ps, c);
		}
		
		return users;
	}
	
	
	public static boolean deleteUser(Integer id) {
		if(!existsById(id))
			return false;
		
		boolean result = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = { id };
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_DELETE_USER, false, values);
			ps.executeUpdate();
			result = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(ps, c);
		}
		
		return result;
	}
	
	public static boolean insertUser(User user, String password, String pin) {
		boolean isInserted = false;
		
		Connection c = null;
		PreparedStatement ps = null;
		Object values[] = {null, user.getFirstName(), user.getLastName(), user.getUsername(), password, user.getCity(), user.getAvatar(), user.getMail(), pin, false, false };
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_INSERT_USER, false, values);
			ps.executeUpdate();
			isInserted = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(ps, c);
		}
		
		return isInserted;
	}
	
	public static boolean existsByUsername(String username) {
		boolean exists = false;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { username };
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_BY_USERNAME, false, values);
			rs = ps.executeQuery();
			if(rs.next())
				exists = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(ps, c);
		}
		
		return exists;
	}
	
	private static boolean existsById(Integer id) {
		boolean exists = false;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = { id };
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_BY_ID, false, values);
			rs = ps.executeQuery();
			if(rs.next())
				exists = true;
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(ps, c);
		}
		
		return exists;
	}	
}
