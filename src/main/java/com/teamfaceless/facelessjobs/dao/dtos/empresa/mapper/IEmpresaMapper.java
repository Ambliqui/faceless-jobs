package com.teamfaceless.facelessjobs.dao.dtos.empresa.mapper;

import com.teamfaceless.facelessjobs.dao.dtos.empresa.EmpresaRegistroDto;
import com.teamfaceless.facelessjobs.model.Empresa;

public interface IEmpresaMapper {

	Empresa empresaEmpresaDtoToEmpresa(EmpresaRegistroDto empresaRegistroDto);
	
}
