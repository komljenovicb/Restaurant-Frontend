/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc acer
 */
@Entity
@Table(name = "usluga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usluga.findAll", query = "SELECT u FROM Usluga u")
    , @NamedQuery(name = "Usluga.findByUslugaID", query = "SELECT u FROM Usluga u WHERE u.uslugaID = :uslugaID")
    , @NamedQuery(name = "Usluga.findByNazivUsluge", query = "SELECT u FROM Usluga u WHERE u.nazivUsluge = :nazivUsluge")
    , @NamedQuery(name = "Usluga.findByOpisUsluge", query = "SELECT u FROM Usluga u WHERE u.opisUsluge = :opisUsluge")})
public class Usluga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UslugaID")
    private Integer uslugaID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NazivUsluge")
    private String nazivUsluge;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "OpisUsluge")
    private String opisUsluge;
    @JoinColumn(name = "JedinicaMereID", referencedColumnName = "sifraJM")
    @ManyToOne(optional = false)
    private JedinicaMere jedinicaMereID;

    public Usluga() {
    }

    public Usluga(Integer uslugaID) {
        this.uslugaID = uslugaID;
    }

    public Usluga(Integer uslugaID, String nazivUsluge, String opisUsluge) {
        this.uslugaID = uslugaID;
        this.nazivUsluge = nazivUsluge;
        this.opisUsluge = opisUsluge;
    }

    public Integer getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(Integer uslugaID) {
        this.uslugaID = uslugaID;
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

    public JedinicaMere getJedinicaMereID() {
        return jedinicaMereID;
    }

    public void setJedinicaMereID(JedinicaMere jedinicaMereID) {
        this.jedinicaMereID = jedinicaMereID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uslugaID != null ? uslugaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usluga)) {
            return false;
        }
        Usluga other = (Usluga) object;
        if ((this.uslugaID == null && other.uslugaID != null) || (this.uslugaID != null && !this.uslugaID.equals(other.uslugaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Usluga[ uslugaID=" + uslugaID + " ]";
    }
    
}
