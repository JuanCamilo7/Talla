package co.edu.uniandes.csw.producto.master.persistence.entity;

import co.edu.uniandes.csw.promocion.persistence.entity.PromocionEntity;
import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;
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
@IdClass(ProductoPromocionEntityId.class)
@NamedQueries({
    @NamedQuery(name = "ProductoPromocionEntity.getPromocionListForProducto", query = "SELECT  u FROM ProductoPromocionEntity u WHERE u.productoId=:productoId"),
    @NamedQuery(name = "ProductoPromocionEntity.deleteProductoPromocion", query = "DELETE FROM ProductoPromocionEntity u WHERE u.promocionId=:promocionId and  u.productoId=:productoId")
})
public class ProductoPromocionEntity implements Serializable {

    @Id
    @Column(name = "productoId")
    private Long productoId;
    @Id
    @Column(name = "promocionId")
    private Long promocionId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "promocionId", referencedColumnName = "id")
    @JoinFetch
    private PromocionEntity promocionEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "productoId", referencedColumnName = "id")
    @JoinFetch
    private ProductoEntity productoEntity;

    public ProductoPromocionEntity() {
    }

    public ProductoPromocionEntity(Long productoId, Long promocionId) {
        this.productoId = productoId;
        this.promocionId = promocionId;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Long getPromocionId() {
        return promocionId;
    }

    public void setPromocionId(Long promocionId) {
        this.promocionId = promocionId;
    }

    public PromocionEntity getPromocionEntity() {
        return promocionEntity;
    }

    public void setPromocionEntity(PromocionEntity promocionEntity) {
        this.promocionEntity = promocionEntity;
    }

    public ProductoEntity getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoEntity productoEntity) {
        this.productoEntity = productoEntity;
    }

}
