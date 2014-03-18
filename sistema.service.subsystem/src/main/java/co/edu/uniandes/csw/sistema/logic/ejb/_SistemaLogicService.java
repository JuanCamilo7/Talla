
package co.edu.uniandes.csw.sistema.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.sistema.logic.dto.SistemaDTO;
import co.edu.uniandes.csw.sistema.logic.api._ISistemaLogicService;
import co.edu.uniandes.csw.sistema.persistence.api.ISistemaPersistence;

public abstract class _SistemaLogicService implements _ISistemaLogicService {

	@Inject
	protected ISistemaPersistence persistance;

	public SistemaDTO createSistema(SistemaDTO sistema){
		return persistance.createSistema( sistema); 
    }

	public List<SistemaDTO> getSistemas(){
		return persistance.getSistemas(); 
	}

	public SistemaDTO getSistema(Long id){
		return persistance.getSistema(id); 
	}

	public void deleteSistema(Long id){
	    persistance.deleteSistema(id); 
	}

	public void updateSistema(SistemaDTO sistema){
	    persistance.updateSistema(sistema); 
	}	
}