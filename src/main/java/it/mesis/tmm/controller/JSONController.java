package it.mesis.tmm.controller;

import java.util.List;

import it.mesis.tmm.service.ComunicfService;
import it.mesis.util.model.AutoCompleteData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/JSON")
public class JSONController {
	
	@Autowired
	ComunicfService serviceComuniCf;
	
	Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

	/**
	 * 
	 * @param term
	 * @param model
	 * @return json object con una lista di AutoCompleteData
	 */
	@RequestMapping(value = { "/city" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String listAnagraficasByExample(String term, ModelMap model) {
		List<AutoCompleteData> l = serviceComuniCf.json(term);
		String retValue = gson.toJson(l);
		
		return retValue;
	}
	
	
}

