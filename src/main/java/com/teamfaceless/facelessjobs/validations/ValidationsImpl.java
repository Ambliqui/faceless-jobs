package com.teamfaceless.facelessjobs.validations;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.ICredencialRepository;
import com.teamfaceless.facelessjobs.dao.IEmpresaRepository;
import com.teamfaceless.facelessjobs.exceptions.CIFExisteException;
import com.teamfaceless.facelessjobs.exceptions.CamposNoCoincidentesException;
import com.teamfaceless.facelessjobs.exceptions.EmailExisteException;

@Service
public class ValidationsImpl implements IValidations{

	@Autowired
	private IEmpresaRepository empresaRepository;
	
	@Autowired
	private ICredencialRepository credencialRepository;
	
	/**
	 * Validar una fecha es anterior a otra
	 * @author Mefisto
	 * @param fechaInicio Supuesta fecha anterior
	 * @param fechaFin Supuesta fecha posterior
	 * @return Boolean
	 */
	@Override
	public boolean validarFecha(LocalDate fechaInicio, LocalDate fechaFin) {
		return fechaInicio.isBefore(fechaFin);
	}

    /**
     * Comprueba si un email existe en nuestra base de datos Tiene que tener un
     * metodo para buscar dicho email
     * @author Mefisto
     * @param email Valor a comprobar
     * @return Devuelve una excepcion personalizada envuelta en un Optional
     */
	@Override
	public Optional<EmailExisteException> emailExistente(String email) {
		if(credencialRepository.findByEmail(email) != null) {
			return Optional.empty();
		}
		EmailExisteException exception = new EmailExisteException("Este nombe de usuario ya está registrado");
        return Optional.of(exception);
	}

    /**
     * Metodo para comprobar que dos campos son iguales diferenciando mayusculas
     * y minusculas
     *
     * @author Mefisto
     * @param nombreCampo1 Nombre del primer campo a validar
     * @param nombreCampo2 Nombre del segundo campo a validar
     * @param campo1 Valor del primer campo a validar
     * @param campo2 Valor del segundo campo a validar
     * @return Devuelve una excepcion personalizada envuelta en un Optional
     */
	@Override
	public Optional<CamposNoCoincidentesException> camposCoincidentes(String nombreCampo1, String nombreCampo2, String campo1, String campo2) {
        if (campo1.equals(campo2)) {
            return Optional.empty();
        }
        CamposNoCoincidentesException exception = new CamposNoCoincidentesException("Los campos " + nombreCampo1 + " y " + nombreCampo2 + " no coindicen");
        return Optional.of(exception);
	}

	/**
	 * @author Mefisto
	 * Comprobamos si el CIF de una empresa ya existe en la BD
	 * 
	 */
	@Override
	public Optional<CIFExisteException> cifExistente(String cif) {
		
		if(Objects.isNull(empresaRepository.findBycIFempresa(cif))){
			return Optional.empty();
		}
		CIFExisteException exception = new CIFExisteException("El CIF ya existe en el sistema");
		return Optional.of(exception);
	}

	/**
	 * Metodo para comprobar que un CIF no esta repetido cuando se modifica
	 * @param cifActual El CIF antes de hacer la modificacion
	 * @param nuevoCif El CIF el nuevo valor que debe tomar el CIF
	 * @return Optional con una Excepcion
	 */
	
	@Override
	public Optional<CIFExisteException> cifPreExistente(String cifActual, String nuevoCif) {
		//El CIF no cambia
		if(cifActual.equals(nuevoCif)) {
			return Optional.empty();
		}
		//El CIF no existe
		if(Objects.isNull(empresaRepository.findBycIFempresa(nuevoCif))){
			return Optional.empty();
		}
		//El CIF ya estaba
		CIFExisteException exception = new CIFExisteException("El CIF ya existe en el sistema");
		return Optional.of(exception);
	}

}
