package ua.uipa.math.db.entity;

public enum Role {

	ADMIN, DEFAULT;
	
	public static Role getRole(User user){
		int roleId = user.getRole();
		return Role.values()[roleId];
	}
	
	public String getName(){
		return name().toLowerCase();
	}
	
}
