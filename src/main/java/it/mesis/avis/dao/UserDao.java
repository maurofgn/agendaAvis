package it.mesis.avis.dao;

import java.util.List;

import it.mesis.avis.bean.jpa.UserEntity;
import it.mesis.avis.security.UserAttempts;
import it.mesis.avis.security.UserSession;
import it.mesis.util.model.YearMonth;

public interface UserDao {

	void save(UserEntity user);
	
	UserEntity findById(int id);
	
	UserEntity findBySSO(String sso);
	
	List<UserEntity> findAllUsers();
	
	void deleteBySSO(String sso);

	void resetFailAttempts(String sso);

	void updateFailAttempts(String sso, int maxAccountAttempt);

	UserAttempts getUserAttempts(String sso);

	UserEntity findUserByCodFisc(String codFisc);

//	List<TipoDonaPuntoPrel> getTipoDonazPuntiPrel();
//
//	List<TipoDonaPuntoPrel> getTipoDonazPuntiPrel(List<Integer> listTipoDona);

	List<YearMonth> getYearMonths(List<Integer> listTipoDona);
	
	UserSession getUserSession(String sso, int dayBefore);

	void credentialsExpired(String sso);

	void updateOldPsw(String sso, String newPsw);
	
}

