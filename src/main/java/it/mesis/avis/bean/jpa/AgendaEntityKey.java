package it.mesis.avis.bean.jpa;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Embeddable
public class AgendaEntityKey implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(optional = false)
    @JoinColumn(name="IDMACCHINA")
    private MacchineEntity macchina;
	
	@NotNull
//	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATAORAPREN", nullable = false)
	private java.util.Date dataorapren;

	public MacchineEntity getMacchina() {
		return macchina;
	}
	public void setMacchina(MacchineEntity macchina) {
		this.macchina = macchina;
	}

	public java.util.Date getDataorapren() {
		return dataorapren;
	}
	public void setDataorapren(java.sql.Timestamp dataorapren) {
		this.dataorapren = dataorapren;
	}
	
	@Override
	public String toString() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		StringBuffer sb = new StringBuffer();
		sb.append(sdf.format(getDataorapren()) + " ");
		sb.append(getMacchina().getTipoDonazione().getDescrizione() + " ");
//		sb.append(getMacchina().getTipoDonazione().getSigla() + " ");
		sb.append(getMacchina().getPuntoprelievo().getNomepuntoprel());

		return sb.toString();
	}
	
}