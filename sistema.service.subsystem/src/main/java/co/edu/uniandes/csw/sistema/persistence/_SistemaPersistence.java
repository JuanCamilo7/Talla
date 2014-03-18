
package co.edu.uniandes.csw.sistema.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.sistema.logic.dto.SistemaDTO;
import co.edu.uniandes.csw.sistema.persistence.api._ISistemaPersistence;
import co.edu.uniandes.csw.sistema.persistence.converter.SistemaConverter;
import co.edu.uniandes.csw.sistema.persistence.entity.SistemaEntity;

public abstract class _SistemaPersistence implements _ISistemaPersistence {

	@PersistenceContext(unitName="SistemaPU")
	protected EntityManager entityManager;
	
	public SistemaDTO createSistema(SistemaDTO sistema) {
		SistemaEntity entity=SistemaConverter.persistenceDTO2Entity(sistema);
		entityManager.persist(entity);
		return SistemaConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<SistemaDTO> getSistemas() {
		Query q = entityManager.createQuery("select u from SistemaEntity u");
		return SistemaConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public SistemaDTO getSistema(Long id) {
		return SistemaConverter.entity2PersistenceDTO(entityManager.find(SistemaEntity.class, id));
	}

	public void deleteSistema(Long id) {
		SistemaEntity entity=entityManager.find(SistemaEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateSistema(SistemaDTO detail) {
		SistemaEntity entity=entityManager.merge(SistemaConverter.persistenceDTO2Entity(detail));
		SistemaConverter.entity2PersistenceDTO(entity);
	}

}