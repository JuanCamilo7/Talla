package co.edu.uniandes.csw.sistema.master.logic.ejb;

import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.usuario.persistence.api.IUsuarioPersistence;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.api.IFacturaPersistence;
import co.edu.uniandes.csw.sistema.logic.dto.SistemaDTO;
import co.edu.uniandes.csw.sistema.master.logic.api._ISistemaMasterLogicService;
import co.edu.uniandes.csw.sistema.master.logic.dto.SistemaMasterDTO;
import co.edu.uniandes.csw.sistema.master.persistence.api.ISistemaMasterPersistence;
import co.edu.uniandes.csw.sistema.master.persistence.entity.SistemaUsuarioEntity;
import co.edu.uniandes.csw.sistema.master.persistence.entity.SistemaFacturaEntity;
import co.edu.uniandes.csw.sistema.persistence.api.ISistemaPersistence;
import javax.inject.Inject;

public abstract class _SistemaMasterLogicService implements _ISistemaMasterLogicService {

    @Inject
    protected ISistemaPersistence sistemaPersistance;
    @Inject
    protected ISistemaMasterPersistence sistemaMasterPersistance;
    @Inject
    protected IUsuarioPersistence usuarioPersistance;
    @Inject
    protected IFacturaPersistence facturaPersistance;

    public SistemaMasterDTO createMasterSistema(SistemaMasterDTO sistema) {
        SistemaDTO persistedSistemaDTO = sistemaPersistance.createSistema(sistema.getSistemaEntity());
        if (sistema.getCreateUsuario() != null) {
            for (UsuarioDTO usuarioDTO : sistema.getCreateUsuario()) {
                UsuarioDTO persistedUsuarioDTO = usuarioPersistance.createUsuario(usuarioDTO);
                SistemaUsuarioEntity sistemaUsuarioEntity = new SistemaUsuarioEntity(persistedSistemaDTO.getId(), persistedUsuarioDTO.getId());
                sistemaMasterPersistance.createSistemaUsuario(sistemaUsuarioEntity);
            }
        }
        if (sistema.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : sistema.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                SistemaFacturaEntity sistemaFacturaEntity = new SistemaFacturaEntity(persistedSistemaDTO.getId(), persistedFacturaDTO.getId());
                sistemaMasterPersistance.createSistemaFactura(sistemaFacturaEntity);
            }
        }
        return sistema;
    }

    public SistemaMasterDTO getMasterSistema(Long id) {
        return sistemaMasterPersistance.getSistema(id);
    }

    public void deleteMasterSistema(Long id) {
        sistemaPersistance.deleteSistema(id);
    }

    public void updateMasterSistema(SistemaMasterDTO sistema) {
        sistemaPersistance.updateSistema(sistema.getSistemaEntity());

        //---- FOR RELATIONSHIP
        // persist new usuario
        if (sistema.getCreateUsuario() != null) {
            for (UsuarioDTO usuarioDTO : sistema.getCreateUsuario()) {
                UsuarioDTO persistedUsuarioDTO = usuarioPersistance.createUsuario(usuarioDTO);
                SistemaUsuarioEntity sistemaUsuarioEntity = new SistemaUsuarioEntity(sistema.getSistemaEntity().getId(), persistedUsuarioDTO.getId());
                sistemaMasterPersistance.createSistemaUsuario(sistemaUsuarioEntity);
            }
        }
        // update usuario
        if (sistema.getUpdateUsuario() != null) {
            for (UsuarioDTO usuarioDTO : sistema.getUpdateUsuario()) {
                usuarioPersistance.updateUsuario(usuarioDTO);
            }
        }
        // delete usuario
        if (sistema.getDeleteUsuario() != null) {
            for (UsuarioDTO usuarioDTO : sistema.getDeleteUsuario()) {
                sistemaMasterPersistance.deleteSistemaUsuario(sistema.getSistemaEntity().getId(), usuarioDTO.getId());
                usuarioPersistance.deleteUsuario(usuarioDTO.getId());
            }
        }
        // persist new factura
        if (sistema.getCreateFactura() != null) {
            for (FacturaDTO facturaDTO : sistema.getCreateFactura()) {
                FacturaDTO persistedFacturaDTO = facturaPersistance.createFactura(facturaDTO);
                SistemaFacturaEntity sistemaFacturaEntity = new SistemaFacturaEntity(sistema.getSistemaEntity().getId(), persistedFacturaDTO.getId());
                sistemaMasterPersistance.createSistemaFactura(sistemaFacturaEntity);
            }
        }
        // update factura
        if (sistema.getUpdateFactura() != null) {
            for (FacturaDTO facturaDTO : sistema.getUpdateFactura()) {
                facturaPersistance.updateFactura(facturaDTO);
            }
        }
        // delete factura
        if (sistema.getDeleteFactura() != null) {
            for (FacturaDTO facturaDTO : sistema.getDeleteFactura()) {
                sistemaMasterPersistance.deleteSistemaFactura(sistema.getSistemaEntity().getId(), facturaDTO.getId());
                facturaPersistance.deleteFactura(facturaDTO.getId());
            }
        }
    }
}
