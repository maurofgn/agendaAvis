package it.mesis.tmm.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import it.mesis.tmm.dao.AnagraficaDao;
import it.mesis.tmm.model.Anagrafica;

public class AnagraficaServiceImplTest {

	@Mock
	AnagraficaDao dao;
	
	@InjectMocks
	AnagraficaServiceImpl anagraficaService;
	
	@Spy
	List<Anagrafica> anagrafiche = new ArrayList<Anagrafica>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		anagrafiche = getAnagraficaList();
	}

	@Test
	public void findById(){
		Anagrafica emp = anagrafiche.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		Assert.assertEquals(anagraficaService.findById(emp.getId()),emp);
	}

	@Test
	public void saveAnagrafica(){
		doNothing().when(dao).saveAnagrafica(any(Anagrafica.class));
		anagraficaService.saveAnagrafica(any(Anagrafica.class));
		verify(dao, atLeastOnce()).saveAnagrafica(any(Anagrafica.class));
	}
	
	@Test
	public void updateAnagrafica(){
		Anagrafica emp = anagrafiche.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		anagraficaService.updateAnagrafica(emp);
		verify(dao, atLeastOnce()).findById(anyInt());
	}

	@Test
	public void deleteAnagraficaBySsn(){
		doNothing().when(dao).deleteAnagraficaById(anyInt());
		anagraficaService.deleteAnagraficaById(anyInt());
		verify(dao, atLeastOnce()).deleteAnagraficaById(anyInt());
	}
	
	@Test
	public void findAllAnagraficas(){
		when(dao.findAllAnagraficas()).thenReturn(anagrafiche);
		Assert.assertEquals(anagraficaService.findAllAnagraficas(), anagrafiche);
	}
	
//	@Test
//	public void findAnagraficaBySsn(){
//		Anagrafica emp = anagrafiche.get(0);
//		when(dao.findAnagraficaById(anyInt())).thenReturn(emp);
//		Assert.assertEquals(anagraficaService.findById(anyInt()), emp);
//	}

//	@Test
//	public void isAnagraficaSsnUnique(){
//		Anagrafica emp = anagrafiche.get(0);
//		when(dao.findById(anyInt())).thenReturn(emp);
//		Assert.assertEquals(anagraficaService.isAnagraficaSsnUnique(emp.getId(), emp.getSsn()), true);
//	}
	
	
	public List<Anagrafica> getAnagraficaList(){
		Anagrafica e1 = new Anagrafica();
		e1.setId(1);
		e1.setNome("Axel");
//		e1.setDatanascita(new LocalDate());
		e1.setCognome("pippo");
		
		Anagrafica e2 = new Anagrafica();
		e2.setId(2);
		e2.setNome("Jeremy");
//		e2.setDatanascita(new LocalDate());
		e2.setCognome("pippo2");
		
		anagrafiche.add(e1);
		anagrafiche.add(e2);
		return anagrafiche;
	}
	
}
