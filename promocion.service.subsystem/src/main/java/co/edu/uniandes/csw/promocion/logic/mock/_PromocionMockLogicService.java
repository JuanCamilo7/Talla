
package co.edu.uniandes.csw.promocion.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;
import co.edu.uniandes.csw.promocion.logic.api._IPromocionLogicService;

public abstract class _PromocionMockLogicService implements _IPromocionLogicService {

	private Long id= new Long(1);
	protected List<PromocionDTO> data=new ArrayList<PromocionDTO>();

	public PromocionDTO createPromocion(PromocionDTO promocion){
		id++;
		promocion.setId(id);
		return promocion;
    }

	public List<PromocionDTO> getPromocions(){
		return data; 
	}

	public PromocionDTO getPromocion(Long id){
		for(PromocionDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deletePromocion(Long id){
	    PromocionDTO delete=null;
		for(PromocionDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updatePromocion(PromocionDTO promocion){
	    PromocionDTO delete=null;
		for(PromocionDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(promocion);
		} 
	}	
}