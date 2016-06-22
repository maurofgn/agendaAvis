package it.mesis.avis.dao;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

//import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
//import org.testng.annotations.BeforeMethod;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.mesis.avis.configuration.HibernateTestConfiguration;
import it.mesis.avis.hsqlDataType.DataTypeFactory;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateTestConfiguration.class })
@Transactional()
public abstract class EntityDaoImplTest 
//extends AbstractTransactionalTestNGSpringContextTests 
{

	@Autowired
	DataSource dataSource;

	@Before
	public void setUp() throws Exception {
		IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
		
		DatabaseConfig config = dbConn.getConfig();
		config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new DataTypeFactory());
		
		DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataSet());
	}
	
	protected abstract IDataSet getDataSet() throws Exception;
	
	
//	protected IDatabaseConnection getConnection() throws Exception
//	{
//	  IDatabaseConnection connection = new DatabaseConnection(dataSource.getConnection());
//	  DatabaseConfig config = connection.getConfig();
//	  config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqlDataTypeFactory());
//	 
//	  return connection;
//	}

}