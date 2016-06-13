package it.mesis.avis.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@Profile("Production")
@Configuration
@EnableTransactionManagement
//@PropertySource(value = { "classpath:application.properties" })
public class HibernateConfiguration {
	
	@Value("${jdbc.driverClassName:net.sourceforge.jtds.jdbc.Driver}") private String driverClassName;
	@Value("${jdbc.url:jdbc:jtds:sqlserver://192.200.200.38:1433;databaseName=assoAvis_test}") private String url;
	@Value("${jdbc.username:TMMSA}") private String username;
	@Value("${jdbc.password:1406MeSIS}") private String password;
	@Value("${hibernate.dialect:org.hibernate.dialect.SQLServerDialect}") private String dialect;
	@Value("${hibernate.show_sql:false}") private String showSql;
	@Value("${hibernate.format_sql:false}") private String formatSql;
	@Value("${hibernate.hbm2ddl.auto:@null}") private String hbm2ddlAuto;	//validate | update | create | create-drop 
	
//    @Autowired
//    private Environment environment;

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
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

//    @Profile("Production")
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, dialect);
        properties.put(Environment.SHOW_SQL, showSql);
        properties.put(Environment.FORMAT_SQL, formatSql);
        
        if (hbm2ddlAuto != null && !hbm2ddlAuto.isEmpty())
        	properties.put(Environment.HBM2DDL_AUTO, hbm2ddlAuto);
        
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

