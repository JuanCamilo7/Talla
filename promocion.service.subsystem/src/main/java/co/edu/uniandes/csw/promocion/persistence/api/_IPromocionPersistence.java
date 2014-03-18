
package co.edu.uniandes.csw.promocion.persistence.api;

import java.util.List; 

import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;

public interface _IPromocionPersistence {

	public PromocionDTO createPromocion(PromocionDTO detail);
	public List<PromocionDTO> getPromocions();
	public PromocionDTO getPromocion(Long id);
	public void deletePromocion(Long id);
	public void updatePromocion(PromocionDTO detail);
	
}