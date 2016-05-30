package it.mesis.avis.service;

import it.mesis.avis.model.Audit;
import it.mesis.util.model.AuditDto;
import it.mesis.util.model.jq.ColumnsDataTable;
import it.mesis.util.model.jq.DataTable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface AuditService {
	
	void save(Audit audit);
	
	void audit(String msg);
	
	void audit(String userName, String msg);
	
	Audit findById(int id);
	
	List<Audit> findAllBySSO(String sso, Timestamp timestamp);

	List<Audit> findAllAudits();
	
	List<Audit> findAudits(Date dateFrom, Date dateTo, String user, String state);
	
	List<Audit> findAudits(int firstResult, int pageSize, Date dateFrom, Date dateTo, String user, String state);

	DataTable<AuditDto> findAuditsPage(ColumnsDataTable columnsDataTable, Date dateFrom, Date dateTo, String user, String state);
	
}
