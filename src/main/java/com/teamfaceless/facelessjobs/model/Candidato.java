package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "candidato")
public class Candidato implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_candidato")
    private Integer idCandidato;
    
    @Size(max = 45)
    @Column(name = "nombre_candidato")
    private String nombreCandidato;
    
    @Size(max = 45)
    @Column(name = "apellidos_candidato")
    private String apellidosCandidato;
    
    @Size(max = 45)
    @Column(name = "telefono_candidato")
    private String telefonoCandidato;
    
    @Column(name = "fecha_nacimiento_candidato")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoCandidato;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidato")
    private List<HabilidadCandidato> habilidadCandidatoList;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "candidato")
    private List<InscripcionOferta> inscripcionOfertaList;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credencial_id_credencial", referencedColumnName = "id_credencial")
	private Credencial credencial;
    
    @JoinColumn(name = "provincia_candidato", referencedColumnName = "id_provincia")
    @ManyToOne
    private Provincia provinciaCandidato;



	public HabilidadCandidato addHabilidadCandidato(HabilidadCandidato habilidadCandidato) {
		getHabilidadCandidatoList().add(habilidadCandidato);
		habilidadCandidato.setCandidato(this);

		return habilidadCandidato;
	}

	public HabilidadCandidato removeHabilidadCandidato(HabilidadCandidato habilidadCandidato) {
	
		getHabilidadCandidatoList().remove(habilidadCandidato);
		habilidadCandidato.setCandidato(this);

		return habilidadCandidato;
	}
  
    
}
