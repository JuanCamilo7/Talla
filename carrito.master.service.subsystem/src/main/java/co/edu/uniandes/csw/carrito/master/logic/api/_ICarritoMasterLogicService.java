 
package co.edu.uniandes.csw.carrito.master.logic.api;

import co.edu.uniandes.csw.carrito.master.logic.dto.CarritoMasterDTO;

public interface _ICarritoMasterLogicService {

	public CarritoMasterDTO createMasterCarrito(CarritoMasterDTO detail);
    public void updateMasterCarrito(CarritoMasterDTO detail);
	public void deleteMasterCarrito(Long id); 
	public CarritoMasterDTO getMasterCarrito(Long id);
        
}