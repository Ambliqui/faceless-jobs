package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mefisto
 */
@Entity
@Table(name = "categoria_habilidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaHabilidad.findAll", query = "SELECT c FROM CategoriaHabilidad c"),
    @NamedQuery(name = "CategoriaHabilidad.findByIdCategoriaHabilidad", query = "SELECT c FROM CategoriaHabilidad c WHERE c.idCategoriaHabilidad = :idCategoriaHabilidad"),
    @NamedQuery(name = "CategoriaHabilidad.findByNombreCategoriaHabilidad", query = "SELECT c FROM CategoriaHabilidad c WHERE c.nombreCategoriaHabilidad = :nombreCategoriaHabilidad")})
public class CategoriaHabilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_categoria_habilidad")
    private Integer idCategoriaHabilidad;
    @Size(max = 45)
    @Column(name = "nombre_categoria_habilidad")
    private String nombreCategoriaHabilidad;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "categoriaHabilidad1")
    private Habilidad habilidad;

    public CategoriaHabilidad() {
    }

    public CategoriaHabilidad(Integer idCategoriaHabilidad) {
        this.idCategoriaHabilidad = idCategoriaHabilidad;
    }

    public Integer getIdCategoriaHabilidad() {
        return idCategoriaHabilidad;
    }

    public void setIdCategoriaHabilidad(Integer idCategoriaHabilidad) {
        this.idCategoriaHabilidad = idCategoriaHabilidad;
    }

    public String getNombreCategoriaHabilidad() {
        return nombreCategoriaHabilidad;
    }

    public void setNombreCategoriaHabilidad(String nombreCategoriaHabilidad) {
        this.nombreCategoriaHabilidad = nombreCategoriaHabilidad;
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
        hash += (idCategoriaHabilidad != null ? idCategoriaHabilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaHabilidad)) {
            return false;
        }
        CategoriaHabilidad other = (CategoriaHabilidad) object;
        if ((this.idCategoriaHabilidad == null && other.idCategoriaHabilidad != null) || (this.idCategoriaHabilidad != null && !this.idCategoriaHabilidad.equals(other.idCategoriaHabilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pruebasentidades.modelo.CategoriaHabilidad[ idCategoriaHabilidad=" + idCategoriaHabilidad + " ]";
    }
    
}
