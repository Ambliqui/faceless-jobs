package com.teamfaceless.facelessjobs.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.teamfaceless.facelessjobs.dao.IEmpresaRepository;
import com.teamfaceless.facelessjobs.dao.IRolRepository;
import com.teamfaceless.facelessjobs.dtos.empresa.EmpresaRegistroDto;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.model.OfertaEmpleo;
import com.teamfaceless.facelessjobs.model.Rol;
import com.teamfaceless.facelessjobs.services.IEmpresaService;
import com.teamfaceless.facelessjobs.validations.IValidations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService implements IEmpresaService {

	@Autowired
	private IEmpresaRepository repository;
	
	@Autowired
	private IValidations iValidations;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IRolRepository rolRepository;
	
	@Override
	public List<Empresa> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Empresa> findById(Integer id) {
		return repository.findById(id);
	}
	
	@Override
	public void create(Empresa empresa) {
		
		Rol rol = rolRepository.findById(2).get();
		empresa.getCredencial().addRole(rol);

		String password = empresa.getCredencial().getPass();
		password = passwordEncoder.encode(password);
		empresa.getCredencial().setPass(password);
		repository.save(empresa);
	}

	@Override
	public void delete(Empresa empresa) {
		repository.delete(empresa);

	}

	@Override
	public void delete(Integer idEmpresa) {
		repository.deleteById(idEmpresa);

	}

	@Override
	public void modify(Empresa empresa) {
		repository.save(empresa);
	}

	@Override
	public Empresa findEmpresa(OfertaEmpleo oferta) {
		return repository.findEmpresa(oferta);
	}

	@Override
	public boolean isPresent(Empresa empresa) {
		return repository.findByEmailEmpresa(empresa.getCredencial().getEmail()).isPresent();
	}

	@Override
	public Map<String, String> validateRegister(EmpresaRegistroDto empresaRegistroDto) {
		
		Map<String, String> mapaErrores = new HashMap<>();
		
		iValidations.emailExistente(empresaRegistroDto.getEmailEmpresa())
			.ifPresent((error) -> mapaErrores.put("ErrorEmailExist", error.getMessage()));
		iValidations.camposCoincidentes(empresaRegistroDto.getConfirmEmailEmpresa(), empresaRegistroDto.getEmailEmpresa(), "Email", "Repite Email")
			.ifPresent((error) -> mapaErrores.put("errorEmailNoDuplicate", error.getMessage()));
		iValidations.camposCoincidentes(empresaRegistroDto.getPassEmpresa(), empresaRegistroDto.getConfirmPassEmpresa(), "Password", "Repite Password")
			.ifPresent((error) -> mapaErrores.put("errorPassNoDuplicate", error.getMessage()));
		iValidations.cifExistente(empresaRegistroDto.getCIFempresa())
			.ifPresent((error) -> mapaErrores.put("errorCIFExist", error.getMessage()));
		return mapaErrores;
	}

	@Override
	public Optional<Empresa> findByEmailEmpresa(String email) {
		return repository.findByEmailEmpresa(email);
	}

}
