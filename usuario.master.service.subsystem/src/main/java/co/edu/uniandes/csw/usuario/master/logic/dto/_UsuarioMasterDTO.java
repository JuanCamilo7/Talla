package co.edu.uniandes.csw.usuario.master.logic.dto;

import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _UsuarioMasterDTO {

 
    protected UsuarioDTO usuarioEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public UsuarioDTO getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioDTO usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }
    
    public List<FacturaDTO> createFactura;
    public List<FacturaDTO> updateFactura;
    public List<FacturaDTO> deleteFactura;
    public List<FacturaDTO> listFactura;	
	
	
	
    public List<FacturaDTO> getCreateFactura(){ return createFactura; };
    public void setCreateFactura(List<FacturaDTO> createFactura){ this.createFactura=createFactura; };
    public List<FacturaDTO> getUpdateFactura(){ return updateFactura; };
    public void setUpdateFactura(List<FacturaDTO> updateFactura){ this.updateFactura=updateFactura; };
    public List<FacturaDTO> getDeleteFactura(){ return deleteFactura; };
    public void setDeleteFactura(List<FacturaDTO> deleteFactura){ this.deleteFactura=deleteFactura; };
    public List<FacturaDTO> getListFactura(){ return listFactura; };
    public void setListFactura(List<FacturaDTO> listFactura){ this.listFactura=listFactura; };	
	
	
}

