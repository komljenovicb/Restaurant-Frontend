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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pc acer
 */
@Entity
@Table(name = "sluzba_odrzavanja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SluzbaOdrzavanja.findAll", query = "SELECT s FROM SluzbaOdrzavanja s")
    , @NamedQuery(name = "SluzbaOdrzavanja.findBySluzbaID", query = "SELECT s FROM SluzbaOdrzavanja s WHERE s.sluzbaID = :sluzbaID")
    , @NamedQuery(name = "SluzbaOdrzavanja.findByBrojZaposlenih", query = "SELECT s FROM SluzbaOdrzavanja s WHERE s.brojZaposlenih = :brojZaposlenih")})
public class SluzbaOdrzavanja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "SluzbaID")
    private Integer sluzbaID;
    @Column(name = "BrojZaposlenih")
    private Integer brojZaposlenih;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sluzbaOdrzavanja")
    private SluzbaZaInventar sluzbaZaInventar;
    @OneToMany(mappedBy = "sluzbaID")
    private List<Zaposleni> zaposleniList;

    public SluzbaOdrzavanja() {
    }

    public SluzbaOdrzavanja(Integer sluzbaID) {
        this.sluzbaID = sluzbaID;
    }

    public Integer getSluzbaID() {
        return sluzbaID;
    }

    public void setSluzbaID(Integer sluzbaID) {
        this.sluzbaID = sluzbaID;
    }

    public Integer getBrojZaposlenih() {
        return brojZaposlenih;
    }

    public void setBrojZaposlenih(Integer brojZaposlenih) {
        this.brojZaposlenih = brojZaposlenih;
    }

    public SluzbaZaInventar getSluzbaZaInventar() {
        return sluzbaZaInventar;
    }

    public void setSluzbaZaInventar(SluzbaZaInventar sluzbaZaInventar) {
        this.sluzbaZaInventar = sluzbaZaInventar;
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
        hash += (sluzbaID != null ? sluzbaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SluzbaOdrzavanja)) {
            return false;
        }
        SluzbaOdrzavanja other = (SluzbaOdrzavanja) object;
        if ((this.sluzbaID == null && other.sluzbaID != null) || (this.sluzbaID != null && !this.sluzbaID.equals(other.sluzbaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SluzbaOdrzavanja[ sluzbaID=" + sluzbaID + " ]";
    }
    
}
