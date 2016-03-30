package it.mesis.tmm.dao;

import java.util.List;

import it.mesis.tmm.model.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
