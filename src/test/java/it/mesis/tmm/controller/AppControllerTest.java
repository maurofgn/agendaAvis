package it.mesis.tmm.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import it.mesis.tmm.model.Anagrafica;
import it.mesis.tmm.service.AnagraficaService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppControllerTest {

	@Mock
	AnagraficaService service;
	
	@Mock
	MessageSource message;
	
	@InjectMocks
	AppController appController;
	
	@Spy
	List<Anagrafica> anagrafiche = new ArrayList<Anagrafica>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		anagrafiche = getEmployeeList();
	}
	
	@Test
	public void listEmployees(){
		when(service.findAllAnagraficas()).thenReturn(anagrafiche);
		Assert.assertEquals(appController.listParaAnagrafica(model), "allemployees");
		Assert.assertEquals(model.get("employees"), anagrafiche);
		verify(service, atLeastOnce()).findAllAnagraficas();
	}
	
	@Test
	public void newEmployee(){
		Assert.assertEquals(appController.newAnagrafica(model), "registration");
		Assert.assertNotNull(model.get("anagarfica"));
		Assert.assertFalse((Boolean)model.get("edit"));
//		Assert.assertEquals(((Anagrafica)model.get("id")).getId(), 0);
	}


	@Test
	public void saveEmployeeWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveAnagrafica(any(Anagrafica.class));
		Assert.assertEquals(appController.saveAnagrafica(anagrafiche.get(0), result, model), "registration");
	}

//	@Test
//	public void saveEmployeeWithValidationErrorNonUniqueSSN(){
//		when(result.hasErrors()).thenReturn(false);
//		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(false);
//		Assert.assertEquals(appController.saveAnagrafica(employees.get(0), result, model), "registration");
//	}

//	
//	@Test
//	public void saveEmployeeWithSuccess(){
//		when(result.hasErrors()).thenReturn(false);
//		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(true);
//		doNothing().when(service).saveEmployee(any(Anagrafica.class));
//		Assert.assertEquals(appController.saveEmployee(employees.get(0), result, model), "success");
//		Assert.assertEquals(model.get("success"), "Anagrafica Axel registered successfully");
//	}
//
//	@Test
//	public void editEmployee(){
//		Anagrafica emp = anagrafiche.get(0);
//		when(service.find EmployeeBySsn(anyString())).thenReturn(emp);
//		Assert.assertEquals(appController.editEmployee(anyString(), model), "registration");
//		Assert.assertNotNull(model.get("employee"));
//		Assert.assertTrue((Boolean)model.get("edit"));
//		Assert.assertEquals(((Anagrafica)model.get("employee")).getId(), 1);
//	}

	@Test
	public void updateEmployeeWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updateAnagrafica(any(Anagrafica.class));
		Assert.assertEquals(appController.updateAnagrafica(anagrafiche.get(0), result, model,1), "registration");
	}

//	@Test
//	public void updateEmployeeWithValidationErrorNonUniqueSSN(){
//		when(result.hasErrors()).thenReturn(false);
//		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(false);
//		Assert.assertEquals(appController.updateAnagrafica(anagrafiche.get(0), result, model,1), "registration");
//	}
//
//	@Test
//	public void updateEmployeeWithSuccess(){
//		when(result.hasErrors()).thenReturn(false);
//		when(service.isEmployeeSsnUnique(anyInt(), anyString())).thenReturn(true);
//		doNothing().when(service).updateEmployee(any(Anagrafica.class));
//		Assert.assertEquals(appController.updateEmployee(employees.get(0), result, model, ""), "success");
//		Assert.assertEquals(model.get("success"), "Anagrafica Axel updated successfully");
//	}
	
	
	@Test
	public void deleteEmployee(){
		doNothing().when(service).deleteAnagraficaById(anyInt() );
		Assert.assertEquals(appController.deleteAnagrafica(1), "redirect:/list");
	}

	public List<Anagrafica> getEmployeeList(){
		Anagrafica e1 = new Anagrafica();
		e1.setId(1);
		e1.setNome("Axel");
		e1.setDataconfermaanag(new Date());
		e1.setConsensoprivacy(true);
		e1.setCodfiscale("XXX111");
		
		Anagrafica e2 = new Anagrafica();
		e2.setId(2);
		e2.setNome("Jeremy");
		e2.setDataconfermaanag(new Date());
		e2.setConsensoprivacy(false);
		e2.setCodfiscale("XXX222");
		
		anagrafiche.add(e1);
		anagrafiche.add(e2);
		return anagrafiche;
	}
}
