package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mefisto
 */
@Entity
@Table(name = "habilidad_candidato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HabilidadCandidato.findAll", query = "SELECT h FROM HabilidadCandidato h"),
    @NamedQuery(name = "HabilidadCandidato.findByHabilidadCandidatoId", query = "SELECT h FROM HabilidadCandidato h WHERE h.habilidadCandidatoId = :habilidadCandidatoId"),
    @NamedQuery(name = "HabilidadCandidato.findByCandidatoIdHabilidad", query = "SELECT h FROM HabilidadCandidato h WHERE h.habilidadCandidatoPK.candidatoIdHabilidad = :candidatoIdHabilidad"),
    @NamedQuery(name = "HabilidadCandidato.findByHabilidadIdHabilidad", query = "SELECT h FROM HabilidadCandidato h WHERE h.habilidadCandidatoPK.habilidadIdHabilidad = :habilidadIdHabilidad"),
    @NamedQuery(name = "HabilidadCandidato.findByExperienciaCandidato", query = "SELECT h FROM HabilidadCandidato h WHERE h.experienciaCandidato = :experienciaCandidato")})
public class HabilidadCandidato implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HabilidadCandidatoPK habilidadCandidatoPK;
    @Basic(optional = false)
    @Column(name = "habilidad_candidato_id")
    private int habilidadCandidatoId;
    @Column(name = "experiencia_candidato")
    private Integer experienciaCandidato;
    @JoinColumn(name = "candidato_id_habilidad", referencedColumnName = "id_candidato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Candidato candidato;
    @JoinColumn(name = "habilidad_id_habilidad", referencedColumnName = "id_habilidad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Habilidad habilidad;

    public HabilidadCandidato() {
    }

    public HabilidadCandidato(HabilidadCandidatoPK habilidadCandidatoPK) {
        this.habilidadCandidatoPK = habilidadCandidatoPK;
    }

    public HabilidadCandidato(HabilidadCandidatoPK habilidadCandidatoPK, int habilidadCandidatoId) {
        this.habilidadCandidatoPK = habilidadCandidatoPK;
        this.habilidadCandidatoId = habilidadCandidatoId;
    }

    public HabilidadCandidato(int candidatoIdHabilidad, int habilidadIdHabilidad) {
        this.habilidadCandidatoPK = new HabilidadCandidatoPK(candidatoIdHabilidad, habilidadIdHabilidad);
    }

    public HabilidadCandidatoPK getHabilidadCandidatoPK() {
        return habilidadCandidatoPK;
    }

    public void setHabilidadCandidatoPK(HabilidadCandidatoPK habilidadCandidatoPK) {
        this.habilidadCandidatoPK = habilidadCandidatoPK;
    }

    public int getHabilidadCandidatoId() {
        return habilidadCandidatoId;
    }

    public void setHabilidadCandidatoId(int habilidadCandidatoId) {
        this.habilidadCandidatoId = habilidadCandidatoId;
    }

    public Integer getExperienciaCandidato() {
        return experienciaCandidato;
    }

    public void setExperienciaCandidato(Integer experienciaCandidato) {
        this.experienciaCandidato = experienciaCandidato;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Habilidad getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Habilidad habilidad) {
        this.habilidad = habilidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (habilidadCandidatoPK != null ? habilidadCandidatoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HabilidadCandidato)) {
            return false;
        }
        HabilidadCandidato other = (HabilidadCandidato) object;
        if ((this.habilidadCandidatoPK == null && other.habilidadCandidatoPK != null) || (this.habilidadCandidatoPK != null && !this.habilidadCandidatoPK.equals(other.habilidadCandidatoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pruebasentidades.modelo.HabilidadCandidato[ habilidadCandidatoPK=" + habilidadCandidatoPK + " ]";
    }
    
}
