
package co.edu.uniandes.csw.promocion.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;
import co.edu.uniandes.csw.promocion.logic.api._IPromocionLogicService;
import co.edu.uniandes.csw.promocion.persistence.api.IPromocionPersistence;

public abstract class _PromocionLogicService implements _IPromocionLogicService {

	@Inject
	protected IPromocionPersistence persistance;

	public PromocionDTO createPromocion(PromocionDTO promocion){
		return persistance.createPromocion( promocion); 
    }

	public List<PromocionDTO> getPromocions(){
		return persistance.getPromocions(); 
	}

	public PromocionDTO getPromocion(Long id){
		return persistance.getPromocion(id); 
	}

	public void deletePromocion(Long id){
	    persistance.deletePromocion(id); 
	}

	public void updatePromocion(PromocionDTO promocion){
	    persistance.updatePromocion(promocion); 
	}	
}