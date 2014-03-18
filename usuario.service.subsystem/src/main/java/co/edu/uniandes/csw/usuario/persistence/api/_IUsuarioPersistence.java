
package co.edu.uniandes.csw.usuario.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;

public interface _IUsuarioPersistence {

	public UsuarioDTO createUsuario(UsuarioDTO detail);
	public List<UsuarioDTO> getUsuarios();
	public UsuarioDTO getUsuario(Long id);
	public void deleteUsuario(Long id);
	public void updateUsuario(UsuarioDTO detail);
	
}