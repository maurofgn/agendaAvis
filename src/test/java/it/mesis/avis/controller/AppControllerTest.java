package it.mesis.avis.controller;

import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppControllerTest {

//	@Mock
//	AnagraficaService service;
	
//	@Mock
//	MessageSource message;
	
	@InjectMocks
	AppController appController;
	
//	@Spy
//	List<Anagrafica> anagrafiche = new ArrayList<Anagrafica>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
//		anagrafiche = getAnagraficaList();
	}
	
	@Test
	public void listAnagraficas(){
//		when(service.findAllAnagraficas()).thenReturn(anagrafiche);
//		Assert.assertEquals(appController.listParaAnagrafica(model), "allemployees");
//		Assert.assertEquals(model.get("employees"), anagrafiche);
//		verify(service, atLeastOnce()).findAllAnagraficas();
	}
	
//	@Test
//	public void newAnagrafica(){
//		Assert.assertEquals(appController.newAnagrafica(model), "registration");
//		Assert.assertNotNull(model.get("anagarfica"));
//		Assert.assertFalse((Boolean)model.get("edit"));
////		Assert.assertEquals(((Anagrafica)model.get("id")).getId(), 0);
//	}


	@Test
	public void saveAnagraficaWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
//		doNothing().when(service).saveAnagrafica(any(Anagrafica.class));
//		Assert.assertEquals(appController.saveAnagrafica(anagrafiche.get(0), result, model), "registration");
	}

//	@Test
//	public void saveAnagraficaWithValidationErrorNonUniqueSSN(){
//		when(result.hasErrors()).thenReturn(false);
//		when(service.isAnagraficaSsnUnique(anyInt(), anyString())).thenReturn(false);
//		Assert.assertEquals(appController.saveAnagrafica(employees.get(0), result, model), "registration");
//	}

//	
//	@Test
//	public void saveAnagraficaWithSuccess(){
//		when(result.hasErrors()).thenReturn(false);
//		when(service.isAnagraficaSsnUnique(anyInt(), anyString())).thenReturn(true);
//		doNothing().when(service).saveAnagrafica(any(Anagrafica.class));
//		Assert.assertEquals(appController.saveAnagrafica(employees.get(0), result, model), "success");
//		Assert.assertEquals(model.get("success"), "Anagrafica Axel registered successfully");
//	}
//
//	@Test
//	public void editAnagrafica(){
//		Anagrafica emp = anagrafiche.get(0);
//		when(service.find AnagraficaBySsn(anyString())).thenReturn(emp);
//		Assert.assertEquals(appController.editAnagrafica(anyString(), model), "registration");
//		Assert.assertNotNull(model.get("employee"));
//		Assert.assertTrue((Boolean)model.get("edit"));
//		Assert.assertEquals(((Anagrafica)model.get("employee")).getId(), 1);
//	}

//	@Test
//	public void updateAnagraficaWithValidationError(){
//		when(result.hasErrors()).thenReturn(true);
//		doNothing().when(service).updateAnagrafica(any(Anagrafica.class));
//		Assert.assertEquals(appController.updateAnagrafica(anagrafiche.get(0), result, model,1), "registration");
//	}

//	@Test
//	public void updateAnagraficaWithValidationErrorNonUniqueSSN(){
//		when(result.hasErrors()).thenReturn(false);
//		when(service.isAnagraficaSsnUnique(anyInt(), anyString())).thenReturn(false);
//		Assert.assertEquals(appController.updateAnagrafica(anagrafiche.get(0), result, model,1), "registration");
//	}
//
//	@Test
//	public void updateAnagraficaWithSuccess(){
//		when(result.hasErrors()).thenReturn(false);
//		when(service.isAnagraficaSsnUnique(anyInt(), anyString())).thenReturn(true);
//		doNothing().when(service).updateAnagrafica(any(Anagrafica.class));
//		Assert.assertEquals(appController.updateAnagrafica(employees.get(0), result, model, ""), "success");
//		Assert.assertEquals(model.get("success"), "Anagrafica Axel updated successfully");
//	}
	
	
//	@Test
//	public void deleteAnagrafica(){
//		doNothing().when(service).deleteAnagraficaById(anyInt() );
//		Assert.assertEquals(appController.deleteAnagrafica(1), "redirect:/list");
//	}
//
//	public List<Anagrafica> getAnagraficaList(){
//		Anagrafica e1 = new Anagrafica();
//		e1.setId(1);
//		e1.setNome("Axel");
//		e1.setDataconfermaanag(new Date());
//		e1.setConsensoprivacy(true);
//		e1.setCodfiscale("XXX111");
//		
//		Anagrafica e2 = new Anagrafica();
//		e2.setId(2);
//		e2.setNome("Jeremy");
//		e2.setDataconfermaanag(new Date());
//		e2.setConsensoprivacy(false);
//		e2.setCodfiscale("XXX222");
//		
//		anagrafiche.add(e1);
//		anagrafiche.add(e2);
//		return anagrafiche;
//	}
}
