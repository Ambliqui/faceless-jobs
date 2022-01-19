package com.teamfaceless.facelessjobs.dtos.candidato;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.teamfaceless.facelessjobs.model.Rol;

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
public class CandidatoRegistroDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Size(min = 4,max = 30)
	@Pattern (regexp = "^[ A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙñÑ]+$")
	private String apellidosCandidato;
	
	@Email
	private String emailCandidato;
	
	@Email
	private String emailConfirmCandidato;
	
	@NotBlank
	@Pattern(regexp = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$")
	private String passCandidato;
	
	@NotBlank
	@Pattern(regexp = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$")
	private String passConfirmCandidato;
	
	@NotBlank
	@Pattern(regexp = "^(?:0?[1-9]|[1-4]\\d|5[0-2])\\d{3}$")
	private String cpCandidato;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Past
	private Date fechaNacimientoCandidato;
	
	@NotBlank
	@Size(min = 3,max = 20)
	@Pattern (regexp = "^[ A-Za-zäÄëËïÏöÖüÜáéíóúáéíóúÁÉÍÓÚÂÊÎÔÛâêîôûàèìòùÀÈÌÒÙñÑ]+$")
	private String nombreCandidato;
	
	@Pattern (regexp = "^(\\+34|0034|34)?[ -]*(6|9)[ -]*([0-9][ -]*){8}$")
	private String telefonoCandidato;

	private Set<Rol> roles = new HashSet<>();
	
	public boolean emailsEquals() {
		return emailCandidato.equals(emailConfirmCandidato);
	}
	
	public boolean passEquals() {
		return passCandidato.equals(passConfirmCandidato);
	}

	private Boolean enable = true;
}
