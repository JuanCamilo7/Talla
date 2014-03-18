
package co.edu.uniandes.csw.carrito.logic.api;

import java.util.List; 

import co.edu.uniandes.csw.carrito.logic.dto.CarritoDTO;

public interface _ICarritoLogicService {

	public CarritoDTO createCarrito(CarritoDTO detail);
	public List<CarritoDTO> getCarritos();
	public CarritoDTO getCarrito(Long id);
	public void deleteCarrito(Long id);
	public void updateCarrito(CarritoDTO detail);
	
}