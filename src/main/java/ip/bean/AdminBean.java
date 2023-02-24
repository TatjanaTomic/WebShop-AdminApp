package ip.bean;

import java.io.Serializable;

import ip.dao.AdminDAO;
import ip.dto.Admin;

public class AdminBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1062093552962411704L;
	private Admin admin = new Admin();
	private boolean isLoggedIn = false;

	public AdminBean() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean logIn(String username, String password) {
		if((admin = AdminDAO.selectByUsernameAndPassword(username, password)) != null) {
			isLoggedIn = true;
		}
		return isLoggedIn;
	}
	
	public void logOut() {
		admin = new Admin();
		isLoggedIn = false;
	}

	public Admin getAdmin() {
		return admin;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}
}
