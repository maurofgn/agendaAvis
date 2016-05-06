package it.mesis.avis.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
//import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
//import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import it.mesis.avis.dao.AgendaDao;
import it.mesis.avis.model.Agenda;
import it.mesis.avis.model.AgendaKey;
import it.mesis.avis.model.Donatore;
import it.mesis.util.model.TipoDonaPuntoPrel;

import java.util.ArrayList;
import java.util.Date;
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
//		doNothing().when(dao).prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));
		Agenda age = agendas.get(0);
		when(dao.prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class))).thenReturn(age);
		Agenda ageReturn = agendaService.prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));
		System.out.println(ageReturn);
		verify(dao, atLeastOnce()).prenota(anyString(), any(Date.class), any(TipoDonaPuntoPrel.class));
	}
	
//	@Test
//	public void updateAgenda(){
//		Agenda emp = agendas.get(0);
//		when(dao.findById(any(AgendaKey.class))).thenReturn(emp);
//		agendaService.updateAgenda(emp);
//		verify(dao, atLeastOnce()).findById(any(AgendaKey.class));
//	}
//
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
		a1.setDonatore(new Donatore());
		a1.setId(new AgendaKey());
		a1.setNotapren("Nota 1");
		
		Agenda a2 = new Agenda();
		a2.setDonatore(new Donatore());
		a2.setId(new AgendaKey());
		a2.setNotapren("Nota 2");
		
		agendas.add(a1);
		agendas.add(a2);
		return agendas;
	}
	
}
