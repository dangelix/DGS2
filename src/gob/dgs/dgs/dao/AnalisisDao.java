package gob.dgs.dgs.dao;

import java.util.List;

import gob.dgs.dgs.model.Analisis;

public interface AnalisisDao {
	
	public void guardar(Analisis c);

	public Analisis cargar(Long id);

//	public List<ListaAsistencia> buscar(String search);
	
	public List<Analisis> todos(int page);
	
	public List<Analisis> todos();
	
	public int pages();
	
	public void delete(Analisis c);

}
