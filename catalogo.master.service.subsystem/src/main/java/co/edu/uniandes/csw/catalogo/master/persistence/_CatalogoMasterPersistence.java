package co.edu.uniandes.csw.catalogo.master.persistence;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.catalogo.master.persistence.entity.CatalogoProductoEntity;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.catalogo.logic.dto.CatalogoDTO;
import co.edu.uniandes.csw.catalogo.master.logic.dto.CatalogoMasterDTO;
import co.edu.uniandes.csw.catalogo.master.persistence.api._ICatalogoMasterPersistence;
import co.edu.uniandes.csw.catalogo.persistence.api.ICatalogoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _CatalogoMasterPersistence implements _ICatalogoMasterPersistence {

    @PersistenceContext(unitName = "CatalogoMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected ICatalogoPersistence catalogoPersistence;  

    public CatalogoMasterDTO getCatalogo(Long catalogoId) {
        CatalogoMasterDTO catalogoMasterDTO = new CatalogoMasterDTO();
        CatalogoDTO catalogo = catalogoPersistence.getCatalogo(catalogoId);
        catalogoMasterDTO.setCatalogoEntity(catalogo);
        catalogoMasterDTO.setListProducto(getProductoListForCatalogo(catalogoId));
        return catalogoMasterDTO;
    }

    public CatalogoProductoEntity createCatalogoProducto(CatalogoProductoEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteCatalogoProducto(Long catalogoId, Long productoId) {
        Query q = entityManager.createNamedQuery("CatalogoProductoEntity.deleteCatalogoProducto");
        q.setParameter("catalogoId", catalogoId);
        q.setParameter("productoId", productoId);
        q.executeUpdate();
    }

    public List<ProductoDTO> getProductoListForCatalogo(Long catalogoId) {
        ArrayList<ProductoDTO> resp = new ArrayList<ProductoDTO>();
        Query q = entityManager.createNamedQuery("CatalogoProductoEntity.getProductoListForCatalogo");
        q.setParameter("catalogoId", catalogoId);
        List<CatalogoProductoEntity> qResult =  q.getResultList();
        for (CatalogoProductoEntity catalogoProductoEntity : qResult) { 
            if(catalogoProductoEntity.getProductoEntity()==null){
                entityManager.refresh(catalogoProductoEntity);
            }
            resp.add(ProductoConverter.entity2PersistenceDTO(catalogoProductoEntity.getProductoEntity()));
        }
        return resp;
    }

}
