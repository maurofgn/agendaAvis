package it.mesis.avis.dao;

import java.util.List;

import it.mesis.avis.model.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
	
	void save(UserProfile userProfile);
}
