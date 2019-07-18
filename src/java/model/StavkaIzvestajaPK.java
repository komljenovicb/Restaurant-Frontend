/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

/**
 *
 * @author pc acer
 */
@Embeddable
public class StavkaIzvestajaPK implements Serializable {

    private static final long serialVersionUID = 1L;
    private int rbrIzvestaja;
    private int rbrStavke;

    public StavkaIzvestajaPK() {
    }

    public StavkaIzvestajaPK(int rbrIzvestaja, int rbrStavke) {
        this.rbrIzvestaja = rbrIzvestaja;
        this.rbrStavke = rbrStavke;
    }

    @Id
    @Column(name = "RBRIzvestaja")
    public int getRbrIzvestaja() {
        return rbrIzvestaja;
    }

    public void setRbrIzvestaja(int rbrIzvestaja) {
        this.rbrIzvestaja = rbrIzvestaja;
    }

    @Id
    @Column(name = "RBRStavke")
    public int getRbrStavke() {
        return rbrStavke;
    }

    public void setRbrStavke(int rbrStavke) {
        this.rbrStavke = rbrStavke;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) rbrIzvestaja;
        hash += (int) rbrStavke;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkaIzvestajaPK)) {
            return false;
        }
        StavkaIzvestajaPK other = (StavkaIzvestajaPK) object;
        if (this.rbrIzvestaja != other.rbrIzvestaja) {
            return false;
        }
        if (this.rbrStavke != other.rbrStavke) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "config.StavkaIzvestajaPK[ rbrIzvestaja=" + rbrIzvestaja + ", rbrStavke=" + rbrStavke + " ]";
    }
    
}
