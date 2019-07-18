/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prikaz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.StavkaIzvestaja;

/**
 *
 * @author Bojana Komljenovic
 */
public class IzvestajPrikaz implements Serializable {

    private int rbrIzvestaja;
    private Date datumOd;
    private Date datumDo;
    private int ukupanBrojDorucaka;
    private String status = "Kreiran"; // inicijalno kreiran
    private int idRestorana;
    private String nazivRestorana;
    private int zaposleniID;
    private String imeZaposlenog;
    private String prezimeZaposlenog;
    private List<StavkaIzvestajaPrikaz> stavkeIzvestaja;

    public IzvestajPrikaz() {
        stavkeIzvestaja = new ArrayList<>();
    }

    public List<StavkaIzvestajaPrikaz> getStavkeIzvestaja() {
        return stavkeIzvestaja;
    }

    public void setStavkeIzvestaja(List<StavkaIzvestajaPrikaz> stavkeIzvestaja) {
        this.stavkeIzvestaja = stavkeIzvestaja;
    }

    public int getRbrIzvestaja() {
        return rbrIzvestaja;
    }

    public void setRbrIzvestaja(int rbrIzvestaja) {
        this.rbrIzvestaja = rbrIzvestaja;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public int getUkupanBrojDorucaka() {
          if (stavkeIzvestaja != null) {
            int suma = 0;
            for (StavkaIzvestajaPrikaz si : stavkeIzvestaja) {
                suma += si.getBrojDorucaka();
            }
            ukupanBrojDorucaka = suma;
        }
        return ukupanBrojDorucaka;
    }

    public void setUkupanBrojDorucaka(int ukupanBrojDorucaka) {
        this.ukupanBrojDorucaka = ukupanBrojDorucaka;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }

    public int getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(int zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public String getImeZaposlenog() {
        return imeZaposlenog;
    }

    public void setImeZaposlenog(String imeZaposlenog) {
        this.imeZaposlenog = imeZaposlenog;
    }

    public String getPrezimeZaposlenog() {
        return prezimeZaposlenog;
    }

    public void setPrezimeZaposlenog(String prezimeZaposlenog) {
        this.prezimeZaposlenog = prezimeZaposlenog;
    }
 
}
