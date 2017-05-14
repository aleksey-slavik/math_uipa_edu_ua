package ua.uipa.math.db.entity;

public class Image extends Entity {

	private static final long serialVersionUID = -4703051621543762751L;
	private Long id;
	private String name;
	private String path;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", path=" + path + "]";
	}
}
