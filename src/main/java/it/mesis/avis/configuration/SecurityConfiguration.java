package it.mesis.avis.configuration;

import it.mesis.utility.PswEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//	@Autowired
//	@Qualifier("customUserDetailsService")
//	UserDetailsService userDetailsService;
	
//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//		auth.authenticationProvider(authenticationProvider());
//	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//	    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//	    authenticationProvider.setUserDetailsService(userDetailsService);
//	    authenticationProvider.setPasswordEncoder(passwordEncoder());
//	    return authenticationProvider;
//	}
	
	@Autowired
    private AuthenticationSuccessHandler urlAuthenticationSuccessHandler;
	
	
	@Autowired
    private AuthenticationFailureHandler urlAuthenticationFailureHandler;
	
	
	@Autowired
	@Qualifier("authenticationProvider")
	AuthenticationProvider authenticationProvider;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
//		auth.setPasswordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
		return new PswEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		INSERT INTO user_profile (id,type) VALUES (1,'ADMIN');
//		INSERT INTO user_profile (id,type) VALUES (2,'AVIS');
//		INSERT INTO user_profile (id,type) VALUES (3,'DONA');
//		INSERT INTO user_profile (id,type) VALUES (4,'OPERA');
		
	  http
	  	.authorizeRequests()
	  	.antMatchers("/user/resetPassword").permitAll()
	  	.antMatchers("/", "/agenda*").access("hasRole('DONA') or hasRole('OPERA') or hasRole('AVIS') or hasRole('ADMIN')")
	  	.antMatchers("/admin/**","/newuser", "/delete-user*").access("hasRole('ADMIN')")
	  	.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
	  	.and().formLogin()
	  	.loginPage("/login")
	  	.successHandler(urlAuthenticationSuccessHandler)
	  	.failureHandler(urlAuthenticationFailureHandler)
	  	.usernameParameter("ssoId").passwordParameter("password")
	  	.and().csrf()
	  	.and().exceptionHandling().accessDeniedPage("/Access_Denied")
//	  	
	  	;
	}
}
