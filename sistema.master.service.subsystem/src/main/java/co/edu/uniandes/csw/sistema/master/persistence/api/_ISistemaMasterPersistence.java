package co.edu.uniandes.csw.sistema.master.persistence.api;

import co.edu.uniandes.csw.sistema.master.persistence.entity.SistemaUsuarioEntity;
import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.sistema.master.persistence.entity.SistemaFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.sistema.master.logic.dto.SistemaMasterDTO;
import java.util.List;

public interface _ISistemaMasterPersistence {

    public SistemaUsuarioEntity createSistemaUsuario(SistemaUsuarioEntity entity);

    public void deleteSistemaUsuario(Long sistemaId, Long usuarioId);
    
    public List<UsuarioDTO> getUsuarioListForSistema(Long sistemaId);
    public SistemaFacturaEntity createSistemaFactura(SistemaFacturaEntity entity);

    public void deleteSistemaFactura(Long sistemaId, Long facturaId);
    
    public List<FacturaDTO> getFacturaListForSistema(Long sistemaId);
    
    public SistemaMasterDTO getSistema(Long sistemaId);

}
