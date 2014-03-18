package co.edu.uniandes.csw.carrito.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.carrito.master.logic.api.ICarritoMasterLogicService;
import co.edu.uniandes.csw.carrito.master.logic.dto.CarritoMasterDTO;

public abstract class _CarritoMasterService {

    @Inject
    protected ICarritoMasterLogicService carritoLogicService;

    @POST
    public CarritoMasterDTO createCarrito(CarritoMasterDTO carrito) {
        return carritoLogicService.createMasterCarrito(carrito);
    }

    @DELETE
    @Path("{id}")
    public void deleteCarrito(@PathParam("id") Long id) {
        carritoLogicService.deleteMasterCarrito(id);
    }
    
    @GET
    @Path("{id}")
    public CarritoMasterDTO getCarrito(@PathParam("id") Long id) {
        return carritoLogicService.getMasterCarrito(id);
    }

    @PUT
    @Path("{id}")
    public void updateCarrito(@PathParam("id") Long id, CarritoMasterDTO carrito) {
        carritoLogicService.updateMasterCarrito(carrito);
    }

}
