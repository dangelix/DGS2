package gob.dgs.dgs.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import gob.dgs.dgs.dao.ProyectoDao;
import gob.dgs.dgs.model.Proyecto;



public class ProyectoDaoImpl implements ProyectoDao{
	@Override
	public void guardar(Proyecto c) {
		ofy().save().entity(c).now();
	}

	@Override
	public Proyecto cargar(Long id) {
		return ofy().load().type(Proyecto.class).id(id).now();
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
	public List<Proyecto> todos(int page) {
		return ofy().load().type(Proyecto.class).offset((page-1)*25).limit(25).list();
	}

	@Override
	public int pages() {
		int total= ofy().load().type(Proyecto.class).count();
		return ((total-1)/25)+1;
	}

	@Override
	public List<Proyecto> todos() {
		// TODO Auto-generated method stub
		return ofy().load().type(Proyecto.class).list();
	}
	
	@Override
	public void delete(Proyecto c) {
		// TODO Auto-generated method stub
		ofy().delete().entity(c).now();
	}

}
