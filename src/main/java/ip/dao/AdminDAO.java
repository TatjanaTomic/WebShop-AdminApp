package ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ip.dto.Admin;

public class AdminDAO {
	
	private static final String SQL_CHECK_CREDENTIALS = "SELECT * FROM account WHERE username=? AND password=? AND id_account_type=1";
	
	public static Admin selectByUsernameAndPassword(String username, String password) {
		Admin admin = null;
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = {username, password};
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_CHECK_CREDENTIALS, false, values);
			rs = ps.executeQuery();
			if(rs.next()) {
				admin = new Admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(rs, ps, c);
		}
		
		return admin;
	}

}
