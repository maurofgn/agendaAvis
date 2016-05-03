package it.mesis.avis.dao;

import it.mesis.avis.model.Agenda;
import it.mesis.avis.model.Donatore;
import it.mesis.avis.model.User;
import it.mesis.avis.security.UserAttempts;
import it.mesis.avis.security.UserSession;
import it.mesis.util.model.DonaStatus;
import it.mesis.util.model.DonaStatusType;
import it.mesis.util.model.State;
import it.mesis.util.model.TipoDonaPuntoPrel;
import it.mesis.util.model.YearMonth;
import it.mesis.utility.TimeUtil;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	
	public void save(User user) {
		persist(user);
	}
	
	@Override
	public User findById(int id) {
		return getByKey(id);
	}
	
	public User findBySSO(String sso) {
		if (sso == null || sso.isEmpty())
			return null;
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		return (User) crit.uniqueResult();
	}
	
	@Override
	public User findUserByCodFisc(String codFisc) {
		if (codFisc == null || codFisc.isEmpty())
			return null;
		
		String hql = "select u FROM User as u inner join u.donatore as d where d.codicefiscale = :codFisc";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("codFisc", codFisc);
		
		@SuppressWarnings("unchecked")
		List<User> results = query.list();
		
		User user = null;
		
		if (!results.isEmpty()) {
			user = results.get(0);
			Hibernate.initialize(user.getDonatore());	//forza il load del donatore
		}
		
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return users;
	}
	
	public void deleteBySSO(String sso) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();
		delete(user);
	}

	@Override
	public void resetFailAttempts(String sso) {
		Query query = getSession().createSQLQuery("UPDATE APP_USER SET attempts = 0, last_Access = :now WHERE sso_id = :sso_id");
		query.setParameter("now", new Timestamp(System.currentTimeMillis()));
		query.setParameter("sso_id", sso);
//		int result = 
		query.executeUpdate();
//		System.out.println(result);
		
//		User user = findBySSO(sso);
//		if (user == null) 
//			return;
//		
//		user.setAttempts(0);
//		user.setLastAccess(new Timestamp(System.currentTimeMillis()));
	}
	
	@Override
	public void updateFailAttempts(String sso, int maxAccountAttempt) {

		if (sso == null || sso.isEmpty())
			return;

		User user = findBySSO(sso);
		if (user == null) 
			return;
		
		user.addAttempts();
		
		if (user.getAttempts() >= maxAccountAttempt) {
			// locked user
			user.setState(State.LOCKED);
			// throw exception
			//throw new LockedException("User Account is locked!");
		}
	}
	
	@Override
	public void credentialsExpired(String sso) {
		if (sso == null || sso.isEmpty())
			return;
		
		//TODO: niente da fare. Ma si potrebbe usare questo punto per aggiornare l'audit x password scaduta 
	}
	
	@Override
	public UserAttempts getUserAttempts(String sso) {
		User user = findBySSO(sso);		
		return user != null ? user.getUserAttempts() : null;
	}

//	@Override
//	public DonaStatus getStatus(String sso) {
//		
//		User user = findBySSO(sso);
//		Donatore donatore = user.getDonatore();
//		
//		if (donatore == null)
//			return null;
//		
//		if (donatore != null) {
//			DonaStatus donaStatus = donatore.getDonaStatus();
//			donaStatus.getStatus().forEach(donaStatusType -> donaStatusType.setSuspended(setSuspend(donaStatusType, donatore.getCodinternodonat())));
//			
//			donaStatus.setListTipoDonazPuntiPrel(getTipoDonazPuntiPrel(donaStatus.getListTipoDona()));
//			donaStatus.setAgenda(getAgenda(donatore.getCodinternodonat()));	//setta l'eventuale prenotazione attiva del donatore
//		}
//		
//		return donaStatus;
//	}
	
	@Override
	public UserSession getUserSession(String sso) {
		
		User user = findBySSO(sso);
		if (user == null)
			return null;
		
		UserSession userSession = new UserSession();
		
		Donatore donatore = user.getDonatore();
		
		DonaStatus donaStatus = null;
		if (donatore != null) {
			
			donaStatus = donatore.getDonaStatus();
			
//			donaStatus.getStatus().forEach(donaStatusType -> donaStatusType.setSuspended(isSuspend(donaStatusType, donatore.getCodinternodonat())));
			Set<DonaStatusType> list = donaStatus.getStatus();
			for (DonaStatusType donaStatusType : list) {
				boolean isSusp = isSuspend(donaStatusType, donatore.getCodinternodonat());
				donaStatusType.setSuspended(isSusp);
			}
			
			Agenda agenda = getAgenda(donatore.getCodinternodonat());	//prenotazione attiva del donatore
			donaStatus.setAgenda(agenda);								//setta l'eventuale prenotazione attiva del donatore
		}
		
		userSession.setListTipoDonazPuntiPrel(getTipoDonazPuntiPrel(donaStatus != null ? donaStatus.getListTipoDona() : null));
		
		userSession.setDonaStatus(donaStatus);
		userSession.setListYearMonth(getYearMonths(donaStatus != null ? donaStatus.getListTipoDona() : null));
		
		return userSession;
	}
	
	/**
	 * Elenco di tutti i tipi donazione e punti di prelievo
	 */
	@Override
	public List<TipoDonaPuntoPrel> getTipoDonazPuntiPrel() {
		return getTipoDonazPuntiPrel(null);
	}
	
	/**
	 * Elenco di tutti i tipi donazione e punti di prelievo filtrati per tipo donazione
	 */
	@Override
	public List<TipoDonaPuntoPrel> getTipoDonazPuntiPrel(List<Integer> listTipoDona) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT distinct ");
		sb.append("m.pp, "); 				//0 punto prelievo id
		sb.append("pp.NOMEPUNTOPREL, "); 	//1 punto di prelievo descrizione
		sb.append("td.CODICE, "); 			//2	tipo donazione id
		sb.append("td.DESCRIZIONE, "); 		//3	tipo donazione descrizione
		sb.append("td.SIGLA ");				//4 sigla
		sb.append("FROM macchine m "); 
		sb.append("inner join TIPODONAZ td on td.CODICE = m.tipoDonaz_id "); 
		sb.append("inner join puntoPrelievo pp on pp.CODICEPUNTOPREL = m.pp "); 
		sb.append("where "); 
		
		
		if (listTipoDona != null && !listTipoDona.isEmpty()) {
			sb.append("td.CODICE in ( ");
			sb.append(StringUtils.join(listTipoDona.iterator(), ','));
			sb.append(") and ");
		}
		
		sb.append("exists (SELECT * FROM agenda where dataoraPren >= :dateFrom and idMacchina=m.ID) "); 
		sb.append("order by td.CODICE, pp.NOMEPUNTOPREL ");
		
		ArrayList<TipoDonaPuntoPrel> retValue = new ArrayList<TipoDonaPuntoPrel>();
		
		Query query = getSession().createSQLQuery(sb.toString());

		GregorianCalendar gc = new GregorianCalendar();
		TimeUtil.setMinHour(gc);
		query.setParameter("dateFrom", gc.getTime());
		
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		
		for (Object[] row: rows) {
			retValue.add(new TipoDonaPuntoPrel( (int)row[0], (String)row[1], (int)row[2], (String)row[3], (String)row[4]));
		}
		
		return retValue;
	}
	
	
	/**
	 * 
	 * @param listTipoDona
	 * @return elenco delle coppie possibili di anno e mese (0 based)
	 */
	@Override
	public List<YearMonth> getYearMonths(List<Integer> listTipoDona) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT distinct "); 
		sb.append("year(a.DATAORAPREN) y, month(a.DATAORAPREN) m "); 
		sb.append("FROM "); 
		sb.append("agenda a "); 
		sb.append("inner join macchine m on m.id = a.IDMACCHINA "); 
		sb.append("inner join TIPODONAZ td on td.CODICE = m.tipoDonaz_id "); 
		sb.append("inner join puntoPrelievo pp on pp.CODICEPUNTOPREL = m.pp "); 
		sb.append("where "); 
		sb.append("a.dataoraPren >= :dateFrom "); 
		
		if (listTipoDona != null && !listTipoDona.isEmpty()) {
			sb.append("and m.tipoDonaz_id in ( ");
			sb.append(StringUtils.join(listTipoDona.iterator(), ','));
			sb.append(") ");
		}
//		sb.append("order by year(a.DATAORAPREN), month(a.DATAORAPREN) ");
		sb.append("order by 1,2 ");
		
		ArrayList<YearMonth> retValue = new ArrayList<YearMonth>();
		
		Query query = getSession().createSQLQuery(sb.toString());

		query.setParameter("dateFrom", TimeUtil.getToday().getTime());
		
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		
		for (Object[] row: rows) {
			retValue.add(new YearMonth( (int)row[0], ((int)row[1])-1));
		}
		
		return retValue;

	}
	
//	/**
//	 * setta il tipo donazione solo se ce ne è uno non sopseso
//	 * inoltre setta anche il flag "esiste almeno un tipo donazione non sospeso"
//	 * @param status
//	 */
//	private void uniqueDonaStatusType(DonaStatus status) {
//		DonaType unique = null;
//		for (DonaStatusType donaStatusType : status.getStatus()) {
//			if (donaStatusType.isSuspended())
//				continue;
//			
//			status.setAtLeastOneTipodonaz(true);
//			if (unique != null) {
//				unique = null;
//				break;
//			}
//			unique = donaStatusType.getType();
//		}
//		status.setTipodonaz(unique != null ? getTipodonaz(unique.getIndex()) : null);
//	}
	
	/**
	 * 
	 * @param donaType
	 * @param codDon
	 * @return true se il donatore è sospeso x lo specifico tipo donazione
	 */
	private boolean isSuspend(DonaStatusType ds, String codDon) {
		
		DecimalFormat df2 = new DecimalFormat( "00" );
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT count(*) "); 
		sb.append("FROM LOGIDONEITA li "); 
		sb.append("where "); 
		sb.append("li.CODINTERNODONAT = :codDon "); 
		sb.append("and li.idoneita != 1 and li.chiusa = 0 "); 
		sb.append("and li.emoc_" + df2.format(ds.getType().getIndex()) + " = 1 ");
		
		Query query = getSession().createSQLQuery(sb.toString());
		query.setString("codDon", codDon);
		Integer result = (Integer) query.uniqueResult();
		return result.longValue() > 0;
	}
	
	/**
	 * 
	 * @param donatoreId
	 * @return la prenotazione (nel futuro oggi compreso) del donatore
	 */
	private Agenda getAgenda(String donatoreId) {

		Query query = getSession().createQuery("from Agenda where CODINTERNODONAT = :donatoreId and dataoraPren >= :today ");

		query.setParameter("donatoreId", donatoreId);
		query.setParameter("today", TimeUtil.getToday().getTime());	//data odierna con oraio 0

		List<?> list = query.list();

		return list != null && !list.isEmpty() ? (Agenda)list.get(0) : null;
	}

	@Override
	public void updateOldPsw(String sso, String newPsw) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("update app_user set password = :newPsw, last_Change_Psw = :du "); 
		sb.append("where sso_id = :sso "); 
		
		Query query = getSession().createSQLQuery(sb.toString());
		query.setParameter("newPsw", newPsw);
		query.setParameter("du", new Date());
		query.setParameter("sso", sso);
		query.executeUpdate();
//		int count = query.executeUpdate();
//		if (count != 1)
//			System.out.println("non aggiornato");
	}

}
