/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prikaz;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Bojana Komljenovic
 */
public class StavkaIzvestajaPrikaz implements Serializable {

    private int rbrIzvestaja;
    private int rbrStavke;
    private Date datum;
    private String dan;
    private String status = "";
    private int brojDorucaka;

    public StavkaIzvestajaPrikaz() {
    }

    public StavkaIzvestajaPrikaz(int rbrIzvestaja, int rbrStavke, Date datum, String dan, int brojDorucaka) {
        this.rbrIzvestaja = rbrIzvestaja;
        this.rbrStavke = rbrStavke;
        this.datum = datum;
        this.dan = dan;
        this.brojDorucaka = brojDorucaka;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRbrIzvestaja() {
        return rbrIzvestaja;
    }

    public void setRbrIzvestaja(int rbrIzvestaja) {
        this.rbrIzvestaja = rbrIzvestaja;
    }

    public int getRbrStavke() {
        return rbrStavke;
    }

    public void setRbrStavke(int rbrStavke) {
        this.rbrStavke = rbrStavke;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public int getBrojDorucaka() {
        return brojDorucaka;
    }

    public void setBrojDorucaka(int brojDorucaka) {
        this.brojDorucaka = brojDorucaka;
    }

}
