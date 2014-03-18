package co.edu.uniandes.csw.usuario.master.persistence.entity;

import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
import co.edu.uniandes.csw.usuario.persistence.entity.UsuarioEntity;
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
@IdClass(UsuarioFacturaEntityId.class)
@NamedQueries({
    @NamedQuery(name = "UsuarioFacturaEntity.getFacturaListForUsuario", query = "SELECT  u FROM UsuarioFacturaEntity u WHERE u.usuarioId=:usuarioId"),
    @NamedQuery(name = "UsuarioFacturaEntity.deleteUsuarioFactura", query = "DELETE FROM UsuarioFacturaEntity u WHERE u.facturaId=:facturaId and  u.usuarioId=:usuarioId")
})
public class UsuarioFacturaEntity implements Serializable {

    @Id
    @Column(name = "usuarioId")
    private Long usuarioId;
    @Id
    @Column(name = "facturaId")
    private Long facturaId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facturaId", referencedColumnName = "id")
    @JoinFetch
    private FacturaEntity facturaEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "usuarioId", referencedColumnName = "id")
    @JoinFetch
    private UsuarioEntity usuarioEntity;

    public UsuarioFacturaEntity() {
    }

    public UsuarioFacturaEntity(Long usuarioId, Long facturaId) {
        this.usuarioId = usuarioId;
        this.facturaId = facturaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Long facturaId) {
        this.facturaId = facturaId;
    }

    public FacturaEntity getFacturaEntity() {
        return facturaEntity;
    }

    public void setFacturaEntity(FacturaEntity facturaEntity) {
        this.facturaEntity = facturaEntity;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

}
