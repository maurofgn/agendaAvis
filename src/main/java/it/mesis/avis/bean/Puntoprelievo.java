/*
 * Created on 21 giu 2016 ( Time 11:51:51 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package it.mesis.avis.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Puntoprelievo implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer codicepuntoprel;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Size( max = 5 )
    private String cap;

    @Size( max = 40 )
    private String citta;

    @Size( max = 100 )
    private String codicehost;

    @Size( max = 50 )
    private String contatto;

    @NotNull
    private Date domalle;

    @NotNull
    private Date domalle2;

    @NotNull
    private Date domdalle;

    @NotNull
    private Date domdalle2;

    @NotNull
    private Short domenica;

    @Size( max = 255 )
    private String email;

    @Size( max = 20 )
    private String fax;

    @NotNull
    private Short gestisceossass;

    @NotNull
    private Date gioalle;

    @NotNull
    private Date gioalle2;

    @NotNull
    private Date giodalle;

    @NotNull
    private Date giodalle2;

    @NotNull
    private Short giovedi;

    @Size( max = 30 )
    private String indirizzo;

    @Size( max = 6 )
    private String istat;

    @Size( max = 255 )
    private String localita;

    @NotNull
    private Date lunalle;

    @NotNull
    private Date lunalle2;

    @NotNull
    private Date lundalle;

    @NotNull
    private Date lundalle2;

    @NotNull
    private Short lunedi;

    @NotNull
    private Date maralle;

    @NotNull
    private Date maralle2;

    @NotNull
    private Date mardalla2;

    @NotNull
    private Date mardalle;

    @NotNull
    private Short martedi;

    @NotNull
    private Date meralle;

    @NotNull
    private Date meralle2;

    @NotNull
    private Short mercoledi;

    @NotNull
    private Date merdalle;

    @NotNull
    private Date merdalle2;

    @Size( max = 20 )
    private String modem;

    @Size( max = 60 )
    private String nomepuntoprel;

    @Size( max = 255 )
    private String notadastampar;

    @Size( max = 2 )
    private String prov;

    @NotNull
    private Date saballe;

    @NotNull
    private Date saballe2;

    @NotNull
    private Short sabato;

    @NotNull
    private Date sabdalle;

    @NotNull
    private Date sabdalle2;

    @Size( max = 255 )
    private String sitointer;

    @Size( max = 20 )
    private String telefono;

    @NotNull
    private Integer tiporimborso;

    @NotNull
    private Short tipostruttura;

    @NotNull
    private Integer usl;

    @NotNull
    private Date venalle;

    @NotNull
    private Date venalle2;

    @NotNull
    private Date vendalle;

    @NotNull
    private Date vendalle2;

    @NotNull
    private Short venerdi;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCodicepuntoprel( Integer codicepuntoprel ) {
        this.codicepuntoprel = codicepuntoprel ;
    }

    public Integer getCodicepuntoprel() {
        return this.codicepuntoprel;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setCap( String cap ) {
        this.cap = cap;
    }
    public String getCap() {
        return this.cap;
    }

    public void setCitta( String citta ) {
        this.citta = citta;
    }
    public String getCitta() {
        return this.citta;
    }

    public void setCodicehost( String codicehost ) {
        this.codicehost = codicehost;
    }
    public String getCodicehost() {
        return this.codicehost;
    }

    public void setContatto( String contatto ) {
        this.contatto = contatto;
    }
    public String getContatto() {
        return this.contatto;
    }

    public void setDomalle( Date domalle ) {
        this.domalle = domalle;
    }
    public Date getDomalle() {
        return this.domalle;
    }

    public void setDomalle2( Date domalle2 ) {
        this.domalle2 = domalle2;
    }
    public Date getDomalle2() {
        return this.domalle2;
    }

    public void setDomdalle( Date domdalle ) {
        this.domdalle = domdalle;
    }
    public Date getDomdalle() {
        return this.domdalle;
    }

    public void setDomdalle2( Date domdalle2 ) {
        this.domdalle2 = domdalle2;
    }
    public Date getDomdalle2() {
        return this.domdalle2;
    }

    public void setDomenica( Short domenica ) {
        this.domenica = domenica;
    }
    public Short getDomenica() {
        return this.domenica;
    }

    public void setEmail( String email ) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    public void setFax( String fax ) {
        this.fax = fax;
    }
    public String getFax() {
        return this.fax;
    }

    public void setGestisceossass( Short gestisceossass ) {
        this.gestisceossass = gestisceossass;
    }
    public Short getGestisceossass() {
        return this.gestisceossass;
    }

    public void setGioalle( Date gioalle ) {
        this.gioalle = gioalle;
    }
    public Date getGioalle() {
        return this.gioalle;
    }

    public void setGioalle2( Date gioalle2 ) {
        this.gioalle2 = gioalle2;
    }
    public Date getGioalle2() {
        return this.gioalle2;
    }

    public void setGiodalle( Date giodalle ) {
        this.giodalle = giodalle;
    }
    public Date getGiodalle() {
        return this.giodalle;
    }

    public void setGiodalle2( Date giodalle2 ) {
        this.giodalle2 = giodalle2;
    }
    public Date getGiodalle2() {
        return this.giodalle2;
    }

    public void setGiovedi( Short giovedi ) {
        this.giovedi = giovedi;
    }
    public Short getGiovedi() {
        return this.giovedi;
    }

    public void setIndirizzo( String indirizzo ) {
        this.indirizzo = indirizzo;
    }
    public String getIndirizzo() {
        return this.indirizzo;
    }

    public void setIstat( String istat ) {
        this.istat = istat;
    }
    public String getIstat() {
        return this.istat;
    }

    public void setLocalita( String localita ) {
        this.localita = localita;
    }
    public String getLocalita() {
        return this.localita;
    }

    public void setLunalle( Date lunalle ) {
        this.lunalle = lunalle;
    }
    public Date getLunalle() {
        return this.lunalle;
    }

    public void setLunalle2( Date lunalle2 ) {
        this.lunalle2 = lunalle2;
    }
    public Date getLunalle2() {
        return this.lunalle2;
    }

    public void setLundalle( Date lundalle ) {
        this.lundalle = lundalle;
    }
    public Date getLundalle() {
        return this.lundalle;
    }

    public void setLundalle2( Date lundalle2 ) {
        this.lundalle2 = lundalle2;
    }
    public Date getLundalle2() {
        return this.lundalle2;
    }

    public void setLunedi( Short lunedi ) {
        this.lunedi = lunedi;
    }
    public Short getLunedi() {
        return this.lunedi;
    }

    public void setMaralle( Date maralle ) {
        this.maralle = maralle;
    }
    public Date getMaralle() {
        return this.maralle;
    }

    public void setMaralle2( Date maralle2 ) {
        this.maralle2 = maralle2;
    }
    public Date getMaralle2() {
        return this.maralle2;
    }

    public void setMardalla2( Date mardalla2 ) {
        this.mardalla2 = mardalla2;
    }
    public Date getMardalla2() {
        return this.mardalla2;
    }

    public void setMardalle( Date mardalle ) {
        this.mardalle = mardalle;
    }
    public Date getMardalle() {
        return this.mardalle;
    }

    public void setMartedi( Short martedi ) {
        this.martedi = martedi;
    }
    public Short getMartedi() {
        return this.martedi;
    }

    public void setMeralle( Date meralle ) {
        this.meralle = meralle;
    }
    public Date getMeralle() {
        return this.meralle;
    }

    public void setMeralle2( Date meralle2 ) {
        this.meralle2 = meralle2;
    }
    public Date getMeralle2() {
        return this.meralle2;
    }

    public void setMercoledi( Short mercoledi ) {
        this.mercoledi = mercoledi;
    }
    public Short getMercoledi() {
        return this.mercoledi;
    }

    public void setMerdalle( Date merdalle ) {
        this.merdalle = merdalle;
    }
    public Date getMerdalle() {
        return this.merdalle;
    }

    public void setMerdalle2( Date merdalle2 ) {
        this.merdalle2 = merdalle2;
    }
    public Date getMerdalle2() {
        return this.merdalle2;
    }

    public void setModem( String modem ) {
        this.modem = modem;
    }
    public String getModem() {
        return this.modem;
    }

    public void setNomepuntoprel( String nomepuntoprel ) {
        this.nomepuntoprel = nomepuntoprel;
    }
    public String getNomepuntoprel() {
        return this.nomepuntoprel;
    }

    public void setNotadastampar( String notadastampar ) {
        this.notadastampar = notadastampar;
    }
    public String getNotadastampar() {
        return this.notadastampar;
    }

    public void setProv( String prov ) {
        this.prov = prov;
    }
    public String getProv() {
        return this.prov;
    }

    public void setSaballe( Date saballe ) {
        this.saballe = saballe;
    }
    public Date getSaballe() {
        return this.saballe;
    }

    public void setSaballe2( Date saballe2 ) {
        this.saballe2 = saballe2;
    }
    public Date getSaballe2() {
        return this.saballe2;
    }

    public void setSabato( Short sabato ) {
        this.sabato = sabato;
    }
    public Short getSabato() {
        return this.sabato;
    }

    public void setSabdalle( Date sabdalle ) {
        this.sabdalle = sabdalle;
    }
    public Date getSabdalle() {
        return this.sabdalle;
    }

    public void setSabdalle2( Date sabdalle2 ) {
        this.sabdalle2 = sabdalle2;
    }
    public Date getSabdalle2() {
        return this.sabdalle2;
    }

    public void setSitointer( String sitointer ) {
        this.sitointer = sitointer;
    }
    public String getSitointer() {
        return this.sitointer;
    }

    public void setTelefono( String telefono ) {
        this.telefono = telefono;
    }
    public String getTelefono() {
        return this.telefono;
    }

    public void setTiporimborso( Integer tiporimborso ) {
        this.tiporimborso = tiporimborso;
    }
    public Integer getTiporimborso() {
        return this.tiporimborso;
    }

    public void setTipostruttura( Short tipostruttura ) {
        this.tipostruttura = tipostruttura;
    }
    public Short getTipostruttura() {
        return this.tipostruttura;
    }

    public void setUsl( Integer usl ) {
        this.usl = usl;
    }
    public Integer getUsl() {
        return this.usl;
    }

    public void setVenalle( Date venalle ) {
        this.venalle = venalle;
    }
    public Date getVenalle() {
        return this.venalle;
    }

    public void setVenalle2( Date venalle2 ) {
        this.venalle2 = venalle2;
    }
    public Date getVenalle2() {
        return this.venalle2;
    }

    public void setVendalle( Date vendalle ) {
        this.vendalle = vendalle;
    }
    public Date getVendalle() {
        return this.vendalle;
    }

    public void setVendalle2( Date vendalle2 ) {
        this.vendalle2 = vendalle2;
    }
    public Date getVendalle2() {
        return this.vendalle2;
    }

    public void setVenerdi( Short venerdi ) {
        this.venerdi = venerdi;
    }
    public Short getVenerdi() {
        return this.venerdi;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(codicepuntoprel);
        sb.append("|");
        sb.append(cap);
        sb.append("|");
        sb.append(citta);
        sb.append("|");
        sb.append(codicehost);
        sb.append("|");
        sb.append(contatto);
        sb.append("|");
        sb.append(domalle);
        sb.append("|");
        sb.append(domalle2);
        sb.append("|");
        sb.append(domdalle);
        sb.append("|");
        sb.append(domdalle2);
        sb.append("|");
        sb.append(domenica);
        sb.append("|");
        sb.append(email);
        sb.append("|");
        sb.append(fax);
        sb.append("|");
        sb.append(gestisceossass);
        sb.append("|");
        sb.append(gioalle);
        sb.append("|");
        sb.append(gioalle2);
        sb.append("|");
        sb.append(giodalle);
        sb.append("|");
        sb.append(giodalle2);
        sb.append("|");
        sb.append(giovedi);
        sb.append("|");
        sb.append(indirizzo);
        sb.append("|");
        sb.append(istat);
        sb.append("|");
        sb.append(localita);
        sb.append("|");
        sb.append(lunalle);
        sb.append("|");
        sb.append(lunalle2);
        sb.append("|");
        sb.append(lundalle);
        sb.append("|");
        sb.append(lundalle2);
        sb.append("|");
        sb.append(lunedi);
        sb.append("|");
        sb.append(maralle);
        sb.append("|");
        sb.append(maralle2);
        sb.append("|");
        sb.append(mardalla2);
        sb.append("|");
        sb.append(mardalle);
        sb.append("|");
        sb.append(martedi);
        sb.append("|");
        sb.append(meralle);
        sb.append("|");
        sb.append(meralle2);
        sb.append("|");
        sb.append(mercoledi);
        sb.append("|");
        sb.append(merdalle);
        sb.append("|");
        sb.append(merdalle2);
        sb.append("|");
        sb.append(modem);
        sb.append("|");
        sb.append(nomepuntoprel);
        sb.append("|");
        sb.append(notadastampar);
        sb.append("|");
        sb.append(prov);
        sb.append("|");
        sb.append(saballe);
        sb.append("|");
        sb.append(saballe2);
        sb.append("|");
        sb.append(sabato);
        sb.append("|");
        sb.append(sabdalle);
        sb.append("|");
        sb.append(sabdalle2);
        sb.append("|");
        sb.append(sitointer);
        sb.append("|");
        sb.append(telefono);
        sb.append("|");
        sb.append(tiporimborso);
        sb.append("|");
        sb.append(tipostruttura);
        sb.append("|");
        sb.append(usl);
        sb.append("|");
        sb.append(venalle);
        sb.append("|");
        sb.append(venalle2);
        sb.append("|");
        sb.append(vendalle);
        sb.append("|");
        sb.append(vendalle2);
        sb.append("|");
        sb.append(venerdi);
        return sb.toString(); 
    } 


}
