package co.edu.uniandes.csw.carrito.master.logic.dto;

import co.edu.uniandes.csw.producto.logic.dto.ProductoDTO;
import co.edu.uniandes.csw.carrito.logic.dto.CarritoDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _CarritoMasterDTO {

 
    protected CarritoDTO carritoEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public CarritoDTO getCarritoEntity() {
        return carritoEntity;
    }

    public void setCarritoEntity(CarritoDTO carritoEntity) {
        this.carritoEntity = carritoEntity;
    }
    
    public List<ProductoDTO> createProducto;
    public List<ProductoDTO> updateProducto;
    public List<ProductoDTO> deleteProducto;
    public List<ProductoDTO> listProducto;	
	
	
	
    public List<ProductoDTO> getCreateProducto(){ return createProducto; };
    public void setCreateProducto(List<ProductoDTO> createProducto){ this.createProducto=createProducto; };
    public List<ProductoDTO> getUpdateProducto(){ return updateProducto; };
    public void setUpdateProducto(List<ProductoDTO> updateProducto){ this.updateProducto=updateProducto; };
    public List<ProductoDTO> getDeleteProducto(){ return deleteProducto; };
    public void setDeleteProducto(List<ProductoDTO> deleteProducto){ this.deleteProducto=deleteProducto; };
    public List<ProductoDTO> getListProducto(){ return listProducto; };
    public void setListProducto(List<ProductoDTO> listProducto){ this.listProducto=listProducto; };	
	
	
}

