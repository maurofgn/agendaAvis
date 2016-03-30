package it.mesis.tmm.service;

import it.mesis.tmm.model.UserProfile;
import it.mesis.tmm.model.Utenti;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UtentiService userService;
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Utenti utenti = userService.findByUsername(userName);
		if (utenti == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		
		return new org.springframework.security.core.userdetails.User(
				utenti.getNomeUtente(), 
				utenti.getPassword(),
				utenti.isEnabled(),				//enabled
				true,							//AccountNotExpired
				utenti.pswNotExpired(),			//Credintial not expired
				true,							//Account not locked
				getGrantedAuthorities(utenti)	//authorities (rols)
				);
	}

	private List<GrantedAuthority> getGrantedAuthorities(Utenti utenti){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserProfile userProfile : utenti.getUserProfiles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		return authorities;
	}

}
