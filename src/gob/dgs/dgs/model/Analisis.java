package gob.dgs.dgs.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Analisis {

	@Id private Long id;
	@Index private Long idPropuesta;
	@Index private String estatus; //viable / no viable
	private String justificacion;
	private Boolean I;
	private Boolean II;
	private Boolean III;
	private Boolean IV;
	private Boolean V;
	private Boolean social;
	private Boolean social2;
	private Boolean art45;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdPropuesta() {
		return idPropuesta;
	}
	public void setIdPropuesta(Long idPropuesta) {
		this.idPropuesta = idPropuesta;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getJustificacion() {
		return justificacion;
	}
	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}
	public Boolean getI() {
		return I;
	}
	public void setI(Boolean i) {
		I = i;
	}
	public Boolean getII() {
		return II;
	}
	public void setII(Boolean iI) {
		II = iI;
	}
	public Boolean getIII() {
		return III;
	}
	public void setIII(Boolean iII) {
		III = iII;
	}
	public Boolean getIV() {
		return IV;
	}
	public void setIV(Boolean iV) {
		IV = iV;
	}
	public Boolean getV() {
		return V;
	}
	public void setV(Boolean v) {
		V = v;
	}
	public Boolean getSocial() {
		return social;
	}
	public void setSocial(Boolean social) {
		this.social = social;
	}
	public Boolean getSocial2() {
		return social2;
	}
	public void setSocial2(Boolean social2) {
		this.social2 = social2;
	}
	public Boolean getArt45() {
		return art45;
	}
	public void setArt45(Boolean art45) {
		this.art45 = art45;
	}

	
	
	
}
