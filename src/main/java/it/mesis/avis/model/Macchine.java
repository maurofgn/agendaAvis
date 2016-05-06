package it.mesis.avis.model;

import it.mesis.util.model.TipoDonaPuntoPrel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "MACCHINE")
public class Macchine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min=0, max=20)
	@Column(name = "NOME")
	private String nome;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="PP")
    private Puntoprelievo puntoprelievo;
	
	@Column(name = "DISPONIBILITA")
	private String disponibilita;

	@Column(name = "NOTAMACCHINA")
	private String notamacchina;

	@ManyToOne(optional = false)
    @JoinColumn(name="TIPODONAZ_ID")
    private Tipodonaz tipoDonazione;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDisponibilita() {
		return disponibilita;
	}
	public void setDisponibilita(String disponibilita) {
		this.disponibilita = disponibilita;
	}

	public String getNotamacchina() {
		return notamacchina;
	}
	public void setNotamacchina(String notamacchina) {
		this.notamacchina = notamacchina;
	}
	
	public Puntoprelievo getPuntoprelievo() {
		return puntoprelievo;
	}
	public void setPuntoprelievo(Puntoprelievo puntoprelievo) {
		this.puntoprelievo = puntoprelievo;
	}
	
	public Tipodonaz getTipoDonazione() {
		return tipoDonazione;
	}
	public void setTipoDonazione(Tipodonaz tipoDonazione) {
		this.tipoDonazione = tipoDonazione;
	}
	
	public TipoDonaPuntoPrel getTipoDonaPuntoPrel() {
		return new TipoDonaPuntoPrel(puntoprelievo.getCodicepuntoprel(), puntoprelievo.getNomepuntoprel(), tipoDonazione.getCodice(), tipoDonazione.getDescrizione(), tipoDonazione.getSigla() );
	}

}
