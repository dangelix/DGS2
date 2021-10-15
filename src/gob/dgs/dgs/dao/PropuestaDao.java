package gob.dgs.dgs.dao;

import java.util.List;

import gob.dgs.dgs.model.Propuesta;

public interface PropuestaDao {
	public void guardar(Propuesta c);

	public Propuesta cargar(Long id);

//	public List<ListaAsistencia> buscar(String search);
	
	public List<Propuesta> todos(int page);
	
	public List<Propuesta> todos();
	
	public int pages();
	
	public void delete(Propuesta c);

}
