
package co.edu.uniandes.csw.promocion.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;
import co.edu.uniandes.csw.promocion.persistence.api._IPromocionPersistence;
import co.edu.uniandes.csw.promocion.persistence.converter.PromocionConverter;
import co.edu.uniandes.csw.promocion.persistence.entity.PromocionEntity;

public abstract class _PromocionPersistence implements _IPromocionPersistence {

	@PersistenceContext(unitName="PromocionPU")
	protected EntityManager entityManager;
	
	public PromocionDTO createPromocion(PromocionDTO promocion) {
		PromocionEntity entity=PromocionConverter.persistenceDTO2Entity(promocion);
		entityManager.persist(entity);
		return PromocionConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<PromocionDTO> getPromocions() {
		Query q = entityManager.createQuery("select u from PromocionEntity u");
		return PromocionConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public PromocionDTO getPromocion(Long id) {
		return PromocionConverter.entity2PersistenceDTO(entityManager.find(PromocionEntity.class, id));
	}

	public void deletePromocion(Long id) {
		PromocionEntity entity=entityManager.find(PromocionEntity.class, id);
		entityManager.remove(entity);
	}

	public void updatePromocion(PromocionDTO detail) {
		PromocionEntity entity=entityManager.merge(PromocionConverter.persistenceDTO2Entity(detail));
		PromocionConverter.entity2PersistenceDTO(entity);
	}

}