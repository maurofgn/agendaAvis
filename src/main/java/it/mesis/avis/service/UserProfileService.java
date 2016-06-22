package it.mesis.avis.service;

import java.util.List;

import it.mesis.avis.bean.jpa.UserProfileEntity;

public interface UserProfileService {

	List<UserProfileEntity> findAll();
	
	UserProfileEntity findByType(String type);
	
	UserProfileEntity findById(int id);

	void save(UserProfileEntity userProfile);
}
