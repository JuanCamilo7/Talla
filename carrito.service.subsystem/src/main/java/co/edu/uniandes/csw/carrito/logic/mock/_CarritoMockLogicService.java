
package co.edu.uniandes.csw.carrito.logic.mock;
import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.carrito.logic.dto.CarritoDTO;
import co.edu.uniandes.csw.carrito.logic.api._ICarritoLogicService;

public abstract class _CarritoMockLogicService implements _ICarritoLogicService {

	private Long id= new Long(1);
	protected List<CarritoDTO> data=new ArrayList<CarritoDTO>();

	public CarritoDTO createCarrito(CarritoDTO carrito){
		id++;
		carrito.setId(id);
		return carrito;
    }

	public List<CarritoDTO> getCarritos(){
		return data; 
	}

	public CarritoDTO getCarrito(Long id){
		for(CarritoDTO d:data){
			if(d.getId().equals(id)){
				return d;
			}
		}
		return null;
	}

	public void deleteCarrito(Long id){
	    CarritoDTO delete=null;
		for(CarritoDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
		} 
	}

	public void updateCarrito(CarritoDTO carrito){
	    CarritoDTO delete=null;
		for(CarritoDTO d:data){
			if(d.getId().equals(id)){
				delete=d;
			}
		}
		if(delete!=null){
			data.remove(delete);
			data.add(carrito);
		} 
	}	
}