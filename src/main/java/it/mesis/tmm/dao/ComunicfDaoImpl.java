package it.mesis.tmm.dao;

import it.mesis.tmm.model.Comunicf;
import it.mesis.util.model.AutoCompleteData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("comunicfDao")
public class ComunicfDaoImpl extends AbstractDao<java.lang.Short, Comunicf> implements ComunicfDao {

	public Comunicf findById(short id) {
		return getByKey(id);
	}

	public void saveComunicf(Comunicf comunicf) {
		persist(comunicf);
	}

	public void deleteComunicfById(short id) {
		Query query = getSession().createSQLQuery("delete from Comunicf where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Comunicf> findAllComunicfs() {
		Criteria criteria = createEntityCriteria();
		return (List<Comunicf>) criteria.list();
	}

	public Comunicf findComunicfById(short id) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", id));
		return (Comunicf) criteria.uniqueResult();
	}
	
	public List<AutoCompleteData> json(String nome) {
		
		List<AutoCompleteData> retValue = new ArrayList<AutoCompleteData>();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT "); 
		sb.append("max(c.id) as id "); 
		sb.append(",c.descrizione, c.prov "); 
		sb.append("FROM Comunicf as c "); 
		sb.append("where c.nonvisibile = 0 "); 
		sb.append("and c.descrizione like :nome "); 
		sb.append("group by c.descrizione, c.prov "); 
		sb.append("order by c.descrizione, c.prov ");
		
		Query q = getSession().createQuery(sb.toString());
		q.setString("nome", "%" + nome + "%");		
		q.setMaxResults(MAX_RESULTS);
		
	    @SuppressWarnings("unchecked")
		List<Object[]> rows = (List<Object[]>)q.list();
	     for(Object[] row: rows){
			retValue.add(new AutoCompleteData(Comunicf.cittaProv((String)row[1], (String)row[2]), (Short)row[0]));
	     }
		
		return retValue;
	}

}



