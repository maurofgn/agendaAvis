package it.mesis.avis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		if (!isValidUserName())
			return true;	//filter don't applied becose session is not authenticate
    	
    	if (request.getRequestURI().endsWith("logout") )
    		return true;	//filter don't applied (possono esser tolti se esclusi in appConfig.addInterceptors)
    	
		HttpSession session = request.getSession(false);	//false ==> per session not existing don't builds it
		if (session != null) {
//			String userName = SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
			
//			Centritrasf ct = userService.centritrasfUnique(userName);
//			if (ct != null) {
//				session.setAttribute("trasfusionale", ct);
//			} else {
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
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
