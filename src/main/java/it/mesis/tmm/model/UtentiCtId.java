package it.mesis.tmm.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UtentiCtId implements Serializable {

	private static final long serialVersionUID = -1624455470150280192L;

	@ManyToOne(cascade = CascadeType.ALL)
	private Utenti utenti;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Centritrasf centritrasf;

	public Utenti getUtenti() {
		return utenti;
	}

	public void setUtenti(Utenti utenti) {
		this.utenti = utenti;
	}

	public Centritrasf getCentritrasf() {
		return centritrasf;
	}

	public void setCentritrasf(Centritrasf centritrasf) {
		this.centritrasf = centritrasf;
	}

}
