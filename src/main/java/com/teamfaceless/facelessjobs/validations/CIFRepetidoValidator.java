package com.teamfaceless.facelessjobs.validations;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.teamfaceless.facelessjobs.dao.IEmpresaRepository;
import com.teamfaceless.facelessjobs.validations.CifRepetido.CIFExist;

public class CIFRepetidoValidator implements ConstraintValidator<CIFExist, String>{

	@Autowired
	private IEmpresaRepository empresaRepository;
	
	@Override
    public boolean isValid(String cif, ConstraintValidatorContext context) {
//		if(Objects.isNull(empresaRepository.findBycIFempresa(cif))){
//		if(cif.equals("S4280108D")){
			return true;
//		}
//		return false;
	}
}
