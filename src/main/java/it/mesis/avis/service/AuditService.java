package it.mesis.avis.service;

import it.mesis.avis.model.Audit;

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
	
}
