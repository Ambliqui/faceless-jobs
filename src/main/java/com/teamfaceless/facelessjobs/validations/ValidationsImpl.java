package com.teamfaceless.facelessjobs.validations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.ICredencialRepository;
import com.teamfaceless.facelessjobs.dao.IEmpresaRepository;
import com.teamfaceless.facelessjobs.dao.IInscriptionRepository;
import com.teamfaceless.facelessjobs.exceptions.CIFExisteException;
import com.teamfaceless.facelessjobs.exceptions.CamposNoCoincidentesException;
import com.teamfaceless.facelessjobs.exceptions.EmailExisteException;
import com.teamfaceless.facelessjobs.exceptions.InscripcionExisteException;
import com.teamfaceless.facelessjobs.exceptions.InscripcionSinRequisitosException;
import com.teamfaceless.facelessjobs.model.Candidato;
import com.teamfaceless.facelessjobs.model.HabilidadCandidato;
import com.teamfaceless.facelessjobs.model.HabilidadOferta;
import com.teamfaceless.facelessjobs.model.InscripcionOfertaPK;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.services.IHabilidadCandidatoService;
import com.teamfaceless.facelessjobs.services.IHabilidadOfertaService;

@Service
public class ValidationsImpl implements IValidations {

	@Autowired
	private IEmpresaRepository empresaRepository;

	@Autowired
	private ICredencialRepository credencialRepository;

	@Autowired
	private IInscriptionRepository inscripcionRepositoy;

	@Autowired
	private IHabilidadOfertaService habOfeService;

	@Autowired
	private IHabilidadCandidatoService habCandidatoService;

	@Override
	public boolean validarFecha(LocalDate fechaInicio, LocalDate fechaFin) {
		return fechaInicio.isBefore(fechaFin);
	}

	@Override
	public Optional<EmailExisteException> emailExistente(String email) {
		if (credencialRepository.findByEmail(email) != null) {
			return Optional.empty();
		}
		EmailExisteException exception = new EmailExisteException("Este nombe de usuario ya está registrado");
		return Optional.of(exception);
	}

	@Override
	public Optional<CamposNoCoincidentesException> camposCoincidentes(String campo1, String campo2, String nombreCampo1,
			String nombreCampo2) {
		if (campo1.equals(campo2)) {
			return Optional.empty();
		}
		CamposNoCoincidentesException exception = new CamposNoCoincidentesException(
				"Los campos " + nombreCampo1 + " y " + nombreCampo2 + " no coindicen");
		return Optional.of(exception);
	}

	@Override
	public Optional<CIFExisteException> cifExistente(String cif) {

		if (Objects.isNull(empresaRepository.findBycIFempresa(cif))) {
			return Optional.empty();
		}
		CIFExisteException exception = new CIFExisteException("El CIF ya existe en el sistema");
		return Optional.of(exception);
	}

	@Override
	public Optional<CIFExisteException> cifPreExistente(String cifActual, String nuevoCif) {
		// El CIF no cambia
		if (cifActual.equals(nuevoCif)) {
			return Optional.empty();
		}
		// El CIF no existe
		if (Objects.isNull(empresaRepository.findBycIFempresa(nuevoCif))) {
			return Optional.empty();
		}
		// El CIF ya estaba
		CIFExisteException exception = new CIFExisteException("El CIF ya existe en el sistema");
		return Optional.of(exception);
	}

	@Override
	public Optional<InscripcionExisteException> inscripcionExistente(Integer idOferta, Integer idCandidato) {

		InscripcionOfertaPK pk = new InscripcionOfertaPK(idOferta, idCandidato);

		if (Objects.isNull(inscripcionRepositoy.findByInscripcionOfertaPK(pk))) {
			return Optional.empty();
		}
		InscripcionExisteException exception = new InscripcionExisteException("Ya esta inscrito en esta oferta");
		return Optional.of(exception);
	}

	@Override
	public Optional<InscripcionExisteException> inscripcionExistente(OfertaEmpleo ofertaEmpleo, Candidato candidato) {

		InscripcionOfertaPK pk = new InscripcionOfertaPK(ofertaEmpleo.getIdOfertaEmpleo(), candidato.getIdCandidato());

		if (Objects.isNull(inscripcionRepositoy.findByInscripcionOfertaPK(pk))) {
			return Optional.empty();
		}
		InscripcionExisteException exception = new InscripcionExisteException("Ya esta inscrito en esta oferta");
		return Optional.of(exception);
	}

	@Override
	public Optional<InscripcionSinRequisitosException> inscripcionRequisitosNoCoincidentes(OfertaEmpleo ofertaEmpleo,
			Candidato candidato) {

		List<HabilidadOferta> habilidadOfertaDuraReqList = habOfeService.habilidadesDurasRequeridas(ofertaEmpleo.getHabilidadOfertaList());

		if (!habilidadOfertaDuraReqList.isEmpty()) {
			List<HabilidadCandidato> habilidadCandidatoDuraReqList = habCandidatoService.especializacionHabilidadesCandidatoCoincidentes(habOfeService.generalizacionHabilidadesOferta(habilidadOfertaDuraReqList), candidato);
			List<HabilidadCandidato> habilidadCandidato = new ArrayList<HabilidadCandidato>();

			for (HabilidadCandidato habCand : habilidadCandidatoDuraReqList) {
				if (habCand.isDemostrable()) {
					habilidadCandidato.add(habCand);
				}
			}

			if (!habilidadCandidato.isEmpty()) {
				return Optional.empty();
			} else {
				InscripcionSinRequisitosException exception = new InscripcionSinRequisitosException("No cumple los requisitos para inscribirse en esta oferta");
				return Optional.of(exception);
			}

		} else {
			return Optional.empty();
		}
	}

}
