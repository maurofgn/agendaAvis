package it.mesis.tmm.configuration;

import it.mesis.tmm.interceptor.Trasfusionale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "it.mesis.tmm")
public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	
	/*
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     *
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
    /*
     * Configure Converter to be used.
     * We need a converter to convert string values[Roles] to UserProfiles in newUser.jsp
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(roleToUserProfileConverter);
    }
    
	@Bean
	public MessageSource messageSource() {
	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasename("messages");
//	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	/**
	 * Questo metodo è necessario, diversamente Trasfusionale non è interno all'ambiente di spring e non può ricevere eventuali iniezzioni
	 * @return Trasfusionale
	 */
	@Bean
	public Trasfusionale trasfusionaleInterceptor() {
	    return new Trasfusionale();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    InterceptorRegistration x = registry.addInterceptor(trasfusionaleInterceptor());
	    x.excludePathPatterns("/login", "/logout", "/defineSid", "/tmm/boot/");	//pattern url per i quali non viene eseguito il filtro   
	} 
}
