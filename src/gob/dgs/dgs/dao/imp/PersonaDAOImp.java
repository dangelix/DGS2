package gob.dgs.dgs.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import gob.dgs.dgs.dao.PersonaDAO;
import gob.dgs.dgs.model.Persona;

public class PersonaDAOImp implements PersonaDAO{

	@Override
	public void guardar(Persona c) {
		ofy().save().entity(c).now();
	}

	@Override
	public Persona cargar(Long id) {
		return ofy().load().type(Persona.class).id(id).now();
	}

	@Override
	public List<Persona> buscar(String search) {
		search= search.toLowerCase();
		List<Persona> lista= ofy().load().type(Persona.class).list();
		List<Persona> result= new ArrayList<Persona>();
		for(Persona c:lista){
			if(c.getNombre().toLowerCase().contains(search)){
				result.add(c);
			}
		}
		return result;
	}

	@Override
	public List<Persona> todos(int page) {
		return ofy().load().type(Persona.class).offset((page-1)*25).limit(25).list();
	}

	@Override
	public int pages() {
		int total= ofy().load().type(Persona.class).count();
		return ((total-1)/25)+1;
	}

	@Override
	public List<Persona> todos() {
		// TODO Auto-generated method stub
		return ofy().load().type(Persona.class).list();
	}

}
