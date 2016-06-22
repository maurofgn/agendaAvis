package it.mesis.avis.dao;

import it.mesis.avis.bean.jpa.AgendaEntity;
import it.mesis.avis.bean.jpa.AgendaEntityKey;
import it.mesis.avis.bean.jpa.DonatoreEntity;
import it.mesis.avis.bean.jpa.PuntoprelievoEntity;
import it.mesis.avis.bean.jpa.TipodonazEntity;
import it.mesis.util.model.DonaStatus;
import it.mesis.util.model.Hour;
import it.mesis.util.model.MonthlyBookings;
import it.mesis.util.model.ReportPreno;
import it.mesis.util.model.TipoDonaPuntoPrel;
import it.mesis.util.model.YearMonth;
import it.mesis.utility.TimeUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

@Repository("agendaDao")
public class AgendaDaoImpl extends AbstractDao<AgendaEntityKey, AgendaEntity> implements AgendaDao {
	
	@Override
	public AgendaEntity findById(AgendaEntityKey id) {
		return getByKey(id);
	}
	
	/**
	 * lista dei punti di prelievo usati almeno una volta in una agenda
	 */
	@Override
	public List<PuntoprelievoEntity> getPuntiPrelievoList() {

		StringBuffer sb = new StringBuffer();
		sb.append("from PuntoprelievoEntity as pp where exists (");
		sb.append("from AgendaEntity as a where a.id.macchina.puntoprelievo = pp.codicepuntoprel");
		sb.append(") ");
		sb.append("order by pp.nomepuntoprel asc");
		
		Query query =  getSession().createQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<PuntoprelievoEntity> list = (List<PuntoprelievoEntity>)query.list();
		return list;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Tipodonaz> getTipoDonazList() {
//		
////		Query query =  getSession().createQuery("FROM TipodonazEntity where codice = :key");
////		query.setParameter("key", key);
////		
////		List<?> list = query.list();
////		return list != null && !list.isEmpty() ? (Tipodonaz)list.get(0) : null;
//		
//		Criteria criteria =  getSession().createCriteria(TipodonazEntity.class)
//			.addOrder(Order.asc("descrizione"));
//		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
//		List<Tipodonaz> tipodonazs = (List<Tipodonaz>)criteria.list();
//		return tipodonazs;
//	}
	

	/**
	 * lista dei tipi donazioni usato almeno una volta in una agenda
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TipodonazEntity> getTipoDonazList() {

		StringBuffer sb = new StringBuffer();
		sb.append("from TipodonazEntity as td where exists (");
		sb.append("from AgendaEntity as a where a.id.macchina.tipoDonazione = td.codice");
		sb.append(")");
		sb.append("order by descrizione asc");
		
		Query query =  getSession().createQuery(sb.toString());
		List<TipodonazEntity> list = (List<TipodonazEntity>)query.list();
		return list;
	}
	
	/**
	 * 
	 */
	public MonthlyBookings getYearMonth(YearMonth yearMonth, TipoDonaPuntoPrel tipoDonazPuntoPrel, DonaStatus donaStatus) {
		
		GregorianCalendar gc = new GregorianCalendar(yearMonth.getYear(), yearMonth.getMonth(), 1);
		TimeUtil.setMinHour(gc);
		
		Date fromDate = gc.getTime();				//inizio mese
		
		gc.add(GregorianCalendar.MONTH, 1);
		gc.add(GregorianCalendar.DAY_OF_YEAR, -1);
		Date toDate = gc.getTime();					//fine mese
		
		StringBuffer sb = new StringBuffer();
        sb.append("select a ");
        sb.append("from AgendaEntity as a ");
        sb.append("  inner join a.id.macchina as m ");
        sb.append("  inner join m.puntoprelievo as p ");
        sb.append("  inner join m.tipoDonazione as t ");
        sb.append("where ");
        sb.append("a.id.dataorapren >= :fromDate ");
        sb.append("and a.id.dataorapren <= :toDate ");

        if (tipoDonazPuntoPrel != null) {
        	sb.append("and p.codicepuntoprel = :pp ");
        	sb.append("and t.codice = :td ");
        }
        
        Query query = getSession().createQuery(sb.toString());
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        
        if (tipoDonazPuntoPrel != null) {
        	query.setParameter("pp", tipoDonazPuntoPrel.getPuntoprelId());
        	query.setParameter("td", tipoDonazPuntoPrel.getTipoDonaId());
        }
        
        @SuppressWarnings("unchecked")
        List<AgendaEntity>resultList = query.list();
        return new MonthlyBookings(yearMonth, tipoDonazPuntoPrel, resultList, donaStatus);
		
//		Criteria criteria = getSession().createCriteria(AgendaEntity.class, "a") 
//	        .add(Restrictions.ge("a.DATAORAPREN", fromDate))
//	        .add(Restrictions.le("a.DATAORAPREN", toDate))
//	        .createCriteria("a.id.macchina" , "macc");
//
//		
//		criteria.add(Restrictions.eq("macc.pp", pp));
//		
//		criteria.add(Restrictions.eq("macc.TIPODONAZ_ID", tipoDona));
		
//		@SuppressWarnings("unchecked")
//		List<Agenda>resultList = (List<Agenda>)getSession().createCriteria(AgendaEntity.class, "a") 
//                .add(Restrictions.ge("a.id.DATAORAPREN", fromDate))
//                .add(Restrictions.le("a.id.DATAORAPREN", toDate))
//                .createCriteria("a.id.macchina" , "macc")
//                .add(Restrictions.eq("macc.pp", pp))
//                .add(Restrictions.eq("macc.TIPODONAZ_ID", tipoDona))
////                .setProjection( Projections.projectionList()
////                        .add( Projections.property("aliasOfTableA.columnA") )
////                        .add( Projections.property("aliasOfTableB.columnB"))
////                        .add( Projections.property("aliasOfTableC.columnC") )
////                )
//                .list();
		
//		SELECT 
//		a.*
//		FROM agenda a
//		inner join macchine m on m.id = a.idMacchina
//		where 
//		a.DATAORAPREN >= getDate()
//		and a.DATAORAPREN <= getDate()+15
//		and m.PP = 12765
//		and m.TIPODONAZ_ID = 1
//		order by a.dataOraPren 
//		
//		ArrayList<TipoDonaPuntoPrel> retValue = new ArrayList<TipoDonaPuntoPrel>();
//		
//		Query query = getSession().createSQLQuery(sb.toString());
//
//		GregorianCalendar gc = new GregorianCalendar();
//		gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
//		query.setParameter("dateFrom", gc.getTime());
//		
//		@SuppressWarnings("unchecked")
//		List<Object[]> rows = query.list();
//		
//		for (Object[] row: rows) {
//			retValue.add(new TipoDonaPuntoPrel( (int)row[0], (String)row[1], (int)row[2], (String)row[3]));
//		}
//		
//		return new MonthlyBookings(new YearMonth(year, month), pp, tipoDona, resultList);
	}
	
	/**
	 * 
	 * @param datePreno
	 * @param tipoDonaPuntoPrel
	 * @return lista di ore con il totale di macchine libere (almeno una)
	 */
	@Override
	public List<Hour> freeHours(Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel) {
		return freeHours(datePreno, tipoDonaPuntoPrel.getPuntoprelId() , tipoDonaPuntoPrel.getTipoDonaId()); 
	}
		
	/**
	 * 
	 * @param datePreno
	 * @param tipoDonaPuntoPrel
	 * @return lista di ore con il totale di macchine libere (almeno una)
	 */
	@Override
	public List<Hour> freeHours(Date datePreno, int puntoprelId , int tipoDonaId) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(datePreno);

		TimeUtil.setMinHour(gc);
		
		Date fromDate = gc.getTime();				//inizio giornata
		
		gc.add(GregorianCalendar.DAY_OF_YEAR, 1);
		gc.add(GregorianCalendar.SECOND, -1);
		Date toDate = gc.getTime();					//fine giornata

		StringBuffer sb = new StringBuffer();
		
		sb.append("select "); 
		sb.append("a.dataorapren, sum(case when a.codInternoDonat is null then 1 else 0 end) free, count(*) tot "); 
		sb.append("from Agenda as a "); 
		sb.append("inner join macchine as m on  m.ID = a.idMacchina "); 
		sb.append("where "); 
		sb.append("a.dataorapren >= :fromDate "); 
		sb.append("and a.dataorapren <= :toDate "); 
		sb.append("and m.tipoDonaz_ID = :td "); 
		sb.append("and m.PP = :pp ");
//		sb.append("and a.codInternoDonat is null "); 
		sb.append("group by a.dataorapren "); 
		sb.append("order by a.dataorapren ");
		
        SQLQuery query = getSession().createSQLQuery(sb.toString());
        //per rendere compatibile il cambio del db è necessario forzare il tipo restituito.
        //Senza questa forzatura, il tipo è quello restituito dal DB ed ogni DB ha un suo tipo restituito
        //per esempio sqlserver resituisce Integer, mentre h2 restutisce un BigInteger e mySQL restituisce BigDecimal
        query.addScalar("dataorapren", StandardBasicTypes.TIMESTAMP);
        query.addScalar("free", StandardBasicTypes.INTEGER);
        query.addScalar("tot", StandardBasicTypes.INTEGER);
        
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
       	query.setParameter("pp", puntoprelId);
       	query.setParameter("td", tipoDonaId);
        
		@SuppressWarnings("unchecked")
		List<Object[]> rows = query.list();
		
		ArrayList<Hour> retValue = new ArrayList<Hour>();
		
		for (Object[] row: rows) {
			retValue.add(new Hour((Date)row[0], ((Integer)row[1]).intValue(), ((Integer)row[2]).intValue()));
		}
		
		return retValue;
	}

	/**
	 * togli il codice donatore dall'agenda
	 * 
	 * @param id
	 */
	@Override
	public void disdetta(AgendaEntityKey id) {
		AgendaEntity agenda = findById(id);
		
		if (agenda != null) {
			
			DonatoreEntity donatore = agenda.getDonatore();
			prenoDonatore(false, donatore, null, null);
			
			agenda.setDonatore(null);
	//		agenda.setNotapren(null);
			persist(agenda);
		}
	}
	
	@Override
	public Boolean hasPrenoActive(DonatoreEntity donatore) {
		
		Query query = getSession().createQuery("select count(*) from AgendaEntity a where a.donatore = :dona and a.id.dataorapren >= :todayMorning");
		query.setParameter("dona", donatore);
		query.setParameter("todayMorning", getTodayMorning());
		return (Long) query.uniqueResult() > 0;
	}
	
	@Override
	public AgendaEntity getPrenoActive(DonatoreEntity donatore) {
		
		Query query = getSession().createQuery("select a from AgendaEntity a where a.donatore = :dona and a.id.dataorapren >= :todayMorning");
		query.setParameter("dona", donatore);
		query.setParameter("todayMorning", getTodayMorning());
		
		return (AgendaEntity)query.uniqueResult();	//potrebbe tornare errore se dovesse esserci più di un risultato, teoricamente non possibile
//		return (Agenda)query.list().get(0);
	}
	
	private Date getTodayMorning() {
		return DateUtils.truncate(new Date(), Calendar.DATE);
	}

	/**
	 * 
	 * @param codinternodonat
	 * @param datePreno
	 * @param tipoDonaPuntoPrel
	 */
	@Override
	public AgendaEntity prenota(String codinternodonat, Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel) {
		
		DonatoreEntity donatore = getDonatore(codinternodonat);
		if (donatore == null)
			return null;
		
		StringBuffer sb = new StringBuffer();
        sb.append("select a ");
        sb.append("from AgendaEntity as a ");
        sb.append("  inner join a.id.macchina as m ");
        sb.append("  inner join m.puntoprelievo as p ");
        sb.append("  inner join m.tipoDonazione as t ");
        sb.append("where ");
        sb.append("a.donatore is null ");
        sb.append("and a.id.dataorapren = :datePreno ");
        sb.append("and p.codicepuntoprel = :pp ");
        sb.append("and t.codice = :td ");
        sb.append("and not exists (select 1 from AgendaEntity a2 where a2.donatore = :dona and a2.id.dataorapren >= :todayMorning)");
        
		Date today = new Date();
		Date todayMorning = DateUtils.truncate(today, Calendar.DATE);

        Query query = getSession().createQuery(sb.toString());
        query.setParameter("datePreno", datePreno);
        query.setParameter("pp", tipoDonaPuntoPrel.getPuntoprelId());
        query.setParameter("td", tipoDonaPuntoPrel.getTipoDonaId());
        query.setParameter("dona", donatore);
        query.setParameter("todayMorning", todayMorning);

		List<?> list = query.list();
		
		if (list == null || list.isEmpty())
			return null;
		
        sb = new StringBuffer();
        sb.append("update AgendaEntity as a "); 
        sb.append("set a.donatore = :dona "); 
        sb.append("where a.id = :id "); 
        sb.append("and a.donatore is null ");
        Query upd = getSession().createQuery(sb.toString());

		AgendaEntity agenda = null;
		for (Object object : list) {
			agenda = (AgendaEntity)object;
			upd.setParameter("dona", donatore);
			upd.setParameter("id", agenda.getId());
			int result = upd.executeUpdate();
			if (result == 0)
				agenda = null;
			else
				break;
		}
		
		if (agenda != null) {
			prenoDonatore(true, donatore, datePreno, tipoDonaPuntoPrel);
			agenda.setDonatore(donatore);
		}
		
		return agenda;
	}

	/**
	 * aggiorna i dati sull'anagrafica del donatore
	 * @param preno
	 * @param donatore
	 * @param datePreno
	 * @param tipoDonaPuntoPrel
	 */
	private void prenoDonatore(boolean preno, DonatoreEntity donatore, Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel) {
		
		if (donatore == null)
			return;
		
		if (preno) {
			donatore.setConvocato((short) 1);
			donatore.setCodconvocazion(0);
			donatore.setTipodonazione(tipoDonaPuntoPrel.getTipoDonaId());
			donatore.setDataconvpren(new Timestamp(datePreno.getTime()));
		} else {
			donatore.setConvocato((short) 0);
			donatore.setCodconvocazion(0);
			donatore.setTipodonazione(0);
			donatore.setDataconvpren(null);
		}
		getSession().persist(donatore);
	}
	
	@Override
	public DonatoreEntity getDonatore(String codinternodonat) {
		return (DonatoreEntity) getSession().get(DonatoreEntity.class, codinternodonat);
	}
	
	@Override
	public TipodonazEntity getTipodonaz(int key) {
		return (TipodonazEntity) getSession().get(TipodonazEntity.class, key);
//		Query query =  getSession().createQuery("FROM TipodonazEntity where codice = :key");
//		query.setParameter("key", key);
//		
//		List<?> list = query.list();
//		return list != null && !list.isEmpty() ? (TipodonazEntity)list.get(0) : null;
	}
	
	@Override
	public PuntoprelievoEntity getPuntoprelievo(int key) {
		return (PuntoprelievoEntity) getSession().get(PuntoprelievoEntity.class, key);
//		Query query =  getSession().createQuery("FROM PuntoprelievoEntity where codicePuntoPrel = :key");
//		query.setParameter("key", key);
//		
//		List<?> list = query.list();
//		return list != null && !list.isEmpty() ? (PuntoprelievoEntity)list.get(0) : null;
	}
	
	@Override
	public List<ReportPreno> reportPreno(Date fromDate, Date toDate, Integer puntoPrelievo, Integer tipoDona) {
		
		StringBuffer sb = new StringBuffer();
		
        sb.append("select a.id.dataorapren, a.notapren, m.nome, p.nomepuntoprel, t.descrizione,");
        sb.append("a.id.macchina.tipoDonazione.sigla, d.cognomeenome, d.luogonascita, d.provdinascita, ");
        sb.append("d.datadinascita, d.codicefiscale, d.cellulare, d.domtel ");
        
        sb.append("from AgendaEntity as a ");
        sb.append("  inner join a.donatore as d ");
        sb.append("  inner join a.id.macchina as m ");
        sb.append("  inner join m.puntoprelievo as p ");
        sb.append("  inner join m.tipoDonazione as t ");
        sb.append("where ");
        sb.append("a.donatore is not null ");
        sb.append("and a.id.dataorapren >= :fromDate ");
        sb.append("and a.id.dataorapren <= :toDate ");
        
        if (puntoPrelievo != 0)
        	sb.append("and p.codicepuntoprel = :pp ");
        if (tipoDona != 0)
        	sb.append("and t.codice = :td ");
        
		sb.append("order by "); 
		sb.append("t.descrizione, p.nomepuntoprel, a.id.dataorapren, a.id.macchina.id ");

        Query query = getSession().createQuery(sb.toString());
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        
        if (puntoPrelievo != 0)
        	query.setParameter("pp", puntoPrelievo);
        
        if (tipoDona != 0)
        	query.setParameter("td", tipoDona);
        
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.list();
		
		if (list == null || list.isEmpty())
			return null;
		
		ArrayList<ReportPreno> retValue = new ArrayList<ReportPreno>();
		
		for (Object[] row: list) {
			
			retValue.add(new ReportPreno(
				(Timestamp)row[0],
				(String)row[1], 
				(String)row[2],
				(String)row[3],
				(String)row[4],
				(String)row[5],
				(String)row[6],
				(String)row[7],
				(String)row[8],
				(Date)row[9],
				(String)row[10],
				(String)row[11],
				(String)row[12])
					);
		}
		return retValue;
	}

}
