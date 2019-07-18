/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pc acer
 */
@Entity
@Table(name = "jedinica_mere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JedinicaMere.findAll", query = "SELECT j FROM JedinicaMere j")
    , @NamedQuery(name = "JedinicaMere.findBySifraJM", query = "SELECT j FROM JedinicaMere j WHERE j.sifraJM = :sifraJM")
    , @NamedQuery(name = "JedinicaMere.findByNazivJM", query = "SELECT j FROM JedinicaMere j WHERE j.nazivJM = :nazivJM")})
public class JedinicaMere implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sifraJM")
    private Integer sifraJM;
    @Size(max = 255)
    @Column(name = "nazivJM")
    private String nazivJM;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jedinicaMereID")
    private List<Usluga> uslugaList;

    public JedinicaMere() {
    }

    public JedinicaMere(Integer sifraJM) {
        this.sifraJM = sifraJM;
    }

    public Integer getSifraJM() {
        return sifraJM;
    }

    public void setSifraJM(Integer sifraJM) {
        this.sifraJM = sifraJM;
    }

    public String getNazivJM() {
        return nazivJM;
    }

    public void setNazivJM(String nazivJM) {
        this.nazivJM = nazivJM;
    }

    @XmlTransient
    public List<Usluga> getUslugaList() {
        return uslugaList;
    }

    public void setUslugaList(List<Usluga> uslugaList) {
        this.uslugaList = uslugaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifraJM != null ? sifraJM.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JedinicaMere)) {
            return false;
        }
        JedinicaMere other = (JedinicaMere) object;
        if ((this.sifraJM == null && other.sifraJM != null) || (this.sifraJM != null && !this.sifraJM.equals(other.sifraJM))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.JedinicaMere[ sifraJM=" + sifraJM + " ]";
    }
    
}
