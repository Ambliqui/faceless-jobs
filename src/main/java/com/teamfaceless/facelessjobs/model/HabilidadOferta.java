package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

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
@Table(name = "habilidad_oferta")
public class HabilidadOferta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HabilidadOfertaPK habilidadOfertaPK;
    
    @JoinColumn(name = "habilidad_id_habilidad", referencedColumnName = "id_habilidad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Habilidad habilidad;
    
    @JoinColumn(name = "oferta_id_habilidad", referencedColumnName = "id_oferta_empleo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OfertaEmpleo ofertaEmpleo;
    
    @Min(0)
    @Column(name = "experiencia_oferta")
    private int experienciaOferta;
    
    @Builder.Default
    @Column(name = "is_obligatorio_habilidad_oferta", columnDefinition = "boolean default true")
    private boolean isObligatorio=false;
    
    @Min(1)
    @Max(9)
    @Builder.Default
    @Column(name = "baremo_habilidad_oferta", columnDefinition = "int default 5")
    private int baremo=5;
    
    public HabilidadOferta(HabilidadOfertaPK habilidadOfertaPK) {
        this.habilidadOfertaPK = habilidadOfertaPK;
    }

    public HabilidadOferta(int ofertaIdHabilidad, int habilidadIdHabilidad) {
        this.habilidadOfertaPK = new HabilidadOfertaPK(ofertaIdHabilidad, habilidadIdHabilidad);
    }
    
}
