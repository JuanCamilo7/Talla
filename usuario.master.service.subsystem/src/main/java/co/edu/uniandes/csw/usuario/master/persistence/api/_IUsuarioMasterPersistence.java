package co.edu.uniandes.csw.usuario.master.persistence.api;

import co.edu.uniandes.csw.usuario.master.persistence.entity.UsuarioFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.usuario.master.logic.dto.UsuarioMasterDTO;
import java.util.List;

public interface _IUsuarioMasterPersistence {

    public UsuarioFacturaEntity createUsuarioFactura(UsuarioFacturaEntity entity);

    public void deleteUsuarioFactura(Long usuarioId, Long facturaId);
    
    public List<FacturaDTO> getFacturaListForUsuario(Long usuarioId);
    
    public UsuarioMasterDTO getUsuario(Long usuarioId);

}
