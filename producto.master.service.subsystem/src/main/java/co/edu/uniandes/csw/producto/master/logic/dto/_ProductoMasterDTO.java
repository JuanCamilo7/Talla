package co.edu.uniandes.csw.producto.master.logic.dto;

import co.edu.uniandes.csw.promocion.logic.dto.PromocionDTO;
import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _ProductoMasterDTO {

 
    protected ProductoDTO productoEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ProductoDTO getProductoEntity() {
        return productoEntity;
    }

    public void setProductoEntity(ProductoDTO productoEntity) {
        this.productoEntity = productoEntity;
    }
    
    public List<PromocionDTO> createPromocion;
    public List<PromocionDTO> updatePromocion;
    public List<PromocionDTO> deletePromocion;
    public List<PromocionDTO> listPromocion;	
	
	
	
    public List<PromocionDTO> getCreatePromocion(){ return createPromocion; };
    public void setCreatePromocion(List<PromocionDTO> createPromocion){ this.createPromocion=createPromocion; };
    public List<PromocionDTO> getUpdatePromocion(){ return updatePromocion; };
    public void setUpdatePromocion(List<PromocionDTO> updatePromocion){ this.updatePromocion=updatePromocion; };
    public List<PromocionDTO> getDeletePromocion(){ return deletePromocion; };
    public void setDeletePromocion(List<PromocionDTO> deletePromocion){ this.deletePromocion=deletePromocion; };
    public List<PromocionDTO> getListPromocion(){ return listPromocion; };
    public void setListPromocion(List<PromocionDTO> listPromocion){ this.listPromocion=listPromocion; };	
	
	
}

