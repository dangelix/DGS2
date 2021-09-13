package gob.dgs.dgs.dao;

import java.util.List;

import gob.dgs.dgs.model.ListaAsistencia;

public interface ListaAsistenciaDao {
public void guardar(ListaAsistencia c);
	
	public ListaAsistencia cargar(Long id);

//	public List<ListaAsistencia> buscar(String search);
	
	public List<ListaAsistencia> todos(int page);
	
	public List<ListaAsistencia> todos();
	
	public int pages();

}
