package co.edu.uniandes.csw.catalogo.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class CatalogoProductoEntityId implements Serializable{

    private Long catalogoId;
    private Long productoId;

    @Override
    public int hashCode() {
        return (int) (catalogoId + productoId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CatalogoProductoEntityId) {
            CatalogoProductoEntityId otherId = (CatalogoProductoEntityId) object;
            return (otherId.catalogoId == this.catalogoId) && (otherId.productoId == this.productoId);
        }
        return false;
    }

}
