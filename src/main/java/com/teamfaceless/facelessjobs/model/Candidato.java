package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimientoCandidato;

	// @Column(name = "localidad_candidato")
	// private String localidadCandidato;

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

	public String codificarNombre() {
		String nombreClave = String.valueOf(idCandidato) + nombreCandidato.substring(0, 1);
		StringTokenizer apellidos = new StringTokenizer(apellidosCandidato);

		while (apellidos.hasMoreTokens()) {
			nombreClave = nombreClave + apellidos.nextToken().substring(0, 1);
		}

		return nombreClave.toUpperCase();
	}

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
