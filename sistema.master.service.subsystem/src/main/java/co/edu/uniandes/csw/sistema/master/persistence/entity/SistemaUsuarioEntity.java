package co.edu.uniandes.csw.sistema.master.persistence.entity;

import co.edu.uniandes.csw.usuario.persistence.entity.UsuarioEntity;
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
@IdClass(SistemaUsuarioEntityId.class)
@NamedQueries({
    @NamedQuery(name = "SistemaUsuarioEntity.getUsuarioListForSistema", query = "SELECT  u FROM SistemaUsuarioEntity u WHERE u.sistemaId=:sistemaId"),
    @NamedQuery(name = "SistemaUsuarioEntity.deleteSistemaUsuario", query = "DELETE FROM SistemaUsuarioEntity u WHERE u.usuarioId=:usuarioId and  u.sistemaId=:sistemaId")
})
public class SistemaUsuarioEntity implements Serializable {

    @Id
    @Column(name = "sistemaId")
    private Long sistemaId;
    @Id
    @Column(name = "usuarioId")
    private Long usuarioId;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "usuarioId", referencedColumnName = "id")
    @JoinFetch
    private UsuarioEntity usuarioEntity;
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "sistemaId", referencedColumnName = "id")
    @JoinFetch
    private SistemaEntity sistemaEntity;

    public SistemaUsuarioEntity() {
    }

    public SistemaUsuarioEntity(Long sistemaId, Long usuarioId) {
        this.sistemaId = sistemaId;
        this.usuarioId = usuarioId;
    }

    public Long getSistemaId() {
        return sistemaId;
    }

    public void setSistemaId(Long sistemaId) {
        this.sistemaId = sistemaId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public SistemaEntity getSistemaEntity() {
        return sistemaEntity;
    }

    public void setSistemaEntity(SistemaEntity sistemaEntity) {
        this.sistemaEntity = sistemaEntity;
    }

}
