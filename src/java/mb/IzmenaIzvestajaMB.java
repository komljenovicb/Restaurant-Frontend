package mb;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import pl.KontrolerIzvestaja;
import prikaz.IzvestajPrikaz;
import prikaz.StavkaIzvestajaPrikaz;
import validator.Validator;

/**
 *
 * @author Bojana Komljenovic
 */
@ManagedBean(name = "izmenaIzvestajaMB")
@ViewScoped
public class IzmenaIzvestajaMB {

    private HashMap<String, Integer> rsRestorani;
    private HashMap<String, Integer> rsZaposleni;

    boolean aktivno = true;

    @ManagedProperty(value = "#{vratiIzvestajeMB.ki}")
    KontrolerIzvestaja ki;

    private int rbrStavke;
    private Date datum;
    private String dan;
    private int brojDorucaka;

    private IzvestajPrikaz rsIzvestaj;

    public IzmenaIzvestajaMB() {
    }

    @PostConstruct
    public void init() {
        rsIzvestaj = ki.dajIzvestaj();
        rsRestorani = ki.vratiRestorane();
        rsZaposleni = ki.vratiZaposlene();
    }

    public boolean isAktivno() {
        return aktivno;
    }

    public void setAktivno(boolean aktivno) {
        this.aktivno = aktivno;
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
        return rsIzvestaj;
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

    public void obrisi(IzvestajPrikaz ip) {
        
        boolean uspesno = ki.obrisi(ip.getRbrIzvestaja());
        if (uspesno) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null,
                    new FacesMessage("Izveštaj je uspešno obrisan."));
        } else {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("poruka",
                    new FacesMessage("Neuspešno brisanje izveštaja."));
        }
        
    }

    public void izmeniStavkuIzvestaja(RowEditEvent re) {
        try {
            StavkaIzvestajaPrikaz sp = (StavkaIzvestajaPrikaz) re.getObject();
            System.out.println("Stavka za izmenu: "
                    + sp.getRbrStavke() + ", "
                    + sp.getDatum() + ", "
                    + sp.getDan() + ", "
                    + sp.getBrojDorucaka());
            ki.izmeniStavkuIzvestaja(sp.getRbrStavke(), sp.getDatum(),
                    sp.getDan(), sp.getBrojDorucaka());

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Stavka je uspešno izmenjena!", ""));

        } catch (Exception ex) {
            ex.printStackTrace();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Greška prilikom izmene stavke!", ""));

        }

    }

    public void ponistiIzmenu() {

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Unete izmene su uspešno poništene!", ""));

    }

    public void popuniComboBoxRestorana(HashMap<String, Integer> rsRestorani) {
        setRsRestorani(rsRestorani);
    }

    public void popuniComboBoxZaposleni(HashMap<String, Integer> rsZaposleni) {
        setRsZaposleni(rsZaposleni);
    }

    public void postaviStatus() {
        ki.postaviStatus("U pripremi");
        prikaziPromenu();
    }

    public void prikaziPromenu() {
        rsIzvestaj.setStatus("U pripremi");
    }

    public void obrisiStavku(StavkaIzvestajaPrikaz si) {

        try {

            rsIzvestaj.getStavkeIzvestaja().remove(si);
            
            ki.obrisiStavkuIzvestaja(si.getRbrStavke(), si.getDatum());

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Stavka uspešno obrisana!", ""));

        } catch (Exception ex) {
            ex.printStackTrace();

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Greška prilikom brisanja stavke!", ""));

        }
    }

    public void izmeniIzvestaj() {

        try {
            if (Validator.getInstanca().validacijaDatuma(rsIzvestaj.getDatumOd(),
                    rsIzvestaj.getDatumDo())) {

                ki.postaviRBRIzvestaja(rsIzvestaj.getRbrIzvestaja());
                ki.postaviRestoran(rsIzvestaj.getIdRestorana());
                ki.postaviDatumOd(rsIzvestaj.getDatumOd());
                ki.postaviDatumDo(rsIzvestaj.getDatumDo());
                ki.postaviZaposlenog(rsIzvestaj.getZaposleniID());
                ki.setUkupanBrojDorucaka(rsIzvestaj.getUkupanBrojDorucaka());
                ki.postaviStatus("update");
                ki.izmeniIzvestaj();

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Izveštaj je uspešno sačuvan!", ""));

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Greška prilikom unosa datuma!", ""));

            }
        } catch (Exception ex) {
            System.err.println("Greška: " + ex);
            ex.printStackTrace();
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("poruka",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Greška prilikom čuvanja izveštaja",
                            "Čuvanje izveštaja"));
        }

    }

    public void sacuvajStavkuIzvestaja() {

        // U tabeli se prikazivao jedan dan ranije
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        /*
        if (datum.after(rsIzvestaj.getDatumOd()) || datum.before(rsIzvestaj.getDatumOd())) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage("porukaStavke", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Datum na stavci izveštaja mora biti u odgovarajćem periodu!",
                    ""));
            return;
        }
         */
        boolean uspesnaValidacja = izvrsiValidaciju();

        try {
            if (uspesnaValidacja) {

                System.out.println(" " + datum + " " + dan + " " + brojDorucaka);
                rbrStavke = vratiMaxID();
                
                rsIzvestaj.getStavkeIzvestaja()
                        .add(new StavkaIzvestajaPrikaz(ki.getRbrIzvestaja(),
                                rbrStavke, datum, dan, brojDorucaka));

                ki.dodajNovuStavkuIzvestaja(datum, dan,
                        brojDorucaka);

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Stavka je uspešno sačuvana!", ""));

            } else {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage("Validacija nije uspela!", ""));
                return;
            }

        } catch (Exception ex) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Neuspešno čuvanje stavke!", ""));

            ex.printStackTrace();
        }

    }

    private boolean izvrsiValidaciju() {

        if (!Validator.getInstanca().ispravnoUnetNazivDana(dan)) {
            return false;
        }

        try {
            if (datum.before(rsIzvestaj.getDatumOd())
                    || datum.after(rsIzvestaj.getDatumDo())) {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Datumi od i do jos uvek nisu uneti...");
            return false;
        }

        for (StavkaIzvestajaPrikaz sp : rsIzvestaj.getStavkeIzvestaja()) {
            if (sp.getDan().equals(dan)) {
                return false;
            }
        }

        for (StavkaIzvestajaPrikaz sp : rsIzvestaj.getStavkeIzvestaja()) {
            if (sp.getDatum().equals(datum)) {
                return false;
            }
        }
        return true;
    }

    private int vratiMaxID() {

        int max = Integer.MIN_VALUE;

        for (StavkaIzvestajaPrikaz sp : rsIzvestaj.getStavkeIzvestaja()) {
            if (sp.getRbrStavke() > max) {
                max = sp.getRbrStavke();
            }
        }
        return ++max;
    }
}
