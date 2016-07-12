package it.mesis.avis.security;

import it.mesis.avis.exception.StatusException;
import it.mesis.avis.service.AuditService;
import it.mesis.avis.service.UserService;

import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("authenticationProvider")
public class AuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	AuditService auditService;

	@Autowired
	@Qualifier("customUserDetailsService")
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
		super.setPasswordEncoder(passwordEncoder);
	}
	
//	@Override
//	public void setPasswordEncoder(Object passwordEncoder)  {
//		super.setPasswordEncoder(this.passwordEncoder);
//	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		try {
			Authentication auth = super.authenticate(authentication);
			//if reach here, means login success, else exception will be thrown. Reset the user_attempts
			userService.resetFailAttempts(authentication.getName());
			
			//verifica, attraverso userService le caratteristiche dell'utente, se è un donatore ed il suo stato
			UserSession userSession = userService.getUserSession(authentication.getName());
			
//	    	User user = userService.findBySso(authentication.getName());
//	        String targetUrl = null;
//	    	if (user.getCodinternodonat() != null && !user.pswNotExpired(userService.getValidityDayPsw())) {
//	    		return "/changePassword";	//Credential expired, solo per donatori
//	    	}
	    	
//			//chk scadenza psw
//	    	if (userSession.getDonaStatus().getCodinternodonat() != null && !user.pswNotExpired(userService.getValidityDayPsw())) {
//	    		return "/changePassword";	//Credential expired, solo per donatori
//	    	}

			
			if (userSession.getDonaStatus() != null && !userSession.getDonaStatus().prenoWeb()) {
				
//				//il donatore non può donare. I motivi sono specificati in causa
				String causa = userSession.getDonaStatus().getMsg();
				auditService.audit(authentication.getName(), "Autenticato con successo, ma non può donare: " + causa);
				String msg = messageSource.getMessage("msg.no.preno", new String[]{causa}, Locale.getDefault());
				throw new StatusException(msg);
			}
			
			auditService.audit(authentication.getName(), "Autenticato con successo");
			
			return auth;
			
		} catch (CredentialsExpiredException e) {
			auditService.audit(authentication.getName(), "Credenziali scadute");
			userService.credentialsExpired(authentication.getName());
			throw e;
		} catch (BadCredentialsException e) {
			auditService.audit(authentication.getName(), "Credenziali non corrette");
			//invalid login, update to user_attempts
			userService.updateFailAttempts(authentication.getName());
			throw e;
		} catch (LockedException e) {
			auditService.audit(authentication.getName(), "Utente bloccato");
			//this user is locked!
			String error = "";
			UserAttempts userAttempts = userService.getUserAttempts(authentication.getName());
			if (userAttempts != null) {
				Date lastAttempts = userAttempts.getLastModified();
				error = "Utente "  + authentication.getName() + " bloccato ! "
//						+ "<br><br>" + authentication.getName()
						+ "<br>Ultimo tentativo: " + lastAttempts
						+ "<br>Dopo " + userService.getMaxAccountAttempt() + " tentativi falliti";
			} else {
				error = e.getMessage();
				auditService.audit(authentication.getName(), "errore in fase autenticazione: " + error);
			}
			
			throw new LockedException(error);
		}
	}

}