package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mefisto
 */
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

    public Respuesta() {
    }

    public Respuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getDetalleRespuesta() {
        return detalleRespuesta;
    }

    public void setDetalleRespuesta(String detalleRespuesta) {
        this.detalleRespuesta = detalleRespuesta;
    }

    public Integer getPuntuacionRespuesta() {
        return puntuacionRespuesta;
    }

    public void setPuntuacionRespuesta(Integer puntuacionRespuesta) {
        this.puntuacionRespuesta = puntuacionRespuesta;
    }

    public Pregunta getPreguntaIdRespuesta() {
        return preguntaIdRespuesta;
    }

    public void setPreguntaIdRespuesta(Pregunta preguntaIdRespuesta) {
        this.preguntaIdRespuesta = preguntaIdRespuesta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRespuesta != null ? idRespuesta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Respuesta)) {
            return false;
        }
        Respuesta other = (Respuesta) object;
        if ((this.idRespuesta == null && other.idRespuesta != null) || (this.idRespuesta != null && !this.idRespuesta.equals(other.idRespuesta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.pruebasentidades.modelo.Respuesta[ idRespuesta=" + idRespuesta + " ]";
    }
    
}
