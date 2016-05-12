package it.mesis.avis.configuration;

import it.mesis.utility.PswEncoder;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
 * This class is same as real HibernateConfiguration class in sources.
 * Only difference is that method dataSource & hibernateProperties 
 * implementations are specific to Hibernate working with H2 database.
 */

@Configuration
//@EnableTransactionManagement
//@ComponentScan({ "it.mesis.avis.dao" })
@PropertySource({"classpath:applicationTest.properties", "classpath:emailTest.properties" })
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

//	@Bean
//	public LocalSessionFactoryBean sessionFactory() {
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource());
//		sessionFactory.setPackagesToScan(new String[] { "it.mesis.avis.model" });
//		sessionFactory.setHibernateProperties(hibernateProperties());
//		return sessionFactory;
//	}
//
//	@Bean(name = "dataSource")
//	public DataSource dataSource() {
//		
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		
//        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
//        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
//		
//		return dataSource;
//	}
//
//	private Properties hibernateProperties() {
//		Properties properties = new Properties();
//		
//		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
//        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
//
//		return properties;
//	}
//
//	@Bean
//	@Autowired
//	public HibernateTransactionManager transactionManager(SessionFactory s) {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(s);
//		return txManager;
//	}
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
		return new PswEncoder();
	}
	
}
