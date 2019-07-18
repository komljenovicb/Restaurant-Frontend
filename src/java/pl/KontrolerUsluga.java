/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl;

import dbb.DBBroker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import javax.ws.rs.core.Response;
import model.JedinicaMere;
import model.Usluga;
import org.json.simple.JSONObject;
import prikaz.UslugaPrikaz;
import rest.RESTKontrolerUsluga;

/**
 *
 * @author Bojana Komljenovic
 */
public class KontrolerUsluga {

    DBBroker dbb;
    Usluga u;

    RESTKontrolerUsluga rk;

    public KontrolerUsluga() {
        rk = new RESTKontrolerUsluga();
        dbb = new DBBroker();
        u = new Usluga();
    }

    public void setUslugaID(int uslugaID) {
        u.setUslugaID(uslugaID);
    }

    public void setNazivUsluge(String nazivUsluge) {
        u.setNazivUsluge(nazivUsluge);
    }

    public void setOpisUsluge(String opisUsluge) {
        u.setOpisUsluge(opisUsluge);
    }

    public void setJM(int jm) {
        u.setJedinicaMereID(new JedinicaMere(jm));
    }

    public HashMap<String, Integer> vratiSveJM() {
        HashMap<String, Integer> jediniceMereMapa = new HashMap<>();
        List<JedinicaMere> rsListaJM = new ArrayList<>();
        try {
            dbb.otvoriKonekciju();
            rsListaJM = dbb.vratiSveJM();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (JedinicaMere jm : rsListaJM) {
            jediniceMereMapa.put(jm.getNazivJM(), jm.getSifraJM());
        }

        return jediniceMereMapa;
    }
    
    public boolean sacuvajUslugu() {

        JSONObject json = new JSONObject();
        json.put("nazivUsluge", u.getNazivUsluge());
        json.put("opisUsluge", u.getOpisUsluge());
        json.put("jedinicaMere", u.getJedinicaMereID().getSifraJM());

        System.out.println(json.toJSONString());
        Response res = rk.sacuvajUslugu_JSON(json.toJSONString());

        return res.getStatus() == Response.Status.OK.getStatusCode();

    }

    public List<UslugaPrikaz> vratiSveUsluge() {

        List<Usluga> usluge = new ArrayList<>();

        try {
            dbb.otvoriKonekciju();
            usluge = dbb.vratiSveUsluge();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
            dbb.zatvoriKonekciju();
        }

        List<UslugaPrikaz> uslugePrikaz = new ArrayList<>();
        for (Usluga u : usluge) {
            UslugaPrikaz up = new UslugaPrikaz();
            up.setUslugaID(u.getUslugaID());
            up.setNazivUsluge(u.getNazivUsluge());
            up.setOpisUsluge(u.getOpisUsluge());
            up.setSifraJM(u.getJedinicaMereID().getSifraJM());
            up.setNazivJM(u.getJedinicaMereID().getNazivJM());
            uslugePrikaz.add(up);

        }

        return uslugePrikaz;

    }

    public boolean izmeniUslugu() {
        
        JSONObject json = new JSONObject();
        json.put("uslugaID", u.getUslugaID());
        System.out.println("uslugaID" + u.getUslugaID());
        json.put("nazivUsluge", u.getNazivUsluge());
        json.put("opisUsluge", u.getOpisUsluge());
        json.put("jedinicaMere", u.getJedinicaMereID().getSifraJM());

        System.out.println("Usluga za izmenu: " 
                + json.toJSONString() + "ID usluge: " 
                + u.getUslugaID());
        
        Response res = rk.izmeniUslugu_JSON(json.toJSONString(), 
                u.getUslugaID());

        return res.getStatus() == Response.Status.OK.getStatusCode();
        
    }

    public boolean obrisiUslugu() {

        Response res = rk.obrisiUslugu(u.getUslugaID());

        return res.getStatus() == Response.Status.OK.getStatusCode();

    }

    public Usluga kreirajUslugu(int uslugaID, String nazivUsluge,
            String opisUsluge, 
            JedinicaMere jm) {

        Usluga u = new Usluga();
        u.setUslugaID(uslugaID);
        u.setNazivUsluge(nazivUsluge);
        u.setOpisUsluge(opisUsluge);
        u.setJedinicaMereID(jm);
        return u;

    }

    public int vratiIDUsluge() {
        
        int idUsluge = Integer.MIN_VALUE;

        try {
            dbb.otvoriKonekciju();
            idUsluge = dbb.vratiIDUsluge();
            dbb.zatvoriKonekciju();
        } catch (Exception e) {
            e.printStackTrace();
            dbb.zatvoriKonekciju();
        }

        return idUsluge;
    }

    public UslugaPrikaz vratiUslugu(String nazivUsluge) {
        
        JSONObject json = new JSONObject();
        json.put("nazivUsluge", nazivUsluge);
        System.out.println(json.toJSONString());

        Response res = rk.vratiUslugu_JSON(Response.class, nazivUsluge);

        UslugaPrikaz up = res.readEntity(UslugaPrikaz.class);
        
        return up;

    }

}
