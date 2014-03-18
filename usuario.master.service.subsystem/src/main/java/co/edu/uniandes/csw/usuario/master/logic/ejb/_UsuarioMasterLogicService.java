package co.edu.uniandes.csw.usuario.master.logic.ejb;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.usuario.master.logic.api._IUsuarioMasterLogicService;
import co.edu.uniandes.csw.usuario.master.logic.dto.UsuarioMasterDTO;
import co.edu.uniandes.csw.usuario.master.persistence.api.IUsuarioMasterPersistence;
import co.edu.uniandes.csw.usuario.master.persistence.entity.UsuarioFacturaEntity;
import co.edu.uniandes.csw.usuario.persistence.api.IUsuarioPersistence;
import javax.inject.Inject;

public abstract class _UsuarioMasterLogicService implements _IUsuarioMasterLogicService {

    @Inject
    protected IUsuarioPersistence usuarioPersistance;
    @Inject
    protected IUsuarioMasterPersistence usuarioMasterPersistance;
    @Inject
    protected IFacturaPersistence facturaPersistance;

    public UsuarioMasterDTO createMasterUsuario(UsuarioMasterDTO usuario) {
        UsuarioDTO persistedUsuarioDTO = usuarioPersistance.createUsuario(usuario.getUsuarioEntity());
        if (usuario.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : usuario.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                UsuarioFacturaEntity usuarioFacturaEntity = new UsuarioFacturaEntity(persistedUsuarioDTO.getId(), persistedFacturaDTO.getId());
                usuarioMasterPersistance.createUsuarioFactura(usuarioFacturaEntity);
            }
        }
        return usuario;
    }

    public UsuarioMasterDTO getMasterUsuario(Long id) {
        return usuarioMasterPersistance.getUsuario(id);
    }

    public void deleteMasterUsuario(Long id) {
        usuarioPersistance.deleteUsuario(id);
    }

    public void updateMasterUsuario(UsuarioMasterDTO usuario) {
        usuarioPersistance.updateUsuario(usuario.getUsuarioEntity());

        //---- FOR RELATIONSHIP
        // persist new factura
        if (usuario.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : usuario.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                UsuarioFacturaEntity usuarioFacturaEntity = new UsuarioFacturaEntity(usuario.getUsuarioEntity().getId(), persistedFacturaDTO.getId());
                usuarioMasterPersistance.createUsuarioFactura(usuarioFacturaEntity);
            }
        }
        // update factura
        if (usuario.getUpdateFactura() != null) {
            for (FacturaDTO facturaDTO : usuario.getUpdateFactura()) {
                facturaPersistance.updateFactura(facturaDTO);
            }
        }
        // delete factura
        if (usuario.getDeleteFactura() != null) {
            for (FacturaDTO facturaDTO : usuario.getDeleteFactura()) {
                usuarioMasterPersistance.deleteUsuarioFactura(usuario.getUsuarioEntity().getId(), facturaDTO.getId());
                facturaPersistance.deleteFactura(facturaDTO.getId());
            }
        }
    }
}
