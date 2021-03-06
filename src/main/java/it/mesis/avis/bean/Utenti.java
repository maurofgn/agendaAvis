/*
 * Created on 21 giu 2016 ( Time 11:51:51 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package it.mesis.avis.bean;

import java.io.Serializable;

import javax.validation.constraints.*;

import java.util.Date;

public class Utenti implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Short id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    

    private Date dataultaccesso;


    private Date dataultcambiopwd;

    @NotNull
    private Boolean disabilitato;

    @Size( max = 60 )
    private String nome;

    @Size( max = 60 )
    private String password;

    @Size( max = 30 )
    private String permessi;

    @NotNull
    @Size( min = 1, max = 20 )
    private String utente;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setId( Short id ) {
        this.id = id ;
    }

    public Short getId() {
        return this.id;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setDataultaccesso( Date dataultaccesso ) {
        this.dataultaccesso = dataultaccesso;
    }
    public Date getDataultaccesso() {
        return this.dataultaccesso;
    }

    public void setDataultcambiopwd( Date dataultcambiopwd ) {
        this.dataultcambiopwd = dataultcambiopwd;
    }
    public Date getDataultcambiopwd() {
        return this.dataultcambiopwd;
    }

    public void setDisabilitato( Boolean disabilitato ) {
        this.disabilitato = disabilitato;
    }
    public Boolean getDisabilitato() {
        return this.disabilitato;
    }

    public void setNome( String nome ) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    public void setPermessi( String permessi ) {
        this.permessi = permessi;
    }
    public String getPermessi() {
        return this.permessi;
    }

    public void setUtente( String utente ) {
        this.utente = utente;
    }
    public String getUtente() {
        return this.utente;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(dataultaccesso);
        sb.append("|");
        sb.append(dataultcambiopwd);
        sb.append("|");
        sb.append(disabilitato);
        sb.append("|");
        sb.append(nome);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(permessi);
        sb.append("|");
        sb.append(utente);
        return sb.toString(); 
    } 


}
