package it.mesis.avis.service;

import it.mesis.avis.dao.AuditDao;
import it.mesis.avis.model.Audit;
import it.mesis.util.model.AuditDto;
import it.mesis.util.model.jq.ColumnsDataTable;
import it.mesis.util.model.jq.DataTable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("auditService")
@Transactional
public class AuditServiceImpl implements AuditService {

	@Autowired
	private AuditDao dao;

	@Override
	public void save(Audit audit) {
		dao.save(audit);
	}
	
	@Override
	public void audit(String msg) {
		audit(getUserName(), msg);
	}
	
	@Override
	public void audit(String userName, String msg) {
		Audit audit = new Audit(userName, msg);
		dao.save(audit);
	}

	@Override
	public Audit findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<Audit> findAllBySSO(String sso, Timestamp timestamp) {
		return dao.findAllBySSO(sso, timestamp);
	}
	
	private String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
	}
	
	@Override
	public List<Audit> findAllAudits() {
		return dao.findAllAudits();
	}

	@Override
	public List<Audit> findAudits(Date dateFrom, Date dateTo, String user, String state) {
		return dao.findAudits(dateFrom, dateTo, user, state);
	}

	@Override
	public 	List<Audit> findAudits(int firstResult, int pageSize, Date dateFrom, Date dateTo, String user, String state) {
		return dao.findAudits(firstResult, pageSize, dateFrom, dateTo, user, state);
	}
	
	@Override
	public DataTable<AuditDto> findAuditsPage(ColumnsDataTable columnsDataTable, Date dateFrom, Date dateTo, String user, String state) {
		return dao.findAuditsPages(columnsDataTable, dateFrom, dateTo, user, state);
	}

	

	

}
