package co.edu.uniandes.csw.catalogo.master.persistence.converter;
import co.edu.uniandes.csw.catalogo.master.persistence.entity.CatalogoProductoEntity;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.persistence.converter.ProductoConverter;
import co.edu.uniandes.csw.catalogo.logic.dto.CatalogoDTO;
import co.edu.uniandes.csw.catalogo.master.logic.dto.CatalogoMasterDTO;
import co.edu.uniandes.csw.catalogo.persistence.converter.CatalogoConverter;
import co.edu.uniandes.csw.catalogo.persistence.entity.CatalogoEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _CatalogoMasterConverter {

    public static CatalogoMasterDTO entity2PersistenceDTO(CatalogoEntity catalogoEntity 
    ,List<CatalogoProductoEntity> catalogoProductoEntity 
    ) {
        CatalogoDTO catalogoDTO = CatalogoConverter.entity2PersistenceDTO(catalogoEntity);
        ArrayList<ProductoDTO> productoEntities = new ArrayList<ProductoDTO>(catalogoProductoEntity.size());
        for (CatalogoProductoEntity catalogoProducto : catalogoProductoEntity) {
            productoEntities.add(ProductoConverter.entity2PersistenceDTO(catalogoProducto.getProductoEntity()));
        }
        CatalogoMasterDTO respDTO  = new CatalogoMasterDTO();
        respDTO.setCatalogoEntity(catalogoDTO);
        respDTO.setListProducto(productoEntities);
        return respDTO;
    }

}