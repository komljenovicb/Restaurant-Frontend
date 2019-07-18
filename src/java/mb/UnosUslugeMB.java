package mb;

import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import pl.KontrolerUsluga;

/**
 *
 * @author Bojana Komljenovic
 */
@ManagedBean
@RequestScoped
public class UnosUslugeMB {

    KontrolerUsluga k;

    private HashMap<String, Integer> jediniceMereMapa;

    private int uslugaID;
    private String nazivUsluge;
    private String opisUsluge;
    private int jm;

    public UnosUslugeMB() {
        k = new KontrolerUsluga();
    }

    @PostConstruct
    public void popuniJM() {
        uslugaID = k.vratiIDUsluge();
        jediniceMereMapa = k.vratiSveJM();
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

    public void sacuvajUslugu() {

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

            boolean uspesno = k.sacuvajUslugu();

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

    public void resetujPolja() {
        nazivUsluge = null;
        opisUsluge = null;
    }

}
