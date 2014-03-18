package co.edu.uniandes.csw.usuario.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class UsuarioFacturaEntityId implements Serializable{

    private Long usuarioId;
    private Long facturaId;

    @Override
    public int hashCode() {
        return (int) (usuarioId + facturaId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof UsuarioFacturaEntityId) {
            UsuarioFacturaEntityId otherId = (UsuarioFacturaEntityId) object;
            return (otherId.usuarioId == this.usuarioId) && (otherId.facturaId == this.facturaId);
        }
        return false;
    }

}
