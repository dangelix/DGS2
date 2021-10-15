package gob.dgs.dgs.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Revision {
	
	@Id private Long id;
	@Index private Long idProyecto;
	@Index private Long idApartado;
	private Boolean correcciones;
	private Date fechaRevision;
	private Date fechaCorreo;
	private Date fechaProveedor;
	private String Observaciones;
	private String Responsable;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
	public Long getIdApartado() {
		return idApartado;
	}
	public void setIdApartado(Long idApartado) {
		this.idApartado = idApartado;
	}
	public Boolean getCorrecciones() {
		return correcciones;
	}
	public void setCorrecciones(Boolean correcciones) {
		this.correcciones = correcciones;
	}
	public Date getFechaRevision() {
		return fechaRevision;
	}
	public void setFechaRevision(Date fechaRevision) {
		this.fechaRevision = fechaRevision;
	}
	public Date getFechaCorreo() {
		return fechaCorreo;
	}
	public void setFechaCorreo(Date fechaCorreo) {
		this.fechaCorreo = fechaCorreo;
	}
	public Date getFechaProveedor() {
		return fechaProveedor;
	}
	public void setFechaProveedor(Date fechaProveedor) {
		this.fechaProveedor = fechaProveedor;
	}
	public String getObservaciones() {
		return Observaciones;
	}
	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}
	public String getResponsable() {
		return Responsable;
	}
	public void setResponsable(String responsable) {
		Responsable = responsable;
	}
	
	
	
	
}
