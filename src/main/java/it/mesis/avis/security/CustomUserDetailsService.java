package it.mesis.avis.security;

import it.mesis.avis.model.User;
import it.mesis.avis.model.UserProfile;
import it.mesis.avis.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.findBySso(userName);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		userService.addDefaultRole(user);
		
		return new org.springframework.security.core.userdetails.User(
				user.getSsoId(), 
				user.getPasswordDefault(),								//psw dell'utente assoavis o password di app_user
				user.isEnabled(),										//enabled
				user.isNotExpired(),									//AccountNotExpired
				user.pswNotExpired(userService.getValidityDayPsw()),	//Credential not expired
				user.isNotLocked(),										//Account not locked
				getGrantedAuthorities(user)								//authorities (rols)
				);
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserProfile userProfile : user.getUserProfiles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		return authorities;
	}
	
}
