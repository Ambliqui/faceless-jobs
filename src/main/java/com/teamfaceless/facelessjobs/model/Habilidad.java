package com.teamfaceless.facelessjobs.model;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Mefisto
 */
@Entity
@Table(name = "habilidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habilidad.findAll", query = "SELECT h FROM Habilidad h"),
    @NamedQuery(name = "Habilidad.findByIdHabilidad", query = "SELECT h FROM Habilidad h WHERE h.idHabilidad = :idHabilidad"),
    @NamedQuery(name = "Habilidad.findByDescripcionHabilidad", query = "SELECT h FROM Habilidad h WHERE h.descripcionHabilidad = :descripcionHabilidad"),
    @NamedQuery(name = "Habilidad.findByCategoriaHabilidad", query = "SELECT h FROM Habilidad h WHERE h.categoriaHabilidad = :categoriaHabilidad")})
public class Habilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_habilidad")
    private Integer idHabilidad;
    @Size(max = 45)
    @Column(name = "descripcion_habilidad")
    private String descripcionHabilidad;
    @Column(name = "categoria_habilidad")
    private Integer categoriaHabilidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habilidad")
    private List<HabilidadOferta> habilidadOfertaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habilidad")
    private List<HabilidadCandidato> habilidadCandidatoList;
    @JoinColumn(name = "id_habilidad", referencedColumnName = "id_categoria_habilidad", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private CategoriaHabilidad categoriaHabilidad1;

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

    public Integer getCategoriaHabilidad() {
        return categoriaHabilidad;
    }

    public void setCategoriaHabilidad(Integer categoriaHabilidad) {
        this.categoriaHabilidad = categoriaHabilidad;
    }

    @XmlTransient
    public List<HabilidadOferta> getHabilidadOfertaList() {
        return habilidadOfertaList;
    }

    public void setHabilidadOfertaList(List<HabilidadOferta> habilidadOfertaList) {
        this.habilidadOfertaList = habilidadOfertaList;
    }

    @XmlTransient
    public List<HabilidadCandidato> getHabilidadCandidatoList() {
        return habilidadCandidatoList;
    }

    public void setHabilidadCandidatoList(List<HabilidadCandidato> habilidadCandidatoList) {
        this.habilidadCandidatoList = habilidadCandidatoList;
    }

    public CategoriaHabilidad getCategoriaHabilidad1() {
        return categoriaHabilidad1;
    }

    public void setCategoriaHabilidad1(CategoriaHabilidad categoriaHabilidad1) {
        this.categoriaHabilidad1 = categoriaHabilidad1;
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
