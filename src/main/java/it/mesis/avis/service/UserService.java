package it.mesis.avis.service;

import java.util.List;

import it.mesis.avis.bean.jpa.UserEntity;
import it.mesis.avis.security.UserAttempts;
import it.mesis.avis.security.UserSession;

public interface UserService {

	void save(UserEntity user);
	
	UserEntity findById(int id);
	
	UserEntity findBySso(String sso);
	
	List<UserEntity> findAllUsers(); 
	
	boolean isUserSSOUnique(Integer id, String sso);
	
	void updateUser(UserEntity user);
	
	void deleteUserBySSO(String sso);

	void resetFailAttempts(String sso);

	void updateFailAttempts(String sso);
	
	void credentialsExpired(String sso);

	UserAttempts getUserAttempts(String sso);

	int getValidityDayPsw();
	
	int getValidityDayPswBefore();
	
	int getValidityDayPswDue();

	int getMaxAccountAttempt();
	
	int getMaxDayBeforePreno();

	int getTimeoutDayPsw();

	UserSession getUserSession(String name);

	void addDefaultRole(UserEntity user);

	UserEntity findUserByCodFisc(String codFisc);
	
	void updateOldPsw(String sso, String newPsw);

}