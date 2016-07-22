package it.mesis.avis.configuration;

import it.mesis.avis.Application;
import it.mesis.avis.interceptor.MyInterceptor;

import java.util.Properties;

//import javax.annotation.Resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.format.FormatterRegistry;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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
@ComponentScan(basePackageClasses = Application.class)
@PropertySource(value = { "classpath:email.properties" , "classpath:application.properties"})

public class AppConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;
	
    @Autowired
    private Environment env;

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
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	/**
	 * Questo metodo è necessario, diversamente non è interno all'ambiente di spring e non può essere iniettato
	 * @return Trasfusionale
	 */
	@Bean
	public MyInterceptor myInterceptor() {
	    return new MyInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    InterceptorRegistration registration = registry.addInterceptor(myInterceptor());
	    registration.excludePathPatterns("/login", "/logout", "/defineSid");	// URL pattern for which the filter is excluded
	}
	
    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        final JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(env.getProperty("smtp.host"));
        mailSenderImpl.setPort(env.getProperty("smtp.port", Integer.class));
        mailSenderImpl.setProtocol(env.getProperty("smtp.protocol"));
        mailSenderImpl.setUsername(env.getProperty("smtp.username"));
        mailSenderImpl.setPassword(env.getProperty("smtp.password"));
        final Properties javaMailProps = new Properties();
        javaMailProps.put("mail.smtp.auth", true);
        javaMailProps.put("mail.smtp.starttls.enable", true);
        mailSenderImpl.setJavaMailProperties(javaMailProps);
        return mailSenderImpl;
    }
    
    
	/**
	 * Add PropertySourcesPlaceholderConfigurer to make placeholder work. This
	 * method MUST be static
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {

		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		propertySourcesPlaceholderConfigurer.setNullValue("@null");	//stringa con la quale si riconosce il null
		String activeProfile = System.getProperty("spring.profiles.active", "production");
		ClassPathResource resource = null;
		// choose different property files for different active profile
		if ("development".equalsIgnoreCase(activeProfile)) {
			resource = new ClassPathResource("development.properties");
		} else if ("test".equalsIgnoreCase(activeProfile)) {
			resource = new ClassPathResource("test.properties");
		} else {
			resource = new ClassPathResource("production.properties");
		}

		// load the property file
		propertySourcesPlaceholderConfigurer.setLocation(resource);
		propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders( true );

		return propertySourcesPlaceholderConfigurer;
	}
    
}
