package co.edu.uniandes.csw.usuario.master.persistence;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.usuario.master.persistence.entity.UsuarioFacturaEntity;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.usuario.master.logic.dto.UsuarioMasterDTO;
import co.edu.uniandes.csw.usuario.master.persistence.api._IUsuarioMasterPersistence;
import co.edu.uniandes.csw.usuario.persistence.api.IUsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _UsuarioMasterPersistence implements _IUsuarioMasterPersistence {

    @PersistenceContext(unitName = "UsuarioMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected IUsuarioPersistence usuarioPersistence;  

    public UsuarioMasterDTO getUsuario(Long usuarioId) {
        UsuarioMasterDTO usuarioMasterDTO = new UsuarioMasterDTO();
        UsuarioDTO usuario = usuarioPersistence.getUsuario(usuarioId);
        usuarioMasterDTO.setUsuarioEntity(usuario);
        usuarioMasterDTO.setListFactura(getFacturaListForUsuario(usuarioId));
        return usuarioMasterDTO;
    }

    public UsuarioFacturaEntity createUsuarioFactura(UsuarioFacturaEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteUsuarioFactura(Long usuarioId, Long facturaId) {
        Query q = entityManager.createNamedQuery("UsuarioFacturaEntity.deleteUsuarioFactura");
        q.setParameter("usuarioId", usuarioId);
        q.setParameter("facturaId", facturaId);
        q.executeUpdate();
    }

    public List<FacturaDTO> getFacturaListForUsuario(Long usuarioId) {
        ArrayList<FacturaDTO> resp = new ArrayList<FacturaDTO>();
        Query q = entityManager.createNamedQuery("UsuarioFacturaEntity.getFacturaListForUsuario");
        q.setParameter("usuarioId", usuarioId);
        List<UsuarioFacturaEntity> qResult =  q.getResultList();
        for (UsuarioFacturaEntity usuarioFacturaEntity : qResult) { 
            if(usuarioFacturaEntity.getFacturaEntity()==null){
                entityManager.refresh(usuarioFacturaEntity);
            }
            resp.add(FacturaConverter.entity2PersistenceDTO(usuarioFacturaEntity.getFacturaEntity()));
        }
        return resp;
    }

}
