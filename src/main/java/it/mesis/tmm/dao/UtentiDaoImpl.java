package it.mesis.tmm.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import it.mesis.tmm.model.Utenti;

@Repository("utentiDao")
public class UtentiDaoImpl extends AbstractDao<Short, Utenti> implements UtentiDao {

	@Override
	public Utenti findById(short id) {
		return getByKey(id);
	}

	@Override
	public void saveUtenti(Utenti utenti) {
		persist(utenti);
	}

	@Override
	public void deleteUtentiById(short ID) {
		Query query = getSession().createSQLQuery("delete from Utenti where id = :id");
		query.setShort("id", ID);
		query.executeUpdate();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Utenti> findAllUtentis() {
		Criteria criteria = createEntityCriteria();
		return (List<Utenti>) criteria.list();
	}

	@Override
	public Utenti findUtentiById(short ID) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", ID));
		return (Utenti) criteria.uniqueResult();
	}

	@Override
	public Utenti findByUsername(String userName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("nomeUtente", userName));
		return (Utenti) crit.uniqueResult();
	}

	@Override
	public boolean isUserNameUnique(Short id, String userName) {
		Utenti u = findByUsername(userName);
		return (u == null || ((id != null) && (u.getId() == id)));
	}

	@Override
	public void deleteByUserName(String userName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("nomeUtente", userName));
		Utenti utenti = (Utenti)crit.uniqueResult();
		delete(utenti);
	}
	
}