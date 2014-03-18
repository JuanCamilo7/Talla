package co.edu.uniandes.csw.producto.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class ProductoPromocionEntityId implements Serializable{

    private Long productoId;
    private Long promocionId;

    @Override
    public int hashCode() {
        return (int) (productoId + promocionId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ProductoPromocionEntityId) {
            ProductoPromocionEntityId otherId = (ProductoPromocionEntityId) object;
            return (otherId.productoId == this.productoId) && (otherId.promocionId == this.promocionId);
        }
        return false;
    }

}
