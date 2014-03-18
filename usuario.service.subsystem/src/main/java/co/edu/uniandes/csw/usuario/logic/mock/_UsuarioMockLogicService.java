
package co.edu.uniandes.csw.usuario.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.usuario.logic.api._IUsuarioLogicService;

public abstract class _UsuarioMockLogicService implements _IUsuarioLogicService {

	private Long id= new Long(1);
	protected List<UsuarioDTO> data=new ArrayList<UsuarioDTO>();

	public UsuarioDTO createUsuario(UsuarioDTO usuario){
		id++;
		usuario.setId(id);
		return usuario;
    }

	public List<UsuarioDTO> getUsuarios(){
		return data; 
	}

	public UsuarioDTO getUsuario(Long id){
		for(UsuarioDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteUsuario(Long id){
	    UsuarioDTO delete=null;
		for(UsuarioDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateUsuario(UsuarioDTO usuario){
	    UsuarioDTO delete=null;
		for(UsuarioDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(usuario);
		} 
	}	
}