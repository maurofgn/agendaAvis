package it.mesis.avis.configuration;

import it.mesis.utility.PswEncoder;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*
 * This class is same as real HibernateConfiguration class in sources.
 * Only difference is that method dataSource & hibernateProperties 
 * implementations are specific to Hibernate working with H2 database.
 */

@Configuration
@EnableTransactionManagement
@ComponentScan({ "it.mesis.avis.dao", "it.mesis.avis.service" })
@PropertySource(value = { "classpath:email.properties" , "classpath:application.properties"})
public class HibernateTestConfiguration {

	@Autowired
	private Environment environment;
	
	@Value("${jdbc.driverClassName:@null}") private String driverClassName;
	@Value("${jdbc.url:@null}") private String url;
	@Value("${jdbc.username:@null}") private String username;
	@Value("${jdbc.password:@null}") private String password;
	@Value("${hibernate.dialect:org.hibernate.dialect.H2Dialect}") private String dialect;
	@Value("${hibernate.show_sql:true}") private String showSql;
	@Value("${hibernate.format_sql:true}") private String formatSql;
	@Value("${hibernate.hbm2ddl.auto:@null}") private String hbm2ddlAuto;	//validate | update | create | create-drop
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "it.mesis.avis.bean.jpa", "it.mesis.avis.revision" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		return sessionFactory;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		
		if (driverClassName == null) {
	        EmbeddedDatabase datasource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
	        return datasource;
		} else {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(driverClassName);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			return dataSource;
		}
	}
	
//    @Override
//    public DataSource dataSource() {
//        EmbeddedDatabase datasource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
//        return datasource;
//    }
//    
//    @Override
//    protected Properties additionalProperties() {
//        Properties properties = super.additionalProperties();
//        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//        return properties;
//    }    


//  @Profile("Production")
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.show_sql", showSql);
		properties.put("hibernate.format_sql", formatSql);

		if (hbm2ddlAuto != null && !hbm2ddlAuto.isEmpty())
			properties.put("hibernate.hbm2ddl.auto", hbm2ddlAuto);

		return properties;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
		return new PswEncoder();
	}
	
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
