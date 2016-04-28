package it.mesis.avis.dao;

import java.util.List;

import it.mesis.avis.model.User;
import it.mesis.avis.security.UserAttempts;
import it.mesis.avis.security.UserSession;
import it.mesis.util.model.TipoDonaPuntoPrel;
import it.mesis.util.model.YearMonth;

public interface UserDao {

	void save(User user);
	
	User findById(int id);
	
	User findBySSO(String sso);
	
	List<User> findAllUsers();
	
	void deleteBySSO(String sso);

	void resetFailAttempts(String sso);

	void updateFailAttempts(String sso, int maxAccountAttempt);

	UserAttempts getUserAttempts(String sso);

	User findUserByCodFisc(String codFisc);

	List<TipoDonaPuntoPrel> getTipoDonazPuntiPrel();

	List<TipoDonaPuntoPrel> getTipoDonazPuntiPrel(List<Integer> listTipoDona);

	List<YearMonth> getYearMonths(List<Integer> listTipoDona);
	
	UserSession getUserSession(String sso);

	void credentialsExpired(String sso);

	void updateOldPsw(String sso, String newPsw);
	
}

