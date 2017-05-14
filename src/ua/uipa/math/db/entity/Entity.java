package ua.uipa.math.db.entity;

import java.io.Serializable;

public class Entity implements Serializable {

	private static final long serialVersionUID = 9183821761297441227L;

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
