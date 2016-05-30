package it.mesis.avis.dao;

import it.mesis.avis.model.Audit;
import it.mesis.util.model.AuditDto;
import it.mesis.util.model.AuditMapper;
import it.mesis.util.model.jq.ColumnsDataTable;
import it.mesis.util.model.jq.DataTable;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);	//To avoid duplicates.
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

	@Override
	public List<Audit> findAudits(Date dateFrom, Date dateTo, String user, String state) {
		return findAudits(-1, -1, dateFrom, dateTo, user, state);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Audit> findAudits(int firstResult, int pageSize, Date dateFrom, Date dateTo, String user, String state) {
		
        Criteria criteria = getCriteria(dateFrom, dateTo, user, state)
                .addOrder(Order.desc("created"))
                .addOrder(Order.asc("id"));

        if (firstResult >= 0 && pageSize > 0) {
        	criteria.setFirstResult(firstResult);
        	criteria.setMaxResults(pageSize);
        }

		List<Audit> results = (List<Audit>)criteria.list();
		
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DataTable<AuditDto> findAuditsPages(ColumnsDataTable columnsDataTable, Date dateFrom, Date dateTo, String user, String state) {
		
        Criteria criteria = getCriteria(dateFrom, dateTo, user, state);
        
		List<Order> orders = columnsDataTable.getOrder();
		for (Order order : orders) {
			criteria.addOrder(order);
		}

        if (columnsDataTable.getStart() >= 0 && columnsDataTable.getLength() > 0) {
        	criteria.setFirstResult(columnsDataTable.getStart());
        	criteria.setMaxResults(columnsDataTable.getLength());
        }

		Long totRec = count();											//record totali della tabella
		Long recordsFiltered = count(dateFrom, dateTo, user, state);	//record totali che soddisfano il filtro
		
		List<Audit> audits = (List<Audit>)criteria.list();
		List<AuditDto> data = AuditMapper.map(audits);
		
		return new DataTable<AuditDto>(totRec.intValue(), recordsFiltered.intValue(), data, columnsDataTable.getDraw());
	}

	@Override
	public Long count() {
		return count(null, null, null, null);
	}
	
	@Override
	public Long count(Date dateFrom, Date dateTo, String user, String state) {
		return (Long) getCriteria(dateFrom, dateTo, user, state).setProjection(Projections.rowCount()).uniqueResult();
	}
	
	/**
	 * 
	 * @param dateFrom
	 * @param dateTo
	 * @param user
	 * @param state
	 * @return Criteria con le restrizioni dei parametri passati
	 */
	private Criteria getCriteria(Date dateFrom, Date dateTo, String user, String state) {
        Criteria criteria = createEntityCriteria();
        
        if (dateFrom != null)
        	criteria.add(Restrictions.ge("created", dateFrom));
        
        if (dateTo != null)
        	criteria.add(Restrictions.le("created", dateTo));
        
        if (user != null && !user.isEmpty())
        	criteria.add(Restrictions.like("ssoId", user, MatchMode.ANYWHERE));
        
        if (state != null && !state.isEmpty())
        	criteria.add(Restrictions.like("state", state, MatchMode.ANYWHERE));

        return criteria;
	}

}
