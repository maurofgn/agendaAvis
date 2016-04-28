package it.mesis.avis.model;

public enum UserProfileType {
	ADMIN("ADMIN"),
	AVIS("AVIS"),
	DONA("DONA"),
	OPERA("OPERA");
	
//	INSERT INTO user_profile (id,type) VALUES (1,'ADMIN');
//	INSERT INTO user_profile (id,type) VALUES (2,'AVIS');
//	INSERT INTO user_profile (id,type) VALUES (3,'DONA');
//	INSERT INTO user_profile (id,type) VALUES (4,'OPERA');
	
	String userProfileType;
	
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	
}
