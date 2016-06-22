package it.mesis.avis.service;

import it.mesis.avis.bean.Audit;
import it.mesis.avis.bean.jpa.AuditEntity;
import it.mesis.avis.dao.AuditDao;
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
	public void save(AuditEntity audit) {
		dao.save(audit);
	}
	
	@Override
	public void audit(String msg) {
		audit(getUserName(), msg);
	}
	
	@Override
	public void audit(String userName, String msg) {
		AuditEntity audit = new AuditEntity(userName, msg);
		dao.save(audit);
	}

	@Override
	public AuditEntity findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<AuditEntity> findAllBySSO(String sso, Timestamp timestamp) {
		return dao.findAllBySSO(sso, timestamp);
	}
	
	private String getUserName() {
		return SecurityContextHolder.getContext().getAuthentication().getName(); //get logged in username
	}
	
	@Override
	public List<AuditEntity> findAllAudits() {
		return dao.findAllAudits();
	}

	@Override
	public List<AuditEntity> findAudits(Date dateFrom, Date dateTo, String user, String state) {
		return dao.findAudits(dateFrom, dateTo, user, state);
	}

	@Override
	public 	List<AuditEntity> findAudits(int firstResult, int pageSize, Date dateFrom, Date dateTo, String user, String state) {
		return dao.findAudits(firstResult, pageSize, dateFrom, dateTo, user, state);
	}
	
	@Override
	public DataTable<Audit> findAuditsPage(ColumnsDataTable columnsDataTable, Date dateFrom, Date dateTo, String user, String state) {
		return dao.findAuditsPages(columnsDataTable, dateFrom, dateTo, user, state);
	}

}
