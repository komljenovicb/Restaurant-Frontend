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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc acer
 */
@Entity
@Table(name = "sluzba_za_inventar")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SluzbaZaInventar.findAll", query = "SELECT s FROM SluzbaZaInventar s")
    , @NamedQuery(name = "SluzbaZaInventar.findBySluzbaID", query = "SELECT s FROM SluzbaZaInventar s WHERE s.sluzbaID = :sluzbaID")
    , @NamedQuery(name = "SluzbaZaInventar.findByNaziv", query = "SELECT s FROM SluzbaZaInventar s WHERE s.naziv = :naziv")})
public class SluzbaZaInventar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SluzbaID")
    private Integer sluzbaID;
    @Size(max = 255)
    @Column(name = "Naziv")
    private String naziv;
    @JoinColumn(name = "SluzbaID", referencedColumnName = "SluzbaID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private SluzbaOdrzavanja sluzbaOdrzavanja;

    public SluzbaZaInventar() {
    }

    public SluzbaZaInventar(Integer sluzbaID) {
        this.sluzbaID = sluzbaID;
    }

    public Integer getSluzbaID() {
        return sluzbaID;
    }

    public void setSluzbaID(Integer sluzbaID) {
        this.sluzbaID = sluzbaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public SluzbaOdrzavanja getSluzbaOdrzavanja() {
        return sluzbaOdrzavanja;
    }

    public void setSluzbaOdrzavanja(SluzbaOdrzavanja sluzbaOdrzavanja) {
        this.sluzbaOdrzavanja = sluzbaOdrzavanja;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sluzbaID != null ? sluzbaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SluzbaZaInventar)) {
            return false;
        }
        SluzbaZaInventar other = (SluzbaZaInventar) object;
        if ((this.sluzbaID == null && other.sluzbaID != null) || (this.sluzbaID != null && !this.sluzbaID.equals(other.sluzbaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SluzbaZaInventar[ sluzbaID=" + sluzbaID + " ]";
    }
    
}
