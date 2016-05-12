package it.mesis.avis.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import it.mesis.avis.configuration.TestConfiguration;
import it.mesis.avis.model.Donatore;
import it.mesis.avis.model.User;
import it.mesis.avis.model.UserProfile;
import it.mesis.avis.service.AuditService;
import it.mesis.avis.service.UserProfileService;
import it.mesis.avis.service.UserService;
import it.mesis.util.model.GenericResponse;
import it.mesis.util.model.State;
import it.mesis.utility.Utility;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = { TestConfiguration.class })
public class AccountControllerTest extends AbstractTestNGSpringContextTests {
	
	@Mock
	UserProfileService userProfileService;
	
	@Mock
	UserService userService;
	
	@Autowired
	@Spy
    private Environment environment;
	
	@Mock
	MessageSource messageSource;
	
	@Autowired
	@Spy
    private JavaMailSender mailSender;

	@Autowired
	@Spy
    PasswordEncoder passwordEncoder;
	
	@Mock
	AuditService auditService;
	
	@InjectMocks
	AccountController accountController;
	
//	@Spy
//	List<Tipodonaz> tipodonazs = new ArrayList<Tipodonaz>();
//	
//	@Spy
//	List<Puntoprelievo> pps = new ArrayList<Puntoprelievo>();
//	
//	@Spy
//	List<Macchine> machines = new ArrayList<Macchine>();
//	
//	@Spy
//	List<Agenda> agendas = new ArrayList<Agenda>();
//		
//	@Spy
//	List<Hour> hours = new ArrayList<Hour>();
	
	@Spy
	User user = new User();
	
	@Spy
	User operator = new User();
	
	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	private org.springframework.security.core.userdetails.User userAccount;
	
	
//	private HashSet<TipoDonaPuntoPrel> tipoDonaPuntoPrels = new HashSet<TipoDonaPuntoPrel>();
//
//	private ArrayList<YearMonth> yearMonths;

	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		
		user = getUser();
		operator = getUserOperator();
		
		userAccount = new org.springframework.security.core.userdetails.User(
				user.getSsoId(), 
				user.getPasswordDefault(),								//psw dell'utente assoavis o password di app_user
				user.isEnabled(),										//enabled
				user.isNotExpired(),									//AccountNotExpired
				user.pswNotExpired(userService.getValidityDayPsw()),	//Credential not expired
				user.isNotLocked(),										//Account not locked
				getGrantedAuthorities(user)								//authorities (rols)
				);

		Authentication authentication = Mockito.mock(Authentication.class);
		SecurityContext securityContext = Mockito.mock(SecurityContext.class);
		Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
		
		Mockito.when(authentication.getPrincipal()).thenReturn(userAccount);
		SecurityContextHolder.setContext(securityContext);
	}

	
	@Test
	public void passwordCrypt() {
		String password = Utility.getNewPsw(10);
		String ecodedPsw = passwordEncoder.encode(password);
		boolean ok = passwordEncoder.matches(password, ecodedPsw);
		Assert.assertTrue(ok);
	}
	
	@Test
	public void resetPassword() {
		
		String testMessage = "messaggio di test";

		MockHttpServletRequest request = new MockHttpServletRequest();
//		MockHttpServletResponse response = new MockHttpServletResponse();
//		try {
//			request.authenticate(response);
//		} catch (IOException | ServletException e) {
//			e.printStackTrace();
//		}
		
		when(userService.findUserByCodFisc(anyString())).thenReturn(user);
		doNothing().when(userService).updateUser(any(User.class));
		doNothing().when(auditService).audit(anyString(), anyString());
		doNothing().when(mailSender).send(any(SimpleMailMessage.class));
		when(messageSource.getMessage(anyString(), any(Object[].class), any(Locale.class))).thenReturn(testMessage);
		
		GenericResponse resp = accountController.resetPassword(request, "mauro.fugante@gmail.com", "fgnmra60r13c704o");
		Assert.assertNull(resp.getError());
		Assert.assertEquals(testMessage, resp.getMessage());
	}
	
//	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
//	public String accessDeniedPage(ModelMap model) {
//		model.addAttribute("user", getPrincipal());
//		return PREFIX_PATH + "accessDenied";
//	}
	
	@Test
	public void accessDeniedPage() {
		
		String resp = accountController.accessDeniedPage(model);
		Assert.assertEquals(model.get("user"), userAccount.getUsername());
		Assert.assertEquals(resp, "/account/accessDenied");
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserProfile userProfile : user.getUserProfiles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		return authorities;
	}
	
//	private List<Hour> getHours() {
//		
//		GregorianCalendar gc = new GregorianCalendar();
//		TimeUtil.setMinHour(gc);
//		gc.set(GregorianCalendar.HOUR_OF_DAY, 9);
//		int hh = 5;
//		hours = new ArrayList<Hour>(hh);
//		for (int i = 0; i < hh; i++) {
//			int tot = Utility.randInt(1, 5);
//			int use = Utility.randInt(0, tot);
//			hours.add(new Hour(gc.getTime(), use, tot));
//			gc.add(GregorianCalendar.HOUR, 1);
//		}
//		
//		return hours;
//	}
//
//	private List<Agenda> getAgendaList() {
//		
//		GregorianCalendar gc = new GregorianCalendar();
//		TimeUtil.setMinHour(gc);
//		
//		int gg = 32;
//		int hh = 3;
//		agendas = new ArrayList<Agenda>(gg * machines.size() * 5);
//		
//		tipoDonaPuntoPrels = new HashSet<TipoDonaPuntoPrel>();
//		yearMonths = new ArrayList<YearMonth>();
//		
//		for (int i = 0; i < gg; i++) {
//			int yy = gc.get(GregorianCalendar.YEAR);
//			int mm = gc.get(GregorianCalendar.MONTH);
//			YearMonth ym = new YearMonth(yy, mm);
//			if (!yearMonths.contains(ym))
//				yearMonths.add(ym);
//			
//			gc.set(GregorianCalendar.HOUR_OF_DAY, 9);
//			for (int j = 0; j < hh; j++) {
//				for (Macchine macchine : machines) {
//					AgendaKey ak = new AgendaKey();
//					ak.setMacchina(macchine);
//					ak.setDataorapren(new Timestamp(gc.getTimeInMillis()));
//					Agenda agenda = new Agenda();
//					agenda.setId(ak);
//					agendas.add(agenda);
//					tipoDonaPuntoPrels.add(agenda.getId().getMacchina().getTipoDonaPuntoPrel());
//					
//				}
//				gc.add(GregorianCalendar.HOUR, 1);
//			}
//			gc.add(GregorianCalendar.DAY_OF_YEAR, 1);
//		}
//		
//		
//		
//		return agendas;
//	}
	
//	private List<YearMonth> getYearMonths(List<Integer> listTipoDona) {
//		return yearMonths;
//	}
//	
//	private List<TipoDonaPuntoPrel> getTipoDonazPuntiPrel(List<Integer> listTipoDona) {
//		ArrayList<TipoDonaPuntoPrel> retValue = new ArrayList<TipoDonaPuntoPrel>();
//		for (TipoDonaPuntoPrel tipoDonaPuntoPrel : tipoDonaPuntoPrels) {
//			if (listTipoDona.contains(tipoDonaPuntoPrel.getTipoDonaId()))
//				retValue.add(tipoDonaPuntoPrel);
//		}
//		
//		return retValue;
//	}
		
//	private List<Macchine> getMacchine() {
//		
//		machines = new ArrayList<Macchine>(4);
//		
//		Macchine m = new Macchine();
//		m.setId(15);
//		m.setNome("Aferesi Prova diego");
//		m.setPuntoprelievo(pps.get(0));
//		m.setDisponibilita("1;09:00|1;11:00|2;13:00|2;15:00|4;09:00|5;09:00|5;12:00|6;09:00");
//		m.setTipoDonazione(tipodonazs.get(0));
//		machines.add(m);
//		
//		m = new Macchine();
//		m.setId(17);
//		m.setNome("Prelievo sangue m1");
//		m.setPuntoprelievo(pps.get(0));
//		m.setDisponibilita("1;09:00|1;09:10|1;09:20|1;09:30|1;10:00|1;11:00|2;09:00|2;10:00|2;11:33|3;08:00|3;09:00|3;10:00|5;09:00|5;09:35|5;11:15|6;08:00|6;08:30|6;09:00");
//		m.setTipoDonazione(tipodonazs.get(0));
//		m.setNotamacchina("Manutenzione il giovedì");
//		machines.add(m);
//		
//		m = new Macchine();
//		m.setId(21);
//		m.setNome("Prelievo sangue m2");
//		m.setPuntoprelievo(pps.get(0));
//		m.setDisponibilita("1;09:00|1;09:10|1;09:20|1;09:30|1;10:00|1;11:00|2;09:00|2;10:00|2;11:33|3;08:00|3;09:00|3;10:00|4;09:00|4;09:35|4;11:15|6;08:00|6;08:30|6;09:00");
//		m.setTipoDonazione(tipodonazs.get(0));
//		m.setNotamacchina("Manutenzione il venerdì");
//		machines.add(m);
//
//		m = new Macchine();
//		m.setId(20);
//		m.setNome("macchina giova mauro");
//		m.setPuntoprelievo(pps.get(0));
//		m.setDisponibilita("1;10:00|3;09:00|3;09:30|3;10:00|3;10:30");
//		m.setTipoDonazione(tipodonazs.get(0));
//		m.setNotamacchina("Manutenzione il lunedì");
//		machines.add(m);
//		
//		return machines;
//	}
//	
//	private List<Tipodonaz> getTipodonaz() {
//		tipodonazs = new ArrayList<Tipodonaz>(2);
//		Tipodonaz td = new Tipodonaz();
//		td.setCodice(1);
//		td.setDescrizione("Sangue Intero");
//		td.setRimborsosp(18.8);
//		td.setSigla("SI");
//		td.setRimborsormat(50.0);
//		td.setRimborsornomat(12.0);
//		td.setRpers1(0.0);
//		td.setRpers2(1.0);
//		td.setRpers3(0.0);
//		td.setRpers4(0.0);
//		td.setRpers5(0.0);
//		tipodonazs.add(td);
//
//		td = new Tipodonaz();
//		td.setCodice(2);
//		td.setDescrizione("Plasmaferesi");
//		td.setRimborsosp(20.5);
//		td.setSigla("PL");
//		td.setRimborsormat(60.0);
//		td.setRimborsornomat(34.0);
//		td.setRpers1(0.0);
//		td.setRpers2(2.0);
//		td.setRpers3(0.0);
//		td.setRpers4(0.0);
//		td.setRpers5(0.0);
//		tipodonazs.add(td);
//
//		return tipodonazs;
//	}
//	
//	private List<Puntoprelievo> getPuntoprelievos() {
//		pps = new ArrayList<Puntoprelievo>(2);
//		Puntoprelievo pp = new Puntoprelievo();
//		pp.setCodicepuntoprel(12765);
//		pp.setNomepuntoprel("Osp.Macerata");
//		pps.add(pp);
//		
//		pp = new Puntoprelievo();
//		pp.setCodicepuntoprel(152765);
//		pp.setNomepuntoprel("Macerata Festivo");
//		pps.add(pp);
//		
//		return pps;
//	}
	
	private User getUser() {
		User user = new User();
		user.setId(126);
		user.setSsoId("CIFSC_0005412765");
		user.setPassword("55CA");
		user.setAttempts(0);
		user.setLastAccess(new Timestamp(System.currentTimeMillis()) );
		user.setLastChangePsw(new Timestamp(System.currentTimeMillis()) );
		user.setState(State.ACTIVE);
		user.setUtentiId(null);
		user.setCodinternodonat("0005412765");
		user.setAssoAvis("Y");
		user.setDonatore(getDonatore());
		return user;
	}
	
	private User getUserOperator() {
		User user = new User();
		user.setId(1);
		user.setSsoId("mauro");
		user.setPassword("55CA");
		user.setAttempts(0);
		user.setLastAccess(new Timestamp(System.currentTimeMillis()) );
		user.setLastChangePsw(new Timestamp(System.currentTimeMillis()) );
		user.setState(State.ACTIVE);
		user.setUtentiId(null);
		user.setCodinternodonat(null);
		user.setAssoAvis("N");
		return user;
	}
	

//	private UserSession getUserSession(User user, boolean withAgenda) {
//		
//		if (user == null)
//			return null;
//		
//		UserSession userSession = new UserSession();
//		
//		Donatore donatore = user.getDonatore();
//		
//		DonaStatus donaStatus = null;
//		if (donatore != null) {
//			
//			donaStatus = donatore.getDonaStatus();
//			
//			donaStatus.getStatus().forEach(donaStatusType -> donaStatusType.setSuspended(false));
//			
////			Set<DonaStatusType> list = donaStatus.getStatus();
////			for (DonaStatusType donaStatusType : list) {
////				boolean isSusp = isSuspend(donaStatusType, donatore.getCodinternodonat());
////				donaStatusType.setSuspended(isSusp);
////			}
////			
//			Agenda agenda = withAgenda ? getAgenda(donatore) : null;
//			donaStatus.setAgenda(agenda);								//setta l'eventuale prenotazione attiva del donatore
//		}
//		
//		userSession.setListTipoDonazPuntiPrel(getTipoDonazPuntiPrel(donaStatus != null ? donaStatus.getListTipoDona() : null));
//		
//		userSession.setDonaStatus(donaStatus);
//		userSession.setListYearMonth(getYearMonths(donaStatus != null ? donaStatus.getListTipoDona() : null));
//		
//		return userSession;
//	}
	
//	private Agenda getAgenda(Donatore donatore) {
//		
//		Agenda a = agendas.get(Utility.randInt(0, agendas.size()));
//		a.setDonatore(donatore);
//		return a;
//	}
	
	private Donatore getDonatore() {
		
		GregorianCalendar gc = new GregorianCalendar(1960, 9, 13);
		
		Donatore d = new Donatore();
		
		d.setCodinternodonat("0005412765");
		d.setTessera("276509040");
		d.setCodicedonatore(null);
		d.setCognomeenome("ALESSANDRO DON_0005412765");
		d.setLuogonascita(null);
		d.setDatadinascita(new Timestamp(gc.getTimeInMillis()));
		d.setProvdinascita(null);
		d.setProfessione(0);
		d.setSesso("M");
		d.setIdoneita(1);
		d.setStatoassociat(4);
		d.setConvocato((short)1);
		d.setCapofamiglia((short)1);
		d.setIntervallosalsa(0);
		d.setUltimochecku(null);
		d.setTotalesi(2);
		d.setDonazanno(0);
		d.setAferesianno(0);
		d.setTotaleaferesi(1);
		d.setAb0(1);
		d.setRh(1);
		d.setKell(1);
		d.setCognomedasposat(null);
		d.setResindirizzo("VIA BARACCA FRANCESCO");
		d.setRescap("60123");
		d.setRescitta("ANCONA");
		d.setResprov("AN");
		d.setRestel(null);
		d.setResistat("042002");
		d.setDomindirizzo("VIA BARACCA FRANCESCO");
		d.setDomcap("60123");
		d.setDomcitta("ANCONA");
		d.setDomprov("AN");
		d.setDomtel(null);
		d.setDomistat("042002");
		d.setDomresuguali((short)1);
		d.setLavcodice(null);
//		d.setIscraido(0);
//		d.setIscradmo(0);
		d.setTrasferitoa(null);
		d.setTrasfadata(null);
		d.setLavtel(null);
		d.setReslocalita("ancona");
		d.setDomlocalita("ancona");
		d.setEmail("mauro.fugante@gmail.com");
		d.setTitolostudio(0);
		d.setOccupazione(8);
		d.setRamoattivita(0);
		d.setTesserasanitari(null);
//		d.setFlagbenemerenz(0);
//		d.setFlagmedbronzo(0);
//		d.setFlagmedargent(0);
//		d.setFlagmedoro(0);
//		d.setFlagdistintivo(0);
//		d.setFlagcroceoro(0);
		
		gc = new GregorianCalendar(2016, 9, 13);
		
		d.setProssdonazione(new Timestamp(gc.getTimeInMillis()));
		d.setProssaferesi(new Timestamp(gc.getTimeInMillis()));
//		d.setDatabenemerenz(null);
//		d.setDatamedbronzo(null);
//		d.setDatamedargent(null);
//		d.setDatamedoro(null);
//		d.setDatadistintivo(null);
//		d.setDatacroceoro(null);
//		d.setDatagocciabro(null);
//		d.setDatagocciaarg(null);
//		d.setDatagocciaoro(null);
//		d.setFlagaltrabene(0);
//		d.setDataaltraben(null);
//		d.setCodcomunenasc(null);
		d.setTitolo("Sig.");
		d.setCodicefiscale("CIFSC_0005412765");
		d.setCodconvocazion(0);
		d.setTipodonazione(1);
		d.setDataconvpren(new Timestamp(gc.getTimeInMillis()));
		d.setFlaggocciabro((short)0);
		d.setSangueintero((short)1);
		d.setPlasmaferesi((short)0);
		d.setPlasmapltaferes((short)1);
//		d.setPltaferesi(0);
//		d.setLeucoaferesi(0);
//		d.setCellulestamina(0);
//		d.setAferesimultipla(0);
		d.setSezione(2765);
		d.setFlaggocciaarg((short)0);
		d.setFlaggocciaoro((short)0);
		d.setMedicobase(null);
		d.setUltimoesacontr(new Timestamp(gc.getTimeInMillis()));
		d.setPreflunedi((short)1);
		d.setPrefmartedi((short)1);
		d.setPrefmercoledi((short)1);
		d.setPrefgiovedi((short)1);
		d.setTipodocconvoc(0);
		d.setGruppoorganizz(0);
		d.setNucleofam("ac1");
		d.setDataprimaiscr(null);
		d.setSedeprimaiscr(null);
		d.setPrefvenerdi((short)1);
		d.setPrefsabato((short)1);
		d.setPrefdomenica((short)1);
		
		gc.add(Calendar.MONTH, -6);
		d.setUltimdonazione(new Timestamp(gc.getTimeInMillis()));
		
		d.setTotaledonazioni(3);
		d.setCaricasociale(0);
//		d.setNotaidoneita("<Clob>");
//		d.setAltracarica(null);
//		d.setCommissione1(0);
//		d.setCommissione2(0);
//		d.setCommissione3(0);
//		d.setCommissione4(0);
//		d.setCommissione5(0);
//		d.setCommissione6(0);
//		d.setCommissione7(0);
//		d.setCommissione8(0);
//		d.setCommissione9(0);
//		d.setCancelladefin(null);
//		d.setIntervallosalaf(0);
//		d.setIntervalloafeaf(0);
//		d.setIntervalloafesa(0);
//		d.setMotivocancell(null);
//		d.setDonazionipregr(0);
//		d.setAferesipregres(0);
		d.setCellulare("32905412765");
		d.setTrasferitoda(null);
		d.setTrasfdadata(null);
		d.setTesserato((short)1);
//		d.setFlagallergia((short)0);
//		d.setAllergdal(null);
//		d.setAllergal(null);
//		d.setStatocivile(0);
//		d.setMemodon(null);
//		d.setLeggeprivacy(0);
//		d.setNobenem(0);
		d.setSolonome("DON_0005412765");
		d.setSolocognome("ALESSANDRO");
//		d.setUltimaaferesi({ts "2015-12-16 00:00:00"});
//		d.setConsigplasmaf(0);
//		d.setIndicpiastrafer(0);
//		d.setFlagreperibile(0);
//		d.setReperdal(null);
//		d.setReperal(null);
//		d.setFermoposta(null);
//		d.setCodicereg(null);
//		d.setStampadomres(0);
//		d.setDettagliososp("");
//		d.setAllerg2dal(null);
//		d.setAllerg2al(null);
		d.setMesidisponib(4095);
//		d.setLibero0(0);
//		d.setLibero1(0);
//		d.setLibero2(0);
//		d.setLibero3(0);
//		d.setLibero4(0);
//		d.setLibero5(0);
//		d.setLibero6(0);
//		d.setLibero7(0);
//		d.setLibero8(0);
//		d.setLibero9(0);
//		d.setLibero10(0);
//		d.setLibero11(0);
//		d.setUltimourine(null);
//		d.setUltimoecg(null);
//		d.setUltimorx(null);
//		d.setEritroplasma(0);
//		d.setEritroplt(0);
//		d.setDoppiapltaf(0);
//		d.setBloccatoda(0);
//		d.setNonpresentato(0);
//		d.setTesserastampata(1);
		gc = new GregorianCalendar();
		gc.add(Calendar.YEAR, -2);
		d.setDatainiziostato(new Timestamp(gc.getTimeInMillis()));

		d.setDatafinestato(null);
		d.setCodicelogido("2765011102");
//		d.setLettprimadonstampata(0);
//		d.setMemocrypto(null);
//		d.setTrasferito(0);
//		d.setImportato(0);
//		d.setFlagpremagg(0);
//		d.setDatapremagg(null);
//		d.setFlagsms(0);
		d.setFlagmail((short)1);
//		d.setFlagrubinocoll(0);
//		d.setDatarubinocoll(null);
//		d.setTestolibero1(null);
//		d.setTestolibero2(null);
//		d.setDonazionipregrdopo2004(0);
//		d.setDatanuovaanagrafica(null);
//		d.setCodicecrmarche(null);
//		d.setTipoprenmarche(0);

		
		return d;
	}
}
