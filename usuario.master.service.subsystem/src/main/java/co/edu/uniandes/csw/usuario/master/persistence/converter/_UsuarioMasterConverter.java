package co.edu.uniandes.csw.usuario.master.persistence.converter;
import co.edu.uniandes.csw.usuario.master.persistence.entity.UsuarioFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.usuario.master.logic.dto.UsuarioMasterDTO;
import co.edu.uniandes.csw.usuario.persistence.converter.UsuarioConverter;
import co.edu.uniandes.csw.usuario.persistence.entity.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _UsuarioMasterConverter {

    public static UsuarioMasterDTO entity2PersistenceDTO(UsuarioEntity usuarioEntity 
    ,List<UsuarioFacturaEntity> usuarioFacturaEntity 
    ) {
        UsuarioDTO usuarioDTO = UsuarioConverter.entity2PersistenceDTO(usuarioEntity);
        ArrayList<FacturaDTO> facturaEntities = new ArrayList<FacturaDTO>(usuarioFacturaEntity.size());
        for (UsuarioFacturaEntity usuarioFactura : usuarioFacturaEntity) {
            facturaEntities.add(FacturaConverter.entity2PersistenceDTO(usuarioFactura.getFacturaEntity()));
        }
        UsuarioMasterDTO respDTO  = new UsuarioMasterDTO();
        respDTO.setUsuarioEntity(usuarioDTO);
        respDTO.setListFactura(facturaEntities);
        return respDTO;
    }

}