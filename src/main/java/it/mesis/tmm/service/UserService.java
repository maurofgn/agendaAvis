package it.mesis.tmm.service;

import java.util.List;

import it.mesis.tmm.model.User;

public interface UserService {

	void save(User user);
	
	User findById(int id);
	
	User findBySso(String sso);
	
	List<User> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);
	
}