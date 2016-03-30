package it.mesis.tmm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//import org.hibernate.annotations.Type;
//import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "COMUNICF")
public class Comunicf {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Size(min = 0, max = 80)
	@Column(name = "DESCRIZIONE")
	private String descrizione;

	@Size(min = 0, max = 4)
	@Column(name = "PROV")
	private String prov;

	@Size(min = 0, max = 4)
	@Column(name = "CODFIS")
	private String codfis;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	// @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "DATAMOD")
	private Date datamod;

	@Size(min = 0, max = 20)
	@Column(name = "REGIONE")
	private String regione;

	@NotNull
	@Column(name = "NONVISIBILE", nullable = false)
	private boolean nonvisibile;

	@NotNull
	@Column(name = "POSIZIONE", nullable = false)
	private short posizione;

	@Size(min = 0, max = 6)
	@Column(name = "CODISTAT")
	private String codistat;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	// @Type(type="org.jadira.usertype.date.joda.PersistentLocalDate")
	@Column(name = "DATAAGGIORNAMENTO")
	private Date dataaggiornamento;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	// @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "DATAINIZIOVALIDITA")
	private Date datainiziovalidita;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	// @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@Column(name = "DATAFINEVALIDITA")
	private Date datafinevalidita;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getCodfis() {
		return codfis;
	}

	public void setCodfis(String codfis) {
		this.codfis = codfis;
	}

	public Date getDatamod() {
		return datamod;
	}

	public void setDatamod(Date datamod) {
		this.datamod = datamod;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public boolean getNonvisibile() {
		return nonvisibile;
	}

	public void setNonvisibile(boolean nonvisibile) {
		this.nonvisibile = nonvisibile;
	}

	public short getPosizione() {
		return posizione;
	}

	public void setPosizione(short posizione) {
		this.posizione = posizione;
	}

	public String getCodistat() {
		return codistat;
	}

	public void setCodistat(String codistat) {
		this.codistat = codistat;
	}

	public Date getDataaggiornamento() {
		return dataaggiornamento;
	}

	public void setDataaggiornamento(Date dataaggiornamento) {
		this.dataaggiornamento = dataaggiornamento;
	}

	public Date getDatainiziovalidita() {
		return datainiziovalidita;
	}

	public void setDatainiziovalidita(Date datainiziovalidita) {
		this.datainiziovalidita = datainiziovalidita;
	}

	public Date getDatafinevalidita() {
		return datafinevalidita;
	}

	public void setDatafinevalidita(Date datafinevalidita) {
		this.datafinevalidita = datafinevalidita;
	}

	public String getCittaProv() {
		return descrizione
				+ (prov != null && !prov.isEmpty() && prov.length() > 1 ? " ("
						+ prov + ")" : "");
	}

	public static String cittaProv(String descrizione, String prov) {
		return descrizione == null ? "" : descrizione
				+ (prov != null && !prov.isEmpty() && prov.length() > 1 ? " ("
						+ prov + ")" : "");
	}

}

//
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.Table;
// import javax.validation.constraints.NotNull;
// import javax.validation.constraints.Size;
//
// import org.hibernate.annotations.Type;
// import org.joda.time.LocalDate;
// import org.springframework.format.annotation.DateTimeFormat;
//
// @Entity
// @Table(name = "COMUNICF")
// public class Comunicf {
//
// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private int id;
//
// @Size(min = 0, max = 80)
// @Column(name = "DESCRIZIONE")
// private String descrizione;
//
// @Size(min = 0, max = 4)
// @Column(name = "PROV")
// private String prov;
//
// @Size(min = 0, max = 4)
// @Column(name = "CODFIS")
// private String codfis;
//
// @DateTimeFormat(pattern = "dd/MM/yyyy")
// @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
// @Column(name = "DATAMOD")
// private LocalDate datamod;
//
// @Size(min = 0, max = 20)
// @Column(name = "REGIONE")
// private String regione;
//
// @NotNull
// @Column(name = "NONVISIBILE", nullable = false)
// private int nonvisibile;
//
// @NotNull
// @Column(name = "POSIZIONE", nullable = false)
// private int posizione;
//
// @Size(min = 0, max = 6)
// @Column(name = "CODISTAT")
// private String codistat;
//
// @DateTimeFormat(pattern = "dd/MM/yyyy")
// @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
// @Column(name = "DATAAGGIORNAMENTO")
// private LocalDate dataaggiornamento;
//
// @DateTimeFormat(pattern = "dd/MM/yyyy")
// @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
// @Column(name = "DATAINIZIOVALIDITA")
// private LocalDate datainiziovalidita;
//
// @DateTimeFormat(pattern = "dd/MM/yyyy")
// @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
// @Column(name = "DATAFINEVALIDITA")
// private LocalDate datafinevalidita;
//
// public int getId() {
// return id;
// }
//
// public void setId(int id) {
// this.id = id;
// }
//
// public String getDescrizione() {
// return descrizione;
// }
//
// public void setDescrizione(String descrizione) {
// this.descrizione = descrizione;
// }
//
// public String getProv() {
// return prov;
// }
//
// public void setProv(String prov) {
// this.prov = prov;
// }
//
// public String getCodfis() {
// return codfis;
// }
//
// public void setCodfis(String codfis) {
// this.codfis = codfis;
// }
//
// public LocalDate getDatamod() {
// return datamod;
// }
//
// public void setDatamod(LocalDate datamod) {
// this.datamod = datamod;
// }
//
// public String getRegione() {
// return regione;
// }
//
// public void setRegione(String regione) {
// this.regione = regione;
// }
//
// public int getNonvisibile() {
// return nonvisibile;
// }
//
// public void setNonvisibile(int nonvisibile) {
// this.nonvisibile = nonvisibile;
// }
//
// public int getPosizione() {
// return posizione;
// }
//
// public void setPosizione(int posizione) {
// this.posizione = posizione;
// }
//
// public String getCodistat() {
// return codistat;
// }
//
// public void setCodistat(String codistat) {
// this.codistat = codistat;
// }
//
// public LocalDate getDataaggiornamento() {
// return dataaggiornamento;
// }
//
// public void setDataaggiornamento(LocalDate dataaggiornamento) {
// this.dataaggiornamento = dataaggiornamento;
// }
//
// public LocalDate getDatainiziovalidita() {
// return datainiziovalidita;
// }
//
// public void setDatainiziovalidita(LocalDate datainiziovalidita) {
// this.datainiziovalidita = datainiziovalidita;
// }
//
// public LocalDate getDatafinevalidita() {
// return datafinevalidita;
// }
//
// public void setDatafinevalidita(LocalDate datafinevalidita) {
// this.datafinevalidita = datafinevalidita;
// }
//
// public String getCittaProv() {
// return descrizione + (prov != null && !prov.isEmpty() && prov.length() > 1 ?
// " (" + prov + ")" : "");
// }
//
// public static String cittaProv(String descrizione, String prov) {
// return descrizione == null ? "" : descrizione + (prov != null &&
// !prov.isEmpty() && prov.length() > 1 ? " (" + prov + ")" : "");
// }
//
// }
