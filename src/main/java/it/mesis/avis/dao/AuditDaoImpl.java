package it.mesis.avis.dao;

import it.mesis.avis.model.Audit;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("auditDao")
public class AuditDaoImpl extends AbstractDao<Integer, Audit> implements AuditDao {
	
	@Override
	public void save(Audit audit) {
		persist(audit);
	}

	@Override
	public Audit findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<Audit> findAllBySSO(String sso, Timestamp timestamp) {
		
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("created"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		@SuppressWarnings("unchecked")
		List<Audit> audits = (List<Audit>) criteria.list();
		
		return audits;

	}

	/**
	 * @return tutti i record di Audit
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> findAllAudits() {
		return createEntityCriteria().list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> findAudits(Date dateFrom, Date dateTo, String user, String state) {
		Criteria criteria = createEntityCriteria()
			.add(Restrictions.ge("created", dateFrom))
			.add(Restrictions.le("created", dateTo));
		
		if (user != null && !user.isEmpty())
			criteria.add(Restrictions.like("ssoId", user, MatchMode.ANYWHERE));
		
		if (state != null && !state.isEmpty())
			criteria.add(Restrictions.like("state", state, MatchMode.ANYWHERE));
		
		criteria.addOrder(Order.desc("created"));

		return criteria.list();
	}

}
