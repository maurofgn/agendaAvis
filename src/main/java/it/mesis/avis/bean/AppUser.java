/*
 * Created on 21 giu 2016 ( Time 11:51:50 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package it.mesis.avis.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @NotNull
    private Integer attempts;

    @Size( max = 50 )
    private String codinternodonat;


    private Date lastAccess;


    private Date lastChangePsw;

    @Size( max = 100 )
    private String password;

    @NotNull
    @Size( min = 1, max = 30 )
    private String ssoId;

    @NotNull
    @Size( min = 1, max = 30 )
    private String state;


    private Short utentiId;

    @NotNull
    @Size( min = 1, max = 1 )
    private String assoAvis;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Integer id ) {
        this.id = id ;
    }

    public Integer getId() {
        return this.id;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setAttempts( Integer attempts ) {
        this.attempts = attempts;
    }
    public Integer getAttempts() {
        return this.attempts;
    }

    public void setCodinternodonat( String codinternodonat ) {
        this.codinternodonat = codinternodonat;
    }
    public String getCodinternodonat() {
        return this.codinternodonat;
    }

    public void setLastAccess( Date lastAccess ) {
        this.lastAccess = lastAccess;
    }
    public Date getLastAccess() {
        return this.lastAccess;
    }

    public void setLastChangePsw( Date lastChangePsw ) {
        this.lastChangePsw = lastChangePsw;
    }
    public Date getLastChangePsw() {
        return this.lastChangePsw;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    public void setSsoId( String ssoId ) {
        this.ssoId = ssoId;
    }
    public String getSsoId() {
        return this.ssoId;
    }

    public void setState( String state ) {
        this.state = state;
    }
    public String getState() {
        return this.state;
    }

    public void setUtentiId( Short utentiId ) {
        this.utentiId = utentiId;
    }
    public Short getUtentiId() {
        return this.utentiId;
    }

    public void setAssoAvis( String assoAvis ) {
        this.assoAvis = assoAvis;
    }
    public String getAssoAvis() {
        return this.assoAvis;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(attempts);
        sb.append("|");
        sb.append(codinternodonat);
        sb.append("|");
        sb.append(lastAccess);
        sb.append("|");
        sb.append(lastChangePsw);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(ssoId);
        sb.append("|");
        sb.append(state);
        sb.append("|");
        sb.append(utentiId);
        sb.append("|");
        sb.append(assoAvis);
        return sb.toString(); 
    } 


}