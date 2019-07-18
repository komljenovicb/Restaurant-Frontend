/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import dbb.DBBroker;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.core.Response;
import model.IzvestajOBrojuDorucaka;
import model.Restoran;
import model.StavkaIzvestaja;
import model.Zaposleni;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import prikaz.IzvestajPrikaz;
import prikaz.StavkaIzvestajaPrikaz;
import rest.RESTKontrolerIzvestaja;

/**
 *
 * @author Bojana Komljenovic
 */
public class KontrolerIzvestaja {

    RESTKontrolerIzvestaja rs;
    private DBBroker dbb;
    private boolean ret = false;
    private IzvestajOBrojuDorucaka i;
    private int rbrIzvestaja;
    private List<IzvestajPrikaz> izvestaji;
    private int ukupanBrojDorucaka;

    public KontrolerIzvestaja() {
        dbb = new DBBroker();
        i = new IzvestajOBrojuDorucaka();
        rs = new RESTKontrolerIzvestaja();
    }

    public List<IzvestajPrikaz> getIzvestaji() {
        return izvestaji;
    }

    public IzvestajOBrojuDorucaka getI() {
        return i;
    }

    public void setI(IzvestajOBrojuDorucaka i) {
        this.i = i;
    }

    public int getUkupanBrojDorucaka() {
        return ukupanBrojDorucaka;
    }

    public void setRbrIzvestaja(int rbrIzvestaja) {
        this.rbrIzvestaja = rbrIzvestaja;
    }

    public void setUkupanBrojDorucaka(int ukupanBrojDorucaka) {
        this.ukupanBrojDorucaka = ukupanBrojDorucaka;
    }

    public int getRbrIzvestaja() {
        return rbrIzvestaja;
    }

    public List<IzvestajPrikaz> vratiSveIzvestaje() {

        List<IzvestajOBrojuDorucaka> rsIzvestaj = new ArrayList<>();
        izvestaji = new ArrayList<>();

        try {
            dbb.otvoriKonekciju();
            dbb.pokreniTransakciju();
            rsIzvestaj = dbb.vratiSveIzvestaje();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
            dbb.zatvoriKonekciju();
        }

        for (IzvestajOBrojuDorucaka izv : rsIzvestaj) {

            IzvestajPrikaz ip = new IzvestajPrikaz();
            ip.setRbrIzvestaja(izv.getRBRIzvestaja());
            ip.setDatumOd(izv.getDatumOd());
            ip.setDatumDo(izv.getDatumDo());
            ip.setIdRestorana(izv.getIDRestorana().getIDRestorana());
            ip.setNazivRestorana(izv.getIDRestorana().getNazivRestorana());
            ip.setZaposleniID(izv.getZaposleniID().getZaposleniID());
            ip.setImeZaposlenog(izv.getZaposleniID().getIme());
            ip.setPrezimeZaposlenog(izv.getZaposleniID().getPrezime());
            ip.setStatus(izv.getStatus());
            ArrayList<StavkaIzvestajaPrikaz> stavke = new ArrayList<>();

            for (StavkaIzvestaja si : izv.getStavkaIzvestajaList()) {
                StavkaIzvestajaPrikaz st = new StavkaIzvestajaPrikaz();
                st.setRbrIzvestaja(izv.getRBRIzvestaja());
                st.setRbrStavke(si.getRbrStavke());
                st.setDatum(si.getDatum());
                st.setDan(si.getDan());
                st.setBrojDorucaka(si.getBrojDorucaka());
                stavke.add(st);
            }

            ip.setStavkeIzvestaja(stavke);
            ip.setUkupanBrojDorucaka(izv.getUkupanBrojDorucaka());
            izvestaji.add(ip);
        }

        return izvestaji;
    }

    public List<IzvestajPrikaz> pretrazi(Date datumOd, Date datumDo) {

        List<IzvestajOBrojuDorucaka> rsIzvestaj = new ArrayList<>();
        izvestaji = new ArrayList<>();

        try {
            dbb.otvoriKonekciju();
            dbb.pokreniTransakciju();
            rsIzvestaj = dbb.pretrazi(datumOd, datumDo);
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
            dbb.zatvoriKonekciju();
        }

        for (IzvestajOBrojuDorucaka i : rsIzvestaj) {
            IzvestajPrikaz ip = new IzvestajPrikaz();
            ip.setRbrIzvestaja(i.getRBRIzvestaja());
            ip.setDatumOd(i.getDatumOd());
            ip.setDatumDo(i.getDatumDo());
            ip.setIdRestorana(i.getIDRestorana().getIDRestorana());
            ip.setNazivRestorana(i.getIDRestorana().getNazivRestorana());
            ip.setZaposleniID(i.getZaposleniID().getZaposleniID());
            ip.setImeZaposlenog(i.getZaposleniID().getIme());
            ip.setPrezimeZaposlenog(i.getZaposleniID().getPrezime());
            ip.setUkupanBrojDorucaka(i.getUkupanBrojDorucaka());
            ip.setStatus(i.getStatus());
            ArrayList<StavkaIzvestajaPrikaz> stavke = new ArrayList<>();
            for (StavkaIzvestaja si : i.getStavkaIzvestajaList()) {
                StavkaIzvestajaPrikaz st = new StavkaIzvestajaPrikaz();
                st.setRbrStavke(si.getRbrStavke());
                st.setDatum(si.getDatum());
                st.setDan(si.getDan());
                st.setBrojDorucaka(si.getBrojDorucaka());
                stavke.add(st);
            }
            ip.setStavkeIzvestaja(stavke);
            izvestaji.add(ip);
        }

        return izvestaji;
    }

    public boolean obrisi(int rbr) {

        ret = false;
        try {

            dbb.otvoriKonekciju();
            dbb.pokreniTransakciju();
            ret = dbb.obrisiIzvestaj(rbr);
            if (ret) {
                dbb.potvrdiTransakciju();
            } else {
                dbb.ponistiTransakciju();
            }
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
            dbb.zatvoriKonekciju();
        }

        return ret;
    }

    // WS metoda
    public boolean sacuvajIzvestaj() {

        for (int j = 0; j < i.getStavkaIzvestajaList().size(); j++) {
            StavkaIzvestaja si = i.getStavkaIzvestajaList().get(j);
            System.out.println("Status u kontroleru: " + si.getStatus());
        }

        if (i.getStavkaIzvestajaList().isEmpty()) {
            System.out.println("Lista stavki je prazna!");
            return false;
        }

        JSONObject json = new JSONObject();

        json.put("rbrIzvestaja", i.getRBRIzvestaja() + "");

        json.put("datumOd",
                new SimpleDateFormat("yyyy-MM-dd").format(i.getDatumOd()));
        json.put("datumDo",
                new SimpleDateFormat("yyyy-MM-dd").format(i.getDatumDo()));
        json.put("ukupanBrojDorucaka", i.getUkupanBrojDorucaka() + "");
        json.put("status", i.getStatus());
        json.put("restoran", i.getIDRestorana().getIDRestorana() + "");
        json.put("zaposleni", i.getZaposleniID().getZaposleniID() + "");

        JSONArray lista = new JSONArray();

        for (StavkaIzvestaja si : i.getStavkaIzvestajaList()) {

            JSONObject st = new JSONObject();
            try {
                st.put("rbrStavke",
                        si.getRbrStavke() + "");
                st.put("datum",
                        new SimpleDateFormat("yyyy-MM-dd").format(si.getDatum()));
                st.put("dan",
                        si.getDan());
                st.put("brojDorucaka",
                        si.getBrojDorucaka());
                st.put("status",
                        si.getStatus());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            lista.add(st);
        }

        json.put("lista", lista);

        System.out.println(json.toJSONString());

        Response res = rs.sacuvajIzvestaj(json.toJSONString());
        return res.getStatus() == Response.Status.OK.getStatusCode();

    }

    public void izmeniIzvestaj() {

        for (int j = 0; j < i.getStavkaIzvestajaList().size(); j++) {
            StavkaIzvestaja si = i.getStavkaIzvestajaList().get(j);
            System.out.println("Status u kontroleru: " + si.getStatus());
        }

        try {

            dbb.otvoriKonekciju();
            dbb.pokreniTransakciju();
            ret = dbb.sacuvajIzvestaj(i);
            if (ret) {
                dbb.potvrdiTransakciju();
            } else {
                dbb.ponistiTransakciju();
            }
            dbb.zatvoriKonekciju();

        } catch (Exception e) {
            e.printStackTrace();
            dbb.zatvoriKonekciju();
        }
    }

    public void postaviRestoran(int restoranID) {
        i.setIDRestorana(new Restoran(restoranID));
    }

    public void postaviDatumOd(Date datumOd) {
        i.setDatumOd(datumOd);
    }

    public void postaviDatumDo(Date datumDo) {
        i.setDatumDo(datumDo);
    }

    public void postaviZaposlenog(int zaposleniID) {
        i.setZaposleniID(new Zaposleni(zaposleniID));
        i.setUkupanBrojDorucaka(ukupanBrojDorucaka);
    }

    public void postaviStatus(String status) {
        i.setStatus(status);
    }

    public HashMap<String, Integer> vratiRestorane() {
        HashMap<String, Integer> rsRestorani = new HashMap<>();
        List<Restoran> restorani = new ArrayList<>();
        try {
            dbb.otvoriKonekciju();
            restorani = dbb.vratiSveRestorane();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
            dbb.zatvoriKonekciju();
        }
        for (Restoran r : restorani) {
            rsRestorani.put(r.getNazivRestorana(), r.getIDRestorana());
        }

        return rsRestorani;
    }

    public HashMap<String, Integer> vratiZaposlene() {
        HashMap<String, Integer> rsZaposleni = new HashMap<>();
        List<Zaposleni> zaposleni = new ArrayList<>();
        try {
            dbb.otvoriKonekciju();
            zaposleni = dbb.vratiSveZaposlene();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
            dbb.zatvoriKonekciju();
        }
        for (Zaposleni z : zaposleni) {
            rsZaposleni.put(z.getPrezime(), z.getZaposleniID());
        }

        return rsZaposleni;
    }

    // unos
    public void dodajNovuStavkuIzvestaja(Date datum, String dan, int brojDorucaka) {

        i.setRBRIzvestaja(rbrIzvestaja);
        i.sacuvajStavkuIzvestaja(datum, dan, brojDorucaka);
        ukupanBrojDorucaka = i.getUkupanBrojDorucaka();
    }

    // izmena
    public void izmeniStavkuIzvestaja(int rbr, Date datum,
            String dan, int brojDorucaka) {
        i.setRBRIzvestaja(rbrIzvestaja);
        i.izmeniStavku(rbr, datum, dan, brojDorucaka);
        ukupanBrojDorucaka = i.getUkupanBrojDorucaka();
    }

    public void obrisiStavkuIzvestaja(int rbrStavke, Date datum) {
        i.obrisiStavkuIzvestaja(rbrStavke, datum);
    }

    public int vratiRBRIzvestaja() {
        rbrIzvestaja = Integer.MIN_VALUE;
        try {
            dbb.otvoriKonekciju();
            dbb.pokreniTransakciju();
            rbrIzvestaja = dbb.vratiRBRIzvestaja();
            System.out.println("Iz baze: " + rbrIzvestaja);
            i.setRBRIzvestaja(rbrIzvestaja);
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
            dbb.zatvoriKonekciju();
        }
        return rbrIzvestaja;
    }

    public IzvestajPrikaz vratiIzvestaj(int rbrIzvestaja) {
        for (IzvestajPrikaz i : izvestaji) {
            if (i.getRbrIzvestaja() == rbrIzvestaja) {
                return i;
            }
        }
        return null;
    }

    public void postaviRBRIzvestaja(int rbrIzvestaja) {
        i.setRBRIzvestaja(rbrIzvestaja);
    }

    public void postaviUkupanBrojDorucaka(int ukupanBrojDorucaka) {
        i.setUkupanBrojDorucaka(ukupanBrojDorucaka);
    }

    public IzvestajPrikaz dajIzvestaj() {

        System.out.println("Redni broj u kontroleru: " + rbrIzvestaja);

        IzvestajPrikaz ip = new IzvestajPrikaz();

        for (IzvestajPrikaz izvestaj : izvestaji) {

            if (izvestaj.getRbrIzvestaja() == rbrIzvestaja) {

                ip.setRbrIzvestaja(rbrIzvestaja);
                ip.setDatumOd(izvestaj.getDatumOd());
                ip.setDatumDo(izvestaj.getDatumDo());
                // ip.setStatus(izvestaj.getStatus());
                ip.setIdRestorana(izvestaj.getIdRestorana());
                ip.setNazivRestorana(izvestaj.getNazivRestorana());
                ip.setZaposleniID(izvestaj.getZaposleniID());
                ip.setPrezimeZaposlenog(izvestaj.getPrezimeZaposlenog());
                ip.setStavkeIzvestaja(izvestaj.getStavkeIzvestaja());
                ip.setUkupanBrojDorucaka(izvestaj.getUkupanBrojDorucaka());

                List<StavkaIzvestaja> lista = new ArrayList<>();
                for (Object stRaw : izvestaj.getStavkeIzvestaja()) {
                    StavkaIzvestaja si = new StavkaIzvestaja();
                    StavkaIzvestajaPrikaz st = (StavkaIzvestajaPrikaz) stRaw;
                    si.setDatum(st.getDatum());
                    si.setDan(st.getDan());
                    si.setBrojDorucaka(st.getBrojDorucaka());
                    si.setRbrIzvestaja(rbrIzvestaja);
                    si.setRbrStavke(st.getRbrStavke());
                    if (!lista.contains(si)) {
                        lista.add(si);
                    }
                }

                i.setRBRIzvestaja(rbrIzvestaja);
                i.setStavkaIzvestajaList(lista);

                System.out.println("Ukupan broj dorucaka: " + ukupanBrojDorucaka);
            }
        }
        return ip;
    }

}
