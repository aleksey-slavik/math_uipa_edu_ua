package ua.uipa.math.db.entity;

public class ConfItem extends Entity {

	private static final long serialVersionUID = -2994334575473198563L;
	
	private Long id;
	private String title;
	private String text;
	private Long date;
	
	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getText() {
		return text;
	}
	
	public Long getDate() {
		return date;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setText(String  text) {
		this.text = text;
	}
	
	public void setDate(Long date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "ConfBean [id=" + id + ", date=" + date + ", title=" + title + ", text =" + text + "]";
	}
}
