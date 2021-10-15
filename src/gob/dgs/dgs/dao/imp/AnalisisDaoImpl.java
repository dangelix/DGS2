package gob.dgs.dgs.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import gob.dgs.dgs.dao.AnalisisDao;
import gob.dgs.dgs.model.Analisis;


public class AnalisisDaoImpl implements AnalisisDao{
	
	
	@Override
	public void guardar(Analisis c) {
		ofy().save().entity(c).now();
	}

	@Override
	public Analisis cargar(Long id) {
		return ofy().load().type(Analisis.class).id(id).now();
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
	public List<Analisis> todos(int page) {
		return ofy().load().type(Analisis.class).offset((page-1)*25).limit(25).list();
	}

	@Override
	public int pages() {
		int total= ofy().load().type(Analisis.class).count();
		return ((total-1)/25)+1;
	}

	@Override
	public List<Analisis> todos() {
		// TODO Auto-generated method stub
		return ofy().load().type(Analisis.class).list();
	}
	
	@Override
	public void delete(Analisis c) {
		// TODO Auto-generated method stub
		ofy().delete().entity(c).now();
	}

}
