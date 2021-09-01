package gob.dgs.dgs.dao;

import java.util.List;

import gob.dgs.dgs.model.Persona;

public interface PersonaDAO {
	
	public void guardar(Persona c);
	
	public Persona cargar(Long id);

	public List<Persona> buscar(String search);
	
	public List<Persona> todos(int page);
	
	public List<Persona> todos();
	
	public int pages();
}
