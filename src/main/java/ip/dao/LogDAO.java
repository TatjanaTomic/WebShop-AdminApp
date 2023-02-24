package ip.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ip.dto.Log;

public class LogDAO {

	private static final String SQL_SELECT_ALL = "SELECT * FROM log ORDER BY id DESC";
	
	public static List<Log> selectAll() {
		List<Log> logs = new ArrayList<>();
		
		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Object values[] = {};
		
		try {
			c = DAOUtil.getConnection();
			ps = DAOUtil.prepareStatement(c, SQL_SELECT_ALL, false, values);
			rs = ps.executeQuery();
			while(rs.next()) {
				logs.add(new Log(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DAOUtil.close(rs, ps, c);
		}
		
		return logs;
	}

}
