package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
@Table(name = "categoria_habilidad")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "CategoriaHabilidad.findAll", query = "SELECT c FROM CategoriaHabilidad c"),
        @NamedQuery(name = "CategoriaHabilidad.findByIdCategoriaHabilidad", query = "SELECT c FROM CategoriaHabilidad c WHERE c.idCategoriaHabilidad = :idCategoriaHabilidad"),
        @NamedQuery(name = "CategoriaHabilidad.findByNombreCategoriaHabilidad", query = "SELECT c FROM CategoriaHabilidad c WHERE c.nombreCategoriaHabilidad = :nombreCategoriaHabilidad") })
public class CategoriaHabilidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria_habilidad")
    private Integer idCategoriaHabilidad;
    @Size(max = 45)
    @Column(name = "nombre_categoria_habilidad")
    private String nombreCategoriaHabilidad;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "categoriaHabilidad1")
    private Habilidad habilidad;

}
