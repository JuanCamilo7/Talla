package co.edu.uniandes.csw.sistema.master.logic.dto;

import co.edu.uniandes.csw.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.factura.logic.dto.FacturaDTO;
import co.edu.uniandes.csw.sistema.logic.dto.SistemaDTO;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public abstract class _SistemaMasterDTO {

 
    protected SistemaDTO sistemaEntity;
    protected Long id;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public SistemaDTO getSistemaEntity() {
        return sistemaEntity;
    }

    public void setSistemaEntity(SistemaDTO sistemaEntity) {
        this.sistemaEntity = sistemaEntity;
    }
    
    public List<UsuarioDTO> createUsuario;
    public List<UsuarioDTO> updateUsuario;
    public List<UsuarioDTO> deleteUsuario;
    public List<UsuarioDTO> listUsuario;	
    public List<FacturaDTO> createFactura;
    public List<FacturaDTO> updateFactura;
    public List<FacturaDTO> deleteFactura;
    public List<FacturaDTO> listFactura;	
	
	
	
    public List<UsuarioDTO> getCreateUsuario(){ return createUsuario; };
    public void setCreateUsuario(List<UsuarioDTO> createUsuario){ this.createUsuario=createUsuario; };
    public List<UsuarioDTO> getUpdateUsuario(){ return updateUsuario; };
    public void setUpdateUsuario(List<UsuarioDTO> updateUsuario){ this.updateUsuario=updateUsuario; };
    public List<UsuarioDTO> getDeleteUsuario(){ return deleteUsuario; };
    public void setDeleteUsuario(List<UsuarioDTO> deleteUsuario){ this.deleteUsuario=deleteUsuario; };
    public List<UsuarioDTO> getListUsuario(){ return listUsuario; };
    public void setListUsuario(List<UsuarioDTO> listUsuario){ this.listUsuario=listUsuario; };	
    public List<FacturaDTO> getCreateFactura(){ return createFactura; };
    public void setCreateFactura(List<FacturaDTO> createFactura){ this.createFactura=createFactura; };
    public List<FacturaDTO> getUpdateFactura(){ return updateFactura; };
    public void setUpdateFactura(List<FacturaDTO> updateFactura){ this.updateFactura=updateFactura; };
    public List<FacturaDTO> getDeleteFactura(){ return deleteFactura; };
    public void setDeleteFactura(List<FacturaDTO> deleteFactura){ this.deleteFactura=deleteFactura; };
    public List<FacturaDTO> getListFactura(){ return listFactura; };
    public void setListFactura(List<FacturaDTO> listFactura){ this.listFactura=listFactura; };	
	
	
}

