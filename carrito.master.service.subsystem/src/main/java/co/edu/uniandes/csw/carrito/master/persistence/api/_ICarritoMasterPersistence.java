package co.edu.uniandes.csw.carrito.master.persistence.api;

import co.edu.uniandes.csw.carrito.master.persistence.entity.CarritoProductoEntity;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.carrito.master.logic.dto.CarritoMasterDTO;
import java.util.List;

public interface _ICarritoMasterPersistence {

    public CarritoProductoEntity createCarritoProducto(CarritoProductoEntity entity);

    public void deleteCarritoProducto(Long carritoId, Long productoId);
    
    public List<ProductoDTO> getProductoListForCarrito(Long carritoId);
    
    public CarritoMasterDTO getCarrito(Long carritoId);

}
