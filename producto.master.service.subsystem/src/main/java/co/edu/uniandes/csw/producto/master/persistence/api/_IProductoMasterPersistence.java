package co.edu.uniandes.csw.producto.master.persistence.api;

import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoPromocionEntity;
import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;
import java.util.List;

public interface _IProductoMasterPersistence {

    public ProductoPromocionEntity createProductoPromocion(ProductoPromocionEntity entity);

    public void deleteProductoPromocion(Long productoId, Long promocionId);
    
    public List<PromocionDTO> getPromocionListForProducto(Long productoId);
    
    public ProductoMasterDTO getProducto(Long productoId);

}
