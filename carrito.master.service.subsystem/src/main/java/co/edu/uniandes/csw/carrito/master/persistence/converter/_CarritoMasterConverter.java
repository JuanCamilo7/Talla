package co.edu.uniandes.csw.carrito.master.persistence.converter;
import co.edu.uniandes.csw.carrito.master.persistence.entity.CarritoProductoEntity;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.carrito.logic.dto.CarritoDTO;
import co.edu.uniandes.csw.carrito.master.logic.dto.CarritoMasterDTO;
import co.edu.uniandes.csw.carrito.persistence.converter.CarritoConverter;
import co.edu.uniandes.csw.carrito.persistence.entity.CarritoEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _CarritoMasterConverter {

    public static CarritoMasterDTO entity2PersistenceDTO(CarritoEntity carritoEntity 
    ,List<CarritoProductoEntity> carritoProductoEntity 
    ) {
        CarritoDTO carritoDTO = CarritoConverter.entity2PersistenceDTO(carritoEntity);
        ArrayList<ProductoDTO> productoEntities = new ArrayList<ProductoDTO>(carritoProductoEntity.size());
        for (CarritoProductoEntity carritoProducto : carritoProductoEntity) {
            productoEntities.add(ProductoConverter.entity2PersistenceDTO(carritoProducto.getProductoEntity()));
        }
        CarritoMasterDTO respDTO  = new CarritoMasterDTO();
        respDTO.setCarritoEntity(carritoDTO);
        respDTO.setListProducto(productoEntities);
        return respDTO;
    }

}