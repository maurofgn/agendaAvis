package it.mesis.avis.service;

import java.util.List;

import it.mesis.avis.model.User;
import it.mesis.avis.security.UserAttempts;
import it.mesis.avis.security.UserSession;

public interface UserService {

	void save(User user);
	
	User findById(int id);
	
	User findBySso(String sso);
	
	List<User> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);
	
	void updateUser(User user);
	
	void deleteUserBySSO(String sso);

	void resetFailAttempts(String sso);

	void updateFailAttempts(String sso);
	
	void credentialsExpired(String sso);

	UserAttempts getUserAttempts(String sso);

	int getValidityDayPsw();

	int getMaxAccountAttempt();
	
	int getMaxDayBeforePreno();

	int getTimeoutDayPsw();

	UserSession getUserSession(String name);

	void addDefaultRole(User user);

	User findUserByCodFisc(String codFisc);
	
	void updateOldPsw(String sso, String newPsw);
	
	
}