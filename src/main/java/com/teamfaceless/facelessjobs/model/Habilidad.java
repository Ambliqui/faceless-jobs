package com.teamfaceless.facelessjobs.model;
import java.io.Serializable;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.util.Arrays;
import java.util.Date;
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
}