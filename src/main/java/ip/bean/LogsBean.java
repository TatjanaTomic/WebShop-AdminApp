package ip.bean;

import java.io.Serializable;
import java.util.List;

import ip.dao.LogDAO;
import ip.dto.Log;

public class LogsBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8092109583743896949L;

	public LogsBean() {
		// TODO Auto-generated constructor stub
	}

	public List<Log> getLogs() {
		return LogDAO.selectAll();
	}
}
