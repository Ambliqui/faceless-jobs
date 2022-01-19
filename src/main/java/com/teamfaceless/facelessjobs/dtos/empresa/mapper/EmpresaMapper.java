package com.teamfaceless.facelessjobs.dtos.empresa.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teamfaceless.facelessjobs.dtos.empresa.EmpresaRegistroDto;
import com.teamfaceless.facelessjobs.model.Credencial;
import com.teamfaceless.facelessjobs.model.Empresa;
import com.teamfaceless.facelessjobs.services.IProvinciaService;
import com.teamfaceless.facelessjobs.services.ISectorService;

@Service
public class EmpresaMapper implements IEmpresaMapper{

	@Autowired
	private IProvinciaService iProvinciaService;
	
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
				.provinciaEmpresa(iProvinciaService.findById(empresaRegistroDto.getProvinciaEmpresa()).get())
				.sectorEmpresa(iSectorService.findById(empresaRegistroDto.getSectorEmpresa()).get())
				.telefonoEmpresa(empresaRegistroDto.getTelefonoEmpresa())
				.whatsappEmpresa(empresaRegistroDto.getWhatsappEmpresa())
				.credencial(Credencial.builder()
						.email(empresaRegistroDto.getEmailEmpresa())
						.pass(empresaRegistroDto.getPassEmpresa())
						.roles(empresaRegistroDto.getRoles())
						.build())
				.build();
	}

}
