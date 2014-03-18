
package co.edu.uniandes.csw.usuario.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _UsuarioDTO {

	private Long id;
	private String name;
	private String direccionEnvio;

	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
	public String getDireccionEnvio() {
		return direccionEnvio;
	}
 
	public void setDireccionEnvio(String direccionenvio) {
		this.direccionEnvio = direccionenvio;
	}
	
}