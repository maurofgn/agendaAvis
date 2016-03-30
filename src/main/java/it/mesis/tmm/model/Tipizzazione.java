package it.mesis.tmm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TIPIZZAZIONE")
public class Tipizzazione {

	@Id
	private int idanagrafica;

	@NotNull
	@Column(name = "AB0", nullable = false)
//	@OneToOne(cascade=CascadeType.ALL, mappedBy = "anagrafica")	//, fetch=FetchType.EAGER
	private byte ab0;

	@NotNull
	@Column(name = "RH", nullable = false)
	private byte rh;

	@NotNull
	@Column(name = "CCEE", nullable = false)
	private byte ccee;

	@NotNull
	@Column(name = "DU", nullable = false)
	private byte du;

	@NotNull
	@Column(name = "KELL", nullable = false)
	private byte kell;

	@NotNull
	@Column(name = "CELLANO", nullable = false)
	private byte cellano;

	@NotNull
	@Column(name = "KP", nullable = false)
	private byte kp;

	@NotNull
	@Column(name = "JS", nullable = false)
	private byte js;

	@NotNull
	@Column(name = "MNSS", nullable = false)
	private byte mnss;

	@NotNull
	@Column(name = "P", nullable = false)
	private byte p;

	@NotNull
	@Column(name = "LEWIS", nullable = false)
	private byte lewis;

	@NotNull
	@Column(name = "LUTHERAN", nullable = false)
	private byte lutheran;

	@NotNull
	@Column(name = "DUFFY", nullable = false)
	private byte duffy;

	@NotNull
	@Column(name = "KIDD", nullable = false)
	private byte kidd;

	@NotNull
	@Column(name = "XG", nullable = false)
	private byte xg;

	@NotNull
	@Column(name = "ABSIRREGOLARI", nullable = false)
	private byte absirregolari;

	@Size(min=0, max=50)
	@Column(name = "TIPOABS")
	private String tipoabs;

	@NotNull
	@Column(name = "ZEROPERICOLOSO", nullable = false)
	private byte zeropericoloso;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLAA1")
	private String fenHlaa1;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLAA2")
	private String fenHlaa2;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLAB1")
	private String fenHlab1;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLAB2")
	private String fenHlab2;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLAC1")
	private String fenHlac1;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLAC2")
	private String fenHlac2;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLADR1")
	private String fenHladr1;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLADR2")
	private String fenHladr2;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLADP1")
	private String fenHladp1;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLADP2")
	private String fenHladp2;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLADQ1")
	private String fenHladq1;

	@Size(min=0, max=14)
	@Column(name = "FEN_HLADQ2")
	private String fenHladq2;

	@NotNull
	@Column(name = "CW", nullable = false)
	private byte cw;

	@NotNull
	@Column(name = "DIEGO", nullable = false)
	private byte diego;

	@NotNull
	@Column(name = "ANTI_A1", nullable = false)
	private byte antiA1;

	@NotNull
	@Column(name = "DUFFY_A", nullable = false)
	private byte duffyA;

	@NotNull
	@Column(name = "DUFFY_B", nullable = false)
	private byte duffyB;

	@NotNull
	@Column(name = "KIDD_A", nullable = false)
	private byte kiddA;

	@NotNull
	@Column(name = "KIDD_B", nullable = false)
	private byte kiddB;

	@NotNull
	@Column(name = "KP_A", nullable = false)
	private byte kpA;

	@NotNull
	@Column(name = "KP_B", nullable = false)
	private byte kpB;

	@NotNull
	@Column(name = "JS_A", nullable = false)
	private byte jsA;

	@NotNull
	@Column(name = "JS_B", nullable = false)
	private byte jsB;

	@NotNull
	@Column(name = "M", nullable = false)
	private byte m;

	@NotNull
	@Column(name = "N", nullable = false)
	private byte n;

	@NotNull
	@Column(name = "S_GRANDE", nullable = false)
	private byte sGrande;

	@NotNull
	@Column(name = "S_PICCOLA", nullable = false)
	private byte sPiccola;

	@NotNull
	@Column(name = "LEWIS_A", nullable = false)
	private byte lewisA;

	@NotNull
	@Column(name = "LEWIS_B", nullable = false)
	private byte lewisB;

	@NotNull
	@Column(name = "LUTHERAN_A", nullable = false)
	private byte lutheranA;

	@NotNull
	@Column(name = "LUTHERAN_B", nullable = false)
	private byte lutheranB;

	@NotNull
	@Column(name = "DIEGO_A", nullable = false)
	private byte diegoA;

	@NotNull
	@Column(name = "DIEGO_B", nullable = false)
	private byte diegoB;
	
	@OneToOne
//	@JoinColumn(name = "idanagrafica")
	@PrimaryKeyJoinColumn
	private Anagrafica anagrafica;


	public int getIdanagrafica() {
		return idanagrafica;
	}
	public void setIdanagrafica(int idanagrafica) {
		this.idanagrafica = idanagrafica;
	}

	public byte getAb0() {
		return ab0;
	}
	public void setAb0(byte ab0) {
		this.ab0 = ab0;
	}

	public byte getRh() {
		return rh;
	}
	public void setRh(byte rh) {
		this.rh = rh;
	}

	public byte getCcee() {
		return ccee;
	}
	public void setCcee(byte ccee) {
		this.ccee = ccee;
	}

	public byte getDu() {
		return du;
	}
	public void setDu(byte du) {
		this.du = du;
	}

	public byte getKell() {
		return kell;
	}
	public void setKell(byte kell) {
		this.kell = kell;
	}

	public byte getCellano() {
		return cellano;
	}
	public void setCellano(byte cellano) {
		this.cellano = cellano;
	}

	public byte getKp() {
		return kp;
	}
	public void setKp(byte kp) {
		this.kp = kp;
	}

	public byte getJs() {
		return js;
	}
	public void setJs(byte js) {
		this.js = js;
	}

	public byte getMnss() {
		return mnss;
	}
	public void setMnss(byte mnss) {
		this.mnss = mnss;
	}

	public byte getP() {
		return p;
	}
	public void setP(byte p) {
		this.p = p;
	}

	public byte getLewis() {
		return lewis;
	}
	public void setLewis(byte lewis) {
		this.lewis = lewis;
	}

	public byte getLutheran() {
		return lutheran;
	}
	public void setLutheran(byte lutheran) {
		this.lutheran = lutheran;
	}

	public byte getDuffy() {
		return duffy;
	}
	public void setDuffy(byte duffy) {
		this.duffy = duffy;
	}

	public byte getKidd() {
		return kidd;
	}
	public void setKidd(byte kidd) {
		this.kidd = kidd;
	}

	public byte getXg() {
		return xg;
	}
	public void setXg(byte xg) {
		this.xg = xg;
	}

	public byte getAbsirregolari() {
		return absirregolari;
	}
	public void setAbsirregolari(byte absirregolari) {
		this.absirregolari = absirregolari;
	}

	public String getTipoabs() {
		return tipoabs;
	}
	public void setTipoabs(String tipoabs) {
		this.tipoabs = tipoabs;
	}

	public byte getZeropericoloso() {
		return zeropericoloso;
	}
	public void setZeropericoloso(byte zeropericoloso) {
		this.zeropericoloso = zeropericoloso;
	}

	public String getFenHlaa1() {
		return fenHlaa1;
	}
	public void setFenHlaa1(String fenHlaa1) {
		this.fenHlaa1 = fenHlaa1;
	}

	public String getFenHlaa2() {
		return fenHlaa2;
	}
	public void setFenHlaa2(String fenHlaa2) {
		this.fenHlaa2 = fenHlaa2;
	}

	public String getFenHlab1() {
		return fenHlab1;
	}
	public void setFenHlab1(String fenHlab1) {
		this.fenHlab1 = fenHlab1;
	}

	public String getFenHlab2() {
		return fenHlab2;
	}
	public void setFenHlab2(String fenHlab2) {
		this.fenHlab2 = fenHlab2;
	}

	public String getFenHlac1() {
		return fenHlac1;
	}
	public void setFenHlac1(String fenHlac1) {
		this.fenHlac1 = fenHlac1;
	}

	public String getFenHlac2() {
		return fenHlac2;
	}
	public void setFenHlac2(String fenHlac2) {
		this.fenHlac2 = fenHlac2;
	}

	public String getFenHladr1() {
		return fenHladr1;
	}
	public void setFenHladr1(String fenHladr1) {
		this.fenHladr1 = fenHladr1;
	}

	public String getFenHladr2() {
		return fenHladr2;
	}
	public void setFenHladr2(String fenHladr2) {
		this.fenHladr2 = fenHladr2;
	}

	public String getFenHladp1() {
		return fenHladp1;
	}
	public void setFenHladp1(String fenHladp1) {
		this.fenHladp1 = fenHladp1;
	}

	public String getFenHladp2() {
		return fenHladp2;
	}
	public void setFenHladp2(String fenHladp2) {
		this.fenHladp2 = fenHladp2;
	}

	public String getFenHladq1() {
		return fenHladq1;
	}
	public void setFenHladq1(String fenHladq1) {
		this.fenHladq1 = fenHladq1;
	}

	public String getFenHladq2() {
		return fenHladq2;
	}
	public void setFenHladq2(String fenHladq2) {
		this.fenHladq2 = fenHladq2;
	}

	public byte getCw() {
		return cw;
	}
	public void setCw(byte cw) {
		this.cw = cw;
	}

	public byte getDiego() {
		return diego;
	}
	public void setDiego(byte diego) {
		this.diego = diego;
	}

	public byte getAntiA1() {
		return antiA1;
	}
	public void setAntiA1(byte antiA1) {
		this.antiA1 = antiA1;
	}

	public byte getDuffyA() {
		return duffyA;
	}
	public void setDuffyA(byte duffyA) {
		this.duffyA = duffyA;
	}

	public byte getDuffyB() {
		return duffyB;
	}
	public void setDuffyB(byte duffyB) {
		this.duffyB = duffyB;
	}

	public byte getKiddA() {
		return kiddA;
	}
	public void setKiddA(byte kiddA) {
		this.kiddA = kiddA;
	}

	public byte getKiddB() {
		return kiddB;
	}
	public void setKiddB(byte kiddB) {
		this.kiddB = kiddB;
	}

	public byte getKpA() {
		return kpA;
	}
	public void setKpA(byte kpA) {
		this.kpA = kpA;
	}

	public byte getKpB() {
		return kpB;
	}
	public void setKpB(byte kpB) {
		this.kpB = kpB;
	}

	public byte getJsA() {
		return jsA;
	}
	public void setJsA(byte jsA) {
		this.jsA = jsA;
	}

	public byte getJsB() {
		return jsB;
	}
	public void setJsB(byte jsB) {
		this.jsB = jsB;
	}

	public byte getM() {
		return m;
	}
	public void setM(byte m) {
		this.m = m;
	}

	public byte getN() {
		return n;
	}
	public void setN(byte n) {
		this.n = n;
	}

	public byte getSGrande() {
		return sGrande;
	}
	public void setSGrande(byte sGrande) {
		this.sGrande = sGrande;
	}

	public byte getSPiccola() {
		return sPiccola;
	}
	public void setSPiccola(byte sPiccola) {
		this.sPiccola = sPiccola;
	}

	public byte getLewisA() {
		return lewisA;
	}
	public void setLewisA(byte lewisA) {
		this.lewisA = lewisA;
	}

	public byte getLewisB() {
		return lewisB;
	}
	public void setLewisB(byte lewisB) {
		this.lewisB = lewisB;
	}

	public byte getLutheranA() {
		return lutheranA;
	}
	public void setLutheranA(byte lutheranA) {
		this.lutheranA = lutheranA;
	}

	public byte getLutheranB() {
		return lutheranB;
	}
	public void setLutheranB(byte lutheranB) {
		this.lutheranB = lutheranB;
	}

	public byte getDiegoA() {
		return diegoA;
	}
	public void setDiegoA(byte diegoA) {
		this.diegoA = diegoA;
	}

	public byte getDiegoB() {
		return diegoB;
	}
	public void setDiegoB(byte diegoB) {
		this.diegoB = diegoB;
	}

}
