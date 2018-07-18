package Bean;

import Core.Database.DatabaseFactory;
import dao.Exceptions.AlreadyExistException;
import dao.Interfaces.UserDaoInterface;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class User {
	private Long id = null;
	private String login;
	private String email;
	private String password;
	private String type;

	private boolean enabled = false;
	private String token;

	public User(String login, String email, String password, String type) {
		this.login = login;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/*
	interactions
	 */

	public boolean save() throws SQLException {
		if (this.getId() != null) {
			userDAO().update(this);
			return true;
		}

		try {
			userDAO().insert(this);
			return true;
		} catch (AlreadyExistException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void delete() throws SQLException{
		userDAO().delete(this);
	}

	public static List<User> all() throws SQLException {
		return userDAO().all();
	}

	public static User findByLogin(String login) throws SQLException {
		return userDAO().findBy("login", login);
	}

	public static User findByEmail(String email) throws SQLException {
		return userDAO().findBy("email", email);
	}

	public static User findById(Long id) throws SQLException {
		return userDAO().findById(id);
	}

	public static int deleteAll() throws SQLException {
		return userDAO().deleteAll();
	}

	private static UserDaoInterface userDAO(){
		DatabaseFactory dao = DatabaseFactory.getDatabase();

		return dao.getUserDao();
	}

	public String generateToken() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();

		while (salt.length() < 20) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}

		return salt.toString();
	}
}
