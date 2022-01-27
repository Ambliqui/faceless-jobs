package com.teamfaceless.facelessjobs.dtos.inscripcion;

import java.util.List;
import java.util.Set;

import com.teamfaceless.facelessjobs.dtos.empresa.EmpresaRegistroDto;
import com.teamfaceless.facelessjobs.model.Habilidad;
import com.teamfaceless.facelessjobs.model.HabilidadCandidato;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;
import com.teamfaceless.facelessjobs.model.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionOfertaInscritoDto {
	
	private String idCandidato;
	private String provincia;
	private String localidad;
	private List<HabilidadCandidato> habilidades;
	private Integer afinidad;

}