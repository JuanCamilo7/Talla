
package co.edu.uniandes.csw.catalogo.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.catalogo.logic.dto.CatalogoDTO;
import co.edu.uniandes.csw.catalogo.logic.api._ICatalogoLogicService;

public abstract class _CatalogoMockLogicService implements _ICatalogoLogicService {

	private Long id= new Long(1);
	protected List<CatalogoDTO> data=new ArrayList<CatalogoDTO>();

	public CatalogoDTO createCatalogo(CatalogoDTO catalogo){
		id++;
		catalogo.setId(id);
		return catalogo;
    }

	public List<CatalogoDTO> getCatalogos(){
		return data; 
	}

	public CatalogoDTO getCatalogo(Long id){
		for(CatalogoDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteCatalogo(Long id){
	    CatalogoDTO delete=null;
		for(CatalogoDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateCatalogo(CatalogoDTO catalogo){
	    CatalogoDTO delete=null;
		for(CatalogoDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(catalogo);
		} 
	}	
}