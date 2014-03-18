package co.edu.uniandes.csw.sistema.master.persistence.entity;

import java.io.Serializable;

/**
 *
 * @author cadmilo
 */
public class SistemaUsuarioEntityId implements Serializable{

    private Long sistemaId;
    private Long usuarioId;

    @Override
    public int hashCode() {
        return (int) (sistemaId + usuarioId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof SistemaUsuarioEntityId) {
            SistemaUsuarioEntityId otherId = (SistemaUsuarioEntityId) object;
            return (otherId.sistemaId == this.sistemaId) && (otherId.usuarioId == this.usuarioId);
        }
        return false;
    }

}
