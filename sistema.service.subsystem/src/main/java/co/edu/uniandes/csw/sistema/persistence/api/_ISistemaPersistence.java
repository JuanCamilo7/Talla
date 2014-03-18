
package co.edu.uniandes.csw.sistema.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.sistema.logic.dto.SistemaDTO;

public interface _ISistemaPersistence {

	public SistemaDTO createSistema(SistemaDTO detail);
	public List<SistemaDTO> getSistemas();
	public SistemaDTO getSistema(Long id);
	public void deleteSistema(Long id);
	public void updateSistema(SistemaDTO detail);
	
}