/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pc acer
 */
@Entity
@Table(name = "izvestaj_o_broju_dorucaka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IzvestajOBrojuDorucaka.findAll", query = "SELECT i FROM IzvestajOBrojuDorucaka i")
    , @NamedQuery(name = "IzvestajOBrojuDorucaka.findByRBRIzvestaja", query = "SELECT i FROM IzvestajOBrojuDorucaka i WHERE i.rBRIzvestaja = :rBRIzvestaja")
    , @NamedQuery(name = "IzvestajOBrojuDorucaka.findByDatumOd", query = "SELECT i FROM IzvestajOBrojuDorucaka i WHERE i.datumOd = :datumOd")
    , @NamedQuery(name = "IzvestajOBrojuDorucaka.findByDatumDo", query = "SELECT i FROM IzvestajOBrojuDorucaka i WHERE i.datumDo = :datumDo")
    , @NamedQuery(name = "IzvestajOBrojuDorucaka.findByUkupanBrojDorucaka", query = "SELECT i FROM IzvestajOBrojuDorucaka i WHERE i.ukupanBrojDorucaka = :ukupanBrojDorucaka")
    , @NamedQuery(name = "IzvestajOBrojuDorucaka.findByStatus", query = "SELECT i FROM IzvestajOBrojuDorucaka i WHERE i.status = :status")})
public class IzvestajOBrojuDorucaka implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RBRIzvestaja")
    private Integer rBRIzvestaja;
    @Column(name = "DatumOd")
    @Temporal(TemporalType.DATE)
    private Date datumOd;
    @Column(name = "DatumDo")
    @Temporal(TemporalType.DATE)
    private Date datumDo;
    @Column(name = "UkupanBrojDorucaka")
    private Integer ukupanBrojDorucaka;
    @Size(max = 255)
    @Column(name = "Status")
    private String status;
    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER, mappedBy = "izvestajOBrojuDorucaka")
    private List<StavkaIzvestaja> stavkaIzvestajaList;
    @JoinColumn(name = "IDRestorana", referencedColumnName = "IDRestorana")
    @ManyToOne
    private Restoran iDRestorana;
    @JoinColumn(name = "ZaposleniID", referencedColumnName = "ZaposleniID")
    @ManyToOne
    private Zaposleni zaposleniID;

    public IzvestajOBrojuDorucaka() {
        stavkaIzvestajaList = new ArrayList<>();
    }

    public IzvestajOBrojuDorucaka(Integer rBRIzvestaja) {
        this.rBRIzvestaja = rBRIzvestaja;
    }

    public Integer getRBRIzvestaja() {
        return rBRIzvestaja;
    }

    public void setRBRIzvestaja(Integer rBRIzvestaja) {
        this.rBRIzvestaja = rBRIzvestaja;
    }

    public Date getDatumOd() {
        return datumOd;
    }

    public void setDatumOd(Date datumOd) {
        this.datumOd = datumOd;
    }

    public Date getDatumDo() {
        return datumDo;
    }

    public void setDatumDo(Date datumDo) {
        this.datumDo = datumDo;
    }

    public Integer getUkupanBrojDorucaka() {
        if (stavkaIzvestajaList != null) {
            int suma = 0;
            for (StavkaIzvestaja si : stavkaIzvestajaList) {
                suma += si.getBrojDorucaka();
            }
            ukupanBrojDorucaka = suma;
        }
        return ukupanBrojDorucaka;
    }

    public void setUkupanBrojDorucaka(Integer ukupanBrojDorucaka) {
        this.ukupanBrojDorucaka = ukupanBrojDorucaka;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<StavkaIzvestaja> getStavkaIzvestajaList() {
        return this.stavkaIzvestajaList;
    }

    public void setStavkaIzvestajaList(List<StavkaIzvestaja> stavkaIzvestajaList) {
        this.stavkaIzvestajaList = stavkaIzvestajaList;
    }

    public Restoran getIDRestorana() {
        return iDRestorana;
    }

    public void setIDRestorana(Restoran iDRestorana) {
        this.iDRestorana = iDRestorana;
    }

    public Zaposleni getZaposleniID() {
        return zaposleniID;
    }

    public void setZaposleniID(Zaposleni zaposleniID) {
        this.zaposleniID = zaposleniID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rBRIzvestaja != null ? rBRIzvestaja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IzvestajOBrojuDorucaka)) {
            return false;
        }
        IzvestajOBrojuDorucaka other = (IzvestajOBrojuDorucaka) object;
        if ((this.rBRIzvestaja == null && other.rBRIzvestaja != null) || (this.rBRIzvestaja != null && !this.rBRIzvestaja.equals(other.rBRIzvestaja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.IzvestajOBrojuDorucaka[ rBRIzvestaja=" + rBRIzvestaja + " ]";
    }

    public void sacuvajStavkuIzvestaja(Date datum, String dan, int brojDorucaka) {
        int rbr = dajRBR();
        StavkaIzvestaja si = new StavkaIzvestaja();
        si.setRbrIzvestaja(rBRIzvestaja);
        si.setRbrStavke(rbr);
        si.setDatum(datum);
        si.setDan(dan);
        si.setBrojDorucaka(brojDorucaka);
        ubaciUKolekciju(si);
    }

    private void ubaciUKolekciju(StavkaIzvestaja si) {
        si.setStatus("insert");
        si.setRbrIzvestaja(rBRIzvestaja);
        stavkaIzvestajaList.add(si);
        System.out.println("Unos stavke");
    }

    private int dajRBR() {
        return stavkaIzvestajaList.size() + 1;
    }

    public void izmeniStavku(int rbr, Date datum, String dan, int brojDorucaka) {

        for (Object siRaw : stavkaIzvestajaList) {
            StavkaIzvestaja si = (StavkaIzvestaja) siRaw;
            if (si.getRbrStavke() == rbr) {
                si.setDatum(datum);
                si.setDan(dan);
                si.setBrojDorucaka(brojDorucaka);
                si.setStatus("update");
                System.out.println("Izmena stavke");
            }
        }

    }

    public void obrisiStavkuIzvestaja(int rbrStavke, Date datum) {

        for (Object siRaw : stavkaIzvestajaList) {
            StavkaIzvestaja si = (StavkaIzvestaja) siRaw;
            if (si.getRbrStavke() == rbrStavke) {
                si.setStatus("delete");
                System.out.println("Brisanje stavke");
            }
        }

    }

}
