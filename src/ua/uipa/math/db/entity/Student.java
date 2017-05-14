package ua.uipa.math.db.entity;

public class Student extends Entity {

	private static final long serialVersionUID = -624201988259223723L;
	private Long id;
	private String name;
	private String phone;
	private String email;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + "]";
	}
}
