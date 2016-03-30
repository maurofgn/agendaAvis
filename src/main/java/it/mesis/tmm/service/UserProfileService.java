package it.mesis.tmm.service;

import java.util.List;

import it.mesis.tmm.model.UserProfile;

public interface UserProfileService {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
