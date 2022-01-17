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
public class HabilidadOfertaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "oferta_id_habilidad")
    private int ofertaIdHabilidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habilidad_id_habilidad")
    private int habilidadIdHabilidad;

    public HabilidadOfertaPK() {
    }

    public HabilidadOfertaPK(int ofertaIdHabilidad, int habilidadIdHabilidad) {
        this.ofertaIdHabilidad = ofertaIdHabilidad;
        this.habilidadIdHabilidad = habilidadIdHabilidad;
    }

    public int getOfertaIdHabilidad() {
        return ofertaIdHabilidad;
    }

    public void setOfertaIdHabilidad(int ofertaIdHabilidad) {
        this.ofertaIdHabilidad = ofertaIdHabilidad;
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
        hash += (int) ofertaIdHabilidad;
        hash += (int) habilidadIdHabilidad;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HabilidadOfertaPK)) {
            return false;
        }
        HabilidadOfertaPK other = (HabilidadOfertaPK) object;
        if (this.ofertaIdHabilidad != other.ofertaIdHabilidad) {
            return false;
        }
        if (this.habilidadIdHabilidad != other.habilidadIdHabilidad) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pruebasentidades.modelo.HabilidadOfertaPK[ ofertaIdHabilidad=" + ofertaIdHabilidad + ", habilidadIdHabilidad=" + habilidadIdHabilidad + " ]";
    }
    
}
