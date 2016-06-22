package it.mesis.avis.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import it.mesis.avis.bean.jpa.UserProfileEntity;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfileEntity>implements UserProfileDao {

	@SuppressWarnings("unchecked")
	public List<UserProfileEntity> findAll(){
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("type"));
		return (List<UserProfileEntity>)crit.list();
	}
	
	public UserProfileEntity findById(int id) {
		return getByKey(id);
	}
	
	public UserProfileEntity findByType(String type) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("type", type));
		return (UserProfileEntity) crit.uniqueResult();
	}

	@Override
	public void save(UserProfileEntity userProfile) {
		persist(userProfile);
	}
}
