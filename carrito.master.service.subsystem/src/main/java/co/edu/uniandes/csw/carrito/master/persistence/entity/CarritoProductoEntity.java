package co.edu.uniandes.csw.carrito.master.persistence.entity;

import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;
import co.edu.uniandes.csw.carrito.persistence.entity.CarritoEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn; 
import org.eclipse.persistence.annotations.JoinFetch;

@Entity
@IdClass(CarritoProductoEntityId.class)
@NamedQueries({
    @NamedQuery(name = "CarritoProductoEntity.getProductoListForCarrito", query = "SELECT  u FROM CarritoProductoEntity u WHERE u.carritoId=:carritoId"),
    @NamedQuery(name = "CarritoProductoEntity.deleteCarritoProducto", query = "DELETE FROM CarritoProductoEntity u WHERE u.productoId=:productoId and  u.carritoId=:carritoId")
})
public class CarritoProductoEntity implements Serializable {

    @Id
    @Column(name = "carritoId")
    private Long carritoId;
    @Id
    @Column(name = "productoId")
    private Long productoId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "productoId", referencedColumnName = "id")
    @JoinFetch
    private ProductoEntity productoEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "carritoId", referencedColumnName = "id")
    @JoinFetch
    private CarritoEntity carritoEntity;

    public CarritoProductoEntity() {
    }

    public CarritoProductoEntity(Long carritoId, Long productoId) {
        this.carritoId = carritoId;
        this.productoId = productoId;
    }

    public Long getCarritoId() {
        return carritoId;
    }

    public void setCarritoId(Long carritoId) {
        this.carritoId = carritoId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public ProductoEntity getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoEntity productoEntity) {
        this.productoEntity = productoEntity;
    }

    public CarritoEntity getCarritoEntity() {
        return carritoEntity;
    }

    public void setCarritoEntity(CarritoEntity carritoEntity) {
        this.carritoEntity = carritoEntity;
    }

}
