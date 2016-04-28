package it.mesis.avis.service;

import java.util.List;

import it.mesis.avis.model.UserProfile;

public interface UserProfileService {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
