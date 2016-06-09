package it.mesis.avis.configuration;

import it.mesis.utility.PswEncoder;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * This class is same as real HibernateConfiguration class in sources.
 * Only difference is that method dataSource & hibernateProperties 
 * implementations are specific to Hibernate working with H2 database.
 */

@Configuration
//@EnableTransactionManagement
//@ComponentScan({ "it.mesis.avis.dao" })
@PropertySource({"classpath:application.properties", "classpath:email.properties" })
public class TestConfiguration {

	@Autowired
	private Environment environment;
	
//	@Bean
//	public MessageSource messageSource() {
//	    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//	    messageSource.setBasename("messages");
//	    messageSource.setDefaultEncoding("UTF-8");
//	    return messageSource;
//	}
	
    @Bean
    public JavaMailSenderImpl javaMailSenderImpl() {
        final JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(environment.getProperty("smtp.host"));
        mailSenderImpl.setPort(environment.getProperty("smtp.port", Integer.class));
        mailSenderImpl.setProtocol(environment.getProperty("smtp.protocol"));
        mailSenderImpl.setUsername(environment.getProperty("smtp.username"));
        mailSenderImpl.setPassword(environment.getProperty("smtp.password"));
        final Properties javaMailProps = new Properties();
        javaMailProps.put("mail.smtp.auth", true);
        javaMailProps.put("mail.smtp.starttls.enable", true);
        mailSenderImpl.setJavaMailProperties(javaMailProps);
        return mailSenderImpl;
    }


	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
		return new PswEncoder();
	}
	
	@Bean(name = "passwordEncoderBCrypt")
	public PasswordEncoder passwordEncoder2() {
	    return new BCryptPasswordEncoder(12);
//		return new PswEncoder();
	}
	
	/**
	 * Add PropertySourcesPlaceholderConfigurer to make placeholder work. This
	 * method MUST be static
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {

		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		
		propertySourcesPlaceholderConfigurer.setNullValue("@null");	//stringa con la quale si riconosce il null

		String activeProfile = System.getProperty("spring.profiles.active", "test");

		ClassPathResource resource = null;
		// choose different property files for different active profile
		if ("development".equalsIgnoreCase(activeProfile)) {
			resource = new ClassPathResource("development.properties");
		} else if ("production".equalsIgnoreCase(activeProfile)) {
			resource = new ClassPathResource("production.properties");
		} else {
			resource = new ClassPathResource("test.properties");
		}

		// load the property file
		propertySourcesPlaceholderConfigurer.setLocation(resource);

		return propertySourcesPlaceholderConfigurer;
	}	
	
}
