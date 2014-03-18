package co.edu.uniandes.csw.catalogo.master.persistence.entity;

import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;
import co.edu.uniandes.csw.catalogo.persistence.entity.CatalogoEntity;
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
@IdClass(CatalogoProductoEntityId.class)
@NamedQueries({
    @NamedQuery(name = "CatalogoProductoEntity.getProductoListForCatalogo", query = "SELECT  u FROM CatalogoProductoEntity u WHERE u.catalogoId=:catalogoId"),
    @NamedQuery(name = "CatalogoProductoEntity.deleteCatalogoProducto", query = "DELETE FROM CatalogoProductoEntity u WHERE u.productoId=:productoId and  u.catalogoId=:catalogoId")
})
public class CatalogoProductoEntity implements Serializable {

    @Id
    @Column(name = "catalogoId")
    private Long catalogoId;
    @Id
    @Column(name = "productoId")
    private Long productoId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "productoId", referencedColumnName = "id")
    @JoinFetch
    private ProductoEntity productoEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "catalogoId", referencedColumnName = "id")
    @JoinFetch
    private CatalogoEntity catalogoEntity;

    public CatalogoProductoEntity() {
    }

    public CatalogoProductoEntity(Long catalogoId, Long productoId) {
        this.catalogoId = catalogoId;
        this.productoId = productoId;
    }

    public Long getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(Long catalogoId) {
        this.catalogoId = catalogoId;
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

    public CatalogoEntity getCatalogoEntity() {
        return catalogoEntity;
    }

    public void setCatalogoEntity(CatalogoEntity catalogoEntity) {
        this.catalogoEntity = catalogoEntity;
    }

}
