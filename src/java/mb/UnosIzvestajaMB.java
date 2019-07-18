package mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.KontrolerIzvestaja;
import prikaz.IzvestajPrikaz;
import prikaz.StavkaIzvestajaPrikaz;
import validator.Validator;

/**
 *
 * @author Bojana Komljenovic
 */
@ManagedBean
@ViewScoped
public class UnosIzvestajaMB implements Serializable {

    KontrolerIzvestaja ki;

    private IzvestajPrikaz ip;
    private int rbrIzvestaja = 0;
    private int rbrStavke;
    private int restoranID;
    private Date datumOd;
    private Date datumDo;
    private String status;
    private Date datum;
    private String dan;
    private int brojDorucaka = 0;
    private int zaposleniID;
    private int ukupanBrojDorucaka;
    private HashMap<String, Integer> rsRestorani;
    private HashMap<String, Integer> rsZaposleni;
    private ArrayList<StavkaIzvestajaPrikaz> stavkeIzvestaja;
    private IzvestajPrikaz tekuciIzvestaj;
    private StavkaIzvestajaPrikaz si;

    /**
     * Creates a new instance of UnosIzvestajaMB
     */
    public UnosIzvestajaMB() {

    }

    @PostConstruct
    public void init() {
        ki = new KontrolerIzvestaja();
        rbrIzvestaja = ki.vratiRBRIzvestaja();
        prikaziRBRIzvestaja(rbrIzvestaja);
        stavkeIzvestaja = new ArrayList<>();
        si = new StavkaIzvestajaPrikaz();
        popuniComboBoxRestorana();
        popuniComboBoxZaposleni();
        setStatus("Kreiran");
        prikaziPromenu();
    }

    // ======================================================== //
    public IzvestajPrikaz getIp() {
        return ip;
    }

    public void setIp(IzvestajPrikaz ip) {
        this.ip = ip;
    }

    public IzvestajPrikaz getTekuciIzvestaj() {
        return tekuciIzvestaj;
    }

    public void setTekuciIzvestaj(IzvestajPrikaz tekuciIzvestaj) {
        this.tekuciIzvestaj = tekuciIzvestaj;
    }

    public StavkaIzvestajaPrikaz getSi() {
        return si;
    }

    public void setSi(StavkaIzvestajaPrikaz si) {
        this.si = si;
    }

    public int getRbrIzvestaja() {
        return rbrIzvestaja;
    }

    public void setRbrIzvestaja(int rbrIzvestaja) {
        this.rbrIzvestaja = rbrIzvestaja;
    }

    public int getRestoranID() {
        return restoranID;
    }

    public void setRestoranID(int restoranID) {
        this.restoranID = restoranID;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(int zaposleniID) {
        this.zaposleniID = zaposleniID;
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

    public ArrayList<StavkaIzvestajaPrikaz> getStavkeIzvestaja() {
        return stavkeIzvestaja;
    }

    public void setStavkeIzvestaja(ArrayList<StavkaIzvestajaPrikaz> stavkeIzvestaja) {
        if (stavkeIzvestaja == null) {
            stavkeIzvestaja = new ArrayList<>();
        }
        this.stavkeIzvestaja = stavkeIzvestaja;
    }

    public int getUkupanBrojDorucaka() {
        return ukupanBrojDorucaka;
    }

    public void setUkupanBrojDorucaka(int ukupanBrojDorucaka) {
        this.ukupanBrojDorucaka = ukupanBrojDorucaka;
    }

    // ======================================================== //
    public void vratiRBRIzvestaja() {
        rbrIzvestaja = ki.vratiRBRIzvestaja();
    }

    public int getRbrStavke() {
        return rbrStavke;
    }

    public void setRbrStavke(int rbrStavke) {
        this.rbrStavke = rbrStavke;
    }

    public void popuniComboBoxRestorana() {
        rsRestorani = ki.vratiRestorane();
    }

    public void popuniComboBoxZaposleni() {
        rsZaposleni = ki.vratiZaposlene();
    }

    public void postaviStatus(String status) {
        ki.postaviStatus(status);
    }

    private void prikaziPromenu() {
        setStatus(status);
    }

    public void prikaziRBRIzvestaja(int rbrIzvestaja) {
        ki.postaviRBRIzvestaja(rbrIzvestaja);
        this.rbrIzvestaja = rbrIzvestaja;
    }

    public void dodajNovuStavkuIzvestaja() {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        boolean uspesnaValidacija = izvrsiValidaciju();

        try {

            if (uspesnaValidacija) {

                System.out.println(" " + datum + " " + dan + " " + brojDorucaka);
                rbrStavke = vratiMaxID();
                stavkeIzvestaja.add(new StavkaIzvestajaPrikaz(rbrIzvestaja,
                        rbrStavke, datum, dan, brojDorucaka));
                ki.dodajNovuStavkuIzvestaja(datum, dan, brojDorucaka);

            } else {
                FacesContext.getCurrentInstance().addMessage("porukaStavke",
                        new FacesMessage("Validacija stavki nije uspela!", ""));
                return;
            }

        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage("porukaStavke",
                    new FacesMessage("Sva polja moraju biti popunjena!", ""));
            return;
        }
    }

    public void sacuvajIzvestaj() {

        try {

            if (Validator.getInstanca().validacijaDatuma(datumOd, datumDo)) {
                
                ki.postaviRBRIzvestaja(rbrIzvestaja);
                ki.postaviRestoran(restoranID);
                ki.postaviDatumOd(datumOd);
                ki.postaviDatumDo(datumDo);
                ki.postaviZaposlenog(zaposleniID);
                ki.postaviStatus("insert");
                
                boolean uspesno = ki.sacuvajIzvestaj();

                if (uspesno) {

                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Izveštaj je uspešno sačuvan!", ""));

                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage("Greška prilikom čuvanja izveštaja!", ""));

                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Datum pocetka mora biti pre datuma kraja!", ""));
            }
        } catch (Exception ex) {
            System.err.println("Greška: " + ex);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Neuspešno čuvanje izveštaja!", ""));
        }

    }

    public void postaviStatus() {
        this.setStatus("U pripremi");
    }

    public void obrisi(StavkaIzvestajaPrikaz si) {
        
        ki.obrisiStavkuIzvestaja(si.getRbrStavke(), si.getDatum());
        stavkeIzvestaja.remove(si);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Stavka izveštaja je obrisana!", ""));

    }

    private boolean izvrsiValidaciju() {

        if (!Validator.getInstanca().ispravnoUnetNazivDana(dan)) {
            return false;
        }

        try {
            if (datum.before(datumOd)
                    || datum.after(datumDo)) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Datumi od i do jos uvek nisu uneti...");
            return false;
        }

        for (StavkaIzvestajaPrikaz sp : stavkeIzvestaja) {
            if (sp.getDan().equals(dan)) {
                return false;
            }
        }

        for (StavkaIzvestajaPrikaz sp : stavkeIzvestaja) {
            if (sp.getDatum().equals(datum)) {
                return false;
            }
        }

        return true;
    }
    
    private int vratiMaxID() {

        int max = 0;

        for (StavkaIzvestajaPrikaz sp : stavkeIzvestaja) {
            if (sp.getRbrStavke() > max) {
                max = sp.getRbrStavke();
            }
        }
        return ++max;
    }

}
