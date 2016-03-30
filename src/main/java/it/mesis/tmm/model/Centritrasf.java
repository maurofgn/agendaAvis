package it.mesis.tmm.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CENTRITRASF")
public class Centritrasf {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	@Size(min = 1, max = 5)
	@NotEmpty
	@Column(name = "CODICEUNI", nullable = false)
	private String codiceuni;

	@Size(min = 0, max = 25)
	@Column(name = "DESCRIZIONE")
	private String descrizione;

	@Size(min = 1, max = 2)
	@NotEmpty
	@Column(name = "SIGLA", nullable = false)
	private String sigla;

	@Size(min = 0, max = 10)
	@Column(name = "CODICEREPOSITORY")
	private String codicerepository;

	@NotNull
	@Column(name = "EMOTECA", nullable = false)
	private boolean emoteca;

	@NotNull
	@Column(name = "MITTENTELAVSACCHE", nullable = false)
	private boolean mittentelavsacche;

	@NotNull
	@Column(name = "INVIACODRICHHOST", nullable = false)
	private boolean inviacodrichhost;
	
	

	@OneToMany(mappedBy = "primaryKey.centritrasf", cascade = CascadeType.ALL)
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
	

	public byte getId() {
		return id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getCodiceuni() {
		return codiceuni;
	}

	public void setCodiceuni(String codiceuni) {
		this.codiceuni = codiceuni;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getCodicerepository() {
		return codicerepository;
	}

	public void setCodicerepository(String codicerepository) {
		this.codicerepository = codicerepository;
	}

	public boolean getEmoteca() {
		return emoteca;
	}

	public void setEmoteca(boolean emoteca) {
		this.emoteca = emoteca;
	}

	public boolean getMittentelavsacche() {
		return mittentelavsacche;
	}

	public void setMittentelavsacche(boolean mittentelavsacche) {
		this.mittentelavsacche = mittentelavsacche;
	}

	public boolean getInviacodrichhost() {
		return inviacodrichhost;
	}

	public void setInviacodrichhost(boolean inviacodrichhost) {
		this.inviacodrichhost = inviacodrichhost;
	}

}
