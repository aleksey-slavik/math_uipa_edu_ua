package ua.uipa.math.db.bean;

import ua.uipa.math.db.entity.Entity;

public class NewsBean extends Entity {

	private static final long serialVersionUID = 7927964774255963135L;

	private Long id;
	private String image;
	private String date;
	private String title;
	private String text;

	public Long getId() {
		return id;
	}

	public String getImage() {
		return image;
	}

	public String getDate() {
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

	public void setImage(String image) {
		this.image = image;
	}

	public void setDate(String date) {
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
		return "NewsBean [id=" + id + ", image=" + image + ", date=" + date + ", title=" + title + ", text =" + text
				+ "]";
	}
}
