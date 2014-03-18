package co.edu.uniandes.csw.producto.master.logic.ejb;

import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;
import co.edu.uniandes.csw.promocion.persistence.api.IPromocionPersistence;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.master.logic.api._IProductoMasterLogicService;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;
import co.edu.uniandes.csw.producto.master.persistence.api.IProductoMasterPersistence;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoPromocionEntity;
import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import javax.inject.Inject;

public abstract class _ProductoMasterLogicService implements _IProductoMasterLogicService {

    @Inject
    protected IProductoPersistence productoPersistance;
    @Inject
    protected IProductoMasterPersistence productoMasterPersistance;
    @Inject
    protected IPromocionPersistence promocionPersistance;

    public ProductoMasterDTO createMasterProducto(ProductoMasterDTO producto) {
        ProductoDTO persistedProductoDTO = productoPersistance.createProducto(producto.getProductoEntity());
        if (producto.getCreatePromocion() != null) {
            for (PromocionDTO promocionDTO : producto.getCreatePromocion()) {
                PromocionDTO persistedPromocionDTO = promocionPersistance.createPromocion(promocionDTO);
                ProductoPromocionEntity productoPromocionEntity = new ProductoPromocionEntity(persistedProductoDTO.getId(), persistedPromocionDTO.getId());
                productoMasterPersistance.createProductoPromocion(productoPromocionEntity);
            }
        }
        return producto;
    }

    public ProductoMasterDTO getMasterProducto(Long id) {
        return productoMasterPersistance.getProducto(id);
    }

    public void deleteMasterProducto(Long id) {
        productoPersistance.deleteProducto(id);
    }

    public void updateMasterProducto(ProductoMasterDTO producto) {
        productoPersistance.updateProducto(producto.getProductoEntity());

        //---- FOR RELATIONSHIP
        // persist new promocion
        if (producto.getCreatePromocion() != null) {
            for (PromocionDTO promocionDTO : producto.getCreatePromocion()) {
                PromocionDTO persistedPromocionDTO = promocionPersistance.createPromocion(promocionDTO);
                ProductoPromocionEntity productoPromocionEntity = new ProductoPromocionEntity(producto.getProductoEntity().getId(), persistedPromocionDTO.getId());
                productoMasterPersistance.createProductoPromocion(productoPromocionEntity);
            }
        }
        // update promocion
        if (producto.getUpdatePromocion() != null) {
            for (PromocionDTO promocionDTO : producto.getUpdatePromocion()) {
                promocionPersistance.updatePromocion(promocionDTO);
            }
        }
        // delete promocion
        if (producto.getDeletePromocion() != null) {
            for (PromocionDTO promocionDTO : producto.getDeletePromocion()) {
                productoMasterPersistance.deleteProductoPromocion(producto.getProductoEntity().getId(), promocionDTO.getId());
                promocionPersistance.deletePromocion(promocionDTO.getId());
            }
        }
    }
}
