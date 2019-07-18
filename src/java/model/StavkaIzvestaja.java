/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pc acer
 */
@Entity
@Table(name = "stavka_izvestaja")
@IdClass(StavkaIzvestajaPK.class)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StavkaIzvestaja.findAll", query = "SELECT s FROM StavkaIzvestaja s")
    , @NamedQuery(name = "StavkaIzvestaja.findByRBRIzvestaja", query = "SELECT s FROM StavkaIzvestaja s WHERE s.rbrIzvestaja = :rBRIzvestaja")
    , @NamedQuery(name = "StavkaIzvestaja.findByRBRStavke", query = "SELECT s FROM StavkaIzvestaja s WHERE s.rbrStavke = :rBRStavke")
    , @NamedQuery(name = "StavkaIzvestaja.findByDatum", query = "SELECT s FROM StavkaIzvestaja s WHERE s.datum = :datum")
    , @NamedQuery(name = "StavkaIzvestaja.findByDan", query = "SELECT s FROM StavkaIzvestaja s WHERE s.dan = :dan")
    , @NamedQuery(name = "StavkaIzvestaja.findByBrojDorucaka", query = "SELECT s FROM StavkaIzvestaja s WHERE s.brojDorucaka = :brojDorucaka")})

public class StavkaIzvestaja implements Serializable {

    private static final long serialVersionUID = 1L;
    private int rbrIzvestaja;
    private int rbrStavke;
    private Date datum;
    private String dan;
    private Integer brojDorucaka;
    private String status = "";
    private IzvestajOBrojuDorucaka izvestajOBrojuDorucaka;

    public StavkaIzvestaja() {
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

    @Column(name = "Datum")
    @Temporal(TemporalType.DATE)
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Column(name = "Dan")
    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    @Column(name = "BrojDorucaka")
    public Integer getBrojDorucaka() {
        return brojDorucaka;
    }

    public void setBrojDorucaka(Integer brojDorucaka) {
        this.brojDorucaka = brojDorucaka;
    }

    @Transient
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "RBRIzvestaja", referencedColumnName = "RBRIzvestaja", nullable = false, insertable = false, updatable = false)
    public IzvestajOBrojuDorucaka getIzvestajOBrojuDorucaka() {
        return izvestajOBrojuDorucaka;
    }

    public void setIzvestajOBrojuDorucaka(IzvestajOBrojuDorucaka izvestajOBrojuDorucaka) {
        this.izvestajOBrojuDorucaka = izvestajOBrojuDorucaka;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StavkaIzvestaja other = (StavkaIzvestaja) obj;
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
        return "model.StavkaIzvestaja stavkaIzvestajaPK=" + " " + rbrStavke + " " + rbrIzvestaja;
    }

}
