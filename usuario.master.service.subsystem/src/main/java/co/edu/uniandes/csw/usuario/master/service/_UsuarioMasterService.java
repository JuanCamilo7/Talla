package co.edu.uniandes.csw.usuario.master.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.usuario.master.logic.api.IUsuarioMasterLogicService;
import co.edu.uniandes.csw.usuario.master.logic.dto.UsuarioMasterDTO;

public abstract class _UsuarioMasterService {

    @Inject
    protected IUsuarioMasterLogicService usuarioLogicService;

    @POST
    public UsuarioMasterDTO createUsuario(UsuarioMasterDTO usuario) {
        return usuarioLogicService.createMasterUsuario(usuario);
    }

    @DELETE
    @Path("{id}")
    public void deleteUsuario(@PathParam("id") Long id) {
        usuarioLogicService.deleteMasterUsuario(id);
    }
    
    @GET
    @Path("{id}")
    public UsuarioMasterDTO getUsuario(@PathParam("id") Long id) {
        return usuarioLogicService.getMasterUsuario(id);
    }

    @PUT
    @Path("{id}")
    public void updateUsuario(@PathParam("id") Long id, UsuarioMasterDTO usuario) {
        usuarioLogicService.updateMasterUsuario(usuario);
    }

}
