package it.mesis.avis.dao;

import it.mesis.avis.bean.jpa.AgendaEntity;
import it.mesis.avis.bean.jpa.AgendaEntityKey;
import it.mesis.avis.bean.jpa.DonatoreEntity;
import it.mesis.avis.bean.jpa.MacchineEntity;
import it.mesis.avis.bean.jpa.PuntoprelievoEntity;
import it.mesis.avis.bean.jpa.TipodonazEntity;
import it.mesis.util.model.Booking;
import it.mesis.util.model.Hour;
import it.mesis.util.model.MonthlyBookings;
import it.mesis.util.model.ReportPreno;
import it.mesis.util.model.TipoDonaPuntoPrel;
import it.mesis.util.model.YearMonth;
import it.mesis.utility.TimeUtil;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AgendaDaoImplTest extends EntityDaoImplTest {
	
	@Autowired
	AgendaDao agendaDao;
	
	private String codDonatoreInterno;
	private String[] donatori;

	private Integer macchinaId;

	private MacchineEntity macchina;

	private Timestamp dataOraPreno;

	private AgendaEntityKey agendaKey;

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
		
//		java.io.InputStream is = this.getClass().getClassLoader().getResourceAsStream("app_user.xml");
//		IDataSet dataSet = new FlatXmlDataSet(is);
//		System.out.println(dataSet);
//		IDataSet retValue = dataSet;
		
		FlatXmlDataSetBuilder flatXmlDataSetBuilder = new FlatXmlDataSetBuilder();
		
		IDataSet[] datasets = new IDataSet[] {
				flatXmlDataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("tipodonaz.xml")),
				flatXmlDataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("puntoprelievo.xml")),
				flatXmlDataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("macchine.xml")),
				flatXmlDataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("donatore.xml")),
				flatXmlDataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("agenda.xml")),
				flatXmlDataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("user_profile.xml")),
				flatXmlDataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("utenti.xml")),
				flatXmlDataSetBuilder.build(this.getClass().getClassLoader().getResourceAsStream("app_user.xml"))
			  };

//		IDataSet[] datasets = new IDataSet[] {
//			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("tipodonaz.xml")),
//			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("puntoprelievo.xml")),
//			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("macchine.xml")),
//			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("donatore.xml")),
//			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("agenda.xml")),
//			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("user_profile.xml")),
//			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("utenti.xml")),
//			new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("app_user.xml"))
//		  };
		
		CompositeDataSet retValue = new CompositeDataSet(datasets);
		
		ITable donatores = retValue.getTable("donatore");
		codDonatoreInterno = (String)donatores.getValue(0, "CODINTERNODONAT");
		
		donatori = new String[donatores.getRowCount()];
		for (int row = 0; row < donatores.getRowCount(); row++) {
			donatori[row] = (String)donatores.getValue(row, "CODINTERNODONAT");
		}
		
		ITable agenda = retValue.getTable("agenda");
		macchinaId = Integer.valueOf((String)agenda.getValue(0, "IDMACCHINA"));
		dataOraPreno = Timestamp.valueOf((String)agenda.getValue(0, "DATAORAPREN"));
		
		ITable macchine = retValue.getTable("macchine");
		for (int row = 0; row < macchine.getRowCount(); row++) {
			Integer mId = Integer.valueOf((String)agenda.getValue(0, "IDMACCHINA"));
			if (mId != macchinaId)
				continue;
			
			macchina = new MacchineEntity();
			macchina.setId(mId);
			macchina.setNome((String)macchine.getValue(row, "NOME"));
			
			PuntoprelievoEntity pp = new PuntoprelievoEntity();
			pp.setCodicepuntoprel(Integer.valueOf((String)macchine.getValue(row, "PP")));
			
			macchina.setPuntoprelievo(pp); 
			
			TipodonazEntity td = new TipodonazEntity();
			td.setCodice(Integer.valueOf((String)macchine.getValue(row, "TIPODONAZ_ID")));
			macchina.setTipoDonazione(td); 
			
			break;
		}
		
		agendaKey = new AgendaEntityKey();
		agendaKey.setMacchina(macchina);
		agendaKey.setDataorapren(dataOraPreno);
		
		
//		ITable users = retValue.getTable("app_user");
//		for (int row = 0; row < users.getRowCount(); row++) {
//			try {
//				System.out.println(users.getValue(row, "CODINTERNODONAT"));
//			} catch (Exception e) {
//				continue;
////				e.printStackTrace();
//			}
//		}

		return retValue;
	}

	@Test
	public void findById() {
		
		AgendaEntityKey ak = new AgendaEntityKey();
		ak.setMacchina(macchina);
		ak.setDataorapren(dataOraPreno);
		
		Assert.assertNotNull(agendaDao.findById(ak));
		ak.setDataorapren(TimeUtil.MIN_DATE_TS);
		Assert.assertNull(agendaDao.findById(ak));
	}
	
	@Test
	public void freeHours() {
		
		List<Hour> hours = agendaDao.freeHours(dataOraPreno, macchina.getPuntoprelievo().getCodicepuntoprel(), macchina.getTipoDonazione().getCodice());
		Assert.assertTrue(!hours.isEmpty());
	}

	@Test
	public void prenotaConcurrent() {
		
		TipoDonaPuntoPrel tdpp = new TipoDonaPuntoPrel(macchina.getPuntoprelievo().getCodicepuntoprel(), macchina.getPuntoprelievo().getNomepuntoprel(), 
				macchina.getTipoDonazione().getCodice(), macchina.getTipoDonazione().getDescrizione(), macchina.getTipoDonazione().getSigla() );
		
		AgendaEntity agenda = agendaDao.prenota(codDonatoreInterno, dataOraPreno, tdpp);
		Assert.assertEquals(agenda.getId().getDataorapren(), dataOraPreno);
		Assert.assertEquals(agenda.getDonatore().getCodinternodonat(), codDonatoreInterno);
		agenda = agendaDao.prenota(codDonatoreInterno, dataOraPreno, tdpp);
		agenda = agendaDao.prenota(codDonatoreInterno, dataOraPreno, tdpp);
		agenda = agendaDao.prenota(codDonatoreInterno, dataOraPreno, tdpp);
	}
	
	@Test
	public void prenota() {
		
		TipoDonaPuntoPrel tdpp = new TipoDonaPuntoPrel(macchina.getPuntoprelievo().getCodicepuntoprel(), macchina.getPuntoprelievo().getNomepuntoprel(), 
				macchina.getTipoDonazione().getCodice(), macchina.getTipoDonazione().getDescrizione(), macchina.getTipoDonazione().getSigla() );

		AgendaEntity agenda = agendaDao.prenota(codDonatoreInterno, dataOraPreno, tdpp);
		Assert.assertEquals(agenda.getId().getDataorapren(), dataOraPreno);
		Assert.assertEquals(agenda.getDonatore().getCodinternodonat(), codDonatoreInterno);
		
		DonatoreEntity donatore = agenda.getDonatore();
		Assert.assertEquals(donatore.getConvocato(), (short)1);
		Assert.assertEquals(donatore.getCodconvocazion(), (short)0);
		Assert.assertEquals(donatore.getTipodonazione(), macchina.getTipoDonazione().getCodice());
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
		List<Hour> hours = agendaDao.freeHours(dataOraPreno, macchina.getPuntoprelievo().getCodicepuntoprel(), macchina.getTipoDonazione().getCodice() );
		
		int totPreno = Math.min(donatori.length, hours.size());
		TipoDonaPuntoPrel tdpp = getTipoDonaPuntoPrelFromMacchina(macchina);

		for (int i = 0; i < totPreno; i++) {
			Hour hour = hours.get(i);
			AgendaEntity a = agendaDao.prenota(donatori[i], hour.getDate(), tdpp);
			Assert.assertNotNull(a);
//			System.out.println(a.getId() + " " + a.getDonatore().getCodinternodonat() + " " + a.getDonatore().getCognomeenome());
		}
		
		return totPreno;
	}
	
	private TipoDonaPuntoPrel getTipoDonaPuntoPrelFromMacchina(
			MacchineEntity macchina) {
		return new TipoDonaPuntoPrel(macchina.getPuntoprelievo()
				.getCodicepuntoprel(), macchina.getPuntoprelievo()
				.getNomepuntoprel(), macchina.getTipoDonazione().getCodice(),
				macchina.getTipoDonazione().getDescrizione(), macchina
						.getTipoDonazione().getSigla());

	}
	
	@Test
	public void getTipodonaz() {
		TipodonazEntity tipodonaz = agendaDao.getTipodonaz(macchina.getTipoDonazione().getCodice());
		Assert.assertEquals(tipodonaz.getCodice(), macchina.getTipoDonazione().getCodice());
	}
	
	@Test
	public void getPuntoprelievo() {
		PuntoprelievoEntity puntoprelievo = agendaDao.getPuntoprelievo(macchina.getPuntoprelievo().getCodicepuntoprel());
		Assert.assertEquals(puntoprelievo.getCodicepuntoprel(), macchina.getPuntoprelievo().getCodicepuntoprel());
	}
	
//	List<Tipodonaz> getTipoDonazList();
//
//	List<Puntoprelievo> getPuntiPrelievoList();
//
//	List<ReportPreno> reportPreno(Date fromDate, Date toDate, Integer puntoPrelievo, Integer tipoDona);
	
	
	@Test
	public void getDonatore() {
		DonatoreEntity donatore = agendaDao.getDonatore(codDonatoreInterno);
		Assert.assertEquals(donatore.getCodinternodonat(), codDonatoreInterno);
	}
	
	@Test
	public void monthlyBookings() {
		
		GregorianCalendar gc = new GregorianCalendar();
		int year = gc.get(GregorianCalendar.YEAR);
		int month = gc.get(GregorianCalendar.MONTH);

		YearMonth yearMonth = new YearMonth(year, month);
		
		DonatoreEntity donatore = agendaDao.getDonatore(codDonatoreInterno);
		Assert.assertEquals(donatore.getCodinternodonat(), codDonatoreInterno);

		MonthlyBookings monthlyBookings = agendaDao.getYearMonth(yearMonth, null, donatore.getDonaStatus(30));
		
		Booking[][] bookingMonth = monthlyBookings.getBookingsWeek();
		AgendaEntityKey firstKeyNotNull = null;
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
