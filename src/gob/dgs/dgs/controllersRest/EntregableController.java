package gob.dgs.dgs.controllersRest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gob.dgs.dgs.dao.EntregableDao;

import gob.dgs.dgs.model.Entregable;
import gob.dgs.dgs.model.Requerimiento;
import gob.dgs.dgs.security.PerfilDAO;
import gob.dgs.dgs.security.UsuarioDAO;
import gob.dgs.dgs.util.AsignadorDeCharset;
import gob.dgs.dgs.util.JsonConvertidor;
import gob.dgs.dgs.util.Util;

@Controller
@RequestMapping(value = { "/entregables" })
public class EntregableController {

	@Autowired
	EntregableDao entregabledao;
	
	@Autowired UsuarioDAO usuariodao;
	
	@Autowired PerfilDAO perfildao;
	
	@RequestMapping(value = {
	"/add" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public void add(HttpServletRequest re, HttpServletResponse rs, @RequestBody String json) throws IOException{
		if(Util.verificarPermiso(re, usuariodao, perfildao, 10, 11)){
			System.out.println("siiis:"+json);
			Entregable c= (Entregable) JsonConvertidor.fromJson(json, Entregable.class);
			c.setEstatus(calculaEstatus(c));
			c.setProgreso(calculaProgreso(c));	
			entregabledao.guardar(c);
			rs.getWriter().println(JsonConvertidor.toJson(c));
		}else{
			rs.sendError(403);
		}
	}
	
	@RequestMapping(value = {
	"/find/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public void find(HttpServletRequest re, HttpServletResponse rs, @PathVariable String id) throws IOException{
		
//		if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
		Entregable c= entregabledao.cargar(Long.parseLong(id));
		rs.getWriter().println(JsonConvertidor.toJson(c));
//		}else{
//			rs.sendError(403);
//		}
	}
	
	@RequestMapping(value = {
	"/byProyecto/{proyecto}" }, method = RequestMethod.GET, produces = "application/json")
	public void byproy(HttpServletRequest re, HttpServletResponse rs, @PathVariable String proyecto) throws IOException{
		System.out.println("proyecto:"+proyecto);
		AsignadorDeCharset.asignar(re, rs);
	//	if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
		List<Entregable> lista= entregabledao.byProyecto(proyecto);
		System.out.println("LISTA:"+lista);
		rs.getWriter().println(JsonConvertidor.toJson(lista));
//		}else{
//			rs.sendError(403);
//		}
	}
	
	@RequestMapping(value = {
	"/byProyEstatus/{proyecto}/{estatus}" }, method = RequestMethod.GET, produces = "application/json")
	public void byproy(HttpServletRequest re, HttpServletResponse rs, @PathVariable String proyecto, @PathVariable String estatus) throws IOException{
		System.out.println("proyecto:"+proyecto+"  estatus:"+estatus);
		AsignadorDeCharset.asignar(re, rs);
	//	if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
		List<Entregable> lista= entregabledao.byProyEstatus(proyecto, estatus);
		System.out.println("LISTA:"+lista);
		rs.getWriter().println(JsonConvertidor.toJson(lista));
//		}else{
//			rs.sendError(403);
//		}
	}
	
//	@RequestMapping(value = {
//	"/search/{search}" }, method = RequestMethod.GET, produces = "application/json")
//	public void search(HttpServletRequest re, HttpServletResponse rs, @PathVariable String search) throws IOException{
//		if(Util.verificarsesion(re)){
//		List<Entregable> lista= entregabledao.buscar(search);
//		rs.getWriter().println(JsonConvertidor.toJson(lista));
//		}else{
//			rs.sendError(403);
//		}
//	}
//	

	@RequestMapping(value = {
	"/findAll/{page}" }, method = RequestMethod.GET, produces = "application/json")
	public void search(HttpServletRequest re, HttpServletResponse rs, @PathVariable int page) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		if(Util.verificarsesion(re)){
		List<Entregable> lista= entregabledao.todos(page);
		rs.getWriter().println(JsonConvertidor.toJson(lista));
		}else{
			rs.sendError(403);
		}
	}
	
	@RequestMapping(value = {
	"/findFull" }, method = RequestMethod.GET, produces = "application/json")
	public void todos(HttpServletRequest re, HttpServletResponse rs) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		if(Util.verificarsesion(re)){
		List<Entregable> lista= entregabledao.todos();
		rs.getWriter().println(JsonConvertidor.toJson(lista));
		}else{
			rs.sendError(403);
		}
	}
	
	@RequestMapping(value = {
	"/pages" }, method = RequestMethod.GET, produces = "application/json")
	public void pages(HttpServletRequest re, HttpServletResponse rs) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		rs.getWriter().print(entregabledao.pages());
	}
	
	@RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public void delete(HttpServletRequest re, HttpServletResponse rs, @PathVariable Long id) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		System.out.println("id"+id);
		//	if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
		Entregable c= entregabledao.cargar(id);
		entregabledao.delete(c);
		rs.getWriter().println(JsonConvertidor.toJson(c));
//		}else{
//			rs.sendError(403);
//		}
	}
	
public Double calculaProgreso(Entregable r) {
		
		int lyp=0;   int listos=0;
		
		if (r.getActividades().equals("Listo") || r.getActividades().equals("En proceso")) {
			lyp++;
			if (r.getActividades().equals("Listo")) listos++;
		}
		if (r.getAvances().equals("Listo") || r.getAvances().equals("En proceso")) {
			lyp++;
			if (r.getAvances().equals("Listo")) listos++;
		}
		if (r.getConstancias().equals("Listo") || r.getConstancias().equals("En proceso")) {
			lyp++;
			if (r.getConstancias().equals("Listo")) listos++;
		}
		
		double progreso=0.0;
		System.out.println("listos="+listos);
		System.out.println("lyp="+lyp);
		if(lyp>0) {
			progreso =(listos*100)/lyp;
			System.out.println("progreso..."+progreso);
		}
			
		return progreso;
		
	}
	
	public String calculaEstatus(Entregable r) {
		//String old=r.getEstatus();
		String estatus="";
		

		if (r.getActividades().equals("En proceso") || r.getAvances().equals("En proceso") || r.getConstancias().equals("En proceso") ) {
			estatus="En proceso";
		}else {
			estatus="Listo ";
		}
		
		
		return estatus;
		
	}
		
}
