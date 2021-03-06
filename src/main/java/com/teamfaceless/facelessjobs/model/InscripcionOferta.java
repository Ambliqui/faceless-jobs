package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.teamfaceless.facelessjobs.enums.EstadoInscripcion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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
@Table(name = "inscripcion_oferta")
public class InscripcionOferta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected InscripcionOfertaPK inscripcionOfertaPK;
    
//    @Basic(optional = false)
//    @Column(name = "id_inscripcion")
//    private int idInscripcion;
    
    @Column(name = "fecha_inscripcion")
    @Temporal(TemporalType.DATE)
    private Date fechaInscripcion;
    
    @ManyToOne
    @MapsId("id_candidato")
    @JoinColumn(name = "candidato_id_inscripcion")
//    @JoinColumn(name = "candidato_id_inscripcion", referencedColumnName = "id_candidato", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
    private Candidato candidato;
    
    @ManyToOne
    @MapsId("id_oferta_empleo")
    @JoinColumn(name = "oferta_id_inscripcion")
//    @JoinColumn(name = "oferta_id_inscripcion", referencedColumnName = "id_oferta_empleo", insertable = false, updatable = false)
//    @ManyToOne(optional = false)
    private OfertaEmpleo ofertaEmpleo;

    @Default
    @Column(name="estado_inscripcion", columnDefinition = "integer default 0")
    private EstadoInscripcion estadoInscripcion=EstadoInscripcion.INSCRITO;
    
    @Column(name="comentario",columnDefinition = "text")
    private String comentario;
    
    
    public InscripcionOferta(InscripcionOfertaPK inscripcionOfertaPK) {
        this.inscripcionOfertaPK = inscripcionOfertaPK;
    }

//    public InscripcionOferta(InscripcionOfertaPK inscripcionOfertaPK, int idInscripcion) {
//        this.inscripcionOfertaPK = inscripcionOfertaPK;
//        this.idInscripcion = idInscripcion;
//    }

    public InscripcionOferta(int ofertaIdInscripcion, int candidatoIdInscripcion) {
        this.inscripcionOfertaPK = new InscripcionOfertaPK(ofertaIdInscripcion, candidatoIdInscripcion);
    }

}
