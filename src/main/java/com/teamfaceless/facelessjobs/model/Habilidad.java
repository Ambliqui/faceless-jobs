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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Mefisto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "habilidad")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Habilidad.findAll", query = "SELECT h FROM Habilidad h"),
        @NamedQuery(name = "Habilidad.findByIdHabilidad", query = "SELECT h FROM Habilidad h WHERE h.idHabilidad = :idHabilidad"),
        @NamedQuery(name = "Habilidad.findByDescripcionHabilidad", query = "SELECT h FROM Habilidad h WHERE h.descripcionHabilidad = :descripcionHabilidad"),
        @NamedQuery(name = "Habilidad.findByCategoriaHabilidad", query = "SELECT h FROM Habilidad h WHERE h.categoriaHabilidad = :categoriaHabilidad") })
public class Habilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_habilidad")
    private Integer idHabilidad;
    @Size(max = 45)
    @Column(name = "nombre_habilidad")
    private String nombreHabilidad;
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

}
