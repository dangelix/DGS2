package gob.dgs.dgs.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Requerimiento {

	@Id private Long id;
	@Index private Long idProyecto;
	@Index private String titulo;
	private String subtitulo;
	private String anio17;
	private String anio18;
	private String anio19;
	private String anio20;
	private String anio21;
	private String anio22;
	private String anio23;
	private Date fechaActualizacion;
	private String user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	public String getAnio17() {
		return anio17;
	}
	public void setAnio17(String anio17) {
		this.anio17 = anio17;
	}
	public String getAnio18() {
		return anio18;
	}
	public void setAnio18(String anio18) {
		this.anio18 = anio18;
	}
	public String getAnio19() {
		return anio19;
	}
	public void setAnio19(String anio19) {
		this.anio19 = anio19;
	}
	public String getAnio20() {
		return anio20;
	}
	public void setAnio20(String anio20) {
		this.anio20 = anio20;
	}
	public String getAnio21() {
		return anio21;
	}
	public void setAnio21(String anio21) {
		this.anio21 = anio21;
	}
	public String getAnio22() {
		return anio22;
	}
	public void setAnio22(String anio22) {
		this.anio22 = anio22;
	}
	public String getAnio23() {
		return anio23;
	}
	public void setAnio23(String anio23) {
		this.anio23 = anio23;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
	
	
	
	
}
