package gob.dgs.dgs.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import gob.dgs.dgs.dao.ApartadoDao;
import gob.dgs.dgs.model.Apartado;

public class ApartadoDaoImpl implements ApartadoDao{
	@Override
	public void guardar(Apartado c) {
		ofy().save().entity(c).now();
	}

	@Override
	public Apartado cargar(Long id) {
		return ofy().load().type(Apartado.class).id(id).now();
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
	public List<Apartado> todos(int page) {
		return ofy().load().type(Apartado.class).offset((page-1)*25).limit(25).list();
	}

	@Override
	public int pages() {
		int total= ofy().load().type(Apartado.class).count();
		return ((total-1)/25)+1;
	}

	@Override
	public List<Apartado> todos() {
		// TODO Auto-generated method stub
		return ofy().load().type(Apartado.class).list();
	}
	
	@Override
	public void delete(Apartado c) {
		// TODO Auto-generated method stub
		ofy().delete().entity(c).now();
	}

}
