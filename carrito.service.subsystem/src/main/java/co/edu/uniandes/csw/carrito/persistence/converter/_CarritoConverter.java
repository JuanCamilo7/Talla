
package co.edu.uniandes.csw.carrito.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.carrito.logic.dto.CarritoDTO;
import co.edu.uniandes.csw.carrito.persistence.entity.CarritoEntity;

public abstract class _CarritoConverter {


	public static CarritoDTO entity2PersistenceDTO(CarritoEntity entity){
		if (entity != null) {
			CarritoDTO dto = new CarritoDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
			return dto;
		}else{
			return null;
		}
	}
	
	public static CarritoEntity persistenceDTO2Entity(CarritoDTO dto){
		if(dto!=null){
			CarritoEntity entity=new CarritoEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<CarritoDTO> entity2PersistenceDTOList(List<CarritoEntity> entities){
		List<CarritoDTO> dtos=new ArrayList<CarritoDTO>();
		for(CarritoEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<CarritoEntity> persistenceDTO2EntityList(List<CarritoDTO> dtos){
		List<CarritoEntity> entities=new ArrayList<CarritoEntity>();
		for(CarritoDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}