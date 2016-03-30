package it.mesis.tmm.dao;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AnagraficaDaoImplTest extends EntityDaoImplTest{

	@Autowired
	AnagraficaDao anagraficaDao;

	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Anagrafica.xml"));
		return dataSet;
	}
	
	/* In case you need multiple datasets (mapping different tables) and you do prefer to keep them in separate XML's
	@Override
	protected IDataSet getDataSet() throws Exception {
	  IDataSet[] datasets = new IDataSet[] {
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Anagrafica.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Benefits.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Departements.xml"))
	  };
	  return new CompositeDataSet(datasets);
	}
	*/

	@Test
	public void findById(){
		Assert.assertNotNull(anagraficaDao.findById(1));
		Assert.assertNull(anagraficaDao.findById(3));
	}

	
//	@Test
//	public void saveAnagrafica(){
//		anagraficaDao.saveAnagrafica(getSampleAnagrafica());
//		Assert.assertEquals(anagraficaDao.findAllAnagraficas().size(), 3);
//	}
	
//	@Test
//	public void deleteAnagraficaBySsn(){
//		anagraficaDao.deleteAnagraficaById(1);
//		Assert.assertEquals(anagraficaDao.findAllAnagraficas().size(), 1);
//	}
	
//	@Test
//	public void deleteAnagraficaByInvalidSsn(){
//		anagraficaDao.deleteAnagraficaBySsn("23423");
//		Assert.assertEquals(anagraficaDao.findAllAnagraficas().size(), 2);
//	}

	@Test
	public void findAllAnagraficas(){
		Assert.assertEquals(anagraficaDao.findAllAnagraficas().size(), 2);
	}
	
	@Test
	public void findAnagraficaBySsn(){
		Assert.assertNotNull(anagraficaDao.findById(1));
		Assert.assertNull(anagraficaDao.findById(5));
	}

//	public Anagrafica getSampleAnagrafica(){
//		Anagrafica employee = new Anagrafica();
//		employee.setNome("Karen");
//		employee.setCognome("Pippo");
////		employee.setDatanascita(new LocalDate());
//		return employee;
//	}

}
