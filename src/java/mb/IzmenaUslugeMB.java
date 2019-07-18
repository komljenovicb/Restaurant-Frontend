package mb;

import java.io.Serializable;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pl.KontrolerUsluga;
import prikaz.UslugaPrikaz;

/**
 *
 * @author Bojana Komljenovic
 */
@ManagedBean
@ViewScoped
public class IzmenaUslugeMB {

    KontrolerUsluga k;

    private HashMap<String, Integer> jediniceMereMapa;

    private UslugaPrikaz up;
    private int uslugaID;
    private String nazivUsluge;
    private String opisUsluge;
    private int jm;

    public IzmenaUslugeMB() {
        k = new KontrolerUsluga();
    }

    @PostConstruct
    public void init() {
        jediniceMereMapa = k.vratiSveJM();
    }

    public UslugaPrikaz getUp() {
        return up;
    }

    public void setUp(UslugaPrikaz up) {
        this.up = up;
    }

    public int getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(int uslugaID) {
        this.uslugaID = uslugaID;
    }

    public int getJm() {
        return jm;
    }

    public void setJm(int jm) {
        this.jm = jm;
    }

    public String getNazivUsluge() {
        return nazivUsluge;
    }

    public void setNazivUsluge(String nazivUsluge) {
        this.nazivUsluge = nazivUsluge;
    }

    public String getOpisUsluge() {
        return opisUsluge;
    }

    public void setOpisUsluge(String opisUsluge) {
        this.opisUsluge = opisUsluge;
    }

    public HashMap<String, Integer> getJediniceMereMapa() {
        return jediniceMereMapa;
    }

    public void setJediniceMereMapa(HashMap<String, Integer> jediniceMereMapa) {
        this.jediniceMereMapa = jediniceMereMapa;
    }

    public void vratiUslugu() {

        System.out.println("Unosi se nova usluga");

        if (nazivUsluge.equals("")) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Info", "Morate uneti naziv!"));
            return;
        }

        try {

            up = k.vratiUslugu(nazivUsluge);

            uslugaID = up.getUslugaID();
            // odmah postavim ID!
            k.setUslugaID(uslugaID);

            nazivUsluge = up.getNazivUsluge();
            opisUsluge = up.getOpisUsluge();

            jm = up.getSifraJM();

        } catch (Exception ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška",
                    "Ne postoji usluga sa unetim nazivom!"));
        }
    }

    public void izmeniUslugu() {

        try {

            System.out.println("Unosi se nova usluga");

            if (nazivUsluge.equals("") || opisUsluge.equals("")) {
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage("Info", "Sva polja moraju biti popunjena!"));
                return;
            }

            k.setNazivUsluge(nazivUsluge);
            k.setOpisUsluge(opisUsluge);
            k.setJM(jm);

            boolean uspesno = k.izmeniUslugu();

            if (uspesno) {
                System.out.println("Uspesan unos");

                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage("Uspešan unos", "Usluga je uspešno uneta."));

                resetujPolja();

            } else {

                System.out.println("Neuspesan unos");
                System.out.println("Usluga nije uneta");

                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška",
                        "Usluga nije uspešno uneta."));

            }

        } catch (Exception ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška",
                    "Usluga nije uspešno uneta."));
        }
    }

    public void obrisiUslugu() {

        try {

            boolean uspesno = k.obrisiUslugu();

            if (uspesno) {
                System.out.println("Uspešno brisanje");

                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage("Uspešno brisanje", "Usluga je uspešno obrisana."));

                resetujPolja();

            } else {

                System.out.println("Neuspesan unos");
                System.out.println("Usluga nije uneta");

                FacesContext fc = FacesContext.getCurrentInstance();
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška",
                        "Usluga nije uspešno uneta."));

            }

        } catch (Exception ex) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Greška",
                    "Usluga nije uspešno uneta."));
        }
    }

    public void resetujPolja() {
        uslugaID = 0;
        nazivUsluge = null;
        opisUsluge = null;
    }

}
