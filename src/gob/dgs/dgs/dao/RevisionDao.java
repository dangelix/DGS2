package gob.dgs.dgs.dao;

import java.util.List;


import gob.dgs.dgs.model.Revision;

public interface RevisionDao {
	public void guardar(Revision c);

	public Revision cargar(Long id);

//	public List<ListaAsistencia> buscar(String search);
	
	public List<Revision> todos(int page);
	
	public List<Revision> todos();
	
	public int pages();
	
	public void delete(Revision c);

}
