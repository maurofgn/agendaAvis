package it.mesis.tmm.controller;

import it.mesis.tmm.model.Anagrafica;
import it.mesis.tmm.service.AnagraficaService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	AnagraficaService service;
	
	@Autowired
	MessageSource messageSource;
	
	private static final String PREFIX_PATH = "anagrafica/";
	
	
	@RequestMapping(value = {"/test" }, method = RequestMethod.GET)
	public String test(ModelMap model) {
		
		return "test";
	}
	
	/*
	 * This method will list all existing anagraficas.
	 */
	@RequestMapping(value = { "/", "/listPara" }, method = RequestMethod.GET)
	public String listParaAnagrafica(ModelMap model) {
		
		Anagrafica anagrafica = new Anagrafica();
		model.addAttribute("anagrafica", anagrafica);

		return PREFIX_PATH + "listPara";
	}
	
	@RequestMapping(value = "/list"
//			, params = {"_search", "nd", "rows", "page", "sidx", "sort"}
			, method = RequestMethod.GET)
	public String listAnagraficasByExample( 
			Anagrafica anagrafica,
//			@RequestParam(value = "anagrafica", required=false) Anagrafica anagrafica,
//			@RequestParam(value = "_search") String search, 
//@RequestParam(value = "luogonascitaid", required=false) int luogonascitaid, 
//			@RequestParam(value = "rows") int rows, 
//			@RequestParam(value = "page") int page, 
//			@RequestParam(value = "sidx") int sidx, 
//			@RequestParam(value = "sort") Sort sort,
			 ModelMap model) {
//		String cc = model.get("comunicf_ID");
//		Comunicf cf = new Comunicf();
//		cf.setId(luogonascitaid);
//		anagrafica.setComunicf(cf);
//		anagrafica.setCom	//getComunicf().setId(luogonascitaid);
		List<Anagrafica> anagraficas = service.findAllAnagraficas(anagrafica);
		model.addAttribute("anagraficaList", anagraficas);
		return PREFIX_PATH + "list";
	}

//	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
//	public String listAnagraficas(ModelMap model) {
//
//		List<Anagrafica> anagraficas = service.findAllAnagraficas();
//		model.addAttribute("anagraficaList", anagraficas);
//		return PREFIX_PATH + "list";
//	}

	/*
	 * This method will provide the medium to add a new anagrafica.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newAnagrafica(ModelMap model) {
		Anagrafica anagrafica = new Anagrafica();
		model.addAttribute("anagrafica", anagrafica);
		model.addAttribute("edit", false);
		return PREFIX_PATH + "edit";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving anagrafica in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveAnagrafica(@Valid Anagrafica anagrafica, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return PREFIX_PATH + "edit";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Anagrafica].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
//		if(!service.isAnagraficaSsnUnique(anagrafica.getId(), anagrafica.getSsn())){
//			FieldError ssnError =new FieldError("anagrafica","ssn",messageSource.getMessage("non.unique.ssn", new String[]{anagrafica.getSsn()}, Locale.getDefault()));
//		    result.addError(ssnError);
//			return "registration";
//		}
		
//		Comunicf comunicf = anagrafica.getComunicfId() != 0 ? comunicfService.findById(anagrafica.getComunicfId()) : null;
//		anagrafica.setComunicf(comunicf);
		
		service.saveAnagrafica(anagrafica);

		model.addAttribute("success", "Anagrafica " + anagrafica.getNome() + " creata correttamente");
		return PREFIX_PATH + "success";
		
	}


	/*
	 * This method will provide the medium to update an existing anagrafica.
	 */
	@RequestMapping(value = { "/edit-{id}-anagrafica" }, method = RequestMethod.GET)
	public String editAnagrafica(@PathVariable int id, ModelMap model) {
		
//		Tipizza tipizza = service.getTipizza(id);
		Anagrafica anagrafica = service.findById(id);
		model.addAttribute("anagrafica", anagrafica);
		model.addAttribute("tipizzazione", service.getTipizza(id));
		model.addAttribute("edit", true);
		return PREFIX_PATH + "edit";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating anagrafica in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{id}-anagrafica" }, method = RequestMethod.POST)
	public String updateAnagrafica(@Valid Anagrafica anagrafica, BindingResult result, ModelMap model, @PathVariable int id) {

		if (result.hasErrors()) {
			return PREFIX_PATH + "edit";
		}

//		if(!service.isAnagraficaSsnUnique(anagrafica.getId(), anagrafica.getSsn())){
//			FieldError ssnError =new FieldError("anagrafica","ssn",messageSource.getMessage("non.unique.ssn", new String[]{anagrafica.getSsn()}, Locale.getDefault()));
//		    result.addError(ssnError);
//			return "registration";
//		}

//		Comunicf comunicf = anagrafica.getComunicfId() != 0 ? comunicfService.findById(anagrafica.getComunicfId()) : null;
//		anagrafica.setComunicf(comunicf);

		service.updateAnagrafica(anagrafica);

//		model.addAttribute("success", "Anagrafica " + anagrafica.getNome()	+ " updated successfully");
//		return "success";
		return "redirect:/listPara";
	}

	
	/*
	 * This method will delete an anagrafica by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{id}-anagrafica" }, method = RequestMethod.GET)
	public String deleteAnagrafica(@PathVariable int id) {
		service.deleteAnagraficaById(id);
		return "redirect:/listPara";
	}
	
}
