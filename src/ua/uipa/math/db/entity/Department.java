package ua.uipa.math.db.entity;

public class Department extends Entity {

	private static final long serialVersionUID = 8064369872011507151L;
	private Long id;
	private String name;
	private String phd;
	private String info;
	private String email;
	private String education;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPhd() {
		return phd;
	}

	public String getInfo() {
		return info;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhd(String phd) {
		this.phd = phd;
	}

	public void setInfo(String info) {
		this.info = info;
	}


	@Override
	public String toString() {
		return "Departent [id=" + id + ", name=" + name + ", phd=" + phd + ", info=" + info + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
}
