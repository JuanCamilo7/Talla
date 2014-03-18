 
package co.edu.uniandes.csw.sistema.master.logic.api;

import co.edu.uniandes.csw.sistema.master.logic.dto.SistemaMasterDTO;

public interface _ISistemaMasterLogicService {

	public SistemaMasterDTO createMasterSistema(SistemaMasterDTO detail);
    public void updateMasterSistema(SistemaMasterDTO detail);
	public void deleteMasterSistema(Long id); 
	public SistemaMasterDTO getMasterSistema(Long id);
        
}