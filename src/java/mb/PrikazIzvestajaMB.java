package mb;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.KontrolerIzvestaja;
import prikaz.IzvestajPrikaz;

/**
 *
 * @author Bojana Komljenovic
 */
@ManagedBean
@ViewScoped
public class PrikazIzvestajaMB {

    private HashMap<String, Integer> rsRestorani;
    private HashMap<String, Integer> rsZaposleni;

    @ManagedProperty(value = "#{vratiIzvestajeMB.ki}")
    KontrolerIzvestaja ki;

    private Date datum;
    private String dan;
    private int brojDorucaka;

    private IzvestajPrikaz rsIzvestaj;

    public PrikazIzvestajaMB() {
    }

    @PostConstruct
    public void init() {
        rsRestorani = ki.vratiRestorane();
        rsZaposleni = ki.vratiZaposlene();
    }

    public KontrolerIzvestaja getKi() {
        return ki;
    }

    public void setKi(KontrolerIzvestaja ki) {
        this.ki = ki;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getBrojDorucaka() {
        return brojDorucaka;
    }

    public void setBrojDorucaka(int brojDorucaka) {
        this.brojDorucaka = brojDorucaka;
    }

    public IzvestajPrikaz getRsIzvestaj() {
        if(rsIzvestaj == null) rsIzvestaj = new IzvestajPrikaz();
        return ki.dajIzvestaj();
    }

    public void setRsIzvestaj(IzvestajPrikaz rsIzvestaj) {
        this.rsIzvestaj = rsIzvestaj;
    }

    public void popuniComboBoxRestorana() {
        rsRestorani = ki.vratiRestorane();
    }

    public void popuniComboBoxZaposleni() {
        rsZaposleni = ki.vratiZaposlene();
    }

    public HashMap<String, Integer> getRsRestorani() {
        return rsRestorani;
    }

    public void setRsRestorani(HashMap<String, Integer> rsRestorani) {
        this.rsRestorani = rsRestorani;
    }

    public HashMap<String, Integer> getRsZaposleni() {
        return rsZaposleni;
    }

    public void setRsZaposleni(HashMap<String, Integer> rsZaposleni) {
        this.rsZaposleni = rsZaposleni;
    }

}
