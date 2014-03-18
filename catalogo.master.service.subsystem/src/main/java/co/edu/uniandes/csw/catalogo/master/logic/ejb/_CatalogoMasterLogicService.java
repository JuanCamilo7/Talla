package co.edu.uniandes.csw.catalogo.master.logic.ejb;

import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import co.edu.uniandes.csw.catalogo.logic.dto.CatalogoDTO;
import co.edu.uniandes.csw.catalogo.master.logic.api._ICatalogoMasterLogicService;
import co.edu.uniandes.csw.catalogo.master.logic.dto.CatalogoMasterDTO;
import co.edu.uniandes.csw.catalogo.master.persistence.api.ICatalogoMasterPersistence;
import co.edu.uniandes.csw.catalogo.master.persistence.entity.CatalogoProductoEntity;
import co.edu.uniandes.csw.catalogo.persistence.api.ICatalogoPersistence;
import javax.inject.Inject;

public abstract class _CatalogoMasterLogicService implements _ICatalogoMasterLogicService {

    @Inject
    protected ICatalogoPersistence catalogoPersistance;
    @Inject
    protected ICatalogoMasterPersistence catalogoMasterPersistance;
    @Inject
    protected IProductoPersistence productoPersistance;

    public CatalogoMasterDTO createMasterCatalogo(CatalogoMasterDTO catalogo) {
        CatalogoDTO persistedCatalogoDTO = catalogoPersistance.createCatalogo(catalogo.getCatalogoEntity());
        if (catalogo.getCreateProducto() != null) {
            for (ProductoDTO productoDTO : catalogo.getCreateProducto()) {
                ProductoDTO persistedProductoDTO = productoPersistance.createProducto(productoDTO);
                CatalogoProductoEntity catalogoProductoEntity = new CatalogoProductoEntity(persistedCatalogoDTO.getId(), persistedProductoDTO.getId());
                catalogoMasterPersistance.createCatalogoProducto(catalogoProductoEntity);
            }
        }
        return catalogo;
    }

    public CatalogoMasterDTO getMasterCatalogo(Long id) {
        return catalogoMasterPersistance.getCatalogo(id);
    }

    public void deleteMasterCatalogo(Long id) {
        catalogoPersistance.deleteCatalogo(id);
    }

    public void updateMasterCatalogo(CatalogoMasterDTO catalogo) {
        catalogoPersistance.updateCatalogo(catalogo.getCatalogoEntity());

        //---- FOR RELATIONSHIP
        // persist new producto
        if (catalogo.getCreateProducto() != null) {
            for (ProductoDTO productoDTO : catalogo.getCreateProducto()) {
                ProductoDTO persistedProductoDTO = productoPersistance.createProducto(productoDTO);
                CatalogoProductoEntity catalogoProductoEntity = new CatalogoProductoEntity(catalogo.getCatalogoEntity().getId(), persistedProductoDTO.getId());
                catalogoMasterPersistance.createCatalogoProducto(catalogoProductoEntity);
            }
        }
        // update producto
        if (catalogo.getUpdateProducto() != null) {
            for (ProductoDTO productoDTO : catalogo.getUpdateProducto()) {
                productoPersistance.updateProducto(productoDTO);
            }
        }
        // delete producto
        if (catalogo.getDeleteProducto() != null) {
            for (ProductoDTO productoDTO : catalogo.getDeleteProducto()) {
                catalogoMasterPersistance.deleteCatalogoProducto(catalogo.getCatalogoEntity().getId(), productoDTO.getId());
                productoPersistance.deleteProducto(productoDTO.getId());
            }
        }
    }
}
