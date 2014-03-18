
package co.edu.uniandes.csw.promocion.logic.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public abstract class _PromocionDTO {

	private Long id;
	private String name;
	private Date fechaInicial;
	private Date fechaFinal;
	private Double nuevoPrecio;

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
	public Date getFechaInicial() {
		return fechaInicial;
	}
 
	public void setFechaInicial(Date fechainicial) {
		this.fechaInicial = fechainicial;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
 
	public void setFechaFinal(Date fechafinal) {
		this.fechaFinal = fechafinal;
	}
	public Double getNuevoPrecio() {
		return nuevoPrecio;
	}
 
	public void setNuevoPrecio(Double nuevoprecio) {
		this.nuevoPrecio = nuevoprecio;
	}
	
}