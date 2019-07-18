/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pc acer
 */
@Entity
@Table(name = "zaduzenje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaduzenje.findAll", query = "SELECT z FROM Zaduzenje z")
    , @NamedQuery(name = "Zaduzenje.findByZaduzenjeID", query = "SELECT z FROM Zaduzenje z WHERE z.zaduzenjeID = :zaduzenjeID")
    , @NamedQuery(name = "Zaduzenje.findByFunkcija", query = "SELECT z FROM Zaduzenje z WHERE z.funkcija = :funkcija")})
public class Zaduzenje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZaduzenjeID")
    private Integer zaduzenjeID;
    @Size(max = 255)
    @Column(name = "Funkcija")
    private String funkcija;
    @OneToMany(mappedBy = "zaduzenjeID")
    private List<Zaposleni> zaposleniList;

    public Zaduzenje() {
    }

    public Zaduzenje(Integer zaduzenjeID) {
        this.zaduzenjeID = zaduzenjeID;
    }

    public Integer getZaduzenjeID() {
        return zaduzenjeID;
    }

    public void setZaduzenjeID(Integer zaduzenjeID) {
        this.zaduzenjeID = zaduzenjeID;
    }

    public String getFunkcija() {
        return funkcija;
    }

    public void setFunkcija(String funkcija) {
        this.funkcija = funkcija;
    }

    @XmlTransient
    public List<Zaposleni> getZaposleniList() {
        return zaposleniList;
    }

    public void setZaposleniList(List<Zaposleni> zaposleniList) {
        this.zaposleniList = zaposleniList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zaduzenjeID != null ? zaduzenjeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zaduzenje)) {
            return false;
        }
        Zaduzenje other = (Zaduzenje) object;
        if ((this.zaduzenjeID == null && other.zaduzenjeID != null) || (this.zaduzenjeID != null && !this.zaduzenjeID.equals(other.zaduzenjeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Zaduzenje[ zaduzenjeID=" + zaduzenjeID + " ]";
    }
    
}
