package co.edu.uniandes.csw.producto.master.persistence;
import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoPromocionEntity;
import co.edu.uniandes.csw.promocion.persistence.converter.PromocionConverter;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;
import co.edu.uniandes.csw.producto.master.persistence.api._IProductoMasterPersistence;
import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _ProductoMasterPersistence implements _IProductoMasterPersistence {

    @PersistenceContext(unitName = "ProductoMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IProductoPersistence productoPersistence;  

    public ProductoMasterDTO getProducto(Long productoId) {
        ProductoMasterDTO productoMasterDTO = new ProductoMasterDTO();
        ProductoDTO producto = productoPersistence.getProducto(productoId);
        productoMasterDTO.setProductoEntity(producto);
        productoMasterDTO.setListPromocion(getPromocionListForProducto(productoId));
        return productoMasterDTO;
    }

    public ProductoPromocionEntity createProductoPromocion(ProductoPromocionEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteProductoPromocion(Long productoId, Long promocionId) {
        Query q = entityManager.createNamedQuery("ProductoPromocionEntity.deleteProductoPromocion");
        q.setParameter("productoId", productoId);
        q.setParameter("promocionId", promocionId);
        q.executeUpdate();
    }

    public List<PromocionDTO> getPromocionListForProducto(Long productoId) {
        ArrayList<PromocionDTO> resp = new ArrayList<PromocionDTO>();
        Query q = entityManager.createNamedQuery("ProductoPromocionEntity.getPromocionListForProducto");
        q.setParameter("productoId", productoId);
        List<ProductoPromocionEntity> qResult =  q.getResultList();
        for (ProductoPromocionEntity productoPromocionEntity : qResult) { 
            if(productoPromocionEntity.getPromocionEntity()==null){
                entityManager.refresh(productoPromocionEntity);
            }
            resp.add(PromocionConverter.entity2PersistenceDTO(productoPromocionEntity.getPromocionEntity()));
        }
        return resp;
    }

}
