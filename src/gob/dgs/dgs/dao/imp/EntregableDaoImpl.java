package gob.dgs.dgs.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import gob.dgs.dgs.dao.EntregableDao;
import gob.dgs.dgs.model.Entregable;


public class EntregableDaoImpl implements EntregableDao{
	
	@Override
	public void guardar(Entregable c) {
		ofy().save().entity(c).now();
	}

	@Override
	public Entregable cargar(Long id) {
		return ofy().load().type(Entregable.class).id(id).now();
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
	public List<Entregable> todos(int page) {
		return ofy().load().type(Entregable.class).offset((page-1)*25).limit(25).list();
	}

	@Override
	public int pages() {
		int total= ofy().load().type(Entregable.class).count();
		return ((total-1)/25)+1;
	}

	@Override
	public List<Entregable> todos() {
		// TODO Auto-generated method stub
		return ofy().load().type(Entregable.class).list();
	}
	
	@Override
	public List<Entregable> byProyecto(String proyecto) {
		// TODO Auto-generated method stub
		return ofy().load().type(Entregable.class).filter("proyecto", proyecto).list();
	}
	
	@Override
	public List<Entregable> byProyEstatus(String proyecto, String estatus) {
		// TODO Auto-generated method stub
		return ofy().load().type(Entregable.class).filter("proyecto", proyecto).filter("estatus", estatus).list();
	}
	
	@Override
	public void delete(Entregable c) {
		// TODO Auto-generated method stub
		ofy().delete().entity(c).now();
	}

}

