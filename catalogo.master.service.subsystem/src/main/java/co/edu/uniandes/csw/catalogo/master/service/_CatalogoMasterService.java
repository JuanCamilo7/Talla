package co.edu.uniandes.csw.catalogo.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.catalogo.master.logic.api.ICatalogoMasterLogicService;
import co.edu.uniandes.csw.catalogo.master.logic.dto.CatalogoMasterDTO;

public abstract class _CatalogoMasterService {

    @Inject
    protected ICatalogoMasterLogicService catalogoLogicService;

    @POST
    public CatalogoMasterDTO createCatalogo(CatalogoMasterDTO catalogo) {
        return catalogoLogicService.createMasterCatalogo(catalogo);
    }

    @DELETE
    @Path("{id}")
    public void deleteCatalogo(@PathParam("id") Long id) {
        catalogoLogicService.deleteMasterCatalogo(id);
    }
    
    @GET
    @Path("{id}")
    public CatalogoMasterDTO getCatalogo(@PathParam("id") Long id) {
        return catalogoLogicService.getMasterCatalogo(id);
    }

    @PUT
    @Path("{id}")
    public void updateCatalogo(@PathParam("id") Long id, CatalogoMasterDTO catalogo) {
        catalogoLogicService.updateMasterCatalogo(catalogo);
    }

}
