package gob.dgs.dgs.dao;

import java.util.List;

import gob.dgs.dgs.model.Entregable;

public interface EntregableDao {
	
	public void guardar(Entregable c);

	public Entregable cargar(Long id);

//	public List<ListaAsistencia> buscar(String search);
	
	public List<Entregable> todos(int page);
	
	public List<Entregable> todos();
	
	public int pages();
	
	public void delete(Entregable c);

}
