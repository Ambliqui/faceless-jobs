package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Mefisto
 */
@Embeddable
public class InscripcionOfertaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "oferta_id_inscripcion")
    private int ofertaIdInscripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "candidato_id_inscripcion")
    private int candidatoIdInscripcion;

    public InscripcionOfertaPK() {
    }

    public InscripcionOfertaPK(int ofertaIdInscripcion, int candidatoIdInscripcion) {
        this.ofertaIdInscripcion = ofertaIdInscripcion;
        this.candidatoIdInscripcion = candidatoIdInscripcion;
    }

    public int getOfertaIdInscripcion() {
        return ofertaIdInscripcion;
    }

    public void setOfertaIdInscripcion(int ofertaIdInscripcion) {
        this.ofertaIdInscripcion = ofertaIdInscripcion;
    }

    public int getCandidatoIdInscripcion() {
        return candidatoIdInscripcion;
    }

    public void setCandidatoIdInscripcion(int candidatoIdInscripcion) {
        this.candidatoIdInscripcion = candidatoIdInscripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) ofertaIdInscripcion;
        hash += (int) candidatoIdInscripcion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InscripcionOfertaPK)) {
            return false;
        }
        InscripcionOfertaPK other = (InscripcionOfertaPK) object;
        if (this.ofertaIdInscripcion != other.ofertaIdInscripcion) {
            return false;
        }
        if (this.candidatoIdInscripcion != other.candidatoIdInscripcion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pruebasentidades.modelo.InscripcionOfertaPK[ ofertaIdInscripcion=" + ofertaIdInscripcion + ", candidatoIdInscripcion=" + candidatoIdInscripcion + " ]";
    }
    
}
