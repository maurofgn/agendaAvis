package it.mesis.avis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TIPODONAZ")
public class Tipodonaz {

	@Id
	@Column(name = "CODICE", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codice;

	@Size(min=0, max=30)
	@Column(name = "DESCRIZIONE")
	private String descrizione;

	@NotNull
	@Digits(integer=53, fraction=0)
	@Column(name = "RIMBORSOSP", nullable = false)
	private double rimborsosp;

	@Size(min=0, max=2)
	@Column(name = "SIGLA")
	private String sigla;

	@NotNull
	@Digits(integer=53, fraction=0)
	@Column(name = "RIMBORSORMAT", nullable = false)
	private double rimborsormat;

	@NotNull
	@Digits(integer=53, fraction=0)
	@Column(name = "RIMBORSORNOMAT", nullable = false)
	private double rimborsornomat;

	@NotNull
	@Digits(integer=53, fraction=0)
	@Column(name = "RPERS1", nullable = false)
	private double rpers1;

	@NotNull
	@Digits(integer=53, fraction=0)
	@Column(name = "RPERS2", nullable = false)
	private double rpers2;

	@NotNull
	@Digits(integer=53, fraction=0)
	@Column(name = "RPERS3", nullable = false)
	private double rpers3;

	@NotNull
	@Digits(integer=53, fraction=0)
	@Column(name = "RPERS4", nullable = false)
	private double rpers4;

	@NotNull
	@Digits(integer=53, fraction=0)
	@Column(name = "RPERS5", nullable = false)
	private double rpers5;

	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getRimborsosp() {
		return rimborsosp;
	}
	public void setRimborsosp(double rimborsosp) {
		this.rimborsosp = rimborsosp;
	}

	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public double getRimborsormat() {
		return rimborsormat;
	}
	public void setRimborsormat(double rimborsormat) {
		this.rimborsormat = rimborsormat;
	}

	public double getRimborsornomat() {
		return rimborsornomat;
	}
	public void setRimborsornomat(double rimborsornomat) {
		this.rimborsornomat = rimborsornomat;
	}

	public double getRpers1() {
		return rpers1;
	}
	public void setRpers1(double rpers1) {
		this.rpers1 = rpers1;
	}

	public double getRpers2() {
		return rpers2;
	}
	public void setRpers2(double rpers2) {
		this.rpers2 = rpers2;
	}

	public double getRpers3() {
		return rpers3;
	}
	public void setRpers3(double rpers3) {
		this.rpers3 = rpers3;
	}

	public double getRpers4() {
		return rpers4;
	}
	public void setRpers4(double rpers4) {
		this.rpers4 = rpers4;
	}

	public double getRpers5() {
		return rpers5;
	}
	public void setRpers5(double rpers5) {
		this.rpers5 = rpers5;
	}

}
