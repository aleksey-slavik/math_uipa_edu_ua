package ua.uipa.math.db.entity;

public class NewsItem extends Entity {

	private static final long serialVersionUID = 7927964774255963135L;

	private Long id;
	private Long date;
	private String title;
	private String text;

	public Long getId() {
		return id;
	}

	public Long getDate() {
		return date;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "NewsBean [id=" + id + ", date=" + date + ", title=" + title + ", text =" + text + "]";
	}
}
