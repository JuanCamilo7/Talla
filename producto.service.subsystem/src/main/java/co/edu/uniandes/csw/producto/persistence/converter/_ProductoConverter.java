
package co.edu.uniandes.csw.producto.persistence.converter;

import java.util.ArrayList;
import java.util.List;

import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.producto.persistence.entity.ProductoEntity;

public abstract class _ProductoConverter {


	public static ProductoDTO entity2PersistenceDTO(ProductoEntity entity){
		if (entity != null) {
			ProductoDTO dto = new ProductoDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setCosto(entity.getCosto());
			return dto;
		}else{
			return null;
		}
	}
	
	public static ProductoEntity persistenceDTO2Entity(ProductoDTO dto){
		if(dto!=null){
			ProductoEntity entity=new ProductoEntity();
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setCosto(dto.getCosto());
			return entity;
		}else {
			return null;
		}
	}
	
	public static List<ProductoDTO> entity2PersistenceDTOList(List<ProductoEntity> entities){
		List<ProductoDTO> dtos=new ArrayList<ProductoDTO>();
		for(ProductoEntity entity:entities){
			dtos.add(entity2PersistenceDTO(entity));
		}
		return dtos;
	}
	
	public static List<ProductoEntity> persistenceDTO2EntityList(List<ProductoDTO> dtos){
		List<ProductoEntity> entities=new ArrayList<ProductoEntity>();
		for(ProductoDTO dto:dtos){
			entities.add(persistenceDTO2Entity(dto));
		}
		return entities;
	}
}