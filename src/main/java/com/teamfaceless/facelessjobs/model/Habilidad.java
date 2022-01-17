package com.teamfaceless.facelessjobs.model;
import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.PatternSyntaxException;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Target;

import com.teamfaceless.facelessjobs.enums.Categoria;

/**
 *
 * @author Mefisto
 */
@Entity
@Table(name = "habilidad")

public class Habilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_habilidad")
    private Integer idHabilidad;
    @Size(max = 45)
    @NotEmpty
    @Column(name = "nombre_habilidad")
    private String nombreHabilidad;
    @Size(max = 45)
    @NotEmpty
    @Column(name = "descripcion_habilidad")
    private String descripcionHabilidad;
    @Column(name = "categoria_habilidad")
    private Categoria categoriaHabilidad;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habilidad")
    private List<HabilidadOferta> habilidadOfertaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habilidad")
    private List<HabilidadCandidato> habilidadCandidatoList;
    
    public Habilidad() {
    }

    public Habilidad(Integer idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public Integer getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(Integer idHabilidad) {
        this.idHabilidad = idHabilidad;
    }

    public String getDescripcionHabilidad() {
        return descripcionHabilidad;
    }

    public void setDescripcionHabilidad(String descripcionHabilidad) {
        this.descripcionHabilidad = descripcionHabilidad;
    }
    
    public String getNombreHabilidad() {
		return nombreHabilidad;
	}

	public void setNombreHabilidad(String nombreHabilidad) {
		this.nombreHabilidad = nombreHabilidad;
	}

	public Categoria getCategoriaHabilidad() {
		return categoriaHabilidad;
	}

	public void setCategoriaHabilidad(Categoria categoriaHabilidad) {
		this.categoriaHabilidad = categoriaHabilidad;
	}    

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (idHabilidad != null ? idHabilidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habilidad)) {
            return false;
        }
        Habilidad other = (Habilidad) object;
        if ((this.idHabilidad == null && other.idHabilidad != null) || (this.idHabilidad != null && !this.idHabilidad.equals(other.idHabilidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pruebasentidades.modelo.Habilidad[ idHabilidad=" + idHabilidad + " ]";
    }
    
}