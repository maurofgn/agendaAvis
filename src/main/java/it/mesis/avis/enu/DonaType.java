package it.mesis.avis.enu;

public enum DonaType {
	SANGUE_INTERO(1), PLASMA(2);
	
	private int index;

	DonaType(int index) { 
		this.index = index; 
	}

	public int getIndex() {
		return index;
	}; 
	
}
