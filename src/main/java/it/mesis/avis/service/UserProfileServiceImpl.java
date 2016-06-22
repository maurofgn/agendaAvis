package it.mesis.avis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.mesis.avis.bean.jpa.UserProfileEntity;
import it.mesis.avis.dao.UserProfileDao;

@Service("userProfileService")
@Transactional
public class UserProfileServiceImpl implements UserProfileService{
	
	@Autowired
	UserProfileDao dao;
	
	public List<UserProfileEntity> findAll() {
		return dao.findAll();
	}

	public UserProfileEntity findByType(String type){
		return dao.findByType(type);
	}

	public UserProfileEntity findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void save(UserProfileEntity userProfile) {
		dao.save(userProfile);
	}
}
