
package co.edu.uniandes.csw.usuario.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.usuario.persistence.api._IUsuarioPersistence;
import co.edu.uniandes.csw.usuario.persistence.converter.UsuarioConverter;
import co.edu.uniandes.csw.usuario.persistence.entity.UsuarioEntity;

public abstract class _UsuarioPersistence implements _IUsuarioPersistence {

	@PersistenceContext(unitName="UsuarioPU")
	protected EntityManager entityManager;
	
	public UsuarioDTO createUsuario(UsuarioDTO usuario) {
		UsuarioEntity entity=UsuarioConverter.persistenceDTO2Entity(usuario);
		entityManager.persist(entity);
		return UsuarioConverter.entity2PersistenceDTO(entity);
	}

	@SuppressWarnings("unchecked")
	public List<UsuarioDTO> getUsuarios() {
		Query q = entityManager.createQuery("select u from UsuarioEntity u");
		return UsuarioConverter.entity2PersistenceDTOList(q.getResultList());
	}

	public UsuarioDTO getUsuario(Long id) {
		return UsuarioConverter.entity2PersistenceDTO(entityManager.find(UsuarioEntity.class, id));
	}

	public void deleteUsuario(Long id) {
		UsuarioEntity entity=entityManager.find(UsuarioEntity.class, id);
		entityManager.remove(entity);
	}

	public void updateUsuario(UsuarioDTO detail) {
		UsuarioEntity entity=entityManager.merge(UsuarioConverter.persistenceDTO2Entity(detail));
		UsuarioConverter.entity2PersistenceDTO(entity);
	}

}