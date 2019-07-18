package mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import pl.KontrolerIzvestaja;
import prikaz.IzvestajPrikaz;

/**
 *
 * @author Bojana Komljenovic
 */
@ManagedBean(name = "vratiIzvestajeMB")
@SessionScoped
public class VratiIzvestajeMB implements Serializable {

    int rbrIzvestaja;
    List<IzvestajPrikaz> lista;
    IzvestajPrikaz izvestajZaizmenu;
    IzvestajPrikaz izvestajZaPretragu;
    KontrolerIzvestaja ki;

    public VratiIzvestajeMB() {
        ki = new KontrolerIzvestaja();
        lista = new ArrayList<>();
        izvestajZaPretragu = new IzvestajPrikaz();
        izvestajZaizmenu = new IzvestajPrikaz();
    }

    @PostConstruct
    public void dajIzvestaje() {
        // lista = ki.vratiSveIzvestaje();
    }

    /*
    public IzmenaIzvestajaMB getIzmenaIzvestaja() {
        return izmenaIzvestaja;
    }

    public void setIzmenaIzvestaja(IzmenaIzvestajaMB izmenaIzvestaja) {
        this.izmenaIzvestaja = izmenaIzvestaja;
    }
     */
 /*
    public SesijaMB getSesija() {
        return sesija;
    }
    
    public void setSesija(SesijaMB sesija) {
        this.sesija = sesija;
    }
     */
    public KontrolerIzvestaja getKi() {
        return ki;
    }

    public void setKi(KontrolerIzvestaja ki) {
        this.ki = ki;
    }

    public IzvestajPrikaz getIzvestajZaPretragu() {
        return izvestajZaPretragu;
    }

    public void setIzvestajZaPretragu(IzvestajPrikaz izvestajZaPretragu) {
        this.izvestajZaPretragu = izvestajZaPretragu;
    }

    public IzvestajPrikaz getIzvestajZaizmenu() {
        return izvestajZaizmenu;
    }

    public void setIzvestajZaizmenu(IzvestajPrikaz izvestajZaizmenu) {
        this.izvestajZaizmenu = izvestajZaizmenu;
    }

    public List<IzvestajPrikaz> getLista() {
        return lista;
    }

    public void setLista(List<IzvestajPrikaz> lista) {
        this.lista = lista;
    }

    public void prikazi(IzvestajPrikaz ip) {
        if (izvestajZaizmenu == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Niste izabrali izveštaj", ""));
        }

        // sesija.setIzvestajZaIzmenu(izvestajZaizmenu);
        ki.setRbrIzvestaja(ip.getRbrIzvestaja());
        FacesContext.getCurrentInstance().getApplication().
                getNavigationHandler().handleNavigation(
                        FacesContext.getCurrentInstance(),
                        null,
                        "prikaz_izvestaja.xhtml");
    }

    public void izmeni(IzvestajPrikaz ip) {

        ki.setRbrIzvestaja(ip.getRbrIzvestaja());
        FacesContext.getCurrentInstance().getApplication().
                getNavigationHandler().handleNavigation(
                        FacesContext.getCurrentInstance(),
                        null,
                        "izmena_izvestaja.xhtml");
    }

    public void kreiraj() {
        FacesContext.getCurrentInstance().getApplication().
                getNavigationHandler().handleNavigation(
                        FacesContext.getCurrentInstance(),
                        null,
                        "unos_izvestaja.xhtml");
    }

    public void obrisiIzvestaj() {
        if (izvestajZaizmenu == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Niste izabrali izveštaj", ""));
        }
        IzvestajPrikaz i = izvestajZaizmenu;
        izvestajZaizmenu = new IzvestajPrikaz();
        if (ki.obrisi(i.getRbrIzvestaja())) {
            lista = ki.vratiSveIzvestaje();
            i.setStatus("delete");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Izveštaj je obrisan", ""));

        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Odaberite red!", ""));
            return;
        }
    }

    public void pretrazi() {

        try {
            List<IzvestajPrikaz> listaIzvestaja
                    = ki.pretrazi(izvestajZaPretragu.getDatumOd(),
                            izvestajZaPretragu.getDatumDo());

            if (listaIzvestaja == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Sistem ne moze da pronađe izveštaje"
                                + " po zadatom kriterijumu",
                                ""));
            } else {

                System.out.println("Ucitani izvestaji...");
                lista = listaIzvestaja;
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Sistem ne moze da pronađe izveštaje "
                            + "po zadatom kriterijumu",
                            ""));
        }
    }

    public void azurirajPrikaz() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            lista = ki.vratiSveIzvestaje();
        }
    }

}
