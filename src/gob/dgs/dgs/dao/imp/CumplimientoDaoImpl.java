package gob.dgs.dgs.dao.imp;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import gob.dgs.dgs.dao.CumplimientoDao;
import gob.dgs.dgs.model.Cumplimiento;
import gob.dgs.dgs.model.Proyecto;

public class CumplimientoDaoImpl implements CumplimientoDao{
	@Override
	public void guardar(Cumplimiento c) {
		ofy().save().entity(c).now();
	}

	@Override
	public Cumplimiento cargar(Long id) {
		return ofy().load().type(Cumplimiento.class).id(id).now();
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
	public List<Cumplimiento> todos(int page) {
		return ofy().load().type(Cumplimiento.class).offset((page-1)*25).limit(25).list();
	}

	@Override
	public int pages() {
		int total= ofy().load().type(Cumplimiento.class).count();
		return ((total-1)/25)+1;
	}

	@Override
	public List<Cumplimiento> todos() {
		// TODO Auto-generated method stub
		return ofy().load().type(Cumplimiento.class).list();
	}
	
	@Override
	public void delete(Cumplimiento c) {
		// TODO Auto-generated method stub
		ofy().delete().entity(c).now();
	}

}
