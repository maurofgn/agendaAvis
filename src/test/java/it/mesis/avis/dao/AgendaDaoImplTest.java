package it.mesis.avis.dao;

import it.mesis.avis.model.Agenda;
import it.mesis.avis.model.AgendaKey;
import it.mesis.avis.model.Donatore;
import it.mesis.avis.model.Macchine;
import it.mesis.avis.model.Puntoprelievo;
import it.mesis.avis.model.Tipodonaz;
import it.mesis.util.model.Booking;
import it.mesis.util.model.Hour;
import it.mesis.util.model.MonthlyBookings;
import it.mesis.util.model.ReportPreno;
import it.mesis.util.model.YearMonth;
import it.mesis.utility.TimeUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

public class AgendaDaoImplTest extends EntityDaoImplTest {
	
	@Autowired
	AgendaDao agendaDao;
	
	private String codDonatoreInterno;
	private String[] donatori;

	private Integer macchinaId;

	private Macchine macchina;

	private Timestamp dataOraPreno;

	private AgendaKey agendaKey;

//	@Override
//	protected IDataSet getDataSet() throws Exception {
//		
//		InputStream is = this.getClass().getClassLoader().getResourceAsStream("agenda.xml");
//		IDataSet dataSet = new FlatXmlDataSet(is);
//		return dataSet;
//	}
	
	/* In case you need multiple datasets (mapping different tables) and you do prefer to keep them in separate XML's */
	@Override
	protected IDataSet getDataSet() throws Exception {
		
		IDataSet[] datasets = new IDataSet[] {
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("tipodonaz.xml")),
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("puntoprelievo.xml")),
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("macchine.xml")),
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("donatore.xml")),
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("agenda.xml")),
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("user_profile.xml")),
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("utenti.xml")),
			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("app_user.xml"))
		  };
		
		CompositeDataSet retValue = new CompositeDataSet(datasets);
		
		ITable donatore = retValue.getTable("donatore");
		codDonatoreInterno = (String)donatore.getValue(0, "CODINTERNODONAT");
		
		donatori = new String[donatore.getRowCount()];
		for (int row = 0; row < donatore.getRowCount(); row++) {
			donatori[row] = (String)donatore.getValue(row, "CODINTERNODONAT");
		}
		
		ITable agenda = retValue.getTable("agenda");
		macchinaId = Integer.valueOf((String)agenda.getValue(0, "IDMACCHINA"));
		dataOraPreno = Timestamp.valueOf((String)agenda.getValue(0, "DATAORAPREN"));
		
		ITable macchine = retValue.getTable("macchine");
		for (int row = 0; row < macchine.getRowCount(); row++) {
			Integer mId = Integer.valueOf((String)agenda.getValue(0, "IDMACCHINA"));
			if (mId != macchinaId)
				continue;
			
			macchina = new Macchine();
			macchina.setId(mId);
			macchina.setNome((String)macchine.getValue(row, "NOME"));
			
			Puntoprelievo pp = new Puntoprelievo();
			pp.setCodicepuntoprel(Integer.valueOf((String)macchine.getValue(row, "PP")));
			
			macchina.setPuntoprelievo(pp); 
			
			Tipodonaz td = new Tipodonaz();
			td.setCodice(Integer.valueOf((String)macchine.getValue(row, "TIPODONAZ_ID")));
			macchina.setTipoDonazione(td); 
			
			break;
		}
		
		agendaKey = new AgendaKey();
		agendaKey.setMacchina(macchina);
		agendaKey.setDataorapren(dataOraPreno);

		return retValue;
	}

	@Test
	public void findById() {
		
		AgendaKey ak = new AgendaKey();
		ak.setMacchina(macchina);
		ak.setDataorapren(dataOraPreno);
		
		Assert.assertNotNull(agendaDao.findById(ak));
		ak.setDataorapren(TimeUtil.MIN_DATE_TS);
		Assert.assertNull(agendaDao.findById(ak));
	}
	
	@Test
	public void freeHours() {
		List<Hour> hours = agendaDao.freeHours(dataOraPreno, macchina.getTipoDonaPuntoPrel().getPuntoprelId() , macchina.getTipoDonaPuntoPrel().getTipoDonaId());
		Assert.assertTrue(!hours.isEmpty());
	}

	@Test
	public void prenota() {
		Agenda agenda = agendaDao.prenota(codDonatoreInterno, dataOraPreno, macchina.getTipoDonaPuntoPrel());
		Assert.assertEquals(agenda.getId().getDataorapren(), dataOraPreno);
		Assert.assertEquals(agenda.getDonatore().getCodinternodonat(), codDonatoreInterno);
		
		Donatore donatore = agenda.getDonatore();
		Assert.assertEquals(donatore.getConvocato(), (short)1);
		Assert.assertEquals(donatore.getCodconvocazion(), (short)0);
		Assert.assertEquals(donatore.getTipodonazione(), macchina.getTipoDonaPuntoPrel().getTipoDonaId());
		Assert.assertEquals(donatore.getDataconvpren(), dataOraPreno);
		
		agendaDao.disdetta(agenda.getId());
		donatore = agendaDao.getDonatore(codDonatoreInterno);
		Assert.assertEquals(donatore.getConvocato(), (short)0);
		Assert.assertEquals(donatore.getCodconvocazion(), (short)0);
		Assert.assertEquals(donatore.getTipodonazione(), 0);
		Assert.assertNull(donatore.getDataconvpren());
		
		agenda = agendaDao.findById(agenda.getId());
		Assert.assertNull(agenda.getDonatore());
	}
	
	@Test
	public void report() {
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataOraPreno);
		TimeUtil.setMinHour(gc);
		Date fromDate = gc.getTime();
		
		TimeUtil.setMaxHour(gc);
		Date toDate = gc.getTime();
		
		List<ReportPreno> reportData = agendaDao.reportPreno(fromDate, toDate, 0, 0);	//prenotazioni preesistenti
		int totPreno = booking()									//crea delle prenotazioni 
				+ (reportData != null ? reportData.size() : 0)		//prenotazioi preesistenti
				;
		
		reportData = agendaDao.reportPreno(fromDate, toDate, 0, 0);
		
		Assert.assertEquals(totPreno, reportData.size());
	}
	
	/**
	 * crea tante prenotazioni per quante ore libere ci sono. Una per ogni ora libera, anche se la disponibilità è superiore ad uno
	 *  
	 * @return nr di prenotazioni eseguite
	 */
	private int booking() {
		List<Hour> hours = agendaDao.freeHours(dataOraPreno, macchina.getTipoDonaPuntoPrel().getPuntoprelId() , macchina.getTipoDonaPuntoPrel().getTipoDonaId());
		
		int totPreno = Math.min(donatori.length, hours.size());

		for (int i = 0; i < totPreno; i++) {
			Hour hour = hours.get(i);
			Agenda a = agendaDao.prenota(donatori[i], hour.getDate(), macchina.getTipoDonaPuntoPrel());
			Assert.assertNotNull(a);
//			System.out.println(a.getId() + " " + a.getDonatore().getCodinternodonat() + " " + a.getDonatore().getCognomeenome());
		}
		
		return totPreno;
	}
	
	@Test
	public void getTipodonaz() {
		Tipodonaz tipodonaz = agendaDao.getTipodonaz(macchina.getTipoDonazione().getCodice());
		Assert.assertEquals(tipodonaz.getCodice(), macchina.getTipoDonazione().getCodice());
	}
	
	@Test
	public void getPuntoprelievo() {
		Puntoprelievo puntoprelievo = agendaDao.getPuntoprelievo(macchina.getPuntoprelievo().getCodicepuntoprel());
		Assert.assertEquals(puntoprelievo.getCodicepuntoprel(), macchina.getPuntoprelievo().getCodicepuntoprel());
	}
	
//	List<Tipodonaz> getTipoDonazList();
//
//	List<Puntoprelievo> getPuntiPrelievoList();
//
//	List<ReportPreno> reportPreno(Date fromDate, Date toDate, Integer puntoPrelievo, Integer tipoDona);

	@Test
	public void monthlyBookings() {
		
		GregorianCalendar gc = new GregorianCalendar();
		int year = gc.get(GregorianCalendar.YEAR);
		int month = gc.get(GregorianCalendar.MONTH);

		YearMonth yearMonth = new YearMonth(year, month);
		
		MonthlyBookings monthlyBookings = agendaDao.getYearMonth(yearMonth, null, false, null);
		
		Booking[][] bookingMonth = monthlyBookings.getBookingsWeek();
		AgendaKey firstKeyNotNull = null;
		for (int i = 0; i < bookingMonth.length && firstKeyNotNull == null; i++) {
			for (int j = 0; j < bookingMonth[i].length && firstKeyNotNull == null; j++) {
				
				Booking booking = bookingMonth[i][j];
				if (booking == null || booking.getAgendaList() == null || booking.getAgendaList().size() == 0)
					continue;
				firstKeyNotNull = booking.getAgendaList().get(0).getId();
			}
		}
		Assert.assertNotNull(firstKeyNotNull);
	}
	

}
