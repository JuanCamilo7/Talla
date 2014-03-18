 
package co.edu.uniandes.csw.usuario.master.logic.api;

import co.edu.uniandes.csw.usuario.master.logic.dto.UsuarioMasterDTO;

public interface _IUsuarioMasterLogicService {

	public UsuarioMasterDTO createMasterUsuario(UsuarioMasterDTO detail);
    public void updateMasterUsuario(UsuarioMasterDTO detail);
	public void deleteMasterUsuario(Long id); 
	public UsuarioMasterDTO getMasterUsuario(Long id);
        
}