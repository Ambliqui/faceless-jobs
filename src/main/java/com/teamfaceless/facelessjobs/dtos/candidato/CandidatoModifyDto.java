package com.teamfaceless.facelessjobs.dtos.candidato;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.teamfaceless.facelessjobs.model.Credencial;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidatoModifyDto implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer idCandidato;
	
	@NotBlank
	@Size(min = 4,max = 30)
	@Pattern (regexp = "^[ A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙñÑ]+$")
	private String apellidosCandidato;
	
	// @NotBlank
	// @Pattern(regexp = "^(?:0?[1-9]|[1-4]\\d|5[0-2])\\d{3}$")
	// private String cpCandidato;
	
	@NotBlank
	@Size(min = 3,max = 20)
	@Pattern (regexp = "^[ A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙñÑ]+$")
	private String nombreCandidato;
	
	@Pattern (regexp = "^(\\+34|0034|34)?[ -]*(6|9)[ -]*([0-9][ -]*){8}$")
	private String telefonoCandidato;
    	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Past
	private Date fechaNacimientoCandidato;

    private Credencial credencial;

}
    

