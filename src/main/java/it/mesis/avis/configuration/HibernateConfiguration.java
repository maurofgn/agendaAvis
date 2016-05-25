package it.mesis.avis.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


//@Configuration
//@PropertySource("classpath:/com/${my.placeholder:default/path}/app.properties")
//public class AppConfig {
//    @Autowired
//    Environment env;
//
//    @Bean
//    public TestBean testBean() {
//        TestBean testBean = new TestBean();
//        testBean.setName(env.getProperty("testbean.name"));
//        return testBean;
//    }
//}



//@Profile("Production")
@Configuration
@EnableTransactionManagement
//@ComponentScan({ "it.mesis.avis.configuration" })

//@PropertySource(value = { "classpath:application_sqlServer.properties" })

//@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {
	
	@Value("${jdbc.driverClassName:net.sourceforge.jtds.jdbc.Driver}") private String driverClassName;
	@Value("${jdbc.url:jdbc:jtds:sqlserver://192.200.200.38:1433;databaseName=assoAvis_test}") private String url;
	@Value("${jdbc.username:TMMSA}") private String username;
	@Value("${jdbc.password:1406MeSIS}") private String password;

	@Value("${hibernate.dialect:org.hibernate.dialect.SQLServerDialect}") private String dialect;
	@Value("${hibernate.show_sql:false}") private String showSql;
	@Value("${hibernate.format_sql:false}") private String formatSql;
	@Value("${hibernate.hbm2ddl.auto:}") private String hbm2ddlAuto;	//validate | update | create | create-drop 
	
    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {    	
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "it.mesis.avis.model", "it.mesis.avis.revision" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
//    @Profile("Production")
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
//        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
//        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

//    @Profile("Production")
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        
//    	@Value("${hibernate.dialect:org.hibernate.dialect.SQLServerDialect}") private String dialect;
//    	@Value("${hibernate.show_sql:false}") private String showSql;
//    	@Value("${hibernate.format_sql:false}") private String formatSql;
//    	@Value("${hibernate.hbm2ddl.auto:}") private String hbm2ddlAuto;	//validate | update | create | create-drop

//        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
//        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
//        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        
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
}

