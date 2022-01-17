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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "pregunta")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Pregunta.findAll", query = "SELECT p FROM Pregunta p"),
        @NamedQuery(name = "Pregunta.findByIdPregunta", query = "SELECT p FROM Pregunta p WHERE p.idPregunta = :idPregunta"),
        @NamedQuery(name = "Pregunta.findByEnunciado", query = "SELECT p FROM Pregunta p WHERE p.enunciado = :enunciado") })
public class Pregunta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_pregunta")
    private Integer idPregunta;
    @Size(max = 200)
    @Column(name = "enunciado")
    private String enunciado;
    @JoinColumn(name = "test_id_pregunta", referencedColumnName = "id_test")
    @ManyToOne(optional = false)
    private Test testIdPregunta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preguntaIdRespuesta")
    private List<Respuesta> respuestaList;

    public Pregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

}
