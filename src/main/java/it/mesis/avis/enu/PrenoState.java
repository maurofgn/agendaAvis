package it.mesis.avis.enu;

public enum PrenoState {
	
	LIBERO("Libero"), LIBERO_NON_PRENO("LiberoNonPrenotabile"), OCCUPATO("Occupato"), INDISPONIBILE("Indisponibile"), MIA_PRENO("Mia_Preno");
	
	private String value;

	PrenoState(String value) { 
		this.value = value; 
	}

	public String getValue() {
		return value;
	}; 
	
}
