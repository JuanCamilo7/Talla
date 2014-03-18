
package co.edu.uniandes.csw.sistema.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _SistemaEntity {

	@Id
	@GeneratedValue(generator = "Sistema")
	private Long id;

	public Long getId(){
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
}