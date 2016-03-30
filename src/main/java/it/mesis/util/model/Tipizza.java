package it.mesis.util.model;

public class Tipizza {
	
	String ab0;
	String rh;
	String ccee;
	String du;
	String kell;
	String cellano;
	
	public Tipizza(String ab0, String rh, String ccee, String du,
			String kell, String cellano) {
		super();
		this.ab0 = ab0;
		this.rh = rh;
		this.ccee = ccee;
		this.du = du;
		this.kell = kell;
		this.cellano = cellano;
	}

	public String getAb0() {
		return ab0;
	}

	public void setAb0(String ab0) {
		this.ab0 = ab0;
	}
	
	
//	private String getBoolString(Boolean b) {
//		return b == null ? "**" : (b ? "Pos" : "Neg");
//	}
//
//	public String getRhString() {
//		return getBoolString(rh);
//	}
	
	public String getRh() {
		return rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}
	
	public String getRhCompleto() {
		return ccee == null ? getRh() : ccee.substring(0, 2) + getDuString() + ccee.substring(2);
	}

	public String getCcee() {
		return ccee;
	}

	public void setCcee(String ccee) {
		this.ccee = ccee;
	}
	
	/**
	 * Du -> Rh negativo e Du positivo
	 * d  -> Rh negativo e Du negativo
	 * D  -> Rh Positivo (Du senza specifica perchè non necessario)
	 * 
	 * @return du
	 */
	private String getDuString() {
		return isRhPositive() ? "D" : (isDuPositive() ? "Du" : "d"); 
	}
	
	private boolean isRhPositive() {
		return "pos".equalsIgnoreCase(rh);
	}
	
	
	private boolean isDuPositive() {
		return "pos".equalsIgnoreCase(du);
	}

	public String getDu() {
		return du;
	}

	public void setDu(String du) {
		this.du = du;
	}

	public String getKell() {
		return kell;
	}

	public void setKell(String kell) {
		this.kell = kell;
	}

	public String getCellano() {
		return cellano;
	}

	public void setCellano(String cellano) {
		this.cellano = cellano;
	}

	@Override
	public String toString() {
		return "[" + ab0 + ", " + getRhCompleto() + ", " + getCellano() + "]";
	}
	
}
