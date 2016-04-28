package it.mesis.avis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "UTENTI")
public class Utenti {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Size(min=1, max=20)
	@NotEmpty
	@Column(name = "UTENTE", nullable = false)
	private String utente;

	@Size(min=0, max=60)
	@Column(name = "PASSWORD")
	private String password;

	@Size(min=0, max=30)
	@Column(name = "PERMESSI")
	private String permessi;

	@Size(min=0, max=60)
	@Column(name = "NOME")
	private String nome;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAULTACCESSO")
	private java.sql.Timestamp dataultaccesso;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "DATAULTCAMBIOPWD")
	private java.sql.Timestamp dataultcambiopwd;

	@NotNull
	@Column(name = "DISABILITATO", nullable = false)
	private boolean disabilitato;
	

	public short getId() {
		return id;
	}
	public void setId(short id) {
		this.id = id;
	}

	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPermessi() {
		return permessi;
	}
	public void setPermessi(String permessi) {
		this.permessi = permessi;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public java.sql.Timestamp getDataultaccesso() {
		return dataultaccesso;
	}
	public void setDataultaccesso(java.sql.Timestamp dataultaccesso) {
		this.dataultaccesso = dataultaccesso;
	}

	public java.sql.Timestamp getDataultcambiopwd() {
		return dataultcambiopwd;
	}
	public void setDataultcambiopwd(java.sql.Timestamp dataultcambiopwd) {
		this.dataultcambiopwd = dataultcambiopwd;
	}

	public boolean getDisabilitato() {
		return disabilitato;
	}
	public void setDisabilitato(boolean disabilitato) {
		this.disabilitato = disabilitato;
	}

}
