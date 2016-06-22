package it.mesis.avis.bean.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PUNTOPRELIEVO")
public class PuntoprelievoEntity {

	@Id
	@Column(name = "CODICEPUNTOPREL", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codicepuntoprel;

	@Size(min=0, max=60)
	@Column(name = "NOMEPUNTOPREL")
	private String nomepuntoprel;

	@Size(min=0, max=30)
	@Column(name = "INDIRIZZO")
	private String indirizzo;

	@Size(min=0, max=5)
	@Column(name = "CAP")
	private String cap;

	@Size(min=0, max=40)
	@Column(name = "CITTA")
	private String citta;

	@Size(min=0, max=20)
	@Column(name = "TELEFONO")
	private String telefono;

	@Size(min=0, max=20)
	@Column(name = "FAX")
	private String fax;

	@Size(min=0, max=50)
	@Column(name = "CONTATTO")
	private String contatto;

	@NotNull
	@Column(name = "LUNEDI", nullable = false)
	private short lunedi;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "LUNDALLE", nullable = false)
	private java.sql.Timestamp lundalle;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "LUNALLE", nullable = false)
	private java.sql.Timestamp lunalle;

	@NotNull
	@Column(name = "MARTEDI", nullable = false)
	private short martedi;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "MARDALLE", nullable = false)
	private java.sql.Timestamp mardalle;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "MARALLE", nullable = false)
	private java.sql.Timestamp maralle;

	@NotNull
	@Column(name = "MERCOLEDI", nullable = false)
	private short mercoledi;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "MERDALLE", nullable = false)
	private java.sql.Timestamp merdalle;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "MERALLE", nullable = false)
	private java.sql.Timestamp meralle;

	@NotNull
	@Column(name = "GIOVEDI", nullable = false)
	private short giovedi;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "GIODALLE", nullable = false)
	private java.sql.Timestamp giodalle;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "GIOALLE", nullable = false)
	private java.sql.Timestamp gioalle;

	@NotNull
	@Column(name = "VENERDI", nullable = false)
	private short venerdi;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "VENDALLE", nullable = false)
	private java.sql.Timestamp vendalle;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "VENALLE", nullable = false)
	private java.sql.Timestamp venalle;

	@NotNull
	@Column(name = "SABATO", nullable = false)
	private short sabato;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "SABDALLE", nullable = false)
	private java.sql.Timestamp sabdalle;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "SABALLE", nullable = false)
	private java.sql.Timestamp saballe;

	@NotNull
	@Column(name = "DOMENICA", nullable = false)
	private short domenica;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DOMDALLE", nullable = false)
	private java.sql.Timestamp domdalle;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DOMALLE", nullable = false)
	private java.sql.Timestamp domalle;

	@Size(min=0, max=2)
	@Column(name = "PROV")
	private String prov;

	@NotNull
	@Column(name = "USL", nullable = false)
	private int usl;

	@Size(min=0, max=2147483647)
	@Column(name = "NOTADASTAMPAR")
	private String notadastampar;

	@Size(min=0, max=6)
	@Column(name = "ISTAT")
	private String istat;

	@NotNull
	@Column(name = "TIPORIMBORSO", nullable = false)
	private int tiporimborso;

	@Size(min=0, max=2147483647)
	@Email(message="email non valida")
	@Column(name = "EMAIL")
	private String email;

	@NotNull
	@Column(name = "TIPOSTRUTTURA", nullable = false)
	private short tipostruttura;

	@Size(min=0, max=20)
	@Column(name = "MODEM")
	private String modem;

	@NotNull
	@Column(name = "GESTISCEOSSASS", nullable = false)
	private short gestisceossass;

	@Size(min=0, max=2147483647)
	@Column(name = "LOCALITA")
	private String localita;

	@Size(min=0, max=2147483647)
	@Column(name = "SITOINTER")
	private String sitointer;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "LUNDALLE2", nullable = false)
	private java.sql.Timestamp lundalle2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "LUNALLE2", nullable = false)
	private java.sql.Timestamp lunalle2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "MARDALLA2", nullable = false)
	private java.sql.Timestamp mardalla2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "MARALLE2", nullable = false)
	private java.sql.Timestamp maralle2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "MERDALLE2", nullable = false)
	private java.sql.Timestamp merdalle2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "MERALLE2", nullable = false)
	private java.sql.Timestamp meralle2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "GIODALLE2", nullable = false)
	private java.sql.Timestamp giodalle2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "GIOALLE2", nullable = false)
	private java.sql.Timestamp gioalle2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "VENDALLE2", nullable = false)
	private java.sql.Timestamp vendalle2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "VENALLE2", nullable = false)
	private java.sql.Timestamp venalle2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "SABDALLE2", nullable = false)
	private java.sql.Timestamp sabdalle2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "SABALLE2", nullable = false)
	private java.sql.Timestamp saballe2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DOMDALLE2", nullable = false)
	private java.sql.Timestamp domdalle2;

	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DOMALLE2", nullable = false)
	private java.sql.Timestamp domalle2;

	@Size(min=0, max=100)
	@Column(name = "CODICEHOST")
	private String codicehost;


	public int getCodicepuntoprel() {
		return codicepuntoprel;
	}
	public void setCodicepuntoprel(int codicepuntoprel) {
		this.codicepuntoprel = codicepuntoprel;
	}

	public String getNomepuntoprel() {
		return nomepuntoprel;
	}
	public void setNomepuntoprel(String nomepuntoprel) {
		this.nomepuntoprel = nomepuntoprel;
	}

	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getContatto() {
		return contatto;
	}
	public void setContatto(String contatto) {
		this.contatto = contatto;
	}

	public short getLunedi() {
		return lunedi;
	}
	public void setLunedi(short lunedi) {
		this.lunedi = lunedi;
	}

	public java.sql.Timestamp getLundalle() {
		return lundalle;
	}
	public void setLundalle(java.sql.Timestamp lundalle) {
		this.lundalle = lundalle;
	}

	public java.sql.Timestamp getLunalle() {
		return lunalle;
	}
	public void setLunalle(java.sql.Timestamp lunalle) {
		this.lunalle = lunalle;
	}

	public short getMartedi() {
		return martedi;
	}
	public void setMartedi(short martedi) {
		this.martedi = martedi;
	}

	public java.sql.Timestamp getMardalle() {
		return mardalle;
	}
	public void setMardalle(java.sql.Timestamp mardalle) {
		this.mardalle = mardalle;
	}

	public java.sql.Timestamp getMaralle() {
		return maralle;
	}
	public void setMaralle(java.sql.Timestamp maralle) {
		this.maralle = maralle;
	}

	public short getMercoledi() {
		return mercoledi;
	}
	public void setMercoledi(short mercoledi) {
		this.mercoledi = mercoledi;
	}

	public java.sql.Timestamp getMerdalle() {
		return merdalle;
	}
	public void setMerdalle(java.sql.Timestamp merdalle) {
		this.merdalle = merdalle;
	}

	public java.sql.Timestamp getMeralle() {
		return meralle;
	}
	public void setMeralle(java.sql.Timestamp meralle) {
		this.meralle = meralle;
	}

	public short getGiovedi() {
		return giovedi;
	}
	public void setGiovedi(short giovedi) {
		this.giovedi = giovedi;
	}

	public java.sql.Timestamp getGiodalle() {
		return giodalle;
	}
	public void setGiodalle(java.sql.Timestamp giodalle) {
		this.giodalle = giodalle;
	}

	public java.sql.Timestamp getGioalle() {
		return gioalle;
	}
	public void setGioalle(java.sql.Timestamp gioalle) {
		this.gioalle = gioalle;
	}

	public short getVenerdi() {
		return venerdi;
	}
	public void setVenerdi(short venerdi) {
		this.venerdi = venerdi;
	}

	public java.sql.Timestamp getVendalle() {
		return vendalle;
	}
	public void setVendalle(java.sql.Timestamp vendalle) {
		this.vendalle = vendalle;
	}

	public java.sql.Timestamp getVenalle() {
		return venalle;
	}
	public void setVenalle(java.sql.Timestamp venalle) {
		this.venalle = venalle;
	}

	public short getSabato() {
		return sabato;
	}
	public void setSabato(short sabato) {
		this.sabato = sabato;
	}

	public java.sql.Timestamp getSabdalle() {
		return sabdalle;
	}
	public void setSabdalle(java.sql.Timestamp sabdalle) {
		this.sabdalle = sabdalle;
	}

	public java.sql.Timestamp getSaballe() {
		return saballe;
	}
	public void setSaballe(java.sql.Timestamp saballe) {
		this.saballe = saballe;
	}

	public short getDomenica() {
		return domenica;
	}
	public void setDomenica(short domenica) {
		this.domenica = domenica;
	}

	public java.sql.Timestamp getDomdalle() {
		return domdalle;
	}
	public void setDomdalle(java.sql.Timestamp domdalle) {
		this.domdalle = domdalle;
	}

	public java.sql.Timestamp getDomalle() {
		return domalle;
	}
	public void setDomalle(java.sql.Timestamp domalle) {
		this.domalle = domalle;
	}

	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}

	public int getUsl() {
		return usl;
	}
	public void setUsl(int usl) {
		this.usl = usl;
	}

	public String getNotadastampar() {
		return notadastampar;
	}
	public void setNotadastampar(String notadastampar) {
		this.notadastampar = notadastampar;
	}

	public String getIstat() {
		return istat;
	}
	public void setIstat(String istat) {
		this.istat = istat;
	}

	public int getTiporimborso() {
		return tiporimborso;
	}
	public void setTiporimborso(int tiporimborso) {
		this.tiporimborso = tiporimborso;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public short getTipostruttura() {
		return tipostruttura;
	}
	public void setTipostruttura(short tipostruttura) {
		this.tipostruttura = tipostruttura;
	}

	public String getModem() {
		return modem;
	}
	public void setModem(String modem) {
		this.modem = modem;
	}

	public short getGestisceossass() {
		return gestisceossass;
	}
	public void setGestisceossass(short gestisceossass) {
		this.gestisceossass = gestisceossass;
	}

	public String getLocalita() {
		return localita;
	}
	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getSitointer() {
		return sitointer;
	}
	public void setSitointer(String sitointer) {
		this.sitointer = sitointer;
	}

	public java.sql.Timestamp getLundalle2() {
		return lundalle2;
	}
	public void setLundalle2(java.sql.Timestamp lundalle2) {
		this.lundalle2 = lundalle2;
	}

	public java.sql.Timestamp getLunalle2() {
		return lunalle2;
	}
	public void setLunalle2(java.sql.Timestamp lunalle2) {
		this.lunalle2 = lunalle2;
	}

	public java.sql.Timestamp getMardalla2() {
		return mardalla2;
	}
	public void setMardalla2(java.sql.Timestamp mardalla2) {
		this.mardalla2 = mardalla2;
	}

	public java.sql.Timestamp getMaralle2() {
		return maralle2;
	}
	public void setMaralle2(java.sql.Timestamp maralle2) {
		this.maralle2 = maralle2;
	}

	public java.sql.Timestamp getMerdalle2() {
		return merdalle2;
	}
	public void setMerdalle2(java.sql.Timestamp merdalle2) {
		this.merdalle2 = merdalle2;
	}

	public java.sql.Timestamp getMeralle2() {
		return meralle2;
	}
	public void setMeralle2(java.sql.Timestamp meralle2) {
		this.meralle2 = meralle2;
	}

	public java.sql.Timestamp getGiodalle2() {
		return giodalle2;
	}
	public void setGiodalle2(java.sql.Timestamp giodalle2) {
		this.giodalle2 = giodalle2;
	}

	public java.sql.Timestamp getGioalle2() {
		return gioalle2;
	}
	public void setGioalle2(java.sql.Timestamp gioalle2) {
		this.gioalle2 = gioalle2;
	}

	public java.sql.Timestamp getVendalle2() {
		return vendalle2;
	}
	public void setVendalle2(java.sql.Timestamp vendalle2) {
		this.vendalle2 = vendalle2;
	}

	public java.sql.Timestamp getVenalle2() {
		return venalle2;
	}
	public void setVenalle2(java.sql.Timestamp venalle2) {
		this.venalle2 = venalle2;
	}

	public java.sql.Timestamp getSabdalle2() {
		return sabdalle2;
	}
	public void setSabdalle2(java.sql.Timestamp sabdalle2) {
		this.sabdalle2 = sabdalle2;
	}

	public java.sql.Timestamp getSaballe2() {
		return saballe2;
	}
	public void setSaballe2(java.sql.Timestamp saballe2) {
		this.saballe2 = saballe2;
	}

	public java.sql.Timestamp getDomdalle2() {
		return domdalle2;
	}
	public void setDomdalle2(java.sql.Timestamp domdalle2) {
		this.domdalle2 = domdalle2;
	}

	public java.sql.Timestamp getDomalle2() {
		return domalle2;
	}
	public void setDomalle2(java.sql.Timestamp domalle2) {
		this.domalle2 = domalle2;
	}

	public String getCodicehost() {
		return codicehost;
	}
	public void setCodicehost(String codicehost) {
		this.codicehost = codicehost;
	}

}
