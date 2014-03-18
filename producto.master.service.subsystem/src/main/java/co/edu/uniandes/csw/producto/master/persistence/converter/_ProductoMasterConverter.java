package co.edu.uniandes.csw.producto.master.persistence.converter;
import co.edu.uniandes.csw.producto.master.persistence.entity.ProductoPromocionEntity;
import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;
import co.edu.uniandes.csw.promocion.persistence.converter.PromocionConverter;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.master.logic.dto.ProductoMasterDTO;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _ProductoMasterConverter {

    public static ProductoMasterDTO entity2PersistenceDTO(ProductoEntity productoEntity 
    ,List<ProductoPromocionEntity> productoPromocionEntity 
    ) {
        ProductoDTO productoDTO = ProductoConverter.entity2PersistenceDTO(productoEntity);
        ArrayList<PromocionDTO> promocionEntities = new ArrayList<PromocionDTO>(productoPromocionEntity.size());
        for (ProductoPromocionEntity productoPromocion : productoPromocionEntity) {
            promocionEntities.add(PromocionConverter.entity2PersistenceDTO(productoPromocion.getPromocionEntity()));
        }
        ProductoMasterDTO respDTO  = new ProductoMasterDTO();
        respDTO.setProductoEntity(productoDTO);
        respDTO.setListPromocion(promocionEntities);
        return respDTO;
    }

}