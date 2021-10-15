package gob.dgs.dgs.dao;

import java.util.List;

import gob.dgs.dgs.model.Reunion;

public interface ReunionDao {
	
	public void guardar(Reunion c);

	public Reunion cargar(Long id);

//	public List<ListaAsistencia> buscar(String search);
	
	public List<Reunion> todos(int page);
	
	public List<Reunion> todos();
	
	public int pages();
	
	public void delete(Reunion c);

}
