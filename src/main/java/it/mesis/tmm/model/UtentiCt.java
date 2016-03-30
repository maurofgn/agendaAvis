package it.mesis.tmm.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "UTENTI_CT")

@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.utenti",
        joinColumns = @JoinColumn(name = "IDUTENTE")),
    @AssociationOverride(name = "primaryKey.centritrasf",
        joinColumns = @JoinColumn(name = "CT")) })

public class UtentiCt {
	
	// composite-id key
    @EmbeddedId
    private UtentiCtId primaryKey = new UtentiCtId();

    public UtentiCtId getPrimaryKey() {
        return primaryKey;
    }
 
    public void setPrimaryKey(UtentiCtId primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    @Transient
    public Utenti getUtenti() {
        return getPrimaryKey().getUtenti();
    }
 
    public void setUser(Utenti utenti) {
        getPrimaryKey().setUtenti(utenti);
    }
 
    @Transient
    public Centritrasf getCentritrasf() {
        return getPrimaryKey().getCentritrasf();
    }
 
    public void setCentritrasf(Centritrasf centritrasf) {
        getPrimaryKey().setCentritrasf(centritrasf);
    }
    
    
	@NotNull
	@Column(name = "PREDEFINITO", nullable = false)
	private boolean predefinito;
	
	public boolean isPredefinito() {
		return predefinito;
	}

	public void setPredefinito(boolean predefinito) {
		this.predefinito = predefinito;
	}
	
//	@Id
//	@Column(name = "IDUTENTE", nullable = false)
//	private short idutente;
//
//	@Id
//	@Column(name = "CT", nullable = false)
//	private byte ct;
//	
//	public short getIdutente() {
//	return idutente;
//}
//
//public void setIdutente(short idutente) {
//	this.idutente = idutente;
//}
//
//public byte getCt() {
//	return ct;
//}
//
//public void setCt(byte ct) {
//	this.ct = ct;
//}

}
