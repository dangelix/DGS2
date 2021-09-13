package gob.dgs.dgs.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;

import gob.dgs.dgs.dao.ListaAsistenciaDao;
import gob.dgs.dgs.model.ListaAsistencia;

public class ListaAsistenciaDaoImp implements ListaAsistenciaDao {
	@Override
	public void guardar(ListaAsistencia c) {
		ofy().save().entity(c).now();
	}

	@Override
	public ListaAsistencia cargar(Long id) {
		return ofy().load().type(ListaAsistencia.class).id(id).now();
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
	public List<ListaAsistencia> todos(int page) {
		return ofy().load().type(ListaAsistencia.class).offset((page-1)*25).limit(25).list();
	}

	@Override
	public int pages() {
		int total= ofy().load().type(ListaAsistencia.class).count();
		return ((total-1)/25)+1;
	}

	@Override
	public List<ListaAsistencia> todos() {
		// TODO Auto-generated method stub
		return ofy().load().type(ListaAsistencia.class).list();
	}

}

