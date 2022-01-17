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
public class HabilidadCandidatoPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "candidato_id_habilidad")
    private int candidatoIdHabilidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habilidad_id_habilidad")
    private int habilidadIdHabilidad;

    public HabilidadCandidatoPK() {
    }

    public HabilidadCandidatoPK(int candidatoIdHabilidad, int habilidadIdHabilidad) {
        this.candidatoIdHabilidad = candidatoIdHabilidad;
        this.habilidadIdHabilidad = habilidadIdHabilidad;
    }

    public int getCandidatoIdHabilidad() {
        return candidatoIdHabilidad;
    }

    public void setCandidatoIdHabilidad(int candidatoIdHabilidad) {
        this.candidatoIdHabilidad = candidatoIdHabilidad;
    }

    public int getHabilidadIdHabilidad() {
        return habilidadIdHabilidad;
    }

    public void setHabilidadIdHabilidad(int habilidadIdHabilidad) {
        this.habilidadIdHabilidad = habilidadIdHabilidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) candidatoIdHabilidad;
        hash += (int) habilidadIdHabilidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HabilidadCandidatoPK)) {
            return false;
        }
        HabilidadCandidatoPK other = (HabilidadCandidatoPK) object;
        if (this.candidatoIdHabilidad != other.candidatoIdHabilidad) {
            return false;
        }
        if (this.habilidadIdHabilidad != other.habilidadIdHabilidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pruebasentidades.modelo.HabilidadCandidatoPK[ candidatoIdHabilidad=" + candidatoIdHabilidad + ", habilidadIdHabilidad=" + habilidadIdHabilidad + " ]";
    }
    
}
