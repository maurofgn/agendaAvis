package it.mesis.avis.controller;

import it.mesis.avis.exception.StatusException;
import it.mesis.avis.model.Audit;
import it.mesis.avis.model.User;
import it.mesis.avis.model.UserProfile;
import it.mesis.avis.service.AuditService;
import it.mesis.avis.service.UserProfileService;
import it.mesis.avis.service.UserService;
import it.mesis.util.model.AuditDto;
import it.mesis.util.model.AuditMapper;
import it.mesis.util.model.GenericResponse;
import it.mesis.util.model.JqgridResponse;
import it.mesis.utility.TimeUtil;
import it.mesis.utility.Utility;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	UserService userService;
	
    @Autowired
    private Environment environment;

	@Autowired
	MessageSource messageSource;
	
    @Autowired
    private JavaMailSender mailSender;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	AuditService auditService;

	private static final String PREFIX_PATH = "/account/";

	@Secured ({"ROLE_ADMIN", "ROLE_AVIS"})
	@RequestMapping(value = "/audit", method = RequestMethod.GET)
	public String audit() {
//		model.addAttribute("user", getPrincipal());
		return PREFIX_PATH + "audit";
	}

	/**
	 * non usata, ma lascita per eventuale uso futuro in cui è viene gestita la paginazione dei dati in output
	 * @param page
	 * @param rows
	 * @return
	 */
	@Secured ({"ROLE_ADMIN", "ROLE_AVIS"})
	@RequestMapping(value="/auditRecsPages", produces="application/json")
	public @ResponseBody JqgridResponse<AuditDto> recordsPages(
    		@RequestParam(value="page", required=false) Integer page,	//pagina richiesta
    		@RequestParam(value="rows", required=false) Integer rows	//nr righe per ogni singola pagina
    		) {

		List<Audit> audits = auditService.findAllAudits();
		List<AuditDto> dtos = AuditMapper.map(audits);
		
		JqgridResponse<AuditDto> response = new JqgridResponse<AuditDto>();
		response.setRows(dtos);
		
		response.setRecords(Long.valueOf(audits.size()).toString());
		response.setTotal(Integer.valueOf(1).toString());	//totale pagine
		response.setPage(Integer.valueOf(1).toString());	//numero pagina (1 based)

		return response;
	}
	
	@Secured ({"ROLE_ADMIN", "ROLE_AVIS"})
	@RequestMapping(value="/auditRecs", produces="application/json")
	public @ResponseBody JqgridResponse<AuditDto> records(
    		@RequestParam(value="dateFrom", required=false) @DateTimeFormat(pattern="dd/MM/yyyy") Date dateFrom,
    		@RequestParam(value="dateTo",   required=false) @DateTimeFormat(pattern="dd/MM/yyyy") Date dateTo,
    		@RequestParam(value="user",     required=false) String user,
    		@RequestParam(value="state",    required=false) String state
    		) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
		
		if (dateFrom == null) {
			if (dateTo == null)
				dateFrom = gc.getTime();
			else if (dateTo.after(gc.getTime()))
				dateFrom = gc.getTime();
			else
				dateFrom = dateTo;
		}
		
		if (dateTo == null)
			dateTo = dateFrom;
		
		List<Audit> audits = auditService.findAudits(TimeUtil.getMinHour(dateFrom), TimeUtil.getMaxHour(dateTo), user, state);
		List<AuditDto> dtos = AuditMapper.map(audits);
		
		JqgridResponse<AuditDto> response = new JqgridResponse<AuditDto>();
		response.setRows(dtos);
		
//		response.setRecords(Long.valueOf(audits.getTotalElements()).toString());
//		response.setTotal(Integer.valueOf(audits.getTotalPages()).toString());
//		response.setPage(Integer.valueOf(audits.getNumber()+1).toString());
		
		response.setRecords(Long.valueOf(audits.size()).toString());
		response.setTotal(Integer.valueOf(1).toString());	//totale pagine
		response.setPage(Integer.valueOf(1).toString());	//numero pagina (1 based)

		return response;
	}

	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return PREFIX_PATH + "accessDenied";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePassword(ModelMap model) {
		
		model.addAttribute("pswMinLen", environment.getProperty("psw.min.length"));
		model.addAttribute("pswRegExp", environment.getProperty("psw.reg.exp").replace("\\", "\\\\"));
		
		return PREFIX_PATH + "changePassword";
	}
	
	@RequestMapping(value = "/changePassword2", method = RequestMethod.GET, produces="application/json")
	public 	@ResponseBody Map<String, String> changePasswordUpd(
			@RequestParam("password") String password,
			@RequestParam("oldpassword") String oldpassword,
			final HttpServletRequest request) {
		
		Map<String, String> retValue = new HashMap<String, String>();

		if (password.compareTo(oldpassword) == 0) {
			retValue.put("result", "fail");
			retValue.put("msg", messageSource.getMessage("message.newPsw.equal", null, request.getLocale()));
			return retValue;
		}
		
		if (!correctnessFormalPsw(password, request.getLocale(), retValue))
			return retValue;
		
		try {
			
			String userName = getPrincipal();
			User user = userService.findBySso(userName);
			
			if (passwordEncoder.matches(oldpassword, user.getPassword())) {
				
				auditService.audit(userName, "Cambio password");

				userService.updateOldPsw(userName, passwordEncoder.encode(password));
				retValue.put("result", "ok");
				retValue.put("msg", messageSource.getMessage("message.password.changed", null, request.getLocale()));
			}
			else {
				retValue.put("result", "fail");
				retValue.put("msg", messageSource.getMessage("message.oldPassword.invalid", null, request.getLocale()));
			}
		} catch (Exception e) {
			retValue.put("result", "fail");
			retValue.put("msg", e.getMessage());
		}

		return retValue;
	}
	
	/**
	 * check password correctness formal
	 * @param password
	 * @param locale
	 * @param map
	 * @return result
	 */
	private boolean correctnessFormalPsw(String password, Locale locale, Map<String, String> map) {
		
//		Integer pswMinLen = Utility.getInteger(environment.getProperty("psw.min.length"));
//		if (pswMinLen != null && pswMinLen > 0 && password.length() < pswMinLen) {
//			map.put("result", "fail");
//			map.put("msg", messageSource.getMessage("message.password.minLength", new Object[] {pswMinLen}, locale));
//			return false;
//		}
		
		String regExp = environment.getProperty("psw.reg.exp");
		if (regExp != null && !regExp.isEmpty() && !password.matches(regExp)) {
			map.put("result", "fail");
			map.put("msg", messageSource.getMessage("message.password.regExp", null, locale));
			return false;
		}
		
		return true;
	}
	
	
//	  @ResponseStatus(value=HttpStatus.CONFLICT, reason="sql exception")
//	  @ExceptionHandler({SQLException.class, DataException.class})
//	  public ModelAndView dataException(Exception exception) {
//		  
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("exc", exception);
//		mav.setViewName("myerror");
//		return mav;	  
//	  }
	  
//	  @ExceptionHandler(SQLException.class)
//	  public ModelAndView myError(Exception exception) {
//	    System.out.println("----Caught SQLException----");
//	    ModelAndView mav = new ModelAndView();
//	    mav.addObject("exc", exception);
//	    mav.setViewName("myerror");
//	    return mav;
//	  }	
	
//	// Specify the name of a specific view that will be used to display the
//	// error:
//	@ExceptionHandler({ SQLException.class, DataAccessException.class })
//	public String databaseError() {
//		// Nothing to do. Returns the logical view name of an error page, passed
//		// to
//		// the view-resolver(s) in usual way.
//		// Note that the exception is _not_ available to this view (it is not
//		// added to
//		// the model) but see "Extending ExceptionHandlerExceptionResolver"
//		// below.
//		return "databaseError";
//	}
//
//	// Total control - setup a model and return the view name yourself. Or
//	// consider
//	// subclassing ExceptionHandlerExceptionResolver (see below).
//	@ExceptionHandler(Exception.class)
//	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
//		// logger.error("Request: " + req.getRequestURL() + " raised " +
//		// exception);
//
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("exception", exception);
//		mav.addObject("url", req.getRequestURL());
//		mav.setViewName("error");
//		return mav;
//	} 

	@RequestMapping(value = "/user/resetPassword", method = RequestMethod.GET)
	public String resetPassword() {
		return PREFIX_PATH + "forgetPassword";
	}
	
	
    @RequestMapping(value = "/user/resetPassword2", method = RequestMethod.GET)
    @ResponseBody
    public GenericResponse resetPassword(final HttpServletRequest request, @RequestParam("email") String userEmail, @RequestParam("codFisc") String codFisc) {
    	
	    User user = userService.findUserByCodFisc(codFisc);
	    if (user == null || user.getDonatore() == null) {
	    	
	    	auditService.audit(codFisc, "Richiesta password di un donatore non presente tra i donatori");
	    	
	        throw new UsernameNotFoundException("Non trovato: " + codFisc);
	    }
	    
	    if (user.getDonatore().getEmail() == null || user.getDonatore().getEmail().isEmpty()) {
	    	auditService.audit(codFisc, "Richiesta password di un donatore, con e-mail in assoAvis non definita. L'utente ha fornito la sua email: " + userEmail);
	    	throw new UsernameNotFoundException("Non trovato: " + userEmail);
	    }
	    
	    if (!user.getDonatore().getEmail().equalsIgnoreCase(userEmail)) {
	    	auditService.audit(codFisc, "Richiesta password di un donatore, ma l'e-mail specificata non è la stessa di assoavis. " + user.getDonatore().getEmail() + " <> " + userEmail);
	    	throw new UsernameNotFoundException("Non trovato: " + userEmail);
	    }
	    
//	    String token = UUID.randomUUID().toString();
//	    userService.createPasswordResetTokenForUser(user, token);
//	    String appUrl = 
//	      "http://" + request.getServerName() + 
//	      ":" + request.getServerPort() + 
//	      request.getContextPath();
	    
        String password = Utility.getNewPsw(10);
        
	    SimpleMailMessage email = constructResetTokenEmail("", request.getLocale(), password, user);
	    mailSender.send(email);
	    
	    GregorianCalendar gc = new GregorianCalendar();
	    gc.add(GregorianCalendar.YEAR, -1);
	    
	    user.setLastChangePsw(new Timestamp(gc.getTimeInMillis()));
		user.setPassword(passwordEncoder.encode(password));
	    userService.updateUser(user);
	    
    	auditService.audit(codFisc, "Nuova password per " +  user.getDonatore().getRefDonatore() + " iviata a: " + user.getDonatore().getEmail());
    	
    	GenericResponse genericResponse = new GenericResponse(messageSource.getMessage("message.resetPasswordEmail", null, request.getLocale()));

    	return genericResponse;
//        return new GenericResponse(messageSource.getMessage("message.resetPasswordEmail", null, request.getLocale()));
        
    }
	
//	@RequestMapping(value = "/user/resetPassword2", method = RequestMethod.GET)
//	public String resetPassword(ModelMap model, final HttpServletRequest request, @RequestParam("email") String userEmail, @RequestParam("codFisc") String codFisc) {
//		
//	    User user = userService.findUserByCodFisc(codFisc);
//	    if (user == null || user.getDonatore() == null) {
//	    	
//	    	auditService.audit(codFisc, "Richiesta password di un donatore non presente tra i donatori");
//	    	
//	        throw new UsernameNotFoundException("Non trovato: " + codFisc);
//	    }
//	    
//	    if (user.getDonatore().getEmail() == null || user.getDonatore().getEmail().isEmpty()) {
//	    	auditService.audit(codFisc, "Richiesta password di un donatore, con e-mail in assoAvis non definita. L'utente ha fornito la sua email: " + userEmail);
//	    	throw new UsernameNotFoundException("Non trovato: " + userEmail);
//	    }
//	    
//	    if (!user.getDonatore().getEmail().equalsIgnoreCase(userEmail)) {
//	    	auditService.audit(codFisc, "Richiesta password di un donatore, ma l'e-mail specificata non è la stessa di assoavis. " + user.getDonatore().getEmail() + " <> " + userEmail);
//	    	throw new UsernameNotFoundException("Non trovato: " + userEmail);
//	    }
//	    
////	    String token = UUID.randomUUID().toString();
////	    userService.createPasswordResetTokenForUser(user, token);
////	    String appUrl = 
////	      "http://" + request.getServerName() + 
////	      ":" + request.getServerPort() + 
////	      request.getContextPath();
//	    
//        String password = Utility.getNewPsw(10);
//        
//	    SimpleMailMessage email = constructResetTokenEmail("", request.getLocale(), password, user);
//	    mailSender.send(email);
//	    
//	    GregorianCalendar gc = new GregorianCalendar();
//	    gc.add(GregorianCalendar.YEAR, -1);
//	    
//	    user.setLastChangePsw(new Timestamp(gc.getTimeInMillis()));
//		user.setPassword(passwordEncoder.encode(password));
//	    userService.updateUser(user);
//	    
//    	auditService.audit(codFisc, "Nuova password per " +  user.getDonatore().getRefDonatore() + " iviata a: " + user.getDonatore().getEmail());
//
////		model.addAttribute("msg", "e-mail inviata all'indirizzo specificato");
//
////	    return model;
//		return "e-mail inviata all'indirizzo specificato";
//	}
//	
//	private ModelAndView resetPassword_ori(final HttpServletRequest request, @RequestParam("email") String userEmail, @RequestParam("codFisc") String codFisc) {
//		
//	    User user = userService.findUserByCodFisc(codFisc);
//	    if (user == null || user.getDonatore() == null) {
//	    	
//	    	auditService.audit(codFisc, "Richiesta password di un donatore non presente tra i donatori");
//	    	
//	        throw new UsernameNotFoundException("Non trovato: " + codFisc);
//	    }
//	    
//	    if (user.getDonatore().getEmail() == null || user.getDonatore().getEmail().isEmpty()) {
//	    	auditService.audit(codFisc, "Richiesta password di un donatore, con e-mail in assoAvis non definita. L'utente ha fornito la sua email: " + userEmail);
//	    	throw new UsernameNotFoundException("Non trovato: " + userEmail);
//	    }
//	    
//	    if (!user.getDonatore().getEmail().equalsIgnoreCase(userEmail)) {
//	    	auditService.audit(codFisc, "Richiesta password di un donatore, ma l'e-mail specificata non è la stessa di assoavis. " + user.getDonatore().getEmail() + " <> " + userEmail);
//	    	throw new UsernameNotFoundException("Non trovato: " + userEmail);
//	    }
//	    
////	    String token = UUID.randomUUID().toString();
////	    userService.createPasswordResetTokenForUser(user, token);
////	    String appUrl = 
////	      "http://" + request.getServerName() + 
////	      ":" + request.getServerPort() + 
////	      request.getContextPath();
//	    
//        String password = Utility.getNewPsw(10);
//        
//	    SimpleMailMessage email = constructResetTokenEmail("", request.getLocale(), password, user);
//	    mailSender.send(email);
//	    
//	    GregorianCalendar gc = new GregorianCalendar();
//	    gc.add(GregorianCalendar.YEAR, -1);
//	    
//	    user.setLastChangePsw(new Timestamp(gc.getTimeInMillis()));
//		user.setPassword(passwordEncoder.encode(password));
//	    userService.updateUser(user);
//	    
//    	auditService.audit(codFisc, "Nuova password per " +  user.getDonatore().getRefDonatore() + " iviata a: " + user.getDonatore().getEmail());
//
//	    ModelAndView model = new ModelAndView();
//	    
//		model.setViewName(PREFIX_PATH + "login");
//	    return model;
//	}
	
    private final SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String password, final User user) {
//        final String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token;
        final String subject = messageSource.getMessage("message.Subject.resetPassword", null, locale);
        final String message = messageSource.getMessage("message.Body.resetPassword", null, locale);
        
        final SimpleMailMessage email = new SimpleMailMessage();
        
        //TODO: cambiare dopo il test 
        boolean test = true;
        if (test) {
        	email.setTo("mauro.fugante@gmail.com");
//        	email.setTo("giovanni.tenace@gmail.com");
            email.setSubject(subject);
            email.setText(message + " \r\nPassword: " + password + "\n\n" + "Questo messaggio è stato mandato da AgendaWeb con il flag test = true.\nModificare AccountController.SimpleMailMessage ");
            email.setFrom(environment.getProperty("support.email"));
        } else {
        	email.setTo(user.getDonatore().getEmail());
            email.setSubject(subject);
            email.setText(message + " \r\nPassword:" + password);
            email.setFrom(environment.getProperty("support.email"));
        }

        return email;
    }
	
//	@RequestMapping(value =  {"/user/resetPassword"}, method = RequestMethod.POST)
//	public ModelAndView resetPassword1(@RequestParam(value = "error", required = false) String error,
//			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {
//
//		ModelAndView model = new ModelAndView();
//		if (error != null) {
//			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
//		}
//
//		if (logout != null) {
//			model.addObject("msg", "Uscita eseguita correttamente.");
//		}
//		
//		model.setViewName(PREFIX_PATH + "login");
//
//		return model;
//	}
	
	@RequestMapping(value =  {"/login"}, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "Uscita eseguita correttamente.");
		}
		
		model.setViewName(PREFIX_PATH + "login");

		return model;
	}
	
	//customize the error message
	private String getErrorMessage(HttpServletRequest request, String key){
	
		Exception exception = (Exception) request.getSession().getAttribute(key);
		
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Credenziali non valide !";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else if (exception instanceof StatusException) {
			error = exception.getMessage();
		} else {
			error = "Credenziali non valide !";
		}
		
		return error;
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			auditService.audit(auth.getName(), "Logout");
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	
}