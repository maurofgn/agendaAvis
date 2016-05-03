package it.mesis.avis.service;

import it.mesis.avis.dao.UserDao;
import it.mesis.avis.model.User;
import it.mesis.avis.security.UserAttempts;
import it.mesis.avis.security.UserSession;
import it.mesis.utility.Utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	private static final String VALIDITY_DAY_PSW = "90";
	private static final String MAX_ACCOUNT_ATTEMPT = "5";
	private static final String TIMEOUT_DAY_PSW = "180";
	public static final String GG_RANGE_PRENO = "30";
	private static final String VALIDITY_DAY_PSW_BEFORE = "5";	//giorni prima della scadenza x cu iva richiesto il cambio password
	
	@Autowired
	private UserDao dao;

    @Autowired
    private Environment environment;

    @Override
	public void save(User user){		
		dao.save(user);
	}
	
	@Override
	public User findById(int id) {
		return dao.findById(id);
	}

	@Override
	public User findBySso(String sso) {
		return dao.findBySSO(sso);
	}
	
	@Override
	public User findUserByCodFisc(String codFisc) {
		return dao.findUserByCodFisc(codFisc);
	}
	
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public boolean isUserSSOUnique(Integer id, String sso) {
		User user = findBySso(sso);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}
	
	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findById(user.getId());
		if(entity != null){
			entity.setSsoId(user.getSsoId());
			entity.setPassword(user.getPassword());
			entity.setLastChangePsw(user.getLastChangePsw());
			entity.setState(user.getState());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}
	
	public void deleteUserBySSO(String sso) {
		dao.deleteBySSO(sso);
	}

	@Override
	public void resetFailAttempts(String sso) {
		dao.resetFailAttempts(sso);
	}

	@Override
	public void updateFailAttempts(String sso) {
		dao.updateFailAttempts(sso, getMaxAccountAttempt());
	}

	@Override
	public UserAttempts getUserAttempts(String sso) {
		return dao.getUserAttempts(sso);
	}
	
	@Override
	public int getValidityDayPsw() {
		return Utility.parseInteger(environment.getProperty("validity.day.psw", VALIDITY_DAY_PSW));
	}
	
	@Override
	public int getValidityDayPswBefore() {
		return Utility.parseInteger(environment.getProperty("validity.day.psw.before", VALIDITY_DAY_PSW_BEFORE));
	}
	
	@Override
	public int getValidityDayPswDue() {
		return getValidityDayPsw() - getValidityDayPswBefore();
	}
	

	@Override
	public int getMaxAccountAttempt() {
		return Utility.parseInteger(environment.getProperty("max.account.attempt", MAX_ACCOUNT_ATTEMPT));
	}
	
	@Override
	public int getTimeoutDayPsw() {
		return Utility.parseInteger(environment.getProperty("timeout.day.psw", TIMEOUT_DAY_PSW));
	}

	@Override
	public UserSession getUserSession(String name) {
		return dao.getUserSession(name);
	}

	@Override
	public int getMaxDayBeforePreno() {
		return Utility.parseInteger(environment.getProperty("gg.range.preno", TIMEOUT_DAY_PSW));
	}

	@Override
	public void addDefaultRole(User user) {
		user.addDefaultRole();
	}

	@Override
	public void credentialsExpired(String sso) {
		dao.credentialsExpired(sso);
	}

	@Override
	public void updateOldPsw(String sso, String newPsw) {
		dao.updateOldPsw(sso, newPsw);
	}


	
//	public Collection<? extends GrantedAuthority> getRoles() {
////		Principal p = request.getUserPrincipal();
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
////		for (GrantedAuthority grantedAuthority : roles) {
////			System.out.println(grantedAuthority.getAuthority());
////		}
//		return auth.getAuthorities();
//		
//	}


}
