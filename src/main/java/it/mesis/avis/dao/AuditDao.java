package it.mesis.avis.dao;

import it.mesis.avis.model.Audit;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface AuditDao {

	void save(Audit audit);
	
	Audit findById(int id);
	
	List<Audit> findAllBySSO(String sso, Timestamp timestamp);
	
	List<Audit> findAllAudits();

	List<Audit> findAudits(Date dateFrom, Date dateTo, String user, String state);
	
}

