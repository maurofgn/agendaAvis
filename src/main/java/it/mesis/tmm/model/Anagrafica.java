package it.mesis.tmm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.envers.NotAudited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ANAGRAFICA")
//@Audited
public class Anagrafica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private java.lang.Integer id;

	@Size(min = 1, max = 40)
	@NotEmpty
	@Pattern(regexp = "[a-z-A-Z ]*", message = "Il nome contiene caratteri non validi")
	@Column(name = "COGNOME", nullable = false)
	private String cognome;

	
	@Size(min = 1, max = 40)
	@NotEmpty
	@Pattern(regexp = "[a-z-A-Z ]*", message = "Il nome contiene caratteri non validi")
	@Column(name = "NOME", nullable = false)
	private String nome;

	@NotNull
	@Column(name = "SESSO", nullable = false)
	private byte sesso;
	
	@Size(min = 0, max = 16)
	@Pattern(regexp = "^$|^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$", message = "codice fiscale con formato errato")
	@Column(name = "CODFISCALE")
	private String codfiscale;

	@NotNull
	@Column(name = "IDLUOGONASCITA", nullable = false)
	private short idluogonascita;
	
	@NotAudited
	@ManyToOne(optional = true)
    @JoinColumn(name="IDLUOGONASCITA", insertable=false, updatable=false)
	private Comunicf comunicf;
    
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Past(message = "Data di nascita deve essere nel passato")
//	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "DATANASCITA")
	private java.util.Date datanascita;

	@NotNull
	@Column(name = "IDPROFESSIONE", nullable = false)
	private short idprofessione;

	@NotNull
	@Column(name = "IDRESCOMUNE", nullable = false)
	private short idrescomune;

	@Size(min = 0, max = 50)
	@Column(name = "RESINDIRIZZO")
	private String resindirizzo;

	@Size(min = 0, max = 50)
	@Column(name = "RESCITTA")
	private String rescitta;

	@Size(min = 0, max = 10)
	@Column(name = "RESPROVINCIA")
	private String resprovincia;

	@Size(min = 0, max = 6)
	@Column(name = "RESCAP")
	private String rescap;

	@Size(min = 0, max = 25)
	@Column(name = "RESTELEFONO")
	private String restelefono;

	@NotNull
	@Column(name = "IDCITTADINANZA", nullable = false)
	private short idcittadinanza;

	@Size(min = 0, max = 20)
	@Column(name = "CODSANREG")
	private String codsanreg;

	@Size(min = 0, max = 20)
	@Column(name = "DOCIDENTITA")
	private String docidentita;

	@NotNull
	@Column(name = "USL", nullable = false)
	private short usl;

	@Size(min = 0, max = 25)
	@Column(name = "CELLULARE")
	private String cellulare;

	@Size(min = 0, max = 9)
	@Column(name = "CODDONATORE")
	private String coddonatore;

	@Size(min = 0, max = 9)
	@Column(name = "CODPAZIENTE")
	private String codpaziente;

	@NotNull
	@Column(name = "CT", nullable = false)
	private byte ct;

	@NotNull
	@Column(name = "IDMEDICO", nullable = false)
	private short idmedico;

	@Size(min = 0, max = 81)
//	@NotEmpty
	@Column(name = "COGNOMENOME")
	private String cognomenome;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
//	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "DATAMOD")
	private java.util.Date datamod;

	@NotNull
	@Column(name = "IDNAZIONALITA", nullable = false)
	private short idnazionalita;

	@NotNull
	@Column(name = "IDTITOLOSTUDIO", nullable = false)
	private short idtitolostudio;

	@NotNull
	@Column(name = "CONSENSOPRIVACY", nullable = false)
	private boolean consensoprivacy;

	@NotNull
	@Column(name = "NONVALIDATA", nullable = false)
	private boolean nonvalidata;

	@Size(min = 0, max = 1)
	@Column(name = "CARATTIDENTIFICATIVO")
	private String carattidentificativo;

	@Size(min = 0, max = 50)
	@Email(message = "email non valida")
	@Column(name = "EMAIL")
	private String email;

	@Size(min = 0, max = 50)
	@Column(name = "CAMPOLIBERO01")
	private String campolibero01;

	@Size(min = 0, max = 20)
	@Column(name = "CODICEANAGRAFECENTRALE")
	private String codiceanagrafecentrale;

	@Size(min = 0, max = 10)
	@Column(name = "TITOLO")
	private String titolo;

	@Size(min = 0, max = 10)
	@Column(name = "RESNUMEROCIVICO")
	private String resnumerocivico;

	@NotNull
	@Column(name = "POPUPLIBERO01D", nullable = false)
	private short popuplibero01d;

	@Size(min = 0, max = 50)
	@Column(name = "CAMPOLIBERO01D")
	private String campolibero01d;

	@NotNull
	@Column(name = "POPUPLIBERO01P", nullable = false)
	private short popuplibero01p;

	@Size(min = 0, max = 50)
	@Column(name = "CAMPOLIBERO01P")
	private String campolibero01p;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
//	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "DATADECESSO")
	private java.util.Date datadecesso;

	@NotNull
	@Column(name = "DATIBLOCCATI", nullable = false)
	private boolean datibloccati;

	@NotNull
	@Column(name = "IDRESCOMUNE_CENT", nullable = false)
	private short idrescomuneCent;

	@Size(min = 0, max = 50)
	@Column(name = "RESINDIRIZZO_CENT")
	private String resindirizzoCent;

	@Size(min = 0, max = 50)
	@Column(name = "RESCITTA_CENT")
	private String rescittaCent;

	@Size(min = 0, max = 10)
	@Column(name = "RESPROVINCIA_CENT")
	private String resprovinciaCent;

	@Size(min = 0, max = 6)
	@Column(name = "RESCAP_CENT")
	private String rescapCent;

	@Size(min = 0, max = 25)
	@Column(name = "RESTELEFONO_CENT")
	private String restelefonoCent;

	@Size(min = 0, max = 25)
	@Column(name = "CELLULARE_CENT")
	private String cellulareCent;

	@Size(min = 0, max = 10)
	@Column(name = "RESNUMEROCIVICO_CENT")
	private String resnumerocivicoCent;

	@Size(min = 0, max = 20)
	@Column(name = "CODICEANAGRAFICAMEDICO_CENT")
	private String codiceanagraficamedicoCent;

	@NotNull
	@Column(name = "TIPOCONFERMAANAG", nullable = false)
	private short tipoconfermaanag;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
//	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "DATACONFERMAANAG")
	private java.util.Date dataconfermaanag;

	@NotNull
	@Column(name = "IDUTENTECONFERMAANAG", nullable = false)
	private short idutenteconfermaanag;

	@NotNull
	@Column(name = "AUTORITACONFERMAANAG", nullable = false)
	private short autoritaconfermaanag;
	
	@NotAudited
	@OneToOne(cascade=CascadeType.ALL, mappedBy = "anagrafica")	//, fetch=FetchType.EAGER
	private Tipizzazione tipizzazione;
	
	public Tipizzazione getTipizzazione() {
		return tipizzazione;
	}
	
	public void setTipizzazione(Tipizzazione tipizzazione) {
		this.tipizzazione = tipizzazione;
	}

	
	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte getSesso() {
		return sesso;
	}

	public void setSesso(byte sesso) {
		this.sesso = sesso;
	}

	public String getCodfiscale() {
		return codfiscale;
	}

	public void setCodfiscale(String codfiscale) {
		this.codfiscale = codfiscale;
	}

	public short getIdluogonascita() {
		return idluogonascita;
	}

	public void setIdluogonascita(short idluogonascita) {
		this.idluogonascita = idluogonascita;
	}
	
	public Comunicf getComunicf() {
		return comunicf;
	}

	public void setComunicf(Comunicf comunicf) {
		this.comunicf = comunicf;
	}
	
	public String getComuniCittaProv() {
		return comunicf != null ? comunicf.getCittaProv() : "";
	}
	
	public short getComunicfId() {
		return comunicf != null ? comunicf.getId() : 0;
	}
	
	public void setComunicfId(short id) {
		if (id == 0)
			comunicf = null;
		else {
			comunicf = new Comunicf();
			comunicf.setId(id);
		}
	}


	public java.util.Date getDatanascita() {
		return datanascita;
	}

	public void setDatanascita(java.util.Date datanascita) {
		this.datanascita = datanascita;
	}

	public short getIdprofessione() {
		return idprofessione;
	}

	public void setIdprofessione(short idprofessione) {
		this.idprofessione = idprofessione;
	}

	public short getIdrescomune() {
		return idrescomune;
	}

	public void setIdrescomune(short idrescomune) {
		this.idrescomune = idrescomune;
	}

	public String getResindirizzo() {
		return resindirizzo;
	}

	public void setResindirizzo(String resindirizzo) {
		this.resindirizzo = resindirizzo;
	}

	public String getRescitta() {
		return rescitta;
	}

	public void setRescitta(String rescitta) {
		this.rescitta = rescitta;
	}

	public String getResprovincia() {
		return resprovincia;
	}

	public void setResprovincia(String resprovincia) {
		this.resprovincia = resprovincia;
	}

	public String getRescap() {
		return rescap;
	}

	public void setRescap(String rescap) {
		this.rescap = rescap;
	}

	public String getRestelefono() {
		return restelefono;
	}

	public void setRestelefono(String restelefono) {
		this.restelefono = restelefono;
	}

	public short getIdcittadinanza() {
		return idcittadinanza;
	}

	public void setIdcittadinanza(short idcittadinanza) {
		this.idcittadinanza = idcittadinanza;
	}

	public String getCodsanreg() {
		return codsanreg;
	}

	public void setCodsanreg(String codsanreg) {
		this.codsanreg = codsanreg;
	}

	public String getDocidentita() {
		return docidentita;
	}

	public void setDocidentita(String docidentita) {
		this.docidentita = docidentita;
	}

	public short getUsl() {
		return usl;
	}

	public void setUsl(short usl) {
		this.usl = usl;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getCoddonatore() {
		return coddonatore;
	}

	public void setCoddonatore(String coddonatore) {
		this.coddonatore = coddonatore;
	}

	public String getCodpaziente() {
		return codpaziente;
	}

	public void setCodpaziente(String codpaziente) {
		this.codpaziente = codpaziente;
	}

	public byte getCt() {
		return ct;
	}

	public void setCt(byte ct) {
		this.ct = ct;
	}

	public short getIdmedico() {
		return idmedico;
	}

	public void setIdmedico(short idmedico) {
		this.idmedico = idmedico;
	}

	public String getCognomenome() {
		return cognomenome;
	}

	public void setCognomenome(String cognomenome) {
		this.cognomenome = cognomenome;
	}

	public java.util.Date getDatamod() {
		return datamod;
	}

	public void setDatamod(java.util.Date datamod) {
		this.datamod = datamod;
	}

	public short getIdnazionalita() {
		return idnazionalita;
	}

	public void setIdnazionalita(short idnazionalita) {
		this.idnazionalita = idnazionalita;
	}

	public short getIdtitolostudio() {
		return idtitolostudio;
	}

	public void setIdtitolostudio(short idtitolostudio) {
		this.idtitolostudio = idtitolostudio;
	}

	public boolean getConsensoprivacy() {
		return consensoprivacy;
	}

	public void setConsensoprivacy(boolean consensoprivacy) {
		this.consensoprivacy = consensoprivacy;
	}

	public boolean getNonvalidata() {
		return nonvalidata;
	}

	public void setNonvalidata(boolean nonvalidata) {
		this.nonvalidata = nonvalidata;
	}

	public String getCarattidentificativo() {
		return carattidentificativo;
	}

	public void setCarattidentificativo(String carattidentificativo) {
		this.carattidentificativo = carattidentificativo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCampolibero01() {
		return campolibero01;
	}

	public void setCampolibero01(String campolibero01) {
		this.campolibero01 = campolibero01;
	}

	public String getCodiceanagrafecentrale() {
		return codiceanagrafecentrale;
	}

	public void setCodiceanagrafecentrale(String codiceanagrafecentrale) {
		this.codiceanagrafecentrale = codiceanagrafecentrale;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getResnumerocivico() {
		return resnumerocivico;
	}

	public void setResnumerocivico(String resnumerocivico) {
		this.resnumerocivico = resnumerocivico;
	}

	public short getPopuplibero01d() {
		return popuplibero01d;
	}

	public void setPopuplibero01d(short popuplibero01d) {
		this.popuplibero01d = popuplibero01d;
	}

	public String getCampolibero01d() {
		return campolibero01d;
	}

	public void setCampolibero01d(String campolibero01d) {
		this.campolibero01d = campolibero01d;
	}

	public short getPopuplibero01p() {
		return popuplibero01p;
	}

	public void setPopuplibero01p(short popuplibero01p) {
		this.popuplibero01p = popuplibero01p;
	}

	public String getCampolibero01p() {
		return campolibero01p;
	}

	public void setCampolibero01p(String campolibero01p) {
		this.campolibero01p = campolibero01p;
	}

	public java.util.Date getDatadecesso() {
		return datadecesso;
	}

	public void setDatadecesso(java.util.Date datadecesso) {
		this.datadecesso = datadecesso;
	}

	public boolean getDatibloccati() {
		return datibloccati;
	}

	public void setDatibloccati(boolean datibloccati) {
		this.datibloccati = datibloccati;
	}

	public short getIdrescomuneCent() {
		return idrescomuneCent;
	}

	public void setIdrescomuneCent(short idrescomuneCent) {
		this.idrescomuneCent = idrescomuneCent;
	}

	public String getResindirizzoCent() {
		return resindirizzoCent;
	}

	public void setResindirizzoCent(String resindirizzoCent) {
		this.resindirizzoCent = resindirizzoCent;
	}

	public String getRescittaCent() {
		return rescittaCent;
	}

	public void setRescittaCent(String rescittaCent) {
		this.rescittaCent = rescittaCent;
	}

	public String getResprovinciaCent() {
		return resprovinciaCent;
	}

	public void setResprovinciaCent(String resprovinciaCent) {
		this.resprovinciaCent = resprovinciaCent;
	}

	public String getRescapCent() {
		return rescapCent;
	}

	public void setRescapCent(String rescapCent) {
		this.rescapCent = rescapCent;
	}

	public String getRestelefonoCent() {
		return restelefonoCent;
	}

	public void setRestelefonoCent(String restelefonoCent) {
		this.restelefonoCent = restelefonoCent;
	}

	public String getCellulareCent() {
		return cellulareCent;
	}

	public void setCellulareCent(String cellulareCent) {
		this.cellulareCent = cellulareCent;
	}

	public String getResnumerocivicoCent() {
		return resnumerocivicoCent;
	}

	public void setResnumerocivicoCent(String resnumerocivicoCent) {
		this.resnumerocivicoCent = resnumerocivicoCent;
	}

	public String getCodiceanagraficamedicoCent() {
		return codiceanagraficamedicoCent;
	}

	public void setCodiceanagraficamedicoCent(String codiceanagraficamedicoCent) {
		this.codiceanagraficamedicoCent = codiceanagraficamedicoCent;
	}

	public short getTipoconfermaanag() {
		return tipoconfermaanag;
	}

	public void setTipoconfermaanag(short tipoconfermaanag) {
		this.tipoconfermaanag = tipoconfermaanag;
	}

	public java.util.Date getDataconfermaanag() {
		return dataconfermaanag;
	}

	public void setDataconfermaanag(java.util.Date dataconfermaanag) {
		this.dataconfermaanag = dataconfermaanag;
	}

	public short getIdutenteconfermaanag() {
		return idutenteconfermaanag;
	}

	public void setIdutenteconfermaanag(short idutenteconfermaanag) {
		this.idutenteconfermaanag = idutenteconfermaanag;
	}

	public short getAutoritaconfermaanag() {
		return autoritaconfermaanag;
	}

	public void setAutoritaconfermaanag(short autoritaconfermaanag) {
		this.autoritaconfermaanag = autoritaconfermaanag;
	}
	
	public void setCognomeNome() {
		cognomenome = cognome.trim() + " " + nome.trim();
	}

}
