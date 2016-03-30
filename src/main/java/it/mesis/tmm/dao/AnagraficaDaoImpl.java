package it.mesis.tmm.dao;

import it.mesis.tmm.model.Anagrafica;
import it.mesis.util.model.Tipizza;
import it.mesis.utility.Utility;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("anagraficaDao")
public class AnagraficaDaoImpl extends AbstractDao<Integer, Anagrafica> implements AnagraficaDao {

	public Anagrafica findById(int id) {
		return getByKey(id);
	}

	public void saveAnagrafica(Anagrafica anagrafica) {
		anagrafica.setCognomeNome();
		persist(anagrafica);
	}

	public void deleteAnagraficaById(int id) {
		Query query = getSession().createSQLQuery("delete from Anagrafica where id = :id");
		query.setInteger("id", id);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Anagrafica> findAllAnagraficas() {
		Criteria criteria = createEntityCriteria();
		criteria.setMaxResults(MAX_RESULTS);
		
		criteria.add(Restrictions.ne("comunicf.id", 0));	//>TODO: togliere dopo aver risolto il problema delle foreign key con valore 0 invece di null
		
		return (List<Anagrafica>) criteria.list();
	}

//	public Anagrafica findAnagraficaById(int id) {
//		Criteria criteria = createEntityCriteria();
//		criteria.add(Restrictions.eq("id", id));
//		return (Anagrafica) criteria.uniqueResult();
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Anagrafica> findAllAnagraficas(Anagrafica anagrafica) {
		
		Utility.nullifyStrings(anagrafica);
		
		Example example = Example.create(anagrafica).enableLike().ignoreCase().excludeZeroes();
		
//		if (anagrafica.getSesso() == Sex.Indefinito)
//			example.excludeProperty("sesso");
		
		example.excludeProperty("consensoprivacy");
		example.excludeProperty("datibloccati");
		example.excludeProperty("nonvalidata");
		
		Criteria criteria = createEntityCriteria().add(example);
		
		criteria.setMaxResults(MAX_RESULTS);
//		criteria.add(Restrictions.ne("comunicf.id",  (short)0));	//>TODO: togliere dopo aver risolto il problema delle foreign key con valore 0 invece di null

		List<Anagrafica> retValue = (List<Anagrafica>) criteria.list();
		
		return retValue;
	}
	
	@Override
	public Tipizza getTipizza(int id) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT "); 
		sb.append("re10.valore ab0 "); 
		sb.append(",re11.valore rh "); 
		sb.append(",re12.valore ccee "); 
		sb.append(",re13.valore du "); 
		sb.append(",re14.valore Kell "); 
		sb.append(",re51.valore Cellano "); 
		sb.append("FROM TIPIZZAZIONE ti "); 
		sb.append("left join RISULTATIESAME re10 on re10.tipoEsame = 10 and re10.indice = ti.AB0 "); 
		sb.append("left join RISULTATIESAME re11 on re11.tipoEsame = 11 and re11.indice = ti.Rh "); 
		sb.append("left join RISULTATIESAME re12 on re12.tipoEsame = 12 and re12.indice = ti.ccee "); 
		sb.append("left join RISULTATIESAME re13 on re13.tipoEsame = 13 and re13.indice = ti.Du "); 
		sb.append("left join RISULTATIESAME re14 on re14.tipoEsame = 14 and re14.indice = ti.Kell "); 
		sb.append("left join RISULTATIESAME re51 on re51.tipoEsame = 51 and re51.indice = ti.Kell + case when re14.VALORE = 'pos' then 2 else 0 end "); 
		sb.append("where "); 
		sb.append("ti.IDANAGRAFICA = :id ");
		
		Query q = getSession().createSQLQuery(sb.toString());
		
//		q.uniqueResult();
		
		q.setInteger("id", id);
	    @SuppressWarnings("unchecked")
		List<String[]> rows = (List<String[]>)q.list();
	    Object[] row = rows.get(0);
    	Tipizza retValue = new Tipizza((String)row[0], (String)row[1], (String)row[2], (String)row[3], (String)row[4], (String)row[5]);
		
		return retValue;
	} 


}
