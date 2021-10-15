package gob.dgs.dgs.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Reunion {

	@Id private Long id;
	@Index private Long idProyecto;
	@Index private Date fecha;
	@Index private String Objeto;
	private String infPendiente;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getObjeto() {
		return Objeto;
	}
	public void setObjeto(String objeto) {
		Objeto = objeto;
	}
	public String getInfPendiente() {
		return infPendiente;
	}
	public void setInfPendiente(String infPendiente) {
		this.infPendiente = infPendiente;
	}
	
	
	
	
}
