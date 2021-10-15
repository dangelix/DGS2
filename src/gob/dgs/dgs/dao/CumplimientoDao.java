package gob.dgs.dgs.dao;

import java.util.List;

import gob.dgs.dgs.model.Cumplimiento;

public interface CumplimientoDao {
	public void guardar(Cumplimiento c);

	public Cumplimiento cargar(Long id);

//	public List<ListaAsistencia> buscar(String search);
	
	public List<Cumplimiento> todos(int page);
	
	public List<Cumplimiento> todos();
	
	public int pages();
	
	public void delete(Cumplimiento c);

}
