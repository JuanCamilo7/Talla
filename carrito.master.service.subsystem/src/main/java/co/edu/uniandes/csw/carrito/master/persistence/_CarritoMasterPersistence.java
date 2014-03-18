package co.edu.uniandes.csw.carrito.master.persistence;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.carrito.master.persistence.entity.CarritoProductoEntity;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.carrito.logic.dto.CarritoDTO;
import co.edu.uniandes.csw.carrito.master.logic.dto.CarritoMasterDTO;
import co.edu.uniandes.csw.carrito.master.persistence.api._ICarritoMasterPersistence;
import co.edu.uniandes.csw.carrito.persistence.api.ICarritoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _CarritoMasterPersistence implements _ICarritoMasterPersistence {

    @PersistenceContext(unitName = "CarritoMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected ICarritoPersistence carritoPersistence;  

    public CarritoMasterDTO getCarrito(Long carritoId) {
        CarritoMasterDTO carritoMasterDTO = new CarritoMasterDTO();
        CarritoDTO carrito = carritoPersistence.getCarrito(carritoId);
        carritoMasterDTO.setCarritoEntity(carrito);
        carritoMasterDTO.setListProducto(getProductoListForCarrito(carritoId));
        return carritoMasterDTO;
    }

    public CarritoProductoEntity createCarritoProducto(CarritoProductoEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteCarritoProducto(Long carritoId, Long productoId) {
        Query q = entityManager.createNamedQuery("CarritoProductoEntity.deleteCarritoProducto");
        q.setParameter("carritoId", carritoId);
        q.setParameter("productoId", productoId);
        q.executeUpdate();
    }

    public List<ProductoDTO> getProductoListForCarrito(Long carritoId) {
        ArrayList<ProductoDTO> resp = new ArrayList<ProductoDTO>();
        Query q = entityManager.createNamedQuery("CarritoProductoEntity.getProductoListForCarrito");
        q.setParameter("carritoId", carritoId);
        List<CarritoProductoEntity> qResult =  q.getResultList();
        for (CarritoProductoEntity carritoProductoEntity : qResult) { 
            if(carritoProductoEntity.getProductoEntity()==null){
                entityManager.refresh(carritoProductoEntity);
            }
            resp.add(ProductoConverter.entity2PersistenceDTO(carritoProductoEntity.getProductoEntity()));
        }
        return resp;
    }

}
