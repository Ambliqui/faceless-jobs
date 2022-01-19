package com.teamfaceless.facelessjobs.dtos.empresa.mapper;

import com.teamfaceless.facelessjobs.dtos.empresa.EmpresaRegistroDto;
import com.teamfaceless.facelessjobs.model.Empresa;

public interface IEmpresaMapper {

	Empresa empresaEmpresaDtoToEmpresa(EmpresaRegistroDto empresaRegistroDto);
	
}
