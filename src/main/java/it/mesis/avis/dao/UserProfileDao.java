package it.mesis.avis.dao;

import java.util.List;

import it.mesis.avis.bean.jpa.UserProfileEntity;

public interface UserProfileDao {

	List<UserProfileEntity> findAll();
	
	UserProfileEntity findByType(String type);
	
	UserProfileEntity findById(int id);
	
	void save(UserProfileEntity userProfile);
}
