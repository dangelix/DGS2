package gob.dgs.dgs.dao;

import java.util.List;


import gob.dgs.dgs.model.Proyecto;

public interface ProyectoDao {
	public void guardar(Proyecto c);

	public Proyecto cargar(Long id);

//	public List<ListaAsistencia> buscar(String search);
	
	public List<Proyecto> todos(int page);
	
	public List<Proyecto> todos();
	
	public int pages();
	
	public void delete(Proyecto c);
}
