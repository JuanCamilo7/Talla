package co.edu.uniandes.csw.carrito.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class CarritoProductoEntityId implements Serializable{

    private Long carritoId;
    private Long productoId;

    @Override
    public int hashCode() {
        return (int) (carritoId + productoId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CarritoProductoEntityId) {
            CarritoProductoEntityId otherId = (CarritoProductoEntityId) object;
            return (otherId.carritoId == this.carritoId) && (otherId.productoId == this.productoId);
        }
        return false;
    }

}
