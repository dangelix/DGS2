package gob.dgs.dgs.model;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Apartado {
	@Id private Long id;
	@Index private Long idProyecto;
	private String nombre;
	private Date fechaInicial;
	private Date fechaFinal;
	private String obs;
	@Index private String estatus;
	private int diasTranscurridos;
	private int diasEmpleados;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaInicial() {
		return fechaInicial;
	}
	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public int getDiasTranscurridos() {
		return diasTranscurridos;
	}
	public void setDiasTranscurridos(int diasTranscurridos) {
		this.diasTranscurridos = diasTranscurridos;
	}
	public int getDiasEmpleados() {
		return diasEmpleados;
	}
	public void setDiasEmpleados(int diasEmpleados) {
		this.diasEmpleados = diasEmpleados;
	}
	
	
	
}
