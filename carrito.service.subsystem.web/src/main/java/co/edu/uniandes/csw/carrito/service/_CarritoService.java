package co.edu.uniandes.csw.carrito.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.carrito.logic.api.ICarritoLogicService;
import co.edu.uniandes.csw.carrito.logic.dto.CarritoDTO;


public abstract class _CarritoService {

	@Inject
	protected ICarritoLogicService carritoLogicService;
	
	@POST
	public CarritoDTO createCarrito(CarritoDTO carrito){
		return carritoLogicService.createCarrito(carrito);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteCarrito(@PathParam("id") Long id){
		carritoLogicService.deleteCarrito(id);
	}
	
	@GET
	public List<CarritoDTO> getCarritos(){
		return carritoLogicService.getCarritos();
	}
	
	@GET
	@Path("{id}")
	public CarritoDTO getCarrito(@PathParam("id") Long id){
		return carritoLogicService.getCarrito(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateCarrito(@PathParam("id") Long id, CarritoDTO carrito){
		carritoLogicService.updateCarrito(carrito);
	}
	
}