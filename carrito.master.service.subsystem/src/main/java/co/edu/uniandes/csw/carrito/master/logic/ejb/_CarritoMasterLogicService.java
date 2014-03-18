package co.edu.uniandes.csw.carrito.master.logic.ejb;

import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import co.edu.uniandes.csw.carrito.logic.dto.CarritoDTO;
import co.edu.uniandes.csw.carrito.master.logic.api._ICarritoMasterLogicService;
import co.edu.uniandes.csw.carrito.master.logic.dto.CarritoMasterDTO;
import co.edu.uniandes.csw.carrito.master.persistence.api.ICarritoMasterPersistence;
import co.edu.uniandes.csw.carrito.master.persistence.entity.CarritoProductoEntity;
import co.edu.uniandes.csw.carrito.persistence.api.ICarritoPersistence;
import javax.inject.Inject;

public abstract class _CarritoMasterLogicService implements _ICarritoMasterLogicService {

    @Inject
    protected ICarritoPersistence carritoPersistance;
    @Inject
    protected ICarritoMasterPersistence carritoMasterPersistance;
    @Inject
    protected IProductoPersistence productoPersistance;

    public CarritoMasterDTO createMasterCarrito(CarritoMasterDTO carrito) {
        CarritoDTO persistedCarritoDTO = carritoPersistance.createCarrito(carrito.getCarritoEntity());
        if (carrito.getCreateProducto() != null) {
            for (ProductoDTO productoDTO : carrito.getCreateProducto()) {
                ProductoDTO persistedProductoDTO = productoPersistance.createProducto(productoDTO);
                CarritoProductoEntity carritoProductoEntity = new CarritoProductoEntity(persistedCarritoDTO.getId(), persistedProductoDTO.getId());
                carritoMasterPersistance.createCarritoProducto(carritoProductoEntity);
            }
        }
        return carrito;
    }

    public CarritoMasterDTO getMasterCarrito(Long id) {
        return carritoMasterPersistance.getCarrito(id);
    }

    public void deleteMasterCarrito(Long id) {
        carritoPersistance.deleteCarrito(id);
    }

    public void updateMasterCarrito(CarritoMasterDTO carrito) {
        carritoPersistance.updateCarrito(carrito.getCarritoEntity());

        //---- FOR RELATIONSHIP
        // persist new producto
        if (carrito.getCreateProducto() != null) {
            for (ProductoDTO productoDTO : carrito.getCreateProducto()) {
                ProductoDTO persistedProductoDTO = productoPersistance.createProducto(productoDTO);
                CarritoProductoEntity carritoProductoEntity = new CarritoProductoEntity(carrito.getCarritoEntity().getId(), persistedProductoDTO.getId());
                carritoMasterPersistance.createCarritoProducto(carritoProductoEntity);
            }
        }
        // update producto
        if (carrito.getUpdateProducto() != null) {
            for (ProductoDTO productoDTO : carrito.getUpdateProducto()) {
                productoPersistance.updateProducto(productoDTO);
            }
        }
        // delete producto
        if (carrito.getDeleteProducto() != null) {
            for (ProductoDTO productoDTO : carrito.getDeleteProducto()) {
                carritoMasterPersistance.deleteCarritoProducto(carrito.getCarritoEntity().getId(), productoDTO.getId());
                productoPersistance.deleteProducto(productoDTO.getId());
            }
        }
    }
}
