 
package co.edu.uniandes.csw.catalogo.master.logic.api;

import co.edu.uniandes.csw.catalogo.master.logic.dto.CatalogoMasterDTO;

public interface _ICatalogoMasterLogicService {

	public CatalogoMasterDTO createMasterCatalogo(CatalogoMasterDTO detail);
    public void updateMasterCatalogo(CatalogoMasterDTO detail);
	public void deleteMasterCatalogo(Long id); 
	public CatalogoMasterDTO getMasterCatalogo(Long id);
        
}