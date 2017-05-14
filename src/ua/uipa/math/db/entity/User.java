package ua.uipa.math.db.entity;

public class User extends Entity {

	private static final long serialVersionUID = 5440378255614097027L;
	private Long id;
	private String login;
	private String password;
	private int role;

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public int getRole() {
		return role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", pass=" + password + ", role=" + role + "]";
	}
}
