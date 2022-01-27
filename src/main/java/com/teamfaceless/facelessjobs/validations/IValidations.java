package com.teamfaceless.facelessjobs.validations;

import java.time.LocalDate;
import java.util.Optional;

import com.teamfaceless.facelessjobs.exceptions.CIFExisteException;
import com.teamfaceless.facelessjobs.exceptions.CamposNoCoincidentesException;
import com.teamfaceless.facelessjobs.exceptions.EmailExisteException;
import com.teamfaceless.facelessjobs.exceptions.InscripcionExisteException;
import com.teamfaceless.facelessjobs.exceptions.InscripcionSinRequisitosException;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.InscripcionOferta;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;

public interface IValidations{
	
	boolean  validarFecha(LocalDate fechaInicio, LocalDate fechaFin);
	Optional<EmailExisteException> emailExistente(String email);
	Optional<CamposNoCoincidentesException> camposCoincidentes(String nombreCampo1, String nombreCampo2, String campo1, String campo2);
	Optional<CIFExisteException> cifExistente(String cif);
	Optional<CIFExisteException> cifPreExistente(String cifActual, String nuevoCif);
	Optional<InscripcionExisteException> inscripcionExistente(Integer idOferta, Integer idCandidato);
	Optional<InscripcionExisteException> inscripcionExistente(OfertaEmpleo ofertaEmpleo, Candidato candidato);
	Optional<InscripcionSinRequisitosException> inscripcionRequisitosNoCoincidentes(InscripcionOferta inscripcionOferta);
}
