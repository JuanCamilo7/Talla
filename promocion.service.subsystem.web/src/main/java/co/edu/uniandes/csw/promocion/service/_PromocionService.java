package co.edu.uniandes.csw.promocion.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.promocion.logic.api.IPromocionLogicService;
import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;


public abstract class _PromocionService {

	@Inject
	protected IPromocionLogicService promocionLogicService;
	
	@POST
	public PromocionDTO createPromocion(PromocionDTO promocion){
		return promocionLogicService.createPromocion(promocion);
	}
	
	@DELETE
	@Path("{id}")
	public void deletePromocion(@PathParam("id") Long id){
		promocionLogicService.deletePromocion(id);
	}
	
	@GET
	public List<PromocionDTO> getPromocions(){
		return promocionLogicService.getPromocions();
	}
	
	@GET
	@Path("{id}")
	public PromocionDTO getPromocion(@PathParam("id") Long id){
		return promocionLogicService.getPromocion(id);
	}
	
	@PUT
    @Path("{id}")
	public void updatePromocion(@PathParam("id") Long id, PromocionDTO promocion){
		promocionLogicService.updatePromocion(promocion);
	}
	
}