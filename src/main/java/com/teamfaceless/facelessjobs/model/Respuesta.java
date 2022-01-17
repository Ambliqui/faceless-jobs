package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "respuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Respuesta.findAll", query = "SELECT r FROM Respuesta r"),
    @NamedQuery(name = "Respuesta.findByIdRespuesta", query = "SELECT r FROM Respuesta r WHERE r.idRespuesta = :idRespuesta"),
    @NamedQuery(name = "Respuesta.findByDetalleRespuesta", query = "SELECT r FROM Respuesta r WHERE r.detalleRespuesta = :detalleRespuesta"),
    @NamedQuery(name = "Respuesta.findByPuntuacionRespuesta", query = "SELECT r FROM Respuesta r WHERE r.puntuacionRespuesta = :puntuacionRespuesta")})
public class Respuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_respuesta")
    private Integer idRespuesta;
    @Size(max = 200)
    @Column(name = "detalle_respuesta")
    private String detalleRespuesta;
    @Column(name = "puntuacion_respuesta")
    private Integer puntuacionRespuesta;
    @JoinColumn(name = "pregunta_id_respuesta", referencedColumnName = "id_pregunta")
    @ManyToOne(optional = false)
    private Pregunta preguntaIdRespuesta;

    public Respuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }
    
}
