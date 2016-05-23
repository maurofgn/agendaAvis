package it.mesis.avis.controller;

import it.mesis.avis.exception.StatusException;
import it.mesis.avis.model.Agenda;
import it.mesis.avis.model.AgendaKey;
import it.mesis.avis.security.UserSession;
import it.mesis.avis.service.AgendaService;
import it.mesis.avis.service.AuditService;
import it.mesis.avis.service.DownloadService;
import it.mesis.avis.service.ExporterService;
import it.mesis.avis.service.UserService;
import it.mesis.util.model.Hour;
import it.mesis.util.model.MonthlyBookings;
import it.mesis.util.model.ReportPreno;
import it.mesis.util.model.TipoDonaPuntoPrel;
import it.mesis.util.model.YearMonth;
import it.mesis.utility.TimeUtil;
import it.mesis.utility.Utility;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
//import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/")
public class AppController {
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	AgendaService agendaService;
	
	@Autowired
	AuditService auditService;
	
	@Autowired
	DownloadService downloadService;
	
	@Autowired
	UserService userService;

	private static final String PREFIX_PATH = "agenda/";
	
	private static final SimpleDateFormat DD_MMM_YYYY = new SimpleDateFormat("dd MMM yyyy");
	private static final SimpleDateFormat DATE_HHMM = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public static HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true); // true == allow create
	}
	
	/**
	 * report agenda
	 * @param response
	 * @throws JRException
	 * @throws IOException
	 */
	@Secured ({"ROLE_AVIS", "ROLE_ADMIN", "ROLE_OPERA"})
	@RequestMapping(value = "/reportAgenda", method = RequestMethod.GET)
	@ResponseBody
	public void getReportAgenda(
			@RequestParam(value = "dateFrom", required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date dateFrom,
			@RequestParam(value = "dateTo", required = false) @DateTimeFormat(pattern="dd/MM/yyyy") Date dateTo,
			@RequestParam(value = "tipoDona", required = false) int tipoDona,
			@RequestParam(value = "puntoPrelievo", required = false) int puntoPrelievo,
			HttpServletResponse response
			) throws JRException, IOException {
		

		Calendar gc = TimeUtil.getToday();	//oggi con ore, min, sec e millis = 0
		
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
		
		downloadService.reportAgenda(ExporterService.EXTENSION_TYPE_PDF, "",
				response, dateFrom, dateTo,
				tipoDona, puntoPrelievo);
		
	}
	
	@Secured("ROLE_DONA")
	@RequestMapping(value = {"/disdetta" }, method = RequestMethod.GET)
	public String disdetta(ModelMap model, HttpServletRequest request) {
		
		UserSession userSession = (UserSession)request.getSession().getAttribute(UserSession.USER_SESSION_KEY);
		
		if (userSession == null)
			return "redirect:/login";	//TODO: questo non dovrebbe accadere, ma sta accadendo, per cui va risolto
		
		if (userSession.getDonaStatus() != null && userSession.getDonaStatus().getAgenda() != null) {
			agendaService.disdetta(userSession.getDonaStatus().getAgenda().getId());
			request.getSession().setAttribute("prenoMsg", messageSource.getMessage("msg.cancellation.success", null, request.getLocale()));
			auditService.audit("Disdetta " + userSession.getDonaStatus().getAgenda().getId().toString() + " " + userSession.getDonaStatus().getRefDonatore());
			userSession.getDonaStatus().setAgenda(null);
		}
		
		return "redirect:/agenda";
	}
	
	@RequestMapping(value = {"/listFreeHours" }, method = RequestMethod.GET)
	public String freeHours(
			ModelMap model,
			@RequestParam(value = "dayNr", required = true) int dayNr,
			HttpServletRequest request
			) {
		
		UserSession userSession = (UserSession)request.getSession().getAttribute(UserSession.USER_SESSION_KEY);
		
		if (userSession == null)
			return "redirect:/login";	//TODO: questo non dovrebbe accadere, ma sta accadendo, per cui va risolto
		
		GregorianCalendar gc = new GregorianCalendar(userSession.getYearMonth().getYear(), userSession.getYearMonth().getMonth(), dayNr);
		
		List<Hour> hours = agendaService.freeHours(gc.getTime(), userSession.getTipoDonaPuntoPrelSelected());
		
		String title = DD_MMM_YYYY.format(gc.getTime()) + " - " + userSession.getTipoDonaPuntoPrelSelected();
		
		model.addAttribute("freeHoursTitle", title);
		model.addAttribute("freeHours", hours);

		return PREFIX_PATH + "freeHours";
	}
	
	/**
	 * 
	 * @param puntoprelId
	 * @param tipoDonaId
	 * @param year
	 * @param month
	 * @param day
	 * @return lista delle ore presenti in agenda per i parametri passati
	 */
	@RequestMapping(value = {"/freeHours" }, method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Hour> freeHoursJson(
			@RequestParam(value = "puntoprelId", required = false) int puntoprelId,
			@RequestParam(value = "tipoDonaId", required = false) int tipoDonaId,
			@RequestParam(value = "year", required = false) int year,
			@RequestParam(value = "month", required = false) int month,
			@RequestParam(value = "day", required = false) int day
			) {
		
		GregorianCalendar gc = new GregorianCalendar(year, month, day);
		
		List<Hour> hours = agendaService.freeHours(gc.getTime(), puntoprelId, tipoDonaId);
		
		return hours;
	}
	
	@RequestMapping(value = {"/donors" }, method = RequestMethod.GET, produces="application/json")
	public @ResponseBody List<ReportPreno> donorsJson(
			@RequestParam(value = "puntoprelId", required = false) int puntoprelId,
			@RequestParam(value = "tipoDonaId", required = false) int tipoDonaId,
			@RequestParam(value = "year", required = false) int year,
			@RequestParam(value = "month", required = false) int month,
			@RequestParam(value = "day", required = false) int day
			) {
		
		GregorianCalendar gc = new GregorianCalendar(year, month, day);
		
		List<ReportPreno> rows = agendaService.reportPreno(TimeUtil.getMinHour(gc.getTime()),  TimeUtil.getMaxHour(gc.getTime()), puntoprelId, tipoDonaId);
		
		return rows;
	}
	
	/**
	 * 
	 * @param dayNr
	 * @param hhmm
	 * @param model
	 * @param request
	 * @return
	 */
	@Secured("ROLE_DONA")
	@RequestMapping(value = {"/preno-{dayNr}-dd-{hhmm}-hh" }, method = RequestMethod.GET)
	public String preno(
			@PathVariable int dayNr,
			@PathVariable String hhmm,
			ModelMap model,
			HttpServletRequest request
			) {
		
		UserSession userSession = (UserSession)request.getSession().getAttribute(UserSession.USER_SESSION_KEY);
		
		if (userSession == null)
			return "redirect:/login";	//TODO: questo non dovrebbe accadere, ma sta accadendo, per cui va risolto
		
		if (userSession.getDonaStatus() != null) {
			
			if (!userSession.getDonaStatus().prenoWeb()) {
				
//				//il donatore non può donare. I motivi sono specificati in causa
				String causa = userSession.getDonaStatus().getMsg();
				auditService.audit(userSession.getDonaStatus().getCognomeenome() + " prenotazione non eseguita " + causa);
				String msg = messageSource.getMessage("msg.no.preno", new String[]{causa}, Locale.getDefault());
				throw new StatusException(msg);
			}

			GregorianCalendar gc = new GregorianCalendar(userSession.getYearMonth().getYear(), userSession.getYearMonth().getMonth(), dayNr);
			String[] xx = hhmm.split(":");
			
			gc.set(GregorianCalendar.HOUR_OF_DAY, Utility.parseInteger(xx[0]));
			gc.set(GregorianCalendar.MINUTE, Utility.parseInteger(xx[1]));
			
			Agenda agenda = agendaService.prenota(userSession.getDonaStatus().getCodinternodonat(), gc.getTime(), userSession.getTipoDonaPuntoPrelSelected());
			
			if (agenda == null) {
				//non è stato possibile prenotare perchè non c'è più disponibilità
				auditService.audit("Prenotazione fallita " + userSession.getTipoDonaPuntoPrelSelected().toString() + " " + DATE_HHMM.format(gc.getTime()) + " " + userSession.getDonaStatus().getRefDonatore());
				request.getSession().setAttribute("prenoMsg", messageSource.getMessage("msg.preno.missing", null, request.getLocale()));
			} else {
				userSession.getDonaStatus().setAgenda(agenda);
				auditService.audit("Prenotazione " + userSession.getDonaStatus().getAgenda().getId().toString() + " " + userSession.getDonaStatus().getRefDonatore());
				request.getSession().setAttribute("prenoMsg", messageSource.getMessage("msg.preno.success", null, request.getLocale()));
			}
			
		}
		
		return "redirect:/agenda";
	}
	
	/*
	 * This method will list all existing anagraficas.
	 */
	
	@RequestMapping(value = { "/", "/agenda" }, method = RequestMethod.GET)
	public String agenda(
			ModelMap model, 
			@RequestParam(value = "yearMonth", required = false) String yearMonthString,
			@RequestParam(value = "tipoDonazPuntiPrel", required = false) String tipoDonaPuntoPrelString,
			HttpServletRequest request
			) {
		
		UserSession userSession = (UserSession)request.getSession().getAttribute(UserSession.USER_SESSION_KEY);
		
		if (userSession == null)
			return "redirect:/login";	//TODO: questo non dovrebbe accadere, ma sta accadendo, per cui va risolto
		
		YearMonth yearMonth = null;
		if (yearMonthString != null) {
			String[] ym = yearMonthString.split(",");
			if (ym.length == 2) {
				int year = Utility.parseInteger(ym[0]);
				int month = Utility.parseInteger(ym[1]);
				yearMonth = new YearMonth(year, month); 
			}
		}
		
		TipoDonaPuntoPrel tipoDonaPuntoPrel = userSession.getTipoDonaPuntoPrelSelected();
		if (tipoDonaPuntoPrelString != null) {
			String[] tp = tipoDonaPuntoPrelString.split(",");
			if (tp.length == 2) {
				int tipoDon = Utility.parseInteger(tp[0]);		//tipo donazione
				int puntoPrel = Utility.parseInteger(tp[1]);	//punto di prelievo
				tipoDonaPuntoPrel = getTipoDonaPuntoPrelFromReq (userSession.getListTipoDonazPuntiPrel(), tipoDon, puntoPrel); 
			}
		}

		GregorianCalendar gc = new GregorianCalendar();
		AgendaKey agendaKey = null;							//key dell'attuale prenotazione attiva
		
		if (userSession.getDonaStatus() != null) {
			//donatore
			if (userSession.getDonaStatus().getAgenda() != null)
				agendaKey = userSession.getDonaStatus().getAgenda().getId();		//prenotazione attiva

			if (agendaKey != null && yearMonth == null) {
				gc.setTime(agendaKey.getDataorapren());		//data di default x il donatore con una prenotazione attiva
				tipoDonaPuntoPrel = getTipoDonaPuntoPrelFromReq (userSession.getListTipoDonazPuntiPrel(), 
						agendaKey.getMacchina().getTipoDonazione().getCodice(), 
						agendaKey.getMacchina().getPuntoprelievo().getCodicepuntoprel()); 
			}
			
			if (tipoDonaPuntoPrel == null)
				tipoDonaPuntoPrel = userSession.getTipoDonaPuntoPrel();	//tipo donazione e punto di prelievo di default del donatore se questo è unico
		}
		
		if (yearMonth == null) {
			yearMonth = new YearMonth(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH));
		}
		
		if (tipoDonaPuntoPrel == null && userSession.getDonaStatus() == null) {
			tipoDonaPuntoPrel = userSession.getListTipoDonazPuntiPrel().get(0);
		}
		
		if (tipoDonaPuntoPrel != null) {
			MonthlyBookings monthlyBookings = agendaService.getYearMonth(yearMonth, tipoDonaPuntoPrel, userSession.getDonaStatus());
			model.addAttribute("tipoDonazPuntiPrelSelected", tipoDonaPuntoPrel);
			model.addAttribute("monthlyBookings", monthlyBookings);
		}
		
		model.addAttribute("listTipoDonazPuntiPrel", userSession.getListTipoDonazPuntiPrel());
		
		model.addAttribute("listYearMonth", userSession.getListYearMonth());
		model.addAttribute("yearMonthSelected", yearMonth);
		
		model.addAttribute("yearMonthPrev", yearMontInList(yearMonth.prev(), userSession.getListYearMonth()));
		model.addAttribute("yearMonthNext", yearMontInList(yearMonth.next(), userSession.getListYearMonth()));
		
		model.addAttribute("listTipoDonaz", agendaService.getTipoDonazList());
		model.addAttribute("listPuntiPrel", agendaService.getPuntiPrelievoList());

		if (userSession.getDonaStatus() != null) {
			userSession.setYearMonth(yearMonth);
			if (tipoDonaPuntoPrel != null)
				userSession.setTipoDonaPuntoPrelSelected(tipoDonaPuntoPrel); 
		}
		
		return PREFIX_PATH + "agenda";
	}

	
	private YearMonth yearMontInList(YearMonth yearMonth, List<YearMonth> listYearMonth) {
		return listYearMonth.contains(yearMonth) ? yearMonth : null;
	}
	
	private TipoDonaPuntoPrel getTipoDonaPuntoPrelFromReq (List<TipoDonaPuntoPrel> listTipoDonaPuntoPrel, int tipoDon, int puntoPrel) {
		
		for (TipoDonaPuntoPrel tipoDonaPuntoPrel : listTipoDonaPuntoPrel) {
			if (tipoDonaPuntoPrel.getTipoDonaId() == tipoDon && tipoDonaPuntoPrel.getPuntoprelId() == puntoPrel)
				return tipoDonaPuntoPrel;
		}
		
		return null;
	}
	
	
	/**
	 * richiesta tipo donazione e punto di prelievo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/filterTipoDonPP" }, method = RequestMethod.GET)
	public String filterTipoDonPP(ModelMap model) {
		
		UserSession userSession = (UserSession)session().getAttribute(UserSession.USER_SESSION_KEY);
		model.addAttribute("listTipoDonazPuntiPrel", userSession.getListTipoDonazPuntiPrel());
		return PREFIX_PATH + "tipoDonaz";
	}
	
	/*
	 * definisce il tipo donazione e punto di prelievo
	 */
	@RequestMapping(value = { "/agenda-{tdId}-tipoDona-{ppId}-pp" }, method = RequestMethod.GET)
	public String setTipoDonaPuntoPrelievo(@PathVariable int tdId, @PathVariable int ppId, ModelMap model, HttpServletRequest request) {
		
		UserSession userSession = (UserSession)request.getSession().getAttribute(UserSession.USER_SESSION_KEY);
		if (userSession != null) {
			userSession.setTipoDonaPuntoPrel(tdId, ppId);
		}
		
		return "redirect:/agenda";
//		return "forward:/agenda";
	}
	
}
