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
	
	/**
	 * Validar una fecha es anterior a otra
	 * @author Mefisto
	 * @param fechaInicio Supuesta fecha anterior
	 * @param fechaFin Supuesta fecha posterior
	 * @return Boolean
	 */
	boolean  validarFecha(LocalDate fechaInicio, LocalDate fechaFin);
	
    /**
     * Comprueba si un email existe en nuestra base de datos Tiene que tener un
     * metodo para buscar dicho email
     * @author Mefisto
     * @param email Valor a comprobar
     * @return Devuelve una excepcion personalizada envuelta en un Optional
     */
	Optional<EmailExisteException> emailExistente(String email);
	
	/**
	 * @author Mefisto
     * Metodo para comprobar que dos campos son iguales diferenciando mayusculas
     * y minusculas
     * @param nombreCampo1 Nombre del primer campo a validar
     * @param nombreCampo2 Nombre del segundo campo a validar
     * @param campo1 Valor del primer campo a validar
     * @param campo2 Valor del segundo campo a validar
     * @return Devuelve una excepcion personalizada envuelta en un Optional
     */
	Optional<CamposNoCoincidentesException> camposCoincidentes(String nombreCampo1, String nombreCampo2, String campo1, String campo2);
	
	/**
	 * @author Mefisto
	 * Comprobamos si el CIF de una empresa ya existe en la BD
	 * @param cif CIF de la empresa
	 * @return Optional<CIFExisteException> 
	 */
	Optional<CIFExisteException> cifExistente(String cif);
	
	/**
	 * Metodo para comprobar que un CIF no esta repetido cuando se modifica
	 * @param cifActual El CIF antes de hacer la modificacion
	 * @param nuevoCif El CIF el nuevo valor que debe tomar el CIF
	 * @return Optional con una Excepcion
	 */
	Optional<CIFExisteException> cifPreExistente(String cifActual, String nuevoCif);
	
	/**
	 * @author Mefisto
	 * Devuelve si el candidato está inscrito o no
	 * @param idOferta El identificador de la oferta
	 * @param idCandidato identificador del candidato
	 * @return Optional<InscripcionExisteException> 
	 */
	Optional<InscripcionExisteException> inscripcionExistente(Integer idOferta, Integer idCandidato);
	
	/**
	 * @author Mefisto
	 * Devuelve si el candidato está inscrito o no
	 * @param ofertaEmpleo
	 * @param candidato
	 * @return Optional<InscripcionExisteException> 
	 */
	Optional<InscripcionExisteException> inscripcionExistente(OfertaEmpleo ofertaEmpleo, Candidato candidato);
	
	/**
	 * @author Mefisto
	 * Devuelve una Excecpcion envuelta en Optional si las habilidades duras del candidato no coincicen con las requeridas en la oferta
	 * @param inscripcionOferta
	 * @return Optional<InscripcionSinRequisitosException>
	 */
	Optional<InscripcionSinRequisitosException> inscripcionRequisitosNoCoincidentes(OfertaEmpleo ofertaEmpleo, Candidato candidato);
}
