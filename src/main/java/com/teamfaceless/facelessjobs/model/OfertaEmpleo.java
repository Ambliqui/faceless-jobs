package com.teamfaceless.facelessjobs.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.teamfaceless.facelessjobs.enums.EstadoOferta;
import com.teamfaceless.facelessjobs.services.ICandidatoService;
import com.teamfaceless.facelessjobs.services.IInscriptionService;
import com.teamfaceless.facelessjobs.services.impl.CandidatoService;
import com.teamfaceless.facelessjobs.services.impl.InscriptionService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
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

	@Size(max =2000)
	@NotEmpty
	@Column(name = "descripcion_oferta")
	private String descripcionOferta;

	@Column(name = "salario_oferta")
	@NotNull
	@Min(0)
	private Integer salarioOferta;
	@Min(1)
	@Column(name = "numero_vacantes_oferta")
	@NotNull
	private Integer numeroVacantesOferta;

	@Size(max = 100)
	@NotEmpty
	@Column(name = "localidad_oferta")
	private String localidadOferta;

	@Column(name = "fecha_inicio_oferta")
//	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private LocalDate fechaInicioOferta;

	@Column(name = "fecha_fin_oferta")
//	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull

	private LocalDate fechaFinOferta;
	
	@Column(name = "prioridad_oferta")
	@Min(value= 1)
	@Max(value = 10)
	private Integer prioridadOferta;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ofertaEmpleo", fetch = FetchType.EAGER)
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

	@Default
	@Column(name="estadoOferta", columnDefinition = "integer default 1")
	private EstadoOferta estadoOferta=EstadoOferta.DESACTIVADA;
	
	public String getFechaInicioOfertaOrdenada() {
        return fechaInicioOferta.getDayOfMonth() + "-"
                + fechaInicioOferta.getMonthValue() + "-"
                + fechaInicioOferta.getYear();
    }

    public String getFechaFinOfertaOrdenada() {
        return fechaFinOferta.getDayOfMonth() + "-"
                + fechaFinOferta.getMonthValue() + "-"
                + fechaFinOferta.getYear();
    }
	
    public Integer getEstadoIncripcion(int idCandidato) {
    	IInscriptionService insService = new InscriptionService();
    	InscripcionOferta inscripcion = insService.findByInscripcionOfertaPK(new InscripcionOfertaPK(this.idOfertaEmpleo, idCandidato));
    	return inscripcion.getEstadoInscripcion().ordinal();
    }
    
}
