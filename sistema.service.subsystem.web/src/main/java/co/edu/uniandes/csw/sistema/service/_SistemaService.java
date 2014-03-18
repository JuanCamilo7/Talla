package co.edu.uniandes.csw.sistema.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.sistema.logic.api.ISistemaLogicService;
import co.edu.uniandes.csw.sistema.logic.dto.SistemaDTO;


public abstract class _SistemaService {

	@Inject
	protected ISistemaLogicService sistemaLogicService;
	
	@POST
	public SistemaDTO createSistema(SistemaDTO sistema){
		return sistemaLogicService.createSistema(sistema);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteSistema(@PathParam("id") Long id){
		sistemaLogicService.deleteSistema(id);
	}
	
	@GET
	public List<SistemaDTO> getSistemas(){
		return sistemaLogicService.getSistemas();
	}
	
	@GET
	@Path("{id}")
	public SistemaDTO getSistema(@PathParam("id") Long id){
		return sistemaLogicService.getSistema(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateSistema(@PathParam("id") Long id, SistemaDTO sistema){
		sistemaLogicService.updateSistema(sistema);
	}
	
}