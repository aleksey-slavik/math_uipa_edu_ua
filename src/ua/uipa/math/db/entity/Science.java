package ua.uipa.math.db.entity;

public class Science extends Entity {

	private static final long serialVersionUID = -6331776632526235185L;
	private Long id;
	private String title;

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Science [id=" + id + ", title=" + title + "]";
	}
}
