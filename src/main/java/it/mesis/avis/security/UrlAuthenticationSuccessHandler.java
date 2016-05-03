package it.mesis.avis.security;

import it.mesis.avis.model.User;
import it.mesis.avis.service.AuditService;
import it.mesis.avis.service.UserService;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component("urlAuthenticationSuccessHandler")
public class UrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuditService auditService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
        
    	handle(request, response, authentication);
    	
        final HttpSession session = request.getSession(true);
        if (session != null) {
            session.setMaxInactiveInterval(30 * 60);	//timeout di sessione
			session.setAttribute(UserSession.USER_SESSION_KEY, userService.getUserSession(authentication.getName()));
        } 
        
        clearAuthenticationAttributes(request);
    }

    protected void handle(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
    	
        final String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
//            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(final Authentication authentication) {
    	
    	User user = userService.findBySso(authentication.getName());
    	
        String targetUrl = null;
        
    	if (user.getCodinternodonat() != null && !user.pswNotExpired(userService.getValidityDayPswDue())) {
    		auditService.audit(authentication.getName(), "Scadenza password tra " + (userService.getValidityDayPsw() - user.daysPasswordExpiry() + 1) + " gg");
    		return "/changePassword";	//solo per donatori. Le credenziali stanno per scadere
    	}
    	
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			
			if (grantedAuthority.getAuthority().equals("ROLE_DONA")) {
				targetUrl =  "/agenda";
			} else if (grantedAuthority.getAuthority().equals("ROLE_AVIS") || grantedAuthority.getAuthority().equals("ROLE_OPERA")) {
				targetUrl =  "/agenda";	//agenda senza preno
			} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
				targetUrl =  "/agenda";	//admin
				break;
			}
		}
        
		if (targetUrl == null)
			throw new IllegalStateException();
		
		return targetUrl;        
    }

    protected void clearAuthenticationAttributes(final HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
