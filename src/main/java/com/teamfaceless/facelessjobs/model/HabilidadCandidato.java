package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
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
@Table(name = "habilidad_candidato")
public class HabilidadCandidato implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected HabilidadCandidatoPK habilidadCandidatoPK;    
    
    @JoinColumn(name = "candidato_id_habilidad", referencedColumnName = "id_candidato", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Candidato candidato;
    
    @JoinColumn(name = "habilidad_id_habilidad", referencedColumnName = "id_habilidad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Habilidad habilidad;
    
    @Column(name = "experiencia_candidato")
    private Integer experienciaCandidato;
    
    @Column(name = "is_demostrable")
    private boolean isDemostrable;
    
    @Column(name = "nota_habilidad_candidato")
    private int notaHabilidadCandidato;

    public HabilidadCandidato(HabilidadCandidatoPK habilidadCandidatoPK) {
        this.habilidadCandidatoPK = habilidadCandidatoPK;
    }

    public HabilidadCandidato(int candidatoIdHabilidad, int habilidadIdHabilidad) {
        this.habilidadCandidatoPK = new HabilidadCandidatoPK(candidatoIdHabilidad, habilidadIdHabilidad);
    }

}
