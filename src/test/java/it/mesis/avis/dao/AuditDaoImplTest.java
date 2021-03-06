package it.mesis.avis.dao;

import it.mesis.avis.bean.Audit;
import it.mesis.util.model.jq.ColumnsDataTable;
import it.mesis.util.model.jq.DataTable;

import java.util.Date;
import java.util.GregorianCalendar;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

public class AuditDaoImplTest extends EntityDaoImplTest {

	@Autowired
	AuditDao auditDao;

	@Override
	protected IDataSet getDataSet() throws Exception {
		
		FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
		IDataSet dataSet = flatXmlDataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("audit.xml"));
		return dataSet;
	}
	
	@Test
	public void pagination() {
	    String user = "C";
	    String state = "a";
	    
	    GregorianCalendar gc = new GregorianCalendar(2015, 0, 1);
	    Date dateFrom = gc.getTime();
	    Date dateTo = null;
	    
		MockHttpServletRequest request = new MockHttpServletRequest();

		ColumnsDataTable columnsDataTable = new ColumnsDataTable(request);
	    
	    DataTable<Audit> retValue = auditDao.findAuditsPages(columnsDataTable, dateFrom, dateTo, user, state);
	    
	    Assert.assertTrue(retValue.getData().size() > 0);
	}

}
