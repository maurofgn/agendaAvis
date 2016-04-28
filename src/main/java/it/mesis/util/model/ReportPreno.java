package it.mesis.util.model;

import java.io.Serializable;
import java.util.Date;

public class ReportPreno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Date dataOraPreno;
	private String notaPren;
	private String macchina;
	private String nomePuntoPrel;
	private String tipoDona;
	private String sigla;
	private String nome;
	private String luogoNascita;
	private String provNascita;
	private Date dataNascita;
	private String codiceFiscale;
	private String cellulare;
	private String telefono;
	
	public ReportPreno(Date dataOraPreno, String notaPren, String macchina,
			String nomePuntoPrel, String tipoDona, String sigla, String nome,
			String luogoNascita, String provNascita, Date dataNascita,
			String codiceFiscale, String cellulare, String telefono) {
		super();
		this.dataOraPreno = dataOraPreno;
		this.notaPren = notaPren;
		this.macchina = macchina;
		this.nomePuntoPrel = nomePuntoPrel;
		this.tipoDona = tipoDona;
		this.sigla = sigla;
		this.nome = nome;
		this.luogoNascita = luogoNascita;
		this.provNascita = provNascita;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.cellulare = cellulare;
		this.telefono = telefono;
	}
	
	public Date getDataOraPreno() {
		return dataOraPreno;
	}
	public void setDataOraPreno(Date dataOraPreno) {
		this.dataOraPreno = dataOraPreno;
	}
	public String getNotaPren() {
		return notaPren;
	}
	public void setNotaPren(String notaPren) {
		this.notaPren = notaPren;
	}
	public String getMacchina() {
		return macchina;
	}
	public void setMacchina(String macchina) {
		this.macchina = macchina;
	}
	public String getNomePuntoPrel() {
		return nomePuntoPrel;
	}
	public void setNomePuntoPrel(String nomePuntoPrel) {
		this.nomePuntoPrel = nomePuntoPrel;
	}
	public String getTipoDona() {
		return tipoDona;
	}
	public void setTipoDona(String tipoDona) {
		this.tipoDona = tipoDona;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLuogoNascita() {
		return luogoNascita;
	}
	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}
	public String getProvNascita() {
		return provNascita;
	}
	public void setProvNascita(String provNascita) {
		this.provNascita = provNascita;
	}
	public Date getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCellulare() {
		return cellulare;
	}
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
