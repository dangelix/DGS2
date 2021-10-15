package gob.dgs.dgs.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import gob.dgs.dgs.dao.PropuestaDao;
import gob.dgs.dgs.model.Propuesta;

public class PropuestaDaoImpl implements PropuestaDao{

	@Override
	public void guardar(Propuesta c) {
		ofy().save().entity(c).now();
	}

	@Override
	public Propuesta cargar(Long id) {
		return ofy().load().type(Propuesta.class).id(id).now();
	}

//	@Override
//	public List<ListaAsistencia> buscar(String search) {
//		search= search.toLowerCase();
//		List<ListaAsistencia> lista= ofy().load().type(ListaAsistencia.class).list();
//		List<ListaAsistencia> result= new ArrayList<ListaAsistencia>();
//		for(ListaAsistencia c:lista){
//			if(c.getNombre().toLowerCase().contains(search)){
//				result.add(c);
//			}
//		}
//		return result;
//	}

	@Override
	public List<Propuesta> todos(int page) {
		return ofy().load().type(Propuesta.class).offset((page-1)*25).limit(25).list();
	}

	@Override
	public int pages() {
		int total= ofy().load().type(Propuesta.class).count();
		return ((total-1)/25)+1;
	}

	@Override
	public List<Propuesta> todos() {
		// TODO Auto-generated method stub
		return ofy().load().type(Propuesta.class).list();
	}
	
	@Override
	public void delete(Propuesta c) {
		// TODO Auto-generated method stub
		ofy().delete().entity(c).now();
	}

}
