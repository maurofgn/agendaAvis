package it.mesis.avis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LogHandlerInterceptor implements HandlerInterceptor {

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception {
//        System.out.println("=== AFTER COMPLETION ===");
//        System.out.println("Response ContentType: " + response.getContentType());
    }
     
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("=== POST HANDLE ==="); 
//        System.out.println("View name: " + modelAndView.getViewName());
//        System.out.println("Model: " + modelAndView.getModel());
    }
 
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("=== PRE HANDLE ===");
//        System.out.println("Request URI: " + request.getRequestURI()); 
//        System.out.println("Request Method: " + request.getMethod());
//        System.out.println("Request Query String: " + request.getQueryString()); 
        return true;
    }
    
}
