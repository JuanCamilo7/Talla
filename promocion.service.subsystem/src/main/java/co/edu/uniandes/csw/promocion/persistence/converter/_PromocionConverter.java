
package co.edu.uniandes.csw.promocion.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;
import co.edu.uniandes.csw.promocion.persistence.entity.PromocionEntity;

public abstract class _PromocionConverter {


	public static PromocionDTO entity2PersistenceDTO(PromocionEntity entity){
		if (entity != null) {
			PromocionDTO dto = new PromocionDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setFechaInicial(entity.getFechaInicial());
				dto.setFechaFinal(entity.getFechaFinal());
				dto.setNuevoPrecio(entity.getNuevoPrecio());
			return dto;
		}else{
			return null;
		}
	}
	
	public static PromocionEntity persistenceDTO2Entity(PromocionDTO dto){
		if(dto!=null){
			PromocionEntity entity=new PromocionEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setFechaInicial(dto.getFechaInicial());
			entity.setFechaFinal(dto.getFechaFinal());
			entity.setNuevoPrecio(dto.getNuevoPrecio());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<PromocionDTO> entity2PersistenceDTOList(List<PromocionEntity> entities){
		List<PromocionDTO> dtos=new ArrayList<PromocionDTO>();
		for(PromocionEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<PromocionEntity> persistenceDTO2EntityList(List<PromocionDTO> dtos){
		List<PromocionEntity> entities=new ArrayList<PromocionEntity>();
		for(PromocionDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}