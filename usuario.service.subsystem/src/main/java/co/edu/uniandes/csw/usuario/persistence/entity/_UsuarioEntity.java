
package co.edu.uniandes.csw.usuario.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _UsuarioEntity {

	@Id
	@GeneratedValue(generator = "Usuario")
	private Long id;
	private String name;
	private String direccionEnvio;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getDireccionEnvio(){
		return direccionEnvio;
	}
	
	public void setDireccionEnvio(String direccionEnvio){
		this.direccionEnvio = direccionEnvio;
	}
}