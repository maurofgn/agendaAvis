package it.mesis.avis.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
//import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atLeast;
//import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import it.mesis.avis.dao.AgendaDao;
import it.mesis.avis.model.Agenda;
import it.mesis.avis.model.AgendaKey;
import it.mesis.avis.model.Donatore;
import it.mesis.avis.model.Macchine;
import it.mesis.avis.model.Puntoprelievo;
import it.mesis.avis.model.Tipodonaz;
import it.mesis.util.model.TipoDonaPuntoPrel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AgendaServiceImplTest {

	@Mock
	AgendaDao dao;
	
	@InjectMocks
	AgendaServiceImpl agendaService;
	
	@Spy
	List<Agenda> agendas = new ArrayList<Agenda>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		agendas = getAgendaList();
	}
	
	
//	Agenda findById(AgendaKey id);
//	Tipodonaz getTipodonaz(int key);
//	Puntoprelievo getPuntoprelievo(int key);
//	MonthlyBookings getYearMonth(YearMonth yearMonth, TipoDonaPuntoPrel tipoDonazPuntoPrel, boolean updateable, AgendaKey agendaKey);
//	void disdetta(AgendaKey id);
//	Agenda prenota(String codinternodonat, Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel);
//	Donatore getDonatore(String codinternodonat);
//	List<Hour> freeHours(Date datePreno, TipoDonaPuntoPrel tipoDonaPuntoPrel);
//	List<Hour> freeHours(Date datePreno, int puntoprelId, int tipoDonaId);
//	List<Tipodonaz> getTipoDonazList();
//	List<Puntoprelievo> getPuntiPrelievoList();
//	List<ReportPreno> reportPreno(Date fromDate, Date toDate, Integer puntoPrelievo, Integer tipoDona);
	

	@Test
	public void findById(){
		Agenda age = agendas.get(0);
		when(dao.findById(any(AgendaKey.class))).thenReturn(age);
		Assert.assertEquals(agendaService.findById(age.getId()), age);
	}

	@Test
	public void prenota(){
		Agenda age = agendas.get(0);
		when(dao.prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class))).thenReturn(age);
		
		Agenda ageReturn = agendaService.prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));
		ageReturn = agendaService.prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));
		ageReturn = agendaService.prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));
		
		Assert.assertEquals(ageReturn, age);
		
		verify(dao, atLeastOnce()).prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));	//verifica che prenota sia stata chiamata almeno 1 volta
		verify(dao, atLeast(3) ).prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));	//verifica che prenota sia stata chiamata almeno 3 volte
	}
	
	@Test
	public void disdetta(){
		Agenda age = agendas.get(0);
		doNothing().when(dao).disdetta(any(AgendaKey.class));
		agendaService.disdetta(age.getId());
		verify(dao, atLeastOnce()).disdetta(any(AgendaKey.class));
	}
	
//	@Test
//	public void updateAgenda(){
//		Agenda emp = agendas.get(0);
//		when(dao.findById(any(AgendaKey.class))).thenReturn(emp);
//		agendaService.updateAgenda(emp);
//		verify(dao, atLeastOnce()).findById(any(AgendaKey.class));
//	}

//	@Test
//	public void deleteAgendaBySsn(){
//		doNothing().when(dao).deleteAgendaById(anyInt());
//		agendaService.deleteAgendaById(anyInt());
//		verify(dao, atLeastOnce()).deleteAgendaById(any(AgendaKey.class));
//	}
//	
//	@Test
//	public void findAllAgendas(){
//		when(dao.findAllAgendas()).thenReturn(agendas);
//		Assert.assertEquals(agendaService.findAllAgendas(), agendas);
//	}
//	
//	@Test
//	public void findAgendaBySsn(){
//		Agenda emp = agendas.get(0);
//		when(dao.findAgendaById(any(AgendaKey.class))).thenReturn(emp);
//		Assert.assertEquals(agendaService.findById(any(AgendaKey.class)), emp);
//	}
//
//	@Test
//	public void isAgendaSsnUnique(){
//		Agenda emp = agendas.get(0);
//		when(dao.findById(any(AgendaKey.class))).thenReturn(emp);
//		Assert.assertEquals(agendaService.isAgendaSsnUnique(emp.getId(), emp.getSsn()), true);
//	}
	
	
	public List<Agenda> getAgendaList() {
		Agenda a1 = new Agenda();
		a1.setDonatore(getDonatore(1));
		a1.setId(getAgendaKey(9));
		a1.setNotapren("Nota 1");
		
		Agenda a2 = new Agenda();
		a2.setDonatore(getDonatore(2));
		a2.setId(getAgendaKey(10));
		a2.setNotapren("Nota 2");
		
		agendas.add(a1);
		agendas.add(a2);
		return agendas;
	}
	
	private Donatore getDonatore(int id) {
		Donatore donatore = new Donatore();
		donatore.setCodinternodonat("donatore" + id);
		donatore.setCognomeenome("donatore " + id);
		return donatore;
	}
	
	private AgendaKey getAgendaKey(int hh) {
		AgendaKey k = new AgendaKey();
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.MILLISECOND, 0);
		gc.set(GregorianCalendar.SECOND, 0);
		gc.set(GregorianCalendar.MINUTE, 30);
		gc.set(GregorianCalendar.HOUR, hh);
		k.setDataorapren(new Timestamp(gc.getTimeInMillis()));
		k.setMacchina(getMacchine());
		return k;
	}
	
	private Macchine getMacchine() {
		Macchine macchine = new Macchine();
		macchine.setTipoDonazione(getTipodonaz());
		macchine.setPuntoprelievo(getPuntoprelievo());
		macchine.setNome("Macc. 1");
		macchine.setNotamacchina("manut. il giovedì");
		return macchine;
	}
	
	private Tipodonaz getTipodonaz() {
		Tipodonaz tipodonaz = new Tipodonaz();
		tipodonaz.setCodice(1); 
		tipodonaz.setDescrizione("Sangue Intero"); 
		tipodonaz.setRimborsormat(50.0); 
		tipodonaz.setRimborsornomat(12.0); 
		tipodonaz.setRimborsosp(18.8); 
		tipodonaz.setRpers1(0.0); 
		tipodonaz.setRpers2(1.0); 
		tipodonaz.setRpers3(0.0); 
		tipodonaz.setRpers4(0.0); 
		tipodonaz.setRpers5(0.0); 
		tipodonaz.setSigla("SI");
		return tipodonaz;
	}
	
	private Puntoprelievo getPuntoprelievo() {
		Puntoprelievo puntoprelievo = new Puntoprelievo();
		puntoprelievo.setCodicepuntoprel(12765); 
		puntoprelievo.setNomepuntoprel("Osp.Macerata"); 
		return puntoprelievo;
	}
	
}
