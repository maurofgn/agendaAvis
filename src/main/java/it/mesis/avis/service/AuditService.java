package it.mesis.avis.service;

import it.mesis.avis.bean.Audit;
import it.mesis.avis.bean.jpa.AuditEntity;
import it.mesis.util.model.jq.ColumnsDataTable;
import it.mesis.util.model.jq.DataTable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface AuditService {
	
	void save(AuditEntity audit);
	
	void audit(String msg);
	
	void audit(String userName, String msg);
	
	AuditEntity findById(int id);
	
	List<AuditEntity> findAllBySSO(String sso, Timestamp timestamp);

	List<AuditEntity> findAllAudits();
	
	List<AuditEntity> findAudits(Date dateFrom, Date dateTo, String user, String state);
	
	List<AuditEntity> findAudits(int firstResult, int pageSize, Date dateFrom, Date dateTo, String user, String state);

	DataTable<Audit> findAuditsPage(ColumnsDataTable columnsDataTable, Date dateFrom, Date dateTo, String user, String state);
	
}
