package it.mesis.avis.interceptor;

import it.mesis.avis.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Trasfusionale implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if (!isValidUserName())
			return true;	//filtro non applicato perch� la sessione non � stata ancora autenticata
    	
    	if (request.getRequestURI().equalsIgnoreCase("/avis/logout"))
    		return true;	//filtro non applicato  per gli url (possono esser tolti se esclusi in appConfig.addInterceptors)
    	
		HttpSession session = request.getSession(false);	//false ==> se non esiste non la crea
		if (session == null 
//				|| session.getAttribute("trasfusionale") == null
				) {
//			String userName = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
			
//			Centritrasf ct = userService.centritrasfUnique(userName);
//			if (ct != null) {
//				session.setAttribute("trasfusionale", ct);
//			} else {
//				//trasfusionale mancante in sessione
//	            ModelAndView mav = new ModelAndView("boot/test");
//	            // qu� si pu� popolare il model, se c'� bisogno
//	            throw new ModelAndViewDefiningException(mav);
//	            /**
//	             * l'interruzione della catena dei filtri pu� essere eseguita anche nel modo seguente
//	             *   response.sendRedirect("/agendaAvis/boot/");
//	             *   return false;
//	             */
//			}
		}
        return true;
	}

    /**
     * 
     * @return true se la sessione � stata autenticata e l'utente � di tipo UserDetails, diversamente potrebbe essere anonymous
     */
	private boolean isValidUserName() {
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated() && 
				SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
