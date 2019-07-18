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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "zaposleni")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zaposleni.findAll", query = "SELECT z FROM Zaposleni z")
    , @NamedQuery(name = "Zaposleni.findByZaposleniID", query = "SELECT z FROM Zaposleni z WHERE z.zaposleniID = :zaposleniID")
    , @NamedQuery(name = "Zaposleni.findByIme", query = "SELECT z FROM Zaposleni z WHERE z.ime = :ime")
    , @NamedQuery(name = "Zaposleni.findByPrezime", query = "SELECT z FROM Zaposleni z WHERE z.prezime = :prezime")})
public class Zaposleni implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZaposleniID")
    private Integer zaposleniID;
    @Size(max = 255)
    @Column(name = "Ime")
    private String ime;
    @Size(max = 255)
    @Column(name = "Prezime")
    private String prezime;
    @JoinColumn(name = "ZaduzenjeID", referencedColumnName = "ZaduzenjeID")
    @ManyToOne
    private Zaduzenje zaduzenjeID;
    @JoinColumn(name = "SluzbaID", referencedColumnName = "SluzbaID")
    @ManyToOne
    private SluzbaOdrzavanja sluzbaID;
    @OneToMany(mappedBy = "zaposleniID")
    private List<IzvestajOBrojuDorucaka> izvestajOBrojuDorucakaList;

    public Zaposleni() {
    }

    public Zaposleni(Integer zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public Integer getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(Integer zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Zaduzenje getZaduzenjeID() {
        return zaduzenjeID;
    }

    public void setZaduzenjeID(Zaduzenje zaduzenjeID) {
        this.zaduzenjeID = zaduzenjeID;
    }

    public SluzbaOdrzavanja getSluzbaID() {
        return sluzbaID;
    }

    public void setSluzbaID(SluzbaOdrzavanja sluzbaID) {
        this.sluzbaID = sluzbaID;
    }

    @XmlTransient
    public List<IzvestajOBrojuDorucaka> getIzvestajOBrojuDorucakaList() {
        return izvestajOBrojuDorucakaList;
    }

    public void setIzvestajOBrojuDorucakaList(List<IzvestajOBrojuDorucaka> izvestajOBrojuDorucakaList) {
        this.izvestajOBrojuDorucakaList = izvestajOBrojuDorucakaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zaposleniID != null ? zaposleniID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zaposleni)) {
            return false;
        }
        Zaposleni other = (Zaposleni) object;
        if ((this.zaposleniID == null && other.zaposleniID != null) || (this.zaposleniID != null && !this.zaposleniID.equals(other.zaposleniID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Zaposleni[ zaposleniID=" + zaposleniID + " ]";
    }
    
}
