
package co.edu.uniandes.csw.sistema.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.sistema.logic.dto.SistemaDTO;
import co.edu.uniandes.csw.sistema.logic.api._ISistemaLogicService;

public abstract class _SistemaMockLogicService implements _ISistemaLogicService {

	private Long id= new Long(1);
	protected List<SistemaDTO> data=new ArrayList<SistemaDTO>();

	public SistemaDTO createSistema(SistemaDTO sistema){
		id++;
		sistema.setId(id);
		return sistema;
    }

	public List<SistemaDTO> getSistemas(){
		return data; 
	}

	public SistemaDTO getSistema(Long id){
		for(SistemaDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteSistema(Long id){
	    SistemaDTO delete=null;
		for(SistemaDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateSistema(SistemaDTO sistema){
	    SistemaDTO delete=null;
		for(SistemaDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(sistema);
		} 
	}	
}