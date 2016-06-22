/*
 * Created on 21 giu 2016 ( Time 11:51:51 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package it.mesis.avis.bean;

import java.io.Serializable;

import javax.validation.constraints.*;


public class Tipodonaz implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    @NotNull
    private Integer codice;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    @Size( max = 30 )
    private String descrizione;

    @NotNull
    private Double rimborsormat;

    @NotNull
    private Double rimborsornomat;

    @NotNull
    private Double rimborsosp;

    @NotNull
    private Double rpers1;

    @NotNull
    private Double rpers2;

    @NotNull
    private Double rpers3;

    @NotNull
    private Double rpers4;

    @NotNull
    private Double rpers5;

    @Size( max = 2 )
    private String sigla;



    //----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------
    public void setCodice( Integer codice ) {
        this.codice = codice ;
    }

    public Integer getCodice() {
        return this.codice;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setDescrizione( String descrizione ) {
        this.descrizione = descrizione;
    }
    public String getDescrizione() {
        return this.descrizione;
    }

    public void setRimborsormat( Double rimborsormat ) {
        this.rimborsormat = rimborsormat;
    }
    public Double getRimborsormat() {
        return this.rimborsormat;
    }

    public void setRimborsornomat( Double rimborsornomat ) {
        this.rimborsornomat = rimborsornomat;
    }
    public Double getRimborsornomat() {
        return this.rimborsornomat;
    }

    public void setRimborsosp( Double rimborsosp ) {
        this.rimborsosp = rimborsosp;
    }
    public Double getRimborsosp() {
        return this.rimborsosp;
    }

    public void setRpers1( Double rpers1 ) {
        this.rpers1 = rpers1;
    }
    public Double getRpers1() {
        return this.rpers1;
    }

    public void setRpers2( Double rpers2 ) {
        this.rpers2 = rpers2;
    }
    public Double getRpers2() {
        return this.rpers2;
    }

    public void setRpers3( Double rpers3 ) {
        this.rpers3 = rpers3;
    }
    public Double getRpers3() {
        return this.rpers3;
    }

    public void setRpers4( Double rpers4 ) {
        this.rpers4 = rpers4;
    }
    public Double getRpers4() {
        return this.rpers4;
    }

    public void setRpers5( Double rpers5 ) {
        this.rpers5 = rpers5;
    }
    public Double getRpers5() {
        return this.rpers5;
    }

    public void setSigla( String sigla ) {
        this.sigla = sigla;
    }
    public String getSigla() {
        return this.sigla;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
 
        public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(codice);
        sb.append("|");
        sb.append(descrizione);
        sb.append("|");
        sb.append(rimborsormat);
        sb.append("|");
        sb.append(rimborsornomat);
        sb.append("|");
        sb.append(rimborsosp);
        sb.append("|");
        sb.append(rpers1);
        sb.append("|");
        sb.append(rpers2);
        sb.append("|");
        sb.append(rpers3);
        sb.append("|");
        sb.append(rpers4);
        sb.append("|");
        sb.append(rpers5);
        sb.append("|");
        sb.append(sigla);
        return sb.toString(); 
    } 


}