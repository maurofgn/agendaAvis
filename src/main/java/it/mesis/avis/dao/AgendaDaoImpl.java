package it.mesis.avis.dao;

import it.mesis.avis.model.Agenda;
import it.mesis.avis.model.AgendaKey;
import it.mesis.avis.model.Donatore;
import it.mesis.avis.model.Puntoprelievo;
import it.mesis.avis.model.Tipodonaz;
import it.mesis.util.model.Hour;
import it.mesis.util.model.MonthlyBookings;
import it.mesis.util.model.ReportPreno;
import it.mesis.util.model.TipoDonaPuntoPrel;
import it.mesis.util.model.YearMonth;
import it.mesis.utility.TimeUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

@Repository("agendaDao")
public class AgendaDaoImpl extends AbstractDao<AgendaKey, Agenda> implements AgendaDao {
	
	@Override
	public Agenda findById(AgendaKey id) {
		return getByKey(id);
	}
	
	/**
	 * lista dei punti di prelievo usati almeno una volta in una agenda
	 */
	@Override
	public List<Puntoprelievo> getPuntiPrelievoList() {

		StringBuffer sb = new StringBuffer();
		sb.append("from Puntoprelievo as pp where exists (");
		sb.append("from Agenda as a where a.id.macchina.puntoprelievo = pp.codicepuntoprel");
		sb.append(") ");
		sb.append("order by nomepuntoprel asc");
		
		Query query =  getSession().createQuery(sb.toString());
		@SuppressWarnings("unchecked")
		List<Puntoprelievo> list = (List<Puntoprelievo>)query.list();
		return list;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Tipodonaz> getTipoDonazList() {
//		
////		Query query =  getSession().createQuery("FROM Tipodonaz where codice = :key");
////		query.setParameter("key", key);
////		
////		List<?> list = query.list();
////		return list != null && !list.isEmpty() ? (Tipodonaz)list.get(0) : null;
//		
//		Criteria criteria =  getSession().createCriteria(Tipodonaz.class)
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
	public List<Tipodonaz> getTipoDonazList() {

		StringBuffer sb = new StringBuffer();
		sb.append("from Tipodonaz as td where exists (");
		sb.append("from Agenda as a where a.id.macchina.tipoDonazione = td.codice");
		sb.append(")");
		sb.append("order by descrizione asc");
		
		Query query =  getSession().createQuery(sb.toString());
		List<Tipodonaz> list = (List<Tipodonaz>)query.list();
		return list;
	}
	
	@Override
	public Tipodonaz getTipodonaz(int key) {
		Query query =  getSession().createQuery("FROM Tipodonaz where codice = :key");
		query.setParameter("key", key);
		
		List<?> list = query.list();
		return list != null && !list.isEmpty() ? (Tipodonaz)list.get(0) : null;
	}
	
	@Override
	public Puntoprelievo getPuntoprelievo(int key) {
		Query query =  getSession().createQuery("FROM Puntoprelievo where codicePuntoPrel = :key");
		query.setParameter("key", key);
		
		List<?> list = query.list();
		return list != null && !list.isEmpty() ? (Puntoprelievo)list.get(0) : null;
	}
	
	/**
	 * @param month is 1 based
	 */
	@Override
	public MonthlyBookings getYearMonth(YearMonth yearMonth, TipoDonaPuntoPrel tipoDonazPuntoPrel, boolean updateable, AgendaKey agendaKey) {
		
		GregorianCalendar gc = new GregorianCalendar(yearMonth.getYear(), yearMonth.getMonth(), 1);
		TimeUtil.setMinHour(gc);
		
		Date fromDate = gc.getTime();				//inizio mese
		
		gc.add(GregorianCalendar.MONTH, 1);
		gc.add(GregorianCalendar.DAY_OF_YEAR, -1);
		Date toDate = gc.getTime();					//fine mese
		
		StringBuffer sb = new StringBuffer();
        sb.append("select a ");
        sb.append("from Agenda as a ");
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
        
//        if (pp != 0)
//        	sb.append("and p.codicepuntoprel = :pp ");
//        if (tipoDona != 0)
//        	sb.append("and t.codice = :td ");
       
        Query query =  getSession().createQuery(sb.toString());
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        
        if (tipoDonazPuntoPrel != null) {
        	query.setParameter("pp", tipoDonazPuntoPrel.getPuntoprelId());
        	query.setParameter("td", tipoDonazPuntoPrel.getTipoDonaId());
        }
        
//        if (pp != 0)
//        	query.setParameter("pp", pp);
//        if (tipoDona != 0)
//        	query.setParameter("td", tipoDona);

        @SuppressWarnings("unchecked")
        List<Agenda>resultList = query.list();
        return new MonthlyBookings(yearMonth, tipoDonazPuntoPrel, resultList, updateable, agendaKey);
		
//		Criteria criteria = getSession().createCriteria(Agenda.class, "a") 
//	        .add(Restrictions.ge("a.DATAORAPREN", fromDate))
//	        .add(Restrictions.le("a.DATAORAPREN", toDate))
//	        .createCriteria("a.id.macchina" , "macc");
//
//		
//		criteria.add(Restrictions.eq("macc.pp", pp));
//		
//		criteria.add(Restrictions.eq("macc.TIPODONAZ_ID", tipoDona));
		
//		@SuppressWarnings("unchecked")
//		List<Agenda>resultList = (List<Agenda>)getSession().createCriteria(Agenda.class, "a") 
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
	public void disdetta(AgendaKey id) {
		Agenda agenda = findById(id);
		
		Donatore donatore = agenda.getDonatore();
		prenoDonatore(false, donatore, null, null);
		
		agenda.setDonatore(null);
//		agenda.setNotapren(null);
		persist(agenda);
	}
	
	/**
	 * 
	 * @param codinternodonat
	 * @param datePreno
	 * @param tipoDonaPuntoPrel
	 */
	@Override
	public Agenda prenota(String codinternodonat, Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel) {
		
		Donatore donatore = getDonatore(codinternodonat);
		if (donatore == null)
			return null;
		
		StringBuffer sb = new StringBuffer();
        sb.append("select a ");
        sb.append("from Agenda as a ");
        sb.append("  inner join a.id.macchina as m ");
        sb.append("  inner join m.puntoprelievo as p ");
        sb.append("  inner join m.tipoDonazione as t ");
        sb.append("where ");
        sb.append("a.donatore is null ");
        sb.append("and a.id.dataorapren = :datePreno ");
        sb.append("and p.codicepuntoprel = :pp ");
        sb.append("and t.codice = :td ");
        
        Query query =  getSession().createQuery(sb.toString());
        query.setParameter("datePreno", datePreno);
        query.setParameter("pp", tipoDonaPuntoPrel.getPuntoprelId());
        query.setParameter("td", tipoDonaPuntoPrel.getTipoDonaId());
        
		List<?> list = query.list();
		
		if (list == null || list.isEmpty())
			return null;
		
		Agenda agenda = (Agenda)list.get(0);
		
		prenoDonatore(true, donatore, datePreno, tipoDonaPuntoPrel);
		
		agenda.setDonatore(donatore);
		persist(agenda);
		
		return agenda;
	}
	
	private void prenoDonatore(boolean preno, Donatore donatore, Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel) {
		
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
	public Donatore getDonatore(String codinternodonat) {
		Donatore d = (Donatore) getSession().get(Donatore.class, codinternodonat);
		return d;
	}
	
	@Override
	public List<ReportPreno> reportPreno(Date fromDate, Date toDate, Integer puntoPrelievo, Integer tipoDona) {
		
		StringBuffer sb = new StringBuffer();
		
        sb.append("select a.id.dataorapren, a.notapren, m.nome, p.nomepuntoprel, t.descrizione,");
        sb.append("a.id.macchina.tipoDonazione.sigla, d.cognomeenome, d.luogonascita, d.provdinascita, ");
        sb.append("d.datadinascita, d.codicefiscale, d.cellulare, d.domtel ");
        
        sb.append("from Agenda as a ");
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
