package it.mesis.tmm.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.persistence.CascadeType;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.Interval;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "UTENTI")
public class Utenti {
	
	private static final int VALIDITY_DAYS_PASSWORD = 128;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Size(min = 1, max = 128)
	@NotEmpty
	@Column(name = "NOME_UTENTE", nullable = false)
	private String nomeUtente;

	@Size(min = 0, max = 128)
	@Column(name = "NOME_COMPLETO")
	private String nomeCompleto;

	@Size(min = 0, max = 128)
	@Column(name = "DESCRIZIONE")
	private String descrizione;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DATA_ULT_ACCESSO", nullable = false)
	private java.sql.Timestamp dataUltAccesso;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DATA_ULT_CAMBIO_PSW", nullable = false)
	private java.sql.Timestamp dataUltCambioPsw;

	@NotNull
	@Column(name = "CAMBIAMENTO_OBBL_PWD", nullable = false)
	private boolean cambiamentoObblPwd;

	@NotNull
	@Column(name = "NO_CAMBIAMENTO_PWD", nullable = false)
	private boolean noCambiamentoPwd;

	@NotNull
	@Column(name = "NO_SCADENZA_PWD", nullable = false)
	private boolean noScadenzaPwd;

	@NotNull
	@Column(name = "DISABILITATO", nullable = false)
	private boolean disabilitato;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "DATA_CREAZIONE", nullable = false)
	private java.sql.Timestamp dataCreazione;

	@NotNull
	@Column(name = "ACCESSO_UNA_POSTAZIONE", nullable = false)
	private boolean accessoUnaPostazione;

	@Column(name = "TIPOUTENTE")
	private byte tipoutente;

	@Size(min = 0, max = 150)
	@Column(name = "PERMESSI")
	private String permessi;

	@NotNull
	@Column(name = "ADMIN_USER", nullable = false)
	private boolean adminUser;

	@NotNull
	@Column(name = "NONVISIBILE", nullable = false)
	private boolean nonvisibile;

	@NotNull
	@Column(name = "STORICO", nullable = false)
	private boolean storico;

	@Size(min = 0, max = 150)
	@Column(name = "ACCESSOCONFIG")
	private String accessoconfig;

	@Size(min = 0, max = 128)
	@Column(name = "PASSWORD")
	private String password;

	@Size(min = 0, max = 128)
	@Column(name = "UTENTEWINDOWS")
	private String utentewindows;

	@Size(min = 0, max = 128)
	@Column(name = "DOMINIO")
	private String dominio;

	@Size(min = 0, max = 30)
	@Column(name = "NOME")
	private String nome;

	@Size(min = 0, max = 30)
	@Column(name = "COGNOME")
	private String cognome;

	@Size(min = 0, max = 20)
	@Column(name = "CODICEFISCALE")
	private String codicefiscale;

	@Size(min = 0, max = 20)
	@Column(name = "TITOLO")
	private String titolo;

	@NotNull
	@Column(name = "UTENTEAUTO", nullable = false)
	private boolean utenteauto;

	@Size(min = 0, max = 500)
	@Column(name = "SUBJECTDN")
	private String subjectdn;

	@NotNull
	@Column(name = "IDGRUPPO", nullable = false)
	private short idgruppo;

	@NotNull
	@Column(name = "DISABILITATOAUTO", nullable = false)
	private boolean disabilitatoauto;

	@NotNull
	@Column(name = "ACCESSOCRTMM", nullable = false)
	private boolean accessocrtmm;

	@NotNull
	@Column(name = "TIPOACCESSOCRWEB", nullable = false)
	private short tipoaccessocrweb;

	@NotNull
	@Column(name = "NUMEROACCESSIWEB", nullable = false)
	private short numeroaccessiweb;

//	@NotAudited
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "APP_USER_USER_PROFILE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();
	

	
////	@NotAudited
//	@ManyToMany(fetch = FetchType.EAGER)
//	@JoinTable(name = "UTENTI_CT", joinColumns = { @JoinColumn(name = "IDUTENTE") }, inverseJoinColumns = { @JoinColumn(name = "CT") })

	
	
	
	
	
//    @OneToMany(mappedBy = "primaryKey.Utenti", cascade = CascadeType.ALL)
//	private Set<Centritrasf> centritrasfs = new HashSet<Centritrasf>();
//	
//    public Set<Centritrasf> getCentritrasfs() {
//        return centritrasfs;
//    }
// 
//	public void setCentritrasfs(Set<Centritrasf> centritrasfs) {
//		this.centritrasfs = centritrasfs;
//	}
//     
//    public void addUserGroup(Centritrasf centritrasf) {
//        this.centritrasfs.add(centritrasf);
//    }
//    
//    public void addUtentiCentritrasf(Centritrasf centritrasf) {
//        this.centritrasfs.add(centritrasf);
//    }
    
    
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "primaryKey.utenti", cascade = CascadeType.ALL)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryKey.utenti", cascade = CascadeType.ALL)
	private Set<UtentiCt> utentiCts = new HashSet<UtentiCt>();
    
    public Set<UtentiCt> getUtentiCts() {
        return utentiCts;
    }
 
    public void setUtentiCts(Set<UtentiCt> utentiCts) {
        this.utentiCts = utentiCts;
    }
     
    public void addUserGroup(UtentiCt utentiCt) {
        this.utentiCts.add(utentiCt);
    }
    
    public Centritrasf getCentritrasfDefault() {
    	for (UtentiCt utentiCt : utentiCts) {
    		if (utentiCt.isPredefinito()) {
    			return utentiCt.getCentritrasf();
    		}
		}
    	return null;
    }
    
    public Centritrasf getCentritrasfUnique() {
    	return utentiCts.size() == 1 ? utentiCts.iterator().next().getCentritrasf() : null;
    }

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public java.sql.Timestamp getDataUltAccesso() {
		return dataUltAccesso;
	}

	public void setDataUltAccesso(java.sql.Timestamp dataUltAccesso) {
		this.dataUltAccesso = dataUltAccesso;
	}

	public java.sql.Timestamp getDataUltCambioPsw() {
		return dataUltCambioPsw;
	}

	public void setDataUltCambioPsw(java.sql.Timestamp dataUltCambioPsw) {
		this.dataUltCambioPsw = dataUltCambioPsw;
	}

	public boolean getCambiamentoObblPwd() {
		return cambiamentoObblPwd;
	}

	public void setCambiamentoObblPwd(boolean cambiamentoObblPwd) {
		this.cambiamentoObblPwd = cambiamentoObblPwd;
	}

	public boolean getNoCambiamentoPwd() {
		return noCambiamentoPwd;
	}

	public void setNoCambiamentoPwd(boolean noCambiamentoPwd) {
		this.noCambiamentoPwd = noCambiamentoPwd;
	}

	public boolean getNoScadenzaPwd() {
		return noScadenzaPwd;
	}

	public void setNoScadenzaPwd(boolean noScadenzaPwd) {
		this.noScadenzaPwd = noScadenzaPwd;
	}

	public boolean getDisabilitato() {
		return disabilitato;
	}

	public void setDisabilitato(boolean disabilitato) {
		this.disabilitato = disabilitato;
	}

	public java.sql.Timestamp getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(java.sql.Timestamp dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public boolean getAccessoUnaPostazione() {
		return accessoUnaPostazione;
	}

	public void setAccessoUnaPostazione(boolean accessoUnaPostazione) {
		this.accessoUnaPostazione = accessoUnaPostazione;
	}

	public byte getTipoutente() {
		return tipoutente;
	}

	public void setTipoutente(byte tipoutente) {
		this.tipoutente = tipoutente;
	}

	public String getPermessi() {
		return permessi;
	}

	public void setPermessi(String permessi) {
		this.permessi = permessi;
	}

	public boolean getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(boolean adminUser) {
		this.adminUser = adminUser;
	}

	public boolean getNonvisibile() {
		return nonvisibile;
	}

	public void setNonvisibile(boolean nonvisibile) {
		this.nonvisibile = nonvisibile;
	}

	public boolean getStorico() {
		return storico;
	}

	public void setStorico(boolean storico) {
		this.storico = storico;
	}

	public String getAccessoconfig() {
		return accessoconfig;
	}

	public void setAccessoconfig(String accessoconfig) {
		this.accessoconfig = accessoconfig;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUtentewindows() {
		return utentewindows;
	}

	public void setUtentewindows(String utentewindows) {
		this.utentewindows = utentewindows;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodicefiscale() {
		return codicefiscale;
	}

	public void setCodicefiscale(String codicefiscale) {
		this.codicefiscale = codicefiscale;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public boolean getUtenteauto() {
		return utenteauto;
	}

	public void setUtenteauto(boolean utenteauto) {
		this.utenteauto = utenteauto;
	}

	public String getSubjectdn() {
		return subjectdn;
	}

	public void setSubjectdn(String subjectdn) {
		this.subjectdn = subjectdn;
	}

	public short getIdgruppo() {
		return idgruppo;
	}

	public void setIdgruppo(short idgruppo) {
		this.idgruppo = idgruppo;
	}

	public boolean getDisabilitatoauto() {
		return disabilitatoauto;
	}

	public void setDisabilitatoauto(boolean disabilitatoauto) {
		this.disabilitatoauto = disabilitatoauto;
	}

	public boolean getAccessocrtmm() {
		return accessocrtmm;
	}

	public void setAccessocrtmm(boolean accessocrtmm) {
		this.accessocrtmm = accessocrtmm;
	}

	public short getTipoaccessocrweb() {
		return tipoaccessocrweb;
	}

	public void setTipoaccessocrweb(short tipoaccessocrweb) {
		this.tipoaccessocrweb = tipoaccessocrweb;
	}

	public short getNumeroaccessiweb() {
		return numeroaccessiweb;
	}

	public void setNumeroaccessiweb(short numeroaccessiweb) {
		this.numeroaccessiweb = numeroaccessiweb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nomeUtente == null) ? 0 : nomeUtente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utenti other = (Utenti) obj;
		if (id != other.id)
			return false;
		if (nomeUtente == null) {
			if (other.nomeUtente != null)
				return false;
		} else if (!nomeUtente.equals(other.nomeUtente))
			return false;
		return true;
	}
	
	public boolean isEnabled() {
		return !getDisabilitato() && !getDisabilitatoauto();			//enabled
	}
	
	public int daysPasswordExpiry() {
		Interval interval = new Interval(getDataUltCambioPsw().getTime(), new Date().getTime());
		return interval.toDuration().toStandardDays().getDays();
//		System.out.println("delta gg:" + giorni);
//		return TimeUtil.getDeltaDay(getDataUltCambioPsw(), new Date());
	}
	
	public boolean pswNotExpired() {
		return daysPasswordExpiry() <= VALIDITY_DAYS_PASSWORD;
	}
	
}
