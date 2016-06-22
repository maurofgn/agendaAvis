package it.mesis.avis.bean.jpa;

import java.text.SimpleDateFormat;

import it.mesis.avis.enu.DonaType;
import it.mesis.avis.enu.MembershipState;
import it.mesis.util.model.DonaStatus;
import it.mesis.util.model.DonaStatusType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DONATORE", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODICEFISCALE")
		 })

public class DonatoreEntity {

	@Id
	@Size(min=1, max=50)
	@Column(name = "CODINTERNODONAT")
	private String codinternodonat;

	@Size(min=0, max=11)
	@Column(name = "TESSERA")
	private String tessera;

	@Size(min=0, max=50)
	@Column(name = "CODICEDONATORE")
	private String codicedonatore;

	@Size(min=0, max=61)
	@Column(name = "COGNOMEENOME")
	private String cognomeenome;

	@Size(min=0, max=50)
	@Column(name = "LUOGONASCITA")
	private String luogonascita;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATADINASCITA")
	private java.sql.Timestamp datadinascita;

	@Size(min=0, max=2)
	@Column(name = "PROVDINASCITA")
	private String provdinascita;

	@NotNull
	@Column(name = "PROFESSIONE", nullable = false)
	private int professione;

	@Size(min=1, max=1)
	@NotEmpty
	@Column(name = "SESSO", nullable = false)
	private String sesso;

	@NotNull
	@Column(name = "IDONEITA", nullable = false)
	private int idoneita;

	@NotNull
	@Column(name = "STATOASSOCIAT", nullable = false)
	private int statoassociat;

	@NotNull
	@Column(name = "CONVOCATO", nullable = false)
	private short convocato;

	@NotNull
	@Column(name = "CAPOFAMIGLIA", nullable = false)
	private short capofamiglia;

	@NotNull
	@Column(name = "INTERVALLOSALSA", nullable = false)
	private int intervallosalsa;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "ULTIMOCHECKU")
	private java.sql.Timestamp ultimochecku;

	@NotNull
	@Column(name = "TOTALESI", nullable = false)
	private int totalesi;

	@NotNull
	@Column(name = "DONAZANNO", nullable = false)
	private int donazanno;

	@NotNull
	@Column(name = "AFERESIANNO", nullable = false)
	private int aferesianno;

	@NotNull
	@Column(name = "TOTALEAFERESI", nullable = false)
	private int totaleaferesi;

	@NotNull
	@Column(name = "AB0", nullable = false)
	private int ab0;

	@NotNull
	@Column(name = "RH", nullable = false)
	private int rh;

	@NotNull
	@Column(name = "KELL", nullable = false)
	private int kell;

	@Size(min=0, max=2147483647)
	@Column(name = "COGNOMEDASPOSAT")
	private String cognomedasposat;

	@Size(min=0, max=80)
	@Column(name = "RESINDIRIZZO")
	private String resindirizzo;

	@Size(min=0, max=5)
	@Column(name = "RESCAP")
	private String rescap;

	@Size(min=0, max=50)
	@Column(name = "RESCITTA")
	private String rescitta;

	@Size(min=0, max=2)
	@Column(name = "RESPROV")
	private String resprov;

	@Size(min=0, max=20)
	@Column(name = "RESTEL")
	private String restel;

	@Size(min=0, max=6)
	@Column(name = "RESISTAT")
	private String resistat;

	@Size(min=0, max=80)
	@Column(name = "DOMINDIRIZZO")
	private String domindirizzo;

	@Size(min=0, max=5)
	@Column(name = "DOMCAP")
	private String domcap;

	@Size(min=0, max=50)
	@Column(name = "DOMCITTA")
	private String domcitta;

	@Size(min=0, max=2)
	@Column(name = "DOMPROV")
	private String domprov;

	@Size(min=0, max=20)
	@Column(name = "DOMTEL")
	private String domtel;

	@Size(min=0, max=6)
	@Column(name = "DOMISTAT")
	private String domistat;

	@Column(name = "DOMRESUGUALI")
	private short domresuguali;

	@Size(min=0, max=16)
	@Column(name = "LAVCODICE")
	private String lavcodice;

	@NotNull
	@Column(name = "ISCRAIDO", nullable = false)
	private short iscraido;

	@NotNull
	@Column(name = "ISCRADMO", nullable = false)
	private short iscradmo;

	@Size(min=0, max=2147483647)
	@Column(name = "TRASFERITOA")
	private String trasferitoa;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "TRASFADATA")
	private java.sql.Timestamp trasfadata;

	@Size(min=0, max=20)
	@Column(name = "LAVTEL")
	private String lavtel;

	@Size(min=0, max=40)
	@Column(name = "RESLOCALITA")
	private String reslocalita;

	@Size(min=0, max=40)
	@Column(name = "DOMLOCALITA")
	private String domlocalita;

	@Size(min=0, max=2147483647)
	@Email(message="email non valida")
	@Column(name = "EMAIL")
	private String email;

	@NotNull
	@Column(name = "TITOLOSTUDIO", nullable = false)
	private int titolostudio;

	@NotNull
	@Column(name = "OCCUPAZIONE", nullable = false)
	private int occupazione;

	@NotNull
	@Column(name = "RAMOATTIVITA", nullable = false)
	private int ramoattivita;

	@Size(min=0, max=30)
	@Column(name = "TESSERASANITARI")
	private String tesserasanitari;

	@NotNull
	@Column(name = "FLAGBENEMERENZ", nullable = false)
	private short flagbenemerenz;

	@NotNull
	@Column(name = "FLAGMEDBRONZO", nullable = false)
	private short flagmedbronzo;

	@NotNull
	@Column(name = "FLAGMEDARGENT", nullable = false)
	private short flagmedargent;

	@NotNull
	@Column(name = "FLAGMEDORO", nullable = false)
	private short flagmedoro;

	@NotNull
	@Column(name = "FLAGDISTINTIVO", nullable = false)
	private short flagdistintivo;

	@NotNull
	@Column(name = "FLAGCROCEORO", nullable = false)
	private short flagcroceoro;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "PROSSDONAZIONE")
	private java.sql.Timestamp prossdonazione;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "PROSSAFERESI")
	private java.sql.Timestamp prossaferesi;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATABENEMERENZ")
	private java.sql.Timestamp databenemerenz;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAMEDBRONZO")
	private java.sql.Timestamp datamedbronzo;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAMEDARGENT")
	private java.sql.Timestamp datamedargent;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAMEDORO")
	private java.sql.Timestamp datamedoro;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATADISTINTIVO")
	private java.sql.Timestamp datadistintivo;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATACROCEORO")
	private java.sql.Timestamp datacroceoro;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAGOCCIABRO")
	private java.sql.Timestamp datagocciabro;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAGOCCIAARG")
	private java.sql.Timestamp datagocciaarg;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAGOCCIAORO")
	private java.sql.Timestamp datagocciaoro;

	@NotNull
	@Column(name = "FLAGALTRABENE", nullable = false)
	private short flagaltrabene;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAALTRABEN")
	private java.sql.Timestamp dataaltraben;

	@Size(min=0, max=6)
	@Column(name = "CODCOMUNENASC")
	private String codcomunenasc;

	@Size(min=0, max=10)
	@Column(name = "TITOLO")
	private String titolo;

	@Size(min=0, max=16)
	@Column(name = "CODICEFISCALE")
	private String codicefiscale;

	@NotNull
	@Column(name = "CODCONVOCAZION", nullable = false)
	private int codconvocazion;

	@NotNull
	@Column(name = "TIPODONAZIONE", nullable = false)
	private int tipodonazione;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATACONVPREN")
	private java.sql.Timestamp dataconvpren;

	@NotNull
	@Column(name = "FLAGGOCCIABRO", nullable = false)
	private short flaggocciabro;

	@NotNull
	@Column(name = "SANGUEINTERO", nullable = false)
	private short sangueintero;

	@NotNull
	@Column(name = "PLASMAFERESI", nullable = false)
	private short plasmaferesi;

	@NotNull
	@Column(name = "PLASMAPLTAFERES", nullable = false)
	private short plasmapltaferes;

	@NotNull
	@Column(name = "PLTAFERESI", nullable = false)
	private short pltaferesi;

	@NotNull
	@Column(name = "LEUCOAFERESI", nullable = false)
	private short leucoaferesi;

	@NotNull
	@Column(name = "CELLULESTAMINA", nullable = false)
	private short cellulestamina;

	@NotNull
	@Column(name = "AFERESIMULTIPLA", nullable = false)
	private short aferesimultipla;

	@NotNull
	@Column(name = "SEZIONE", nullable = false)
	private int sezione;

	@NotNull
	@Column(name = "FLAGGOCCIAARG", nullable = false)
	private short flaggocciaarg;

	@NotNull
	@Column(name = "FLAGGOCCIAORO", nullable = false)
	private short flaggocciaoro;

	@Size(min=0, max=20)
	@Column(name = "MEDICOBASE")
	private String medicobase;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "ULTIMOESACONTR")
	private java.sql.Timestamp ultimoesacontr;

	@NotNull
	@Column(name = "PREFLUNEDI", nullable = false)
	private short preflunedi;

	@NotNull
	@Column(name = "PREFMARTEDI", nullable = false)
	private short prefmartedi;

	@NotNull
	@Column(name = "PREFMERCOLEDI", nullable = false)
	private short prefmercoledi;

	@NotNull
	@Column(name = "PREFGIOVEDI", nullable = false)
	private short prefgiovedi;

	@NotNull
	@Column(name = "TIPODOCCONVOC", nullable = false)
	private int tipodocconvoc;

	@NotNull
	@Column(name = "GRUPPOORGANIZZ", nullable = false)
	private int gruppoorganizz;

	@Size(min=0, max=10)
	@Column(name = "NUCLEOFAM")
	private String nucleofam;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAPRIMAISCR")
	private java.sql.Timestamp dataprimaiscr;

	@Size(min=0, max=30)
	@Column(name = "SEDEPRIMAISCR")
	private String sedeprimaiscr;

	@NotNull
	@Column(name = "PREFVENERDI", nullable = false)
	private short prefvenerdi;

	@NotNull
	@Column(name = "PREFSABATO", nullable = false)
	private short prefsabato;

	@NotNull
	@Column(name = "PREFDOMENICA", nullable = false)
	private short prefdomenica;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "ULTIMDONAZIONE")
	private java.sql.Timestamp ultimdonazione;

	@NotNull
	@Column(name = "TOTALEDONAZIONI", nullable = false)
	private int totaledonazioni;

	@NotNull
	@Column(name = "CARICASOCIALE", nullable = false)
	private int caricasociale;

	@Size(min=0, max=2147483647)
	@Column(name = "NOTAIDONEITA")
	private String notaidoneita;

	@Size(min=0, max=2147483647)
	@Column(name = "ALTRACARICA")
	private String altracarica;

	@NotNull
	@Column(name = "COMMISSIONE1", nullable = false)
	private short commissione1;

	@NotNull
	@Column(name = "COMMISSIONE2", nullable = false)
	private short commissione2;

	@NotNull
	@Column(name = "COMMISSIONE3", nullable = false)
	private short commissione3;

	@NotNull
	@Column(name = "COMMISSIONE4", nullable = false)
	private short commissione4;

	@NotNull
	@Column(name = "COMMISSIONE5", nullable = false)
	private short commissione5;

	@NotNull
	@Column(name = "COMMISSIONE6", nullable = false)
	private short commissione6;

	@NotNull
	@Column(name = "COMMISSIONE7", nullable = false)
	private short commissione7;

	@NotNull
	@Column(name = "COMMISSIONE8", nullable = false)
	private short commissione8;

	@NotNull
	@Column(name = "COMMISSIONE9", nullable = false)
	private short commissione9;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "CANCELLADEFIN")
	private java.sql.Timestamp cancelladefin;

	@NotNull
	@Column(name = "INTERVALLOSALAF", nullable = false)
	private int intervallosalaf;

	@NotNull
	@Column(name = "INTERVALLOAFEAF", nullable = false)
	private int intervalloafeaf;

	@NotNull
	@Column(name = "INTERVALLOAFESA", nullable = false)
	private int intervalloafesa;

	@Size(min=0, max=2147483647)
	@Column(name = "MOTIVOCANCELL")
	private String motivocancell;

	@NotNull
	@Column(name = "DONAZIONIPREGR", nullable = false)
	private int donazionipregr;

	@NotNull
	@Column(name = "AFERESIPREGRES", nullable = false)
	private int aferesipregres;

	@Size(min=0, max=20)
	@Column(name = "CELLULARE")
	private String cellulare;

	@Size(min=0, max=2147483647)
	@Column(name = "TRASFERITODA")
	private String trasferitoda;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "TRASFDADATA")
	private java.sql.Timestamp trasfdadata;

	@NotNull
	@Column(name = "TESSERATO", nullable = false)
	private short tesserato;

	@NotNull
	@Column(name = "FLAGALLERGIA", nullable = false)
	private short flagallergia;

	@Size(min=0, max=4)
	@Column(name = "ALLERGDAL")
	private String allergdal;

	@Size(min=0, max=4)
	@Column(name = "ALLERGAL")
	private String allergal;

	@NotNull
	@Column(name = "STATOCIVILE", nullable = false)
	private int statocivile;

	@Size(min=0, max=2147483647)
	@Column(name = "MEMODON")
	private String memodon;

	@NotNull
	@Column(name = "LEGGEPRIVACY", nullable = false)
	private short leggeprivacy;

	@NotNull
	@Column(name = "NOBENEM", nullable = false)
	private short nobenem;

	@Size(min=0, max=30)
	@Column(name = "SOLONOME")
	private String solonome;

	@Size(min=0, max=30)
	@Column(name = "SOLOCOGNOME")
	private String solocognome;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "ULTIMAAFERESI")
	private java.sql.Timestamp ultimaaferesi;

	@NotNull
	@Column(name = "CONSIGPLASMAF", nullable = false)
	private short consigplasmaf;

	@NotNull
	@Column(name = "INDICPIASTRAFER", nullable = false)
	private short indicpiastrafer;

	@NotNull
	@Column(name = "FLAGREPERIBILE", nullable = false)
	private short flagreperibile;

	@Size(min=0, max=4)
	@Column(name = "REPERDAL")
	private String reperdal;

	@Size(min=0, max=4)
	@Column(name = "REPERAL")
	private String reperal;

	@Size(min=0, max=2147483647)
	@Column(name = "FERMOPOSTA")
	private String fermoposta;

	@Size(min=0, max=20)
	@Column(name = "CODICEREG")
	private String codicereg;

	@NotNull
	@Column(name = "STAMPADOMRES", nullable = false)
	private short stampadomres;

	@Size(min=0, max=20)
	@Column(name = "DETTAGLIOSOSP")
	private String dettagliososp;

	@Size(min=0, max=4)
	@Column(name = "ALLERG2DAL")
	private String allerg2dal;

	@Size(min=0, max=4)
	@Column(name = "ALLERG2AL")
	private String allerg2al;

	@NotNull
	@Column(name = "MESIDISPONIB", nullable = false)
	private int mesidisponib;

	@NotNull
	@Column(name = "LIBERO0", nullable = false)
	private short libero0;

	@NotNull
	@Column(name = "LIBERO1", nullable = false)
	private short libero1;

	@NotNull
	@Column(name = "LIBERO2", nullable = false)
	private short libero2;

	@NotNull
	@Column(name = "LIBERO3", nullable = false)
	private short libero3;

	@NotNull
	@Column(name = "LIBERO4", nullable = false)
	private short libero4;

	@NotNull
	@Column(name = "LIBERO5", nullable = false)
	private short libero5;

	@NotNull
	@Column(name = "LIBERO6", nullable = false)
	private short libero6;

	@NotNull
	@Column(name = "LIBERO7", nullable = false)
	private short libero7;

	@NotNull
	@Column(name = "LIBERO8", nullable = false)
	private short libero8;

	@NotNull
	@Column(name = "LIBERO9", nullable = false)
	private short libero9;

	@NotNull
	@Column(name = "LIBERO10", nullable = false)
	private short libero10;

	@NotNull
	@Column(name = "LIBERO11", nullable = false)
	private short libero11;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "ULTIMOURINE")
	private java.sql.Timestamp ultimourine;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "ULTIMOECG")
	private java.sql.Timestamp ultimoecg;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "ULTIMORX")
	private java.sql.Timestamp ultimorx;

	@NotNull
	@Column(name = "ERITROPLASMA", nullable = false)
	private short eritroplasma;

	@NotNull
	@Column(name = "ERITROPLT", nullable = false)
	private short eritroplt;

	@NotNull
	@Column(name = "DOPPIAPLTAF", nullable = false)
	private short doppiapltaf;

	@NotNull
	@Column(name = "BLOCCATODA", nullable = false)
	private int bloccatoda;

	@NotNull
	@Column(name = "NONPRESENTATO", nullable = false)
	private short nonpresentato;

	@NotNull
	@Column(name = "TESSERASTAMPATA", nullable = false)
	private short tesserastampata;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAINIZIOSTATO")
	private java.sql.Timestamp datainiziostato;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAFINESTATO")
	private java.sql.Timestamp datafinestato;

	@Size(min=0, max=10)
	@Column(name = "CODICELOGIDO")
	private String codicelogido;

	@NotNull
	@Column(name = "LETTPRIMADONSTAMPATA", nullable = false)
	private short lettprimadonstampata;

	@Size(min=0, max=2147483647)
	@Column(name = "MEMOCRYPTO")
	private String memocrypto;

	@NotNull
	@Column(name = "TRASFERITO", nullable = false)
	private short trasferito;

	@NotNull
	@Column(name = "IMPORTATO", nullable = false)
	private short importato;

	@NotNull
	@Column(name = "FLAGPREMAGG", nullable = false)
	private short flagpremagg;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAPREMAGG")
	private java.sql.Timestamp datapremagg;

	@NotNull
	@Column(name = "FLAGSMS", nullable = false)
	private short flagsms;

	@NotNull
	@Column(name = "FLAGMAIL", nullable = false)
	private short flagmail;

	@NotNull
	@Column(name = "FLAGRUBINOCOLL", nullable = false)
	private short flagrubinocoll;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATARUBINOCOLL")
	private java.sql.Timestamp datarubinocoll;

	@Size(min=0, max=20)
	@Column(name = "TESTOLIBERO1")
	private String testolibero1;

	@Size(min=0, max=20)
	@Column(name = "TESTOLIBERO2")
	private String testolibero2;

	@NotNull
	@Column(name = "DONAZIONIPREGRDOPO2004", nullable = false)
	private short donazionipregrdopo2004;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATANUOVAANAGRAFICA")
	private java.sql.Timestamp datanuovaanagrafica;


	public String getCodinternodonat() {
		return codinternodonat;
	}
	public void setCodinternodonat(String codinternodonat) {
		this.codinternodonat = codinternodonat;
	}

	public String getTessera() {
		return tessera;
	}
	public void setTessera(String tessera) {
		this.tessera = tessera;
	}

	public String getCodicedonatore() {
		return codicedonatore;
	}
	public void setCodicedonatore(String codicedonatore) {
		this.codicedonatore = codicedonatore;
	}

	public String getCognomeenome() {
		return cognomeenome;
	}
	public void setCognomeenome(String cognomeenome) {
		this.cognomeenome = cognomeenome;
	}

	public String getLuogonascita() {
		return luogonascita;
	}
	public void setLuogonascita(String luogonascita) {
		this.luogonascita = luogonascita;
	}

	public java.sql.Timestamp getDatadinascita() {
		return datadinascita;
	}
	public void setDatadinascita(java.sql.Timestamp datadinascita) {
		this.datadinascita = datadinascita;
	}

	public String getProvdinascita() {
		return provdinascita;
	}
	public void setProvdinascita(String provdinascita) {
		this.provdinascita = provdinascita;
	}

	public int getProfessione() {
		return professione;
	}
	public void setProfessione(int professione) {
		this.professione = professione;
	}

	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public int getIdoneita() {
		return idoneita;
	}
	public void setIdoneita(int idoneita) {
		this.idoneita = idoneita;
	}

	public int getStatoassociat() {
		return statoassociat;
	}
	public void setStatoassociat(int statoassociat) {
		this.statoassociat = statoassociat;
	}
	
	public MembershipState getMembershipState() {
		return MembershipState.getMembershipState(statoassociat);
	}

	public short getConvocato() {
		return convocato;
	}
	public void setConvocato(short convocato) {
		this.convocato = convocato;
	}

	public short getCapofamiglia() {
		return capofamiglia;
	}
	public void setCapofamiglia(short capofamiglia) {
		this.capofamiglia = capofamiglia;
	}

	public int getIntervallosalsa() {
		return intervallosalsa;
	}
	public void setIntervallosalsa(int intervallosalsa) {
		this.intervallosalsa = intervallosalsa;
	}

	public java.sql.Timestamp getUltimochecku() {
		return ultimochecku;
	}
	public void setUltimochecku(java.sql.Timestamp ultimochecku) {
		this.ultimochecku = ultimochecku;
	}

	public int getTotalesi() {
		return totalesi;
	}
	public void setTotalesi(int totalesi) {
		this.totalesi = totalesi;
	}

	public int getDonazanno() {
		return donazanno;
	}
	public void setDonazanno(int donazanno) {
		this.donazanno = donazanno;
	}

	public int getAferesianno() {
		return aferesianno;
	}
	public void setAferesianno(int aferesianno) {
		this.aferesianno = aferesianno;
	}

	public int getTotaleaferesi() {
		return totaleaferesi;
	}
	public void setTotaleaferesi(int totaleaferesi) {
		this.totaleaferesi = totaleaferesi;
	}

	public int getAb0() {
		return ab0;
	}
	public void setAb0(int ab0) {
		this.ab0 = ab0;
	}

	public int getRh() {
		return rh;
	}
	public void setRh(int rh) {
		this.rh = rh;
	}

	public int getKell() {
		return kell;
	}
	public void setKell(int kell) {
		this.kell = kell;
	}

	public String getCognomedasposat() {
		return cognomedasposat;
	}
	public void setCognomedasposat(String cognomedasposat) {
		this.cognomedasposat = cognomedasposat;
	}

	public String getResindirizzo() {
		return resindirizzo;
	}
	public void setResindirizzo(String resindirizzo) {
		this.resindirizzo = resindirizzo;
	}

	public String getRescap() {
		return rescap;
	}
	public void setRescap(String rescap) {
		this.rescap = rescap;
	}

	public String getRescitta() {
		return rescitta;
	}
	public void setRescitta(String rescitta) {
		this.rescitta = rescitta;
	}

	public String getResprov() {
		return resprov;
	}
	public void setResprov(String resprov) {
		this.resprov = resprov;
	}

	public String getRestel() {
		return restel;
	}
	public void setRestel(String restel) {
		this.restel = restel;
	}

	public String getResistat() {
		return resistat;
	}
	public void setResistat(String resistat) {
		this.resistat = resistat;
	}

	public String getDomindirizzo() {
		return domindirizzo;
	}
	public void setDomindirizzo(String domindirizzo) {
		this.domindirizzo = domindirizzo;
	}

	public String getDomcap() {
		return domcap;
	}
	public void setDomcap(String domcap) {
		this.domcap = domcap;
	}

	public String getDomcitta() {
		return domcitta;
	}
	public void setDomcitta(String domcitta) {
		this.domcitta = domcitta;
	}

	public String getDomprov() {
		return domprov;
	}
	public void setDomprov(String domprov) {
		this.domprov = domprov;
	}

	public String getDomtel() {
		return domtel;
	}
	public void setDomtel(String domtel) {
		this.domtel = domtel;
	}

	public String getDomistat() {
		return domistat;
	}
	public void setDomistat(String domistat) {
		this.domistat = domistat;
	}

	public short getDomresuguali() {
		return domresuguali;
	}
	public void setDomresuguali(short domresuguali) {
		this.domresuguali = domresuguali;
	}

	public String getLavcodice() {
		return lavcodice;
	}
	public void setLavcodice(String lavcodice) {
		this.lavcodice = lavcodice;
	}

	public short getIscraido() {
		return iscraido;
	}
	public void setIscraido(short iscraido) {
		this.iscraido = iscraido;
	}

	public short getIscradmo() {
		return iscradmo;
	}
	public void setIscradmo(short iscradmo) {
		this.iscradmo = iscradmo;
	}

	public String getTrasferitoa() {
		return trasferitoa;
	}
	public void setTrasferitoa(String trasferitoa) {
		this.trasferitoa = trasferitoa;
	}

	public java.sql.Timestamp getTrasfadata() {
		return trasfadata;
	}
	public void setTrasfadata(java.sql.Timestamp trasfadata) {
		this.trasfadata = trasfadata;
	}

	public String getLavtel() {
		return lavtel;
	}
	public void setLavtel(String lavtel) {
		this.lavtel = lavtel;
	}

	public String getReslocalita() {
		return reslocalita;
	}
	public void setReslocalita(String reslocalita) {
		this.reslocalita = reslocalita;
	}

	public String getDomlocalita() {
		return domlocalita;
	}
	public void setDomlocalita(String domlocalita) {
		this.domlocalita = domlocalita;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public int getTitolostudio() {
		return titolostudio;
	}
	public void setTitolostudio(int titolostudio) {
		this.titolostudio = titolostudio;
	}

	public int getOccupazione() {
		return occupazione;
	}
	public void setOccupazione(int occupazione) {
		this.occupazione = occupazione;
	}

	public int getRamoattivita() {
		return ramoattivita;
	}
	public void setRamoattivita(int ramoattivita) {
		this.ramoattivita = ramoattivita;
	}

	public String getTesserasanitari() {
		return tesserasanitari;
	}
	public void setTesserasanitari(String tesserasanitari) {
		this.tesserasanitari = tesserasanitari;
	}

	public short getFlagbenemerenz() {
		return flagbenemerenz;
	}
	public void setFlagbenemerenz(short flagbenemerenz) {
		this.flagbenemerenz = flagbenemerenz;
	}

	public short getFlagmedbronzo() {
		return flagmedbronzo;
	}
	public void setFlagmedbronzo(short flagmedbronzo) {
		this.flagmedbronzo = flagmedbronzo;
	}

	public short getFlagmedargent() {
		return flagmedargent;
	}
	public void setFlagmedargent(short flagmedargent) {
		this.flagmedargent = flagmedargent;
	}

	public short getFlagmedoro() {
		return flagmedoro;
	}
	public void setFlagmedoro(short flagmedoro) {
		this.flagmedoro = flagmedoro;
	}

	public short getFlagdistintivo() {
		return flagdistintivo;
	}
	public void setFlagdistintivo(short flagdistintivo) {
		this.flagdistintivo = flagdistintivo;
	}

	public short getFlagcroceoro() {
		return flagcroceoro;
	}
	public void setFlagcroceoro(short flagcroceoro) {
		this.flagcroceoro = flagcroceoro;
	}

	public java.sql.Timestamp getProssdonazione() {
		return prossdonazione;
	}
	public void setProssdonazione(java.sql.Timestamp prossdonazione) {
		this.prossdonazione = prossdonazione;
	}

	public java.sql.Timestamp getProssaferesi() {
		return prossaferesi;
	}
	public void setProssaferesi(java.sql.Timestamp prossaferesi) {
		this.prossaferesi = prossaferesi;
	}

	public java.sql.Timestamp getDatabenemerenz() {
		return databenemerenz;
	}
	public void setDatabenemerenz(java.sql.Timestamp databenemerenz) {
		this.databenemerenz = databenemerenz;
	}

	public java.sql.Timestamp getDatamedbronzo() {
		return datamedbronzo;
	}
	public void setDatamedbronzo(java.sql.Timestamp datamedbronzo) {
		this.datamedbronzo = datamedbronzo;
	}

	public java.sql.Timestamp getDatamedargent() {
		return datamedargent;
	}
	public void setDatamedargent(java.sql.Timestamp datamedargent) {
		this.datamedargent = datamedargent;
	}

	public java.sql.Timestamp getDatamedoro() {
		return datamedoro;
	}
	public void setDatamedoro(java.sql.Timestamp datamedoro) {
		this.datamedoro = datamedoro;
	}

	public java.sql.Timestamp getDatadistintivo() {
		return datadistintivo;
	}
	public void setDatadistintivo(java.sql.Timestamp datadistintivo) {
		this.datadistintivo = datadistintivo;
	}

	public java.sql.Timestamp getDatacroceoro() {
		return datacroceoro;
	}
	public void setDatacroceoro(java.sql.Timestamp datacroceoro) {
		this.datacroceoro = datacroceoro;
	}

	public java.sql.Timestamp getDatagocciabro() {
		return datagocciabro;
	}
	public void setDatagocciabro(java.sql.Timestamp datagocciabro) {
		this.datagocciabro = datagocciabro;
	}

	public java.sql.Timestamp getDatagocciaarg() {
		return datagocciaarg;
	}
	public void setDatagocciaarg(java.sql.Timestamp datagocciaarg) {
		this.datagocciaarg = datagocciaarg;
	}

	public java.sql.Timestamp getDatagocciaoro() {
		return datagocciaoro;
	}
	public void setDatagocciaoro(java.sql.Timestamp datagocciaoro) {
		this.datagocciaoro = datagocciaoro;
	}

	public short getFlagaltrabene() {
		return flagaltrabene;
	}
	public void setFlagaltrabene(short flagaltrabene) {
		this.flagaltrabene = flagaltrabene;
	}

	public java.sql.Timestamp getDataaltraben() {
		return dataaltraben;
	}
	public void setDataaltraben(java.sql.Timestamp dataaltraben) {
		this.dataaltraben = dataaltraben;
	}

	public String getCodcomunenasc() {
		return codcomunenasc;
	}
	public void setCodcomunenasc(String codcomunenasc) {
		this.codcomunenasc = codcomunenasc;
	}

	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getCodicefiscale() {
		return codicefiscale;
	}
	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public int getCodconvocazion() {
		return codconvocazion;
	}
	public void setCodconvocazion(int codconvocazion) {
		this.codconvocazion = codconvocazion;
	}

	public int getTipodonazione() {
		return tipodonazione;
	}
	public void setTipodonazione(int tipodonazione) {
		this.tipodonazione = tipodonazione;
	}

	public java.sql.Timestamp getDataconvpren() {
		return dataconvpren;
	}
	public void setDataconvpren(java.sql.Timestamp dataconvpren) {
		this.dataconvpren = dataconvpren;
	}

	public short getFlaggocciabro() {
		return flaggocciabro;
	}
	public void setFlaggocciabro(short flaggocciabro) {
		this.flaggocciabro = flaggocciabro;
	}

	public short getSangueintero() {
		return sangueintero;
	}
	public void setSangueintero(short sangueintero) {
		this.sangueintero = sangueintero;
	}

	public short getPlasmaferesi() {
		return plasmaferesi;
	}
	public void setPlasmaferesi(short plasmaferesi) {
		this.plasmaferesi = plasmaferesi;
	}

	public short getPlasmapltaferes() {
		return plasmapltaferes;
	}
	public void setPlasmapltaferes(short plasmapltaferes) {
		this.plasmapltaferes = plasmapltaferes;
	}

	public short getPltaferesi() {
		return pltaferesi;
	}
	public void setPltaferesi(short pltaferesi) {
		this.pltaferesi = pltaferesi;
	}

	public short getLeucoaferesi() {
		return leucoaferesi;
	}
	public void setLeucoaferesi(short leucoaferesi) {
		this.leucoaferesi = leucoaferesi;
	}

	public short getCellulestamina() {
		return cellulestamina;
	}
	public void setCellulestamina(short cellulestamina) {
		this.cellulestamina = cellulestamina;
	}

	public short getAferesimultipla() {
		return aferesimultipla;
	}
	public void setAferesimultipla(short aferesimultipla) {
		this.aferesimultipla = aferesimultipla;
	}

	public int getSezione() {
		return sezione;
	}
	public void setSezione(int sezione) {
		this.sezione = sezione;
	}

	public short getFlaggocciaarg() {
		return flaggocciaarg;
	}
	public void setFlaggocciaarg(short flaggocciaarg) {
		this.flaggocciaarg = flaggocciaarg;
	}

	public short getFlaggocciaoro() {
		return flaggocciaoro;
	}
	public void setFlaggocciaoro(short flaggocciaoro) {
		this.flaggocciaoro = flaggocciaoro;
	}

	public String getMedicobase() {
		return medicobase;
	}
	public void setMedicobase(String medicobase) {
		this.medicobase = medicobase;
	}

	public java.sql.Timestamp getUltimoesacontr() {
		return ultimoesacontr;
	}
	public void setUltimoesacontr(java.sql.Timestamp ultimoesacontr) {
		this.ultimoesacontr = ultimoesacontr;
	}

	public short getPreflunedi() {
		return preflunedi;
	}
	public void setPreflunedi(short preflunedi) {
		this.preflunedi = preflunedi;
	}

	public short getPrefmartedi() {
		return prefmartedi;
	}
	public void setPrefmartedi(short prefmartedi) {
		this.prefmartedi = prefmartedi;
	}

	public short getPrefmercoledi() {
		return prefmercoledi;
	}
	public void setPrefmercoledi(short prefmercoledi) {
		this.prefmercoledi = prefmercoledi;
	}

	public short getPrefgiovedi() {
		return prefgiovedi;
	}
	public void setPrefgiovedi(short prefgiovedi) {
		this.prefgiovedi = prefgiovedi;
	}

	public int getTipodocconvoc() {
		return tipodocconvoc;
	}
	public void setTipodocconvoc(int tipodocconvoc) {
		this.tipodocconvoc = tipodocconvoc;
	}

	public int getGruppoorganizz() {
		return gruppoorganizz;
	}
	public void setGruppoorganizz(int gruppoorganizz) {
		this.gruppoorganizz = gruppoorganizz;
	}

	public String getNucleofam() {
		return nucleofam;
	}
	public void setNucleofam(String nucleofam) {
		this.nucleofam = nucleofam;
	}

	public java.sql.Timestamp getDataprimaiscr() {
		return dataprimaiscr;
	}
	public void setDataprimaiscr(java.sql.Timestamp dataprimaiscr) {
		this.dataprimaiscr = dataprimaiscr;
	}

	public String getSedeprimaiscr() {
		return sedeprimaiscr;
	}
	public void setSedeprimaiscr(String sedeprimaiscr) {
		this.sedeprimaiscr = sedeprimaiscr;
	}

	public short getPrefvenerdi() {
		return prefvenerdi;
	}
	public void setPrefvenerdi(short prefvenerdi) {
		this.prefvenerdi = prefvenerdi;
	}

	public short getPrefsabato() {
		return prefsabato;
	}
	public void setPrefsabato(short prefsabato) {
		this.prefsabato = prefsabato;
	}

	public short getPrefdomenica() {
		return prefdomenica;
	}
	public void setPrefdomenica(short prefdomenica) {
		this.prefdomenica = prefdomenica;
	}

	public java.sql.Timestamp getUltimdonazione() {
		return ultimdonazione;
	}
	public void setUltimdonazione(java.sql.Timestamp ultimdonazione) {
		this.ultimdonazione = ultimdonazione;
	}

	public int getTotaledonazioni() {
		return totaledonazioni;
	}
	public void setTotaledonazioni(int totaledonazioni) {
		this.totaledonazioni = totaledonazioni;
	}

	public int getCaricasociale() {
		return caricasociale;
	}
	public void setCaricasociale(int caricasociale) {
		this.caricasociale = caricasociale;
	}

	public String getNotaidoneita() {
		return notaidoneita;
	}
	public void setNotaidoneita(String notaidoneita) {
		this.notaidoneita = notaidoneita;
	}

	public String getAltracarica() {
		return altracarica;
	}
	public void setAltracarica(String altracarica) {
		this.altracarica = altracarica;
	}

	public short getCommissione1() {
		return commissione1;
	}
	public void setCommissione1(short commissione1) {
		this.commissione1 = commissione1;
	}

	public short getCommissione2() {
		return commissione2;
	}
	public void setCommissione2(short commissione2) {
		this.commissione2 = commissione2;
	}

	public short getCommissione3() {
		return commissione3;
	}
	public void setCommissione3(short commissione3) {
		this.commissione3 = commissione3;
	}

	public short getCommissione4() {
		return commissione4;
	}
	public void setCommissione4(short commissione4) {
		this.commissione4 = commissione4;
	}

	public short getCommissione5() {
		return commissione5;
	}
	public void setCommissione5(short commissione5) {
		this.commissione5 = commissione5;
	}

	public short getCommissione6() {
		return commissione6;
	}
	public void setCommissione6(short commissione6) {
		this.commissione6 = commissione6;
	}

	public short getCommissione7() {
		return commissione7;
	}
	public void setCommissione7(short commissione7) {
		this.commissione7 = commissione7;
	}

	public short getCommissione8() {
		return commissione8;
	}
	public void setCommissione8(short commissione8) {
		this.commissione8 = commissione8;
	}

	public short getCommissione9() {
		return commissione9;
	}
	public void setCommissione9(short commissione9) {
		this.commissione9 = commissione9;
	}

	public java.sql.Timestamp getCancelladefin() {
		return cancelladefin;
	}
	public void setCancelladefin(java.sql.Timestamp cancelladefin) {
		this.cancelladefin = cancelladefin;
	}

	public int getIntervallosalaf() {
		return intervallosalaf;
	}
	public void setIntervallosalaf(int intervallosalaf) {
		this.intervallosalaf = intervallosalaf;
	}

	public int getIntervalloafeaf() {
		return intervalloafeaf;
	}
	public void setIntervalloafeaf(int intervalloafeaf) {
		this.intervalloafeaf = intervalloafeaf;
	}

	public int getIntervalloafesa() {
		return intervalloafesa;
	}
	public void setIntervalloafesa(int intervalloafesa) {
		this.intervalloafesa = intervalloafesa;
	}

	public String getMotivocancell() {
		return motivocancell;
	}
	public void setMotivocancell(String motivocancell) {
		this.motivocancell = motivocancell;
	}

	public int getDonazionipregr() {
		return donazionipregr;
	}
	public void setDonazionipregr(int donazionipregr) {
		this.donazionipregr = donazionipregr;
	}

	public int getAferesipregres() {
		return aferesipregres;
	}
	public void setAferesipregres(int aferesipregres) {
		this.aferesipregres = aferesipregres;
	}

	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getTrasferitoda() {
		return trasferitoda;
	}
	public void setTrasferitoda(String trasferitoda) {
		this.trasferitoda = trasferitoda;
	}

	public java.sql.Timestamp getTrasfdadata() {
		return trasfdadata;
	}
	public void setTrasfdadata(java.sql.Timestamp trasfdadata) {
		this.trasfdadata = trasfdadata;
	}

	public short getTesserato() {
		return tesserato;
	}
	public void setTesserato(short tesserato) {
		this.tesserato = tesserato;
	}

	public short getFlagallergia() {
		return flagallergia;
	}
	public void setFlagallergia(short flagallergia) {
		this.flagallergia = flagallergia;
	}

	public String getAllergdal() {
		return allergdal;
	}
	public void setAllergdal(String allergdal) {
		this.allergdal = allergdal;
	}

	public String getAllergal() {
		return allergal;
	}
	public void setAllergal(String allergal) {
		this.allergal = allergal;
	}

	public int getStatocivile() {
		return statocivile;
	}
	public void setStatocivile(int statocivile) {
		this.statocivile = statocivile;
	}

	public String getMemodon() {
		return memodon;
	}
	public void setMemodon(String memodon) {
		this.memodon = memodon;
	}

	public short getLeggeprivacy() {
		return leggeprivacy;
	}
	public void setLeggeprivacy(short leggeprivacy) {
		this.leggeprivacy = leggeprivacy;
	}

	public short getNobenem() {
		return nobenem;
	}
	public void setNobenem(short nobenem) {
		this.nobenem = nobenem;
	}

	public String getSolonome() {
		return solonome;
	}
	public void setSolonome(String solonome) {
		this.solonome = solonome;
	}

	public String getSolocognome() {
		return solocognome;
	}
	public void setSolocognome(String solocognome) {
		this.solocognome = solocognome;
	}

	public java.sql.Timestamp getUltimaaferesi() {
		return ultimaaferesi;
	}
	public void setUltimaaferesi(java.sql.Timestamp ultimaaferesi) {
		this.ultimaaferesi = ultimaaferesi;
	}

	public short getConsigplasmaf() {
		return consigplasmaf;
	}
	public void setConsigplasmaf(short consigplasmaf) {
		this.consigplasmaf = consigplasmaf;
	}

	public short getIndicpiastrafer() {
		return indicpiastrafer;
	}
	public void setIndicpiastrafer(short indicpiastrafer) {
		this.indicpiastrafer = indicpiastrafer;
	}

	public short getFlagreperibile() {
		return flagreperibile;
	}
	public void setFlagreperibile(short flagreperibile) {
		this.flagreperibile = flagreperibile;
	}

	public String getReperdal() {
		return reperdal;
	}
	public void setReperdal(String reperdal) {
		this.reperdal = reperdal;
	}

	public String getReperal() {
		return reperal;
	}
	public void setReperal(String reperal) {
		this.reperal = reperal;
	}

	public String getFermoposta() {
		return fermoposta;
	}
	public void setFermoposta(String fermoposta) {
		this.fermoposta = fermoposta;
	}

	public String getCodicereg() {
		return codicereg;
	}
	public void setCodicereg(String codicereg) {
		this.codicereg = codicereg;
	}

	public short getStampadomres() {
		return stampadomres;
	}
	public void setStampadomres(short stampadomres) {
		this.stampadomres = stampadomres;
	}

	public String getDettagliososp() {
		return dettagliososp;
	}
	public void setDettagliososp(String dettagliososp) {
		this.dettagliososp = dettagliososp;
	}

	public String getAllerg2dal() {
		return allerg2dal;
	}
	public void setAllerg2dal(String allerg2dal) {
		this.allerg2dal = allerg2dal;
	}

	public String getAllerg2al() {
		return allerg2al;
	}
	public void setAllerg2al(String allerg2al) {
		this.allerg2al = allerg2al;
	}

	public int getMesidisponib() {
		return mesidisponib;
	}
	public void setMesidisponib(int mesidisponib) {
		this.mesidisponib = mesidisponib;
	}

	public short getLibero0() {
		return libero0;
	}
	public void setLibero0(short libero0) {
		this.libero0 = libero0;
	}

	public short getLibero1() {
		return libero1;
	}
	public void setLibero1(short libero1) {
		this.libero1 = libero1;
	}

	public short getLibero2() {
		return libero2;
	}
	public void setLibero2(short libero2) {
		this.libero2 = libero2;
	}

	public short getLibero3() {
		return libero3;
	}
	public void setLibero3(short libero3) {
		this.libero3 = libero3;
	}

	public short getLibero4() {
		return libero4;
	}
	public void setLibero4(short libero4) {
		this.libero4 = libero4;
	}

	public short getLibero5() {
		return libero5;
	}
	public void setLibero5(short libero5) {
		this.libero5 = libero5;
	}

	public short getLibero6() {
		return libero6;
	}
	public void setLibero6(short libero6) {
		this.libero6 = libero6;
	}

	public short getLibero7() {
		return libero7;
	}
	public void setLibero7(short libero7) {
		this.libero7 = libero7;
	}

	public short getLibero8() {
		return libero8;
	}
	public void setLibero8(short libero8) {
		this.libero8 = libero8;
	}

	public short getLibero9() {
		return libero9;
	}
	public void setLibero9(short libero9) {
		this.libero9 = libero9;
	}

	public short getLibero10() {
		return libero10;
	}
	public void setLibero10(short libero10) {
		this.libero10 = libero10;
	}

	public short getLibero11() {
		return libero11;
	}
	public void setLibero11(short libero11) {
		this.libero11 = libero11;
	}

	public java.sql.Timestamp getUltimourine() {
		return ultimourine;
	}
	public void setUltimourine(java.sql.Timestamp ultimourine) {
		this.ultimourine = ultimourine;
	}

	public java.sql.Timestamp getUltimoecg() {
		return ultimoecg;
	}
	public void setUltimoecg(java.sql.Timestamp ultimoecg) {
		this.ultimoecg = ultimoecg;
	}

	public java.sql.Timestamp getUltimorx() {
		return ultimorx;
	}
	public void setUltimorx(java.sql.Timestamp ultimorx) {
		this.ultimorx = ultimorx;
	}

	public short getEritroplasma() {
		return eritroplasma;
	}
	public void setEritroplasma(short eritroplasma) {
		this.eritroplasma = eritroplasma;
	}

	public short getEritroplt() {
		return eritroplt;
	}
	public void setEritroplt(short eritroplt) {
		this.eritroplt = eritroplt;
	}

	public short getDoppiapltaf() {
		return doppiapltaf;
	}
	public void setDoppiapltaf(short doppiapltaf) {
		this.doppiapltaf = doppiapltaf;
	}

	public int getBloccatoda() {
		return bloccatoda;
	}
	public void setBloccatoda(int bloccatoda) {
		this.bloccatoda = bloccatoda;
	}

	public short getNonpresentato() {
		return nonpresentato;
	}
	public void setNonpresentato(short nonpresentato) {
		this.nonpresentato = nonpresentato;
	}

	public short getTesserastampata() {
		return tesserastampata;
	}
	public void setTesserastampata(short tesserastampata) {
		this.tesserastampata = tesserastampata;
	}

	public java.sql.Timestamp getDatainiziostato() {
		return datainiziostato;
	}
	public void setDatainiziostato(java.sql.Timestamp datainiziostato) {
		this.datainiziostato = datainiziostato;
	}

	public java.sql.Timestamp getDatafinestato() {
		return datafinestato;
	}
	public void setDatafinestato(java.sql.Timestamp datafinestato) {
		this.datafinestato = datafinestato;
	}

	public String getCodicelogido() {
		return codicelogido;
	}
	public void setCodicelogido(String codicelogido) {
		this.codicelogido = codicelogido;
	}

	public short getLettprimadonstampata() {
		return lettprimadonstampata;
	}
	public void setLettprimadonstampata(short lettprimadonstampata) {
		this.lettprimadonstampata = lettprimadonstampata;
	}

	public String getMemocrypto() {
		return memocrypto;
	}
	public void setMemocrypto(String memocrypto) {
		this.memocrypto = memocrypto;
	}

	public short getTrasferito() {
		return trasferito;
	}
	public void setTrasferito(short trasferito) {
		this.trasferito = trasferito;
	}

	public short getImportato() {
		return importato;
	}
	public void setImportato(short importato) {
		this.importato = importato;
	}

	public short getFlagpremagg() {
		return flagpremagg;
	}
	public void setFlagpremagg(short flagpremagg) {
		this.flagpremagg = flagpremagg;
	}

	public java.sql.Timestamp getDatapremagg() {
		return datapremagg;
	}
	public void setDatapremagg(java.sql.Timestamp datapremagg) {
		this.datapremagg = datapremagg;
	}

	public short getFlagsms() {
		return flagsms;
	}
	public void setFlagsms(short flagsms) {
		this.flagsms = flagsms;
	}

	public short getFlagmail() {
		return flagmail;
	}
	public void setFlagmail(short flagmail) {
		this.flagmail = flagmail;
	}

	public short getFlagrubinocoll() {
		return flagrubinocoll;
	}
	public void setFlagrubinocoll(short flagrubinocoll) {
		this.flagrubinocoll = flagrubinocoll;
	}

	public java.sql.Timestamp getDatarubinocoll() {
		return datarubinocoll;
	}
	public void setDatarubinocoll(java.sql.Timestamp datarubinocoll) {
		this.datarubinocoll = datarubinocoll;
	}

	public String getTestolibero1() {
		return testolibero1;
	}
	public void setTestolibero1(String testolibero1) {
		this.testolibero1 = testolibero1;
	}

	public String getTestolibero2() {
		return testolibero2;
	}
	public void setTestolibero2(String testolibero2) {
		this.testolibero2 = testolibero2;
	}

	public short getDonazionipregrdopo2004() {
		return donazionipregrdopo2004;
	}
	public void setDonazionipregrdopo2004(short donazionipregrdopo2004) {
		this.donazionipregrdopo2004 = donazionipregrdopo2004;
	}

	public java.sql.Timestamp getDatanuovaanagrafica() {
		return datanuovaanagrafica;
	}
	public void setDatanuovaanagrafica(java.sql.Timestamp datanuovaanagrafica) {
		this.datanuovaanagrafica = datanuovaanagrafica;
	}
	
	public boolean isIdoneo() {
		return idoneita == 1;
	}

	/**
	 * 
	 * @return status del donatore
	 */
	public DonaStatus getDonaStatus(int dayBefore) {
		
		DonaStatus status = new DonaStatus(codinternodonat, isIdoneo(),
				getMembershipState(), getCognomeenome(), getLuogonascita(),
				getDatadinascita(), getProvdinascita(), getCodicefiscale(),
				getSesso(), dayBefore);
		
		if (sangueintero == 1)
			status.addStatus(new DonaStatusType(DonaType.SANGUE_INTERO, sangueintero == 1, ultimdonazione, prossdonazione));
		if (plasmaferesi == 1)
			status.addStatus(new DonaStatusType(DonaType.PLASMA, plasmaferesi == 1, ultimaaferesi, prossaferesi));
				
		return status;
	}
	
	public String getRefDonatore() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		StringBuffer sb = new StringBuffer();
		if (cognomeenome != null && !cognomeenome.isEmpty())
			sb.append(cognomeenome);
		if (luogonascita != null && !luogonascita.isEmpty())
			sb.append(" nat" + getOA() + " a " + luogonascita);
		if (provdinascita != null && !provdinascita.isEmpty())
			sb.append("(" + provdinascita + ") ");
		if (datadinascita != null) {
			if (luogonascita == null || luogonascita.isEmpty())
				sb.append(" nat" + getOA());
			
			sb.append(" il " + sdf.format(datadinascita) + " ");
		}
		if (codicefiscale != null && !codicefiscale.isEmpty())
			sb.append(" c.f. " + codicefiscale);
		return sb.toString().trim();
	}
	
	private String getOA() {
		return "F".equalsIgnoreCase(sesso) ? "a" : "o";
	}


}
