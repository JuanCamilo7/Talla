
package co.edu.uniandes.csw.usuario.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.usuario.logic.api._IUsuarioLogicService;
import co.edu.uniandes.csw.usuario.persistence.api.IUsuarioPersistence;

public abstract class _UsuarioLogicService implements _IUsuarioLogicService {

	@Inject
	protected IUsuarioPersistence persistance;

	public UsuarioDTO createUsuario(UsuarioDTO usuario){
		return persistance.createUsuario( usuario); 
    }

	public List<UsuarioDTO> getUsuarios(){
		return persistance.getUsuarios(); 
	}

	public UsuarioDTO getUsuario(Long id){
		return persistance.getUsuario(id); 
	}

	public void deleteUsuario(Long id){
	    persistance.deleteUsuario(id); 
	}

	public void updateUsuario(UsuarioDTO usuario){
	    persistance.updateUsuario(usuario); 
	}	
}