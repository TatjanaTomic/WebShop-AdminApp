package ip.bean;

import java.io.Serializable;
import java.util.List;

import ip.dao.UserDAO;
import ip.dto.User;

public class UsersBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -214183822287624039L;
	private static final String DEFAULT_PASSWORD = "password";
	private static final String DEFAULT_PIN = "1234";

	public UsersBean() {
		// TODO Auto-generated constructor stub
	}

	public List<User> getUsers() {
		return UserDAO.selectAll();
	}
	
	public boolean isUsernameAllowed(String username) {
		return !UserDAO.existsByUsername(username);
	}
	
	public boolean addUser(User user) {
		return UserDAO.insertUser(user, generatePassword(), generatePIN());
	}
	
	public boolean deleteUser(Integer id) {
		return UserDAO.deleteUser(id);
	}
	
	private String generatePassword() {
		return DEFAULT_PASSWORD;
	}
	
	private String generatePIN() {
		return DEFAULT_PIN;
	}
}
