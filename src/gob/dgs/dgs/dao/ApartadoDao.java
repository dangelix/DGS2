package gob.dgs.dgs.dao;

import java.util.List;

import gob.dgs.dgs.model.Apartado;

public interface ApartadoDao {
	public void guardar(Apartado c);

	public Apartado cargar(Long id);

//	public List<ListaAsistencia> buscar(String search);
	
	public List<Apartado> todos(int page);
	
	public List<Apartado> todos();
	
	public int pages();
	
	public void delete(Apartado c);

}
