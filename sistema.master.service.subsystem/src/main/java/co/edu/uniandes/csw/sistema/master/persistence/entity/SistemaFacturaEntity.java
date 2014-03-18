package co.edu.uniandes.csw.sistema.master.persistence.entity;

import co.edu.uniandes.csw.factura.persistence.entity.FacturaEntity;
import co.edu.uniandes.csw.sistema.persistence.entity.SistemaEntity;
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
@IdClass(SistemaFacturaEntityId.class)
@NamedQueries({
    @NamedQuery(name = "SistemaFacturaEntity.getFacturaListForSistema", query = "SELECT  u FROM SistemaFacturaEntity u WHERE u.sistemaId=:sistemaId"),
    @NamedQuery(name = "SistemaFacturaEntity.deleteSistemaFactura", query = "DELETE FROM SistemaFacturaEntity u WHERE u.facturaId=:facturaId and  u.sistemaId=:sistemaId")
})
public class SistemaFacturaEntity implements Serializable {

    @Id
    @Column(name = "sistemaId")
    private Long sistemaId;
    @Id
    @Column(name = "facturaId")
    private Long facturaId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "facturaId", referencedColumnName = "id")
    @JoinFetch
    private FacturaEntity facturaEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "sistemaId", referencedColumnName = "id")
    @JoinFetch
    private SistemaEntity sistemaEntity;

    public SistemaFacturaEntity() {
    }

    public SistemaFacturaEntity(Long sistemaId, Long facturaId) {
        this.sistemaId = sistemaId;
        this.facturaId = facturaId;
    }

    public Long getSistemaId() {
        return sistemaId;
    }

    public void setSistemaId(Long sistemaId) {
        this.sistemaId = sistemaId;
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

    public SistemaEntity getSistemaEntity() {
        return sistemaEntity;
    }

    public void setSistemaEntity(SistemaEntity sistemaEntity) {
        this.sistemaEntity = sistemaEntity;
    }

}
