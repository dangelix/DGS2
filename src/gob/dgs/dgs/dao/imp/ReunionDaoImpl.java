package gob.dgs.dgs.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import gob.dgs.dgs.dao.ReunionDao;
import gob.dgs.dgs.model.Proyecto;
import gob.dgs.dgs.model.Reunion;

public class ReunionDaoImpl implements ReunionDao{
	
	@Override
	public void guardar(Reunion c) {
		ofy().save().entity(c).now();
	}

	@Override
	public Reunion cargar(Long id) {
		return ofy().load().type(Reunion.class).id(id).now();
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
	public List<Reunion> todos(int page) {
		return ofy().load().type(Reunion.class).offset((page-1)*25).limit(25).list();
	}

	@Override
	public int pages() {
		int total= ofy().load().type(Reunion.class).count();
		return ((total-1)/25)+1;
	}

	@Override
	public List<Reunion> todos() {
		// TODO Auto-generated method stub
		return ofy().load().type(Reunion.class).list();
	}
	
	@Override
	public void delete(Reunion c) {
		// TODO Auto-generated method stub
		ofy().delete().entity(c).now();
	}

}

