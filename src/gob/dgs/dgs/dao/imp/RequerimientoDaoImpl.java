package gob.dgs.dgs.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import gob.dgs.dgs.dao.RequerimientoDao;

import gob.dgs.dgs.model.Requerimiento;

public class RequerimientoDaoImpl implements RequerimientoDao{

	@Override
	public void guardar(Requerimiento c) {
		ofy().save().entity(c).now();
	}

	@Override
	public Requerimiento cargar(Long id) {
		return ofy().load().type(Requerimiento.class).id(id).now();
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
	public List<Requerimiento> todos(int page) {
		return ofy().load().type(Requerimiento.class).offset((page-1)*25).limit(25).list();
	}

	@Override
	public int pages() {
		int total= ofy().load().type(Requerimiento.class).count();
		return ((total-1)/25)+1;
	}

	@Override
	public List<Requerimiento> todos() {
		// TODO Auto-generated method stub
		return ofy().load().type(Requerimiento.class).list();
	}
	
	@Override
	public void delete(Requerimiento c) {
		// TODO Auto-generated method stub
		ofy().delete().entity(c).now();
	}

}

