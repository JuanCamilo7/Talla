
package co.edu.uniandes.csw.carrito.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.carrito.logic.dto.CarritoDTO;
import co.edu.uniandes.csw.carrito.persistence.api._ICarritoPersistence;
import co.edu.uniandes.csw.carrito.persistence.converter.CarritoConverter;
import co.edu.uniandes.csw.carrito.persistence.entity.CarritoEntity;

public abstract class _CarritoPersistence implements _ICarritoPersistence {

	@PersistenceContext(unitName="CarritoPU")
	protected EntityManager entityManager;
	
	public CarritoDTO createCarrito(CarritoDTO carrito) {
		CarritoEntity entity=CarritoConverter.persistenceDTO2Entity(carrito);
		entityManager.persist(entity);
		return CarritoConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<CarritoDTO> getCarritos() {
		Query q = entityManager.createQuery("select u from CarritoEntity u");
		return CarritoConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public CarritoDTO getCarrito(Long id) {
		return CarritoConverter.entity2PersistenceDTO(entityManager.find(CarritoEntity.class, id));
	}

	public void deleteCarrito(Long id) {
		CarritoEntity entity=entityManager.find(CarritoEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateCarrito(CarritoDTO detail) {
		CarritoEntity entity=entityManager.merge(CarritoConverter.persistenceDTO2Entity(detail));
		CarritoConverter.entity2PersistenceDTO(entity);
	}

}