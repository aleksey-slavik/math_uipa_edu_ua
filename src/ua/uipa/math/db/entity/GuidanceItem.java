package ua.uipa.math.db.entity;

public class GuidanceItem extends Entity{

	private static final long serialVersionUID = 3651131072358898413L;
	
	private String title;
	private String text;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
