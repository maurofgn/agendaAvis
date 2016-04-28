package it.mesis.util.model;

import it.mesis.avis.model.Macchine;

public class TipoDonaPuntoPrel {
	
	private int puntoprelId;
	private String puntoprel;
	private int tipoDonaId;
	private String tipoDonazione;
	private String sigla;

	
//	public TipoDonaPuntoPrel(int puntoprelId, int tipoDonaId) {
//		this(puntoprelId, null, tipoDonaId, null, null) ;
//	}
	
	public TipoDonaPuntoPrel(int puntoprelId, String puntoprel, int tipoDonaId, String tipoDonazione, String sigla) {
		super();
		this.puntoprelId = puntoprelId;
		this.puntoprel = puntoprel;
		this.tipoDonaId = tipoDonaId;
		this.tipoDonazione = tipoDonazione;
		this.sigla =sigla;
	}

	public int getPuntoprelId() {
		return puntoprelId;
	}

	public String getPuntoprel() {
		return puntoprel;
	}

	public int getTipoDonaId() {
		return tipoDonaId;
	}

	public String getTipoDonazione() {
		return tipoDonazione;
	}
	
	public String getSigla() {
		return sigla;
	}

	@Override
	public String toString() {
		return sigla + " " + puntoprel;
	}
	
	public String getValToString() {
		return tipoDonaId + "," + puntoprelId;
	}

	public boolean isSameTipoDonaPuntoPrel(Macchine macchina) {
		if (macchina == null)
			return true;
		return macchina.getTipoDonazione().getCodice() == getTipoDonaId()
				&& macchina.getPuntoprelievo().getCodicepuntoprel() == getPuntoprelId();
	}

}
