package it.mesis.avis.enu;

public enum MembershipState {
	
	ATTESA_IDONIETA(1), ASPIRANTE(2), PRIMA_DONAZIONE(3), DONATORE(4), 
	EX_DONATORE(5), COLLABORATORE(6), AFFILIATO_ONORARIO(7), 
	AFFILIATO_SOSTENITORE(8), EX_SOCIO(9), ALTRO(10);
	
	private int index;

	MembershipState(int index) { 
		this.index = index; 
	}

	public int getIndex() {
		return index;
	}; 
	
	
	public static MembershipState getMembershipState(int id) {
		
		switch (id) {
		case 1: return ATTESA_IDONIETA;
		case 2: return ASPIRANTE;
		case 3: return PRIMA_DONAZIONE;
		case 4: return DONATORE;
		case 5: return EX_DONATORE;
		case 6: return COLLABORATORE;
		case 7: return AFFILIATO_ONORARIO;
		case 8: return AFFILIATO_SOSTENITORE;
		case 9: return EX_SOCIO;
		default: return ALTRO;
		}
		
	}


}
