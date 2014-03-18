package co.edu.uniandes.csw.catalogo.master.persistence.api;

import co.edu.uniandes.csw.catalogo.master.persistence.entity.CatalogoProductoEntity;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.catalogo.master.logic.dto.CatalogoMasterDTO;
import java.util.List;

public interface _ICatalogoMasterPersistence {

    public CatalogoProductoEntity createCatalogoProducto(CatalogoProductoEntity entity);

    public void deleteCatalogoProducto(Long catalogoId, Long productoId);
    
    public List<ProductoDTO> getProductoListForCatalogo(Long catalogoId);
    
    public CatalogoMasterDTO getCatalogo(Long catalogoId);

}
