package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "oferta_empleo")
public class OfertaEmpleo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id_oferta_empleo")
	private Integer idOfertaEmpleo;

	@Size(max = 45)
	@NotEmpty
	@Column(name = "titulo_oferta")
	private String tituloOferta;

	@Size(max = 255)
	@NotEmpty
	@Column(name = "descripcion_oferta")
	private String descripcionOferta;

	@Column(name = "salario_oferta")
	@NotNull
	private Integer salarioOferta;

	@Column(name = "numero_vacantes_oferta")
	@NotNull
	private Integer numeroVacantesOferta;

	@Size(max = 255)
	@NotEmpty
	@Column(name = "localidad_oferta")
	private String localidadOferta;

	@Column(name = "fecha_inicio_oferta")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date fechaInicioOferta;

	@Column(name = "fecha_fin_oferta")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date fechaFinOferta;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ofertaEmpleo")
	private List<HabilidadOferta> habilidadOfertaList;

	@JoinColumn(name = "provincia_oferta", referencedColumnName = "id_provincia")
	@ManyToOne
	@NotNull
	private Provincia provinciaOferta;

	@JoinColumn(name = "sector_oferta", referencedColumnName = "id_sector_laboral")
	@ManyToOne
	@NotNull
	private SectorLaboral sectorOferta;
	@JoinColumn(name = "id_empresa_oferta", referencedColumnName = "id_empresa")
	@ManyToOne
	private Empresa empresa;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ofertaEmpleo")
	private List<InscripcionOferta> inscripcionOfertaList;

}
