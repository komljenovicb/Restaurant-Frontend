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
@Table(name = "restoran")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Restoran.findAll", query = "SELECT r FROM Restoran r")
    , @NamedQuery(name = "Restoran.findByIDRestorana", query = "SELECT r FROM Restoran r WHERE r.iDRestorana = :iDRestorana")
    , @NamedQuery(name = "Restoran.findByNazivRestorana", query = "SELECT r FROM Restoran r WHERE r.nazivRestorana = :nazivRestorana")})
public class Restoran implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDRestorana")
    private Integer iDRestorana;
    @Size(max = 255)
    @Column(name = "NazivRestorana")
    private String nazivRestorana;
    @OneToMany(mappedBy = "iDRestorana")
    private List<IzvestajOBrojuDorucaka> izvestajOBrojuDorucakaList;

    public Restoran() {
    }

    public Restoran(Integer iDRestorana) {
        this.iDRestorana = iDRestorana;
    }

    public Integer getIDRestorana() {
        return iDRestorana;
    }

    public void setIDRestorana(Integer iDRestorana) {
        this.iDRestorana = iDRestorana;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
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
        hash += (iDRestorana != null ? iDRestorana.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Restoran)) {
            return false;
        }
        Restoran other = (Restoran) object;
        if ((this.iDRestorana == null && other.iDRestorana != null) || (this.iDRestorana != null && !this.iDRestorana.equals(other.iDRestorana))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Restoran[ iDRestorana=" + iDRestorana + " ]";
    }
    
}
