/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.util.Date;
import java.util.List;
import model.StavkaIzvestaja;

/**
 *
 * @author Bojana Komljenovic
 */
public class Validator {

    private static Validator instanca;

    private Validator() {

    }

    public static Validator getInstanca() {
        if (instanca == null) {
            instanca = new Validator();
        }
        return instanca;
    }

    public boolean validacijaDatuma(Date datumOd, Date datumDo) {

        if (datumOd.before(datumDo)) {
            return true;
        }

        return false;
    }

    // Provera naziva dana
    public boolean ispravnoUnetNazivDana(String dan) {

        String[] listaMogucih = {"Ponedeljak", "Utorak", "Sreda", "Cetvrtak",
            "Petak", "Subota", "Nedelja"};

        for (String d : listaMogucih) {
            if (dan.equalsIgnoreCase(d)) {
                return true;
            }
        }

        return false;
    }

    public boolean sacuvanaBarJednaStavkaIzvestaja(List<StavkaIzvestaja> lista) {
        if (lista.size() < 1) {
            return false;
        }
        return true;
    }

    // Korisnik ne sme da unese dva ista datuma
    public boolean unetaDvaIstaDatuma(List<StavkaIzvestaja> lista) {
        boolean postoji = false;
        for (int i = 0; i < lista.size(); i++) {
            StavkaIzvestaja si1 = lista.get(i);
            boolean pronadjen = false;
            for (int j = i + 1; j < lista.size() - 1; j++) {
                StavkaIzvestaja si2 = lista.get(j);
                if (si1.getDatum().equals(si2.getDatum())) {
                    pronadjen = true;
                }
                postoji = pronadjen;
            }
        }
        return postoji;
    }

    // Korisnik ne sme da unese dva ista dana
    public boolean unetaDvaIstaDana(List<StavkaIzvestaja> lista) {
        boolean postoji = false;
        for (int i = 0; i < lista.size(); i++) {
            StavkaIzvestaja si1 = lista.get(i);
            boolean pronadjen = false;
            for (int j = i + 1; j < lista.size() - 1; j++) {
                StavkaIzvestaja si2 = lista.get(j);
                if (si1.getDan().equals(si2.getDan())) {
                    pronadjen = true;
                }
                postoji = pronadjen;
            }
        }
        return postoji;
    }

    public boolean unetDatumNakonDanasnjeg(Date datum) {
        if (datum.after(new Date())) {
            return true;
        }
        return false;
    }

}
