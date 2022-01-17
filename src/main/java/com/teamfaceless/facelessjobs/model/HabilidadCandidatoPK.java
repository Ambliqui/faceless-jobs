package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

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
    
}
