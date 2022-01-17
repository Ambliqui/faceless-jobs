package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mefisto
 */
@Entity
@Table(name = "inscripcion_oferta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InscripcionOferta.findAll", query = "SELECT i FROM InscripcionOferta i"),
    @NamedQuery(name = "InscripcionOferta.findByIdInscripcion", query = "SELECT i FROM InscripcionOferta i WHERE i.idInscripcion = :idInscripcion"),
    @NamedQuery(name = "InscripcionOferta.findByOfertaIdInscripcion", query = "SELECT i FROM InscripcionOferta i WHERE i.inscripcionOfertaPK.ofertaIdInscripcion = :ofertaIdInscripcion"),
    @NamedQuery(name = "InscripcionOferta.findByCandidatoIdInscripcion", query = "SELECT i FROM InscripcionOferta i WHERE i.inscripcionOfertaPK.candidatoIdInscripcion = :candidatoIdInscripcion"),
    @NamedQuery(name = "InscripcionOferta.findByFechaInscripcion", query = "SELECT i FROM InscripcionOferta i WHERE i.fechaInscripcion = :fechaInscripcion")})
public class InscripcionOferta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InscripcionOfertaPK inscripcionOfertaPK;
    @Basic(optional = false)
    @Column(name = "id_inscripcion")
    private int idInscripcion;
    @Column(name = "fecha_inscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;
    @JoinColumn(name = "candidato_id_inscripcion", referencedColumnName = "id_candidato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Candidato candidato;
    @JoinColumn(name = "oferta_id_inscripcion", referencedColumnName = "id_oferta_empleo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OfertaEmpleo ofertaEmpleo;

    public InscripcionOferta() {
    }

    public InscripcionOferta(InscripcionOfertaPK inscripcionOfertaPK) {
        this.inscripcionOfertaPK = inscripcionOfertaPK;
    }

    public InscripcionOferta(InscripcionOfertaPK inscripcionOfertaPK, int idInscripcion) {
        this.inscripcionOfertaPK = inscripcionOfertaPK;
        this.idInscripcion = idInscripcion;
    }

    public InscripcionOferta(int ofertaIdInscripcion, int candidatoIdInscripcion) {
        this.inscripcionOfertaPK = new InscripcionOfertaPK(ofertaIdInscripcion, candidatoIdInscripcion);
    }

    public InscripcionOfertaPK getInscripcionOfertaPK() {
        return inscripcionOfertaPK;
    }

    public void setInscripcionOfertaPK(InscripcionOfertaPK inscripcionOfertaPK) {
        this.inscripcionOfertaPK = inscripcionOfertaPK;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public OfertaEmpleo getOfertaEmpleo() {
        return ofertaEmpleo;
    }

    public void setOfertaEmpleo(OfertaEmpleo ofertaEmpleo) {
        this.ofertaEmpleo = ofertaEmpleo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inscripcionOfertaPK != null ? inscripcionOfertaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InscripcionOferta)) {
            return false;
        }
        InscripcionOferta other = (InscripcionOferta) object;
        if ((this.inscripcionOfertaPK == null && other.inscripcionOfertaPK != null) || (this.inscripcionOfertaPK != null && !this.inscripcionOfertaPK.equals(other.inscripcionOfertaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pruebasentidades.modelo.InscripcionOferta[ inscripcionOfertaPK=" + inscripcionOfertaPK + " ]";
    }
    
}
