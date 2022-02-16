package gob.dgs.dgs.dao;

import java.util.List;


import gob.dgs.dgs.model.Requerimiento;

	public interface RequerimientoDao {
	
	public void guardar(Requerimiento c);

	public Requerimiento cargar(Long id);

//	public List<ListaAsistencia> buscar(String search);
	
	public List<Requerimiento> todos(int page);
	
	public List<Requerimiento> byProyectoPag(String proyecto,int page);
	
	public List<Requerimiento> byProyecto(String proyecto);
	
	public List<Requerimiento> byProyectoEtapa(String proyecto, String etapa);
	
	public List<Requerimiento> todos();
	
	public int pages();
	
	public void delete(Requerimiento c);

}
