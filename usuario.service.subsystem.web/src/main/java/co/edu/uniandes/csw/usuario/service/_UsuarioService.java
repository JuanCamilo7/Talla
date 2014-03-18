package co.edu.uniandes.csw.usuario.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.inject.Inject;

import co.edu.uniandes.csw.usuario.logic.api.IUsuarioLogicService;
import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;


public abstract class _UsuarioService {

	@Inject
	protected IUsuarioLogicService usuarioLogicService;
	
	@POST
	public UsuarioDTO createUsuario(UsuarioDTO usuario){
		return usuarioLogicService.createUsuario(usuario);
	}
	
	@DELETE
	@Path("{id}")
	public void deleteUsuario(@PathParam("id") Long id){
		usuarioLogicService.deleteUsuario(id);
	}
	
	@GET
	public List<UsuarioDTO> getUsuarios(){
		return usuarioLogicService.getUsuarios();
	}
	
	@GET
	@Path("{id}")
	public UsuarioDTO getUsuario(@PathParam("id") Long id){
		return usuarioLogicService.getUsuario(id);
	}
	
	@PUT
    @Path("{id}")
	public void updateUsuario(@PathParam("id") Long id, UsuarioDTO usuario){
		usuarioLogicService.updateUsuario(usuario);
	}
	
}