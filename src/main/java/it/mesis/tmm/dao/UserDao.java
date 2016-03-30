package it.mesis.tmm.dao;

import java.util.List;

import it.mesis.tmm.model.User;

public interface UserDao {

	void save(User user);
	
	User findById(int id);
	
	User findBySSO(String sso);
	
	List<User> findAllUsers();
	
	void deleteBySSO(String sso);
	
}

