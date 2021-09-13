package gob.dgs.dgs.model;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class ListaAsistencia {

	@Id private Long id;
	@Index private String proyecto;
	private Date fecha;
	private List<Persona> asistentes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public List<Persona> getAsistentes() {
		return asistentes;
	}
	public void setAsistentes(List<Persona> asistentes) {
		this.asistentes = asistentes;
	}
	
	
}
