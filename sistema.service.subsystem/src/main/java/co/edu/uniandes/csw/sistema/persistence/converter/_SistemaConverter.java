
package co.edu.uniandes.csw.sistema.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.sistema.logic.dto.SistemaDTO;
import co.edu.uniandes.csw.sistema.persistence.entity.SistemaEntity;

public abstract class _SistemaConverter {


	public static SistemaDTO entity2PersistenceDTO(SistemaEntity entity){
		if (entity != null) {
			SistemaDTO dto = new SistemaDTO();
				dto.setId(entity.getId());
			return dto;
		}else{
			return null;
		}
	}
	
	public static SistemaEntity persistenceDTO2Entity(SistemaDTO dto){
		if(dto!=null){
			SistemaEntity entity=new SistemaEntity();
			entity.setId(dto.getId());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<SistemaDTO> entity2PersistenceDTOList(List<SistemaEntity> entities){
		List<SistemaDTO> dtos=new ArrayList<SistemaDTO>();
		for(SistemaEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<SistemaEntity> persistenceDTO2EntityList(List<SistemaDTO> dtos){
		List<SistemaEntity> entities=new ArrayList<SistemaEntity>();
		for(SistemaDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}