package co.edu.uniandes.csw.sistema.master.persistence.converter;
import co.edu.uniandes.csw.sistema.master.persistence.entity.SistemaUsuarioEntity;
import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.usuario.persistence.converter.UsuarioConverter;
import co.edu.uniandes.csw.sistema.master.persistence.entity.SistemaFacturaEntity;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.sistema.logic.dto.SistemaDTO;
import co.edu.uniandes.csw.sistema.master.logic.dto.SistemaMasterDTO;
import co.edu.uniandes.csw.sistema.persistence.converter.SistemaConverter;
import co.edu.uniandes.csw.sistema.persistence.entity.SistemaEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class _SistemaMasterConverter {

    public static SistemaMasterDTO entity2PersistenceDTO(SistemaEntity sistemaEntity 
    ,List<SistemaUsuarioEntity> sistemaUsuarioEntity 
    ,List<SistemaFacturaEntity> sistemaFacturaEntity 
    ) {
        SistemaDTO sistemaDTO = SistemaConverter.entity2PersistenceDTO(sistemaEntity);
        ArrayList<UsuarioDTO> usuarioEntities = new ArrayList<UsuarioDTO>(sistemaUsuarioEntity.size());
        for (SistemaUsuarioEntity sistemaUsuario : sistemaUsuarioEntity) {
            usuarioEntities.add(UsuarioConverter.entity2PersistenceDTO(sistemaUsuario.getUsuarioEntity()));
        }
        ArrayList<FacturaDTO> facturaEntities = new ArrayList<FacturaDTO>(sistemaFacturaEntity.size());
        for (SistemaFacturaEntity sistemaFactura : sistemaFacturaEntity) {
            facturaEntities.add(FacturaConverter.entity2PersistenceDTO(sistemaFactura.getFacturaEntity()));
        }
        SistemaMasterDTO respDTO  = new SistemaMasterDTO();
        respDTO.setSistemaEntity(sistemaDTO);
        respDTO.setListUsuario(usuarioEntities);
        respDTO.setListFactura(facturaEntities);
        return respDTO;
    }

}