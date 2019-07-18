/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prikaz;

/**
 *
 * @author Bojana Komljenovic
 */
public class JedinicaMerePrikaz {

    private int sifraJM;
    private String nazivJM;

    public JedinicaMerePrikaz() {
    }

    public JedinicaMerePrikaz(int sifraJM, String nazivJM) {
        this.sifraJM = sifraJM;
        this.nazivJM = nazivJM;
    }

    public int getSifraJM() {
        return sifraJM;
    }

    public void setSifraJM(int sifraJM) {
        this.sifraJM = sifraJM;
    }

    public String getNazivJM() {
        return nazivJM;
    }

    public void setNazivJM(String nazivJM) {
        this.nazivJM = nazivJM;
    }

}
