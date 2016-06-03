package it.mesis.avis.security;

import it.mesis.avis.exception.StatusException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component("urlAuthenticationFailureHandler")
public class UrlAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	@Autowired
	MessageSource messageSource;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		String url = "/login";

		String msg = exception.getMessage();
		if(exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			msg = messageSource.getMessage("message.badCredentials", null, request.getLocale());
		} else if(exception.getClass().isAssignableFrom(LockedException.class)) {
			msg = messageSource.getMessage("message.locked", null, request.getLocale());
		} else if(exception.getClass().isAssignableFrom(CredentialsExpiredException.class)) {
			msg = messageSource.getMessage("message.credentialsExpired", null, request.getLocale());
//			url = "/changePassword";
		}
		
//		StatusException	//questo errore viene restituito se l'utente si è autenticato correttamente, ma non ha i requisiti per la donazione
		
		setMsgErr(request, msg);
		
		getRedirectStrategy().sendRedirect(request, response, url);
	}
	
    private void setMsgErr(final HttpServletRequest request, String msg) {
    	final HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, new StatusException(msg));
    }

//    public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
//        this.redirectStrategy = redirectStrategy;
//    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }	
	
	
}