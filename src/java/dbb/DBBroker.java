package dbb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import model.IzvestajOBrojuDorucaka;
import model.JedinicaMere;
import model.Restoran;
import model.StavkaIzvestaja;
import model.StavkaIzvestajaPK;
import model.Usluga;
import model.Zaposleni;

/**
 *
 * @author Bojana Komljenovic
 */
public class DBBroker {

    EntityManagerFactory emf;
    EntityManager em;

    public DBBroker() {
        emf = Persistence.createEntityManagerFactory("FpisAplikacijaPU");
    }

    public void otvoriKonekciju() {
        if (em == null || !em.isOpen()) {
            em = emf.createEntityManager();
        }
    }

    public void zatvoriKonekciju() {
        if (em.isOpen()) {
            em.close();
        }
    }

    public void pokreniTransakciju() {
        if (em.isOpen()) {
            em.getTransaction().begin();
        }
    }

    public void potvrdiTransakciju() {
        if (em.isOpen()) {
            em.getTransaction().commit();
        }
    }

    public void ponistiTransakciju() {
        if (em.isOpen()) {
            em.getTransaction().rollback();
        }
    }

    public boolean sacuvajUslugu(Usluga u) {
        try {
            if (em.isOpen()) {
                em.persist(u);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public int vratiRBRIzvestaja() {
        int rbrIzvestaja = Integer.MIN_VALUE;
        if (em.isOpen()) {
            rbrIzvestaja = em.createQuery("select max(i.rBRIzvestaja) + 1 "
                    + "from IzvestajOBrojuDorucaka i",
                    Integer.class).getSingleResult();
        }
        return rbrIzvestaja;
    }

    public int vratiIDUsluge() {
        int idUsluge = Integer.MIN_VALUE;
        if (em.isOpen()) {
            idUsluge = em.createQuery("select max(u.uslugaID) + 1 "
                    + "from Usluga u",
                    Integer.class).getSingleResult();
        }
        return idUsluge;
    }

    public List<JedinicaMere> vratiSveJM() {

        List<JedinicaMere> jediniceMere = new ArrayList<>();

        if (em.isOpen()) {
            jediniceMere = em.createNamedQuery("JedinicaMere.findAll").getResultList();
        }

        return jediniceMere;

    }

    public boolean obrisiUslugu(int uslugaID) {
        try {
            if (em.isOpen()) {
                Usluga u = em.find(Usluga.class, uslugaID);
                em.remove(u);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Usluga> vratiSveUsluge() {
        List<Usluga> rsUsluge = new ArrayList<>();

        if (em.isOpen()) {
            rsUsluge = em.createNamedQuery("Usluga.findAll").getResultList();
        }

        return rsUsluge;
    }

    public boolean izmeniUslugu(Usluga u) {
        try {
            if (em.isOpen()) {
                em.merge(u);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<IzvestajOBrojuDorucaka> vratiSveIzvestaje() {

        List<IzvestajOBrojuDorucaka> rsIzvestaji = new ArrayList<>();

        if (em.isOpen()) {
            rsIzvestaji = em.createNamedQuery("IzvestajOBrojuDorucaka.findAll").getResultList();
        }

        return rsIzvestaji;
    }

    public List<IzvestajOBrojuDorucaka> pretrazi(Date datumOd, Date datumDo) {
        List<IzvestajOBrojuDorucaka> rsIzvestaji = new ArrayList<>();

        if (em.isOpen()) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String query = "SELECT i FROM IzvestajOBrojuDorucaka i WHERE i.datumOd >= ':datumOd' and i.datumDo <= ':datumDo' ";

            query = query.replace(":datumOd",
                    sdf.format(datumOd));
            query = query.replace(":datumDo",
                    sdf.format(datumDo));

            System.out.println(query);

            rsIzvestaji = em.createQuery(query).getResultList();
        }

        return rsIzvestaji;
    }

    public boolean obrisiIzvestaj(int rbr) {
        try {
            if (em.isOpen()) {
                IzvestajOBrojuDorucaka i
                        = em.find(IzvestajOBrojuDorucaka.class, rbr);
                em.remove(i);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean sacuvajIzvestaj(IzvestajOBrojuDorucaka i) {

        System.out.println("Status izvestaja u bazi: " + i.getStatus());

        for (StavkaIzvestaja si : i.getStavkaIzvestajaList()) {
            System.out.println("*****");
            System.out.println("" + si.getRbrIzvestaja() + " " + si.getRbrStavke() + "Stat: " + si.getStatus());
            System.out.println("*****");
        }

        try {
            if (i.getStatus().equals("insert")) {
                System.out.println("Cuvanje izvestaja...");

                if (i.getStavkaIzvestajaList() != null) {

                    for (StavkaIzvestaja stavkaIzvestaja : i.getStavkaIzvestajaList()) {
                        sacuvajStavkeIzvestaja(stavkaIzvestaja);
                    }

                }
                i.setStatus("Potpisan");
                em.persist(i);

            } else if (i.getStatus().equals("update")) {

                if (i.getStavkaIzvestajaList() != null) {

                    for (StavkaIzvestaja stavkaIzvestaja : i.getStavkaIzvestajaList()) {
                        System.out.println("" + stavkaIzvestaja);
                    }

                    for (StavkaIzvestaja stavkaIzvestaja : i.getStavkaIzvestajaList()) {
                        if (stavkaIzvestaja.getStatus() != null) {
                            sacuvajStavkeIzvestaja(stavkaIzvestaja);
                        }
                    }

                } else {
                    System.out.println("Nema stavki za izmenu...");
                }
                i.setStatus("Potpisan");
                em.merge(i);
            } else if (i.getStatus().equals("delete")) {
                i.setStatus("Obrisan");
                em.remove(i);
            }
            return true;
        } catch (Exception ex) {
            System.err.println("Izvestaj nije sacuvan: " + ex);
            return false;
        }

    }

    public List<Restoran> vratiSveRestorane() {
        List<Restoran> rsRestorani = new ArrayList<>();

        if (em.isOpen()) {
            rsRestorani
                    = em.createNamedQuery("Restoran.findAll",
                            Restoran.class
                    ).getResultList();
        }
        return rsRestorani;
    }

    public List<Zaposleni> vratiSveZaposlene() {
        List<Zaposleni> rsZaposleni = new ArrayList<>();

        if (em.isOpen()) {
            rsZaposleni
                    = em.createNamedQuery("Zaposleni.findAll",
                            Zaposleni.class
                    ).getResultList();
        }
        return rsZaposleni;
    }

    private boolean sacuvajStavkeIzvestaja(StavkaIzvestaja si) {
        try {
            if (si.getStatus().equals("insert")) {
                si.setRbrStavke(vratiMaxID());
                em.persist(si);
            } else if (si.getStatus().equals("update")) {
                em.merge(si);
            } else if (si.getStatus().equals("delete")) {
                
                // java.lang.IllegalArgumentException: Entity must be managed to call remove
                // Probala sam da dodam si = merge(si);     
                // Ni izmene u mapiranju nisu uspele
                // em.remove() zasto ne radi ???
                
                String sql = "DELETE FROM StavkaIzvestaja s WHERE s.rbrIzvestaja=:rbrIzvestaja and s.rbrStavke=:rbrStavke";
                Query query = em.createQuery(sql);
                query.setParameter("rbrIzvestaja", si.getRbrIzvestaja());
                query.setParameter("rbrStavke", si.getRbrStavke());
                
                query.executeUpdate();
                
                
            }
            return true;
        } catch (Exception ex) {
            System.err.println("Stavka izvestaja nije sacuvana: " + ex);
            return false;
        }
    }

    private int vratiMaxID() {
        int idStavke = Integer.MIN_VALUE;
        if (em.isOpen()) {
            idStavke = em.createQuery("select max(s.rbrStavke) + 1 "
                    + "from StavkaIzvestaja s",
                    Integer.class).getSingleResult();
        }
        return idStavke;
    }

}
