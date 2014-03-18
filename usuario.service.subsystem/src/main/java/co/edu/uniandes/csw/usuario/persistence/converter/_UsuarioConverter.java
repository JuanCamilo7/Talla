
package co.edu.uniandes.csw.usuario.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.usuario.persistence.entity.UsuarioEntity;

public abstract class _UsuarioConverter {


	public static UsuarioDTO entity2PersistenceDTO(UsuarioEntity entity){
		if (entity != null) {
			UsuarioDTO dto = new UsuarioDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setDireccionEnvio(entity.getDireccionEnvio());
			return dto;
		}else{
			return null;
		}
	}
	
	public static UsuarioEntity persistenceDTO2Entity(UsuarioDTO dto){
		if(dto!=null){
			UsuarioEntity entity=new UsuarioEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setDireccionEnvio(dto.getDireccionEnvio());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<UsuarioDTO> entity2PersistenceDTOList(List<UsuarioEntity> entities){
		List<UsuarioDTO> dtos=new ArrayList<UsuarioDTO>();
		for(UsuarioEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<UsuarioEntity> persistenceDTO2EntityList(List<UsuarioDTO> dtos){
		List<UsuarioEntity> entities=new ArrayList<UsuarioEntity>();
		for(UsuarioDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}