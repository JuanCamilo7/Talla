
package co.edu.uniandes.csw.producto.persistence;

import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

import co.edu.uniandes.csw.producto.persistence.api.IProductoPersistence;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import java.util.List;
import javax.ejb.LocalBean;
import javax.persistence.Query;

@Default
@Stateless 
@LocalBean
public class ProductoPersistence extends _ProductoPersistence  implements IProductoPersistence {
    public List<ProductoDTO> productoSearch(String descr) {
        Query q = entityManager.createQuery("select u from ProductoEntity u where u.talla like '%" + descr + "%'");
 
        List list = q.getResultList();
        if (list.size() != 0) {
            return ProductoConverter.entity2PersistenceDTOList(list);
        }
        return null;
    }

}