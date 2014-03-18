
package co.edu.uniandes.csw.promocion.persistence.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class _PromocionEntity {

	@Id
	@GeneratedValue(generator = "Promocion")
	private Long id;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date fechaInicial;
	@Temporal(TemporalType.DATE)
	private Date fechaFinal;
	private Double nuevoPrecio;

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
	public Date getFechaInicial(){
		return fechaInicial;
	}
	
	public void setFechaInicial(Date fechaInicial){
		this.fechaInicial = fechaInicial;
	}
	public Date getFechaFinal(){
		return fechaFinal;
	}
	
	public void setFechaFinal(Date fechaFinal){
		this.fechaFinal = fechaFinal;
	}
	public Double getNuevoPrecio(){
		return nuevoPrecio;
	}
	
	public void setNuevoPrecio(Double nuevoPrecio){
		this.nuevoPrecio = nuevoPrecio;
	}
}