package co.edu.uniandes.csw.sistema.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.sistema.master.logic.api.ISistemaMasterLogicService;
import co.edu.uniandes.csw.sistema.master.logic.dto.SistemaMasterDTO;

public abstract class _SistemaMasterService {

    @Inject
    protected ISistemaMasterLogicService sistemaLogicService;

    @POST
    public SistemaMasterDTO createSistema(SistemaMasterDTO sistema) {
        return sistemaLogicService.createMasterSistema(sistema);
    }

    @DELETE
    @Path("{id}")
    public void deleteSistema(@PathParam("id") Long id) {
        sistemaLogicService.deleteMasterSistema(id);
    }
    
    @GET
    @Path("{id}")
    public SistemaMasterDTO getSistema(@PathParam("id") Long id) {
        return sistemaLogicService.getMasterSistema(id);
    }

    @PUT
    @Path("{id}")
    public void updateSistema(@PathParam("id") Long id, SistemaMasterDTO sistema) {
        sistemaLogicService.updateMasterSistema(sistema);
    }

}
