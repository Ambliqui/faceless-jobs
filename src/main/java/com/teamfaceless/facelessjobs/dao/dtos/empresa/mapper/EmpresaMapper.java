package com.teamfaceless.facelessjobs.dao.dtos.empresa.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dao.dtos.empresa.EmpresaRegistroDto;
import com.teamfaceless.facelessjobs.enums.Provincias;
import com.teamfaceless.facelessjobs.model.Credencial;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.services.ISectorService;

@Service
public class EmpresaMapper implements IEmpresaMapper{

	
	@Autowired
	private ISectorService iSectorService;
	
	@Override
	public Empresa empresaEmpresaDtoToEmpresa(EmpresaRegistroDto empresaRegistroDto) {

		return Empresa.builder()
				.nombreEmpresa(empresaRegistroDto.getNombreEmpresa())
				.cIFempresa(empresaRegistroDto.getCIFempresa())
				.direccionEmpresa(empresaRegistroDto.getDireccionEmpresa())
				.empleadosEmpresa(empresaRegistroDto.getEmpleadosEmpresa())
				.localidadEmpresa(empresaRegistroDto.getLocalidadEmpresa())
				.nombreJuridicoEmpresa(empresaRegistroDto.getNombreJuridicoEmpresa())
				.provinciaEmpresa(empresaRegistroDto.getProvinciaEmpresa())
				.sectorEmpresa(iSectorService.findById(empresaRegistroDto.getSectorEmpresa()).get())
				.telefonoEmpresa(empresaRegistroDto.getTelefonoEmpresa())
				.whatsappEmpresa(empresaRegistroDto.getWhatsappEmpresa())
				.credencial(Credencial.builder()
						.email(empresaRegistroDto.getEmailEmpresa())
						.pass(empresaRegistroDto.getPassEmpresa())
						.build())
				.build();
	}

}
