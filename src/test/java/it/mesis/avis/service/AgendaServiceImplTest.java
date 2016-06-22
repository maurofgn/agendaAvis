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
import it.mesis.avis.bean.jpa.AgendaEntity;
import it.mesis.avis.bean.jpa.AgendaEntityKey;
import it.mesis.avis.bean.jpa.DonatoreEntity;
import it.mesis.avis.bean.jpa.MacchineEntity;
import it.mesis.avis.bean.jpa.PuntoprelievoEntity;
import it.mesis.avis.bean.jpa.TipodonazEntity;
import it.mesis.avis.dao.AgendaDao;
import it.mesis.util.model.TipoDonaPuntoPrel;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;

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
	List<AgendaEntity> agendas = new ArrayList<AgendaEntity>();
	
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
		AgendaEntity age = agendas.get(0);
		when(dao.findById(any(AgendaEntityKey.class))).thenReturn(age);
		Assert.assertEquals(agendaService.findById(age.getId()), age);
	}

	@Test
	public void prenota(){
		AgendaEntity age = agendas.get(0);
		when(dao.prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class))).thenReturn(age);
		
		AgendaEntity ageReturn = agendaService.prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));
		ageReturn = agendaService.prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));
		ageReturn = agendaService.prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));
		
		Assert.assertEquals(ageReturn, age);
		
		verify(dao, atLeastOnce()).prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));	//verifica che prenota sia stata chiamata almeno 1 volta
		verify(dao, atLeast(3) ).prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));	//verifica che prenota sia stata chiamata almeno 3 volte
	}
	
	@Test
	public void disdetta(){
		AgendaEntity age = agendas.get(0);
		doNothing().when(dao).disdetta(any(AgendaEntityKey.class));
		agendaService.disdetta(age.getId());
		verify(dao, atLeastOnce()).disdetta(any(AgendaEntityKey.class));
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
	
	
	public List<AgendaEntity> getAgendaList() {
		AgendaEntity a1 = new AgendaEntity();
		a1.setDonatore(getDonatore(1));
		a1.setId(getAgendaKey(9));
		a1.setNotapren("Nota 1");
		
		AgendaEntity a2 = new AgendaEntity();
		a2.setDonatore(getDonatore(2));
		a2.setId(getAgendaKey(10));
		a2.setNotapren("Nota 2");
		
		agendas.add(a1);
		agendas.add(a2);
		return agendas;
	}
	
	private DonatoreEntity getDonatore(int id) {
		DonatoreEntity donatore = new DonatoreEntity();
		donatore.setCodinternodonat("donatore" + id);
		donatore.setCognomeenome("donatore " + id);
		return donatore;
	}
	
	private AgendaEntityKey getAgendaKey(int hh) {
		AgendaEntityKey k = new AgendaEntityKey();
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.MILLISECOND, 0);
		gc.set(GregorianCalendar.SECOND, 0);
		gc.set(GregorianCalendar.MINUTE, 30);
		gc.set(GregorianCalendar.HOUR, hh);
		k.setDataorapren(new Timestamp(gc.getTimeInMillis()));
		k.setMacchina(getMacchine());
		return k;
	}
	
	private MacchineEntity getMacchine() {
		MacchineEntity macchine = new MacchineEntity();
		macchine.setTipoDonazione(getTipodonaz());
		macchine.setPuntoprelievo(getPuntoprelievo());
		macchine.setNome("Macc. 1");
		macchine.setNotamacchina("manut. il giovedì");
		return macchine;
	}
	
	private TipodonazEntity getTipodonaz() {
		TipodonazEntity tipodonaz = new TipodonazEntity();
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
	
	private PuntoprelievoEntity getPuntoprelievo() {
		PuntoprelievoEntity puntoprelievo = new PuntoprelievoEntity();
		puntoprelievo.setCodicepuntoprel(12765); 
		puntoprelievo.setNomepuntoprel("Osp.Macerata"); 
		return puntoprelievo;
	}
	
}
