
package co.edu.uniandes.csw.carrito.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.carrito.logic.dto.CarritoDTO;
import co.edu.uniandes.csw.carrito.logic.api._ICarritoLogicService;
import co.edu.uniandes.csw.carrito.persistence.api.ICarritoPersistence;

public abstract class _CarritoLogicService implements _ICarritoLogicService {

	@Inject
	protected ICarritoPersistence persistance;

	public CarritoDTO createCarrito(CarritoDTO carrito){
		return persistance.createCarrito( carrito); 
    }

	public List<CarritoDTO> getCarritos(){
		return persistance.getCarritos(); 
	}

	public CarritoDTO getCarrito(Long id){
		return persistance.getCarrito(id); 
	}

	public void deleteCarrito(Long id){
	    persistance.deleteCarrito(id); 
	}

	public void updateCarrito(CarritoDTO carrito){
	    persistance.updateCarrito(carrito); 
	}	
}