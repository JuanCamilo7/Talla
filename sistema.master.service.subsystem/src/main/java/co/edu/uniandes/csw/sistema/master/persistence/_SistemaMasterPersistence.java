package co.edu.uniandes.csw.sistema.master.persistence;
import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.sistema.master.persistence.entity.SistemaUsuarioEntity;
import co.edu.uniandes.csw.usuario.persistence.converter.UsuarioConverter;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.sistema.master.persistence.entity.SistemaFacturaEntity;
import co.edu.uniandes.csw.factura.persistence.converter.FacturaConverter;
import co.edu.uniandes.csw.sistema.logic.dto.SistemaDTO;
import co.edu.uniandes.csw.sistema.master.logic.dto.SistemaMasterDTO;
import co.edu.uniandes.csw.sistema.master.persistence.api._ISistemaMasterPersistence;
import co.edu.uniandes.csw.sistema.persistence.api.ISistemaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class _SistemaMasterPersistence implements _ISistemaMasterPersistence {

    @PersistenceContext(unitName = "SistemaMasterPU")
    protected EntityManager entityManager;
    
    @Inject
    protected ISistemaPersistence sistemaPersistence;  

    public SistemaMasterDTO getSistema(Long sistemaId) {
        SistemaMasterDTO sistemaMasterDTO = new SistemaMasterDTO();
        SistemaDTO sistema = sistemaPersistence.getSistema(sistemaId);
        sistemaMasterDTO.setSistemaEntity(sistema);
        sistemaMasterDTO.setListUsuario(getUsuarioListForSistema(sistemaId));
        sistemaMasterDTO.setListFactura(getFacturaListForSistema(sistemaId));
        return sistemaMasterDTO;
    }

    public SistemaUsuarioEntity createSistemaUsuario(SistemaUsuarioEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteSistemaUsuario(Long sistemaId, Long usuarioId) {
        Query q = entityManager.createNamedQuery("SistemaUsuarioEntity.deleteSistemaUsuario");
        q.setParameter("sistemaId", sistemaId);
        q.setParameter("usuarioId", usuarioId);
        q.executeUpdate();
    }

    public List<UsuarioDTO> getUsuarioListForSistema(Long sistemaId) {
        ArrayList<UsuarioDTO> resp = new ArrayList<UsuarioDTO>();
        Query q = entityManager.createNamedQuery("SistemaUsuarioEntity.getUsuarioListForSistema");
        q.setParameter("sistemaId", sistemaId);
        List<SistemaUsuarioEntity> qResult =  q.getResultList();
        for (SistemaUsuarioEntity sistemaUsuarioEntity : qResult) { 
            if(sistemaUsuarioEntity.getUsuarioEntity()==null){
                entityManager.refresh(sistemaUsuarioEntity);
            }
            resp.add(UsuarioConverter.entity2PersistenceDTO(sistemaUsuarioEntity.getUsuarioEntity()));
        }
        return resp;
    }
    public SistemaFacturaEntity createSistemaFactura(SistemaFacturaEntity entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void deleteSistemaFactura(Long sistemaId, Long facturaId) {
        Query q = entityManager.createNamedQuery("SistemaFacturaEntity.deleteSistemaFactura");
        q.setParameter("sistemaId", sistemaId);
        q.setParameter("facturaId", facturaId);
        q.executeUpdate();
    }

    public List<FacturaDTO> getFacturaListForSistema(Long sistemaId) {
        ArrayList<FacturaDTO> resp = new ArrayList<FacturaDTO>();
        Query q = entityManager.createNamedQuery("SistemaFacturaEntity.getFacturaListForSistema");
        q.setParameter("sistemaId", sistemaId);
        List<SistemaFacturaEntity> qResult =  q.getResultList();
        for (SistemaFacturaEntity sistemaFacturaEntity : qResult) { 
            if(sistemaFacturaEntity.getFacturaEntity()==null){
                entityManager.refresh(sistemaFacturaEntity);
            }
            resp.add(FacturaConverter.entity2PersistenceDTO(sistemaFacturaEntity.getFacturaEntity()));
        }
        return resp;
    }

}
