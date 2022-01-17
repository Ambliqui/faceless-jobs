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
@Table(name = "habilidad_oferta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HabilidadOferta.findAll", query = "SELECT h FROM HabilidadOferta h"),
    @NamedQuery(name = "HabilidadOferta.findByHabilidadOfertaId", query = "SELECT h FROM HabilidadOferta h WHERE h.habilidadOfertaId = :habilidadOfertaId"),
    @NamedQuery(name = "HabilidadOferta.findByOfertaIdHabilidad", query = "SELECT h FROM HabilidadOferta h WHERE h.habilidadOfertaPK.ofertaIdHabilidad = :ofertaIdHabilidad"),
    @NamedQuery(name = "HabilidadOferta.findByHabilidadIdHabilidad", query = "SELECT h FROM HabilidadOferta h WHERE h.habilidadOfertaPK.habilidadIdHabilidad = :habilidadIdHabilidad"),
    @NamedQuery(name = "HabilidadOferta.findByExperienciaOferta", query = "SELECT h FROM HabilidadOferta h WHERE h.experienciaOferta = :experienciaOferta")})
public class HabilidadOferta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HabilidadOfertaPK habilidadOfertaPK;
    @Basic(optional = false)
    @Column(name = "habilidad_oferta_id")
    private int habilidadOfertaId;
    @Column(name = "experiencia_oferta")
    private Integer experienciaOferta;
    @JoinColumn(name = "habilidad_id_habilidad", referencedColumnName = "id_habilidad", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Habilidad habilidad;
    @JoinColumn(name = "oferta_id_habilidad", referencedColumnName = "id_oferta_empleo", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private OfertaEmpleo ofertaEmpleo;


    public HabilidadOferta(HabilidadOfertaPK habilidadOfertaPK) {
        this.habilidadOfertaPK = habilidadOfertaPK;
    }

    public HabilidadOferta(HabilidadOfertaPK habilidadOfertaPK, int habilidadOfertaId) {
        this.habilidadOfertaPK = habilidadOfertaPK;
        this.habilidadOfertaId = habilidadOfertaId;
    }

    public HabilidadOferta(int ofertaIdHabilidad, int habilidadIdHabilidad) {
        this.habilidadOfertaPK = new HabilidadOfertaPK(ofertaIdHabilidad, habilidadIdHabilidad);
    }
    
}
