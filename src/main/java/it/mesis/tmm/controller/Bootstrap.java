package it.mesis.tmm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Bootstrap {
	
	private static final String PREFIX_PATH = "/boot/";

	@RequestMapping(value = {"/boot" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		return PREFIX_PATH + "test";
	}


}
