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


import gob.dgs.dgs.dao.RequerimientoDao;
import gob.dgs.dgs.model.ListaAsistencia;
import gob.dgs.dgs.model.Requerimiento;
import gob.dgs.dgs.security.PerfilDAO;
import gob.dgs.dgs.security.UsuarioDAO;
import gob.dgs.dgs.util.AsignadorDeCharset;
import gob.dgs.dgs.util.JsonConvertidor;
import gob.dgs.dgs.util.Util;


@Controller
@RequestMapping(value = { "/requerimientos" })
public class RequerimientoController {
	
	
	@Autowired
	RequerimientoDao reqdao;
	
	@Autowired UsuarioDAO usuariodao;
	
	@Autowired PerfilDAO perfildao;
	
	@RequestMapping(value = {
	"/add" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public void add(HttpServletRequest re, HttpServletResponse rs, @RequestBody String json) throws IOException{
	//	if(Util.verificarPermiso(re, usuariodao, perfildao, 10, 11)){
			System.out.println("siiis"+json);
			Requerimiento c= (Requerimiento) JsonConvertidor.fromJson(json, Requerimiento.class);
			
			c.setEstatus(calculaEstatus(c));
			c.setProgreso(calculaProgreso(c));	
			
			reqdao.guardar(c);
			rs.getWriter().println(JsonConvertidor.toJson(c));
			
//		}else{
//			rs.sendError(403);
//		}
	}
	
//	@RequestMapping(value = {
//	"/altaReq/" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
//	public void add(HttpServletRequest re, HttpServletResponse rs, @RequestBody String json) throws IOException{
//		if(Util.verificarPermiso(re, usuariodao, perfildao, 10, 11)){
//			System.out.println("siiis");
//			Requerimiento c= (Requerimiento) JsonConvertidor.fromJson(json, Requerimiento.class);
//			reqdao.guardar(c);
//			rs.getWriter().println(JsonConvertidor.toJson(c));
//		}else{
//			rs.sendError(403);
//		}
//	}
	
	@RequestMapping(value = {
	"/find/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public void find(HttpServletRequest re, HttpServletResponse rs, @PathVariable String id) throws IOException{
		
		//if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
		Requerimiento c= reqdao.cargar(Long.parseLong(id));
		rs.getWriter().println(JsonConvertidor.toJson(c));
//		}else{
//			rs.sendError(403);
//		}
	}
	
	
	@RequestMapping(value = {
	"/byProyectoPag/{proyecto}/{pag}" }, method = RequestMethod.GET, produces = "application/json")
	public void proyPag(HttpServletRequest re, HttpServletResponse rs, @PathVariable String proyecto, @PathVariable int pag) throws IOException{
		System.out.println("pry:"+proyecto+"   pag:"+pag);
		AsignadorDeCharset.asignar(re, rs);
	//	if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
		List<Requerimiento> lista= reqdao.byProyectoPag(proyecto, pag);
		rs.getWriter().println(JsonConvertidor.toJson(lista));
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
		List<Requerimiento> lista= reqdao.byProyecto(proyecto);
		System.out.println("LISTA:"+lista);
		rs.getWriter().println(JsonConvertidor.toJson(lista));
//		}else{
//			rs.sendError(403);
//		}
	}
	
	@RequestMapping(value = {
	"/byProyectoEtapa/{proyecto}/{etapa}" }, method = RequestMethod.GET, produces = "application/json")
	public void byproyetapa(HttpServletRequest re, HttpServletResponse rs, @PathVariable String proyecto, @PathVariable String etapa) throws IOException{
		System.out.println("proyecto:"+proyecto);System.out.println("etapa:"+etapa);
		AsignadorDeCharset.asignar(re, rs);
	//	if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
		List<Requerimiento> lista= reqdao.byProyectoEtapa(proyecto,etapa);
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
//		List<Requerimiento> lista= reqdao.buscar(search);
//		rs.getWriter().println(JsonConvertidor.toJson(lista));
//		}else{
//			rs.sendError(403);
//		}
//	}
	

	@RequestMapping(value = {
	"/findAll/{page}" }, method = RequestMethod.GET, produces = "application/json")
	public void search(HttpServletRequest re, HttpServletResponse rs, @PathVariable int page) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		if(Util.verificarsesion(re)){
		List<Requerimiento> lista= reqdao.todos(page);
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
		List<Requerimiento> lista= reqdao.todos();
		rs.getWriter().println(JsonConvertidor.toJson(lista));
		}else{
			rs.sendError(403);
		}
	}
	
	@RequestMapping(value = {
	"/pages" }, method = RequestMethod.GET, produces = "application/json")
	public void pages(HttpServletRequest re, HttpServletResponse rs) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		rs.getWriter().print(reqdao.pages());
	}
	
	
	@RequestMapping(value = {"/delete/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public void delete(HttpServletRequest re, HttpServletResponse rs, @PathVariable Long id) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		System.out.println("id"+id);
		//	if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
		Requerimiento c= reqdao.cargar(id);
		reqdao.delete(c);
		rs.getWriter().println(JsonConvertidor.toJson(c));
//		}else{
//			rs.sendError(403);
//		}
	}
		
	public Double calculaProgreso(Requerimiento r) {
		
		int lyp=0;   int listos=0;
		if (r.getAnio17().equals("Listo") || r.getAnio17().equals("En proceso")) {
			lyp++;
			if (r.getAnio17().equals("Listo")) listos++;
		}
		if (r.getAnio18().equals("Listo") || r.getAnio18().equals("En proceso")) {
			lyp++;
			if (r.getAnio18().equals("Listo")) listos++;
		}
		if (r.getAnio19().equals("Listo") || r.getAnio19().equals("En proceso")) {
			lyp++;
			if (r.getAnio19().equals("Listo")) listos++;
		}
		if (r.getAnio20().equals("Listo") || r.getAnio20().equals("En proceso")) {
			lyp++;
			if (r.getAnio20().equals("Listo")) listos++;
		}
		if (r.getAnio21().equals("Listo") || r.getAnio21().equals("En proceso")) {
			lyp++;
			if (r.getAnio21().equals("Listo")) listos++;
		}
		if (r.getAnio22().equals("Listo") || r.getAnio22().equals("En proceso")) {
			lyp++;
			if (r.getAnio22().equals("Listo")) listos++;
		}
		if (r.getAnio23().equals("Listo") || r.getAnio23().equals("En proceso")) {
			lyp++;
			if (r.getAnio23().equals("Listo")) listos++;
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
	
	public String calculaEstatus(Requerimiento r) {
		String old=r.getEstatus();
		String estatus="";
		
//		if (r.getAnio17()=="Listo" && r.getAnio18()=="Listo" && r.getAnio19()=="Listo" && r.getAnio20()=="Listo" &&
//				r.getAnio21()=="Listo" && r.getAnio22()=="Listo" && r.getAnio23()=="Listo" ) {
//			estatus="Listo";
//		}
		if (r.getAnio17().equals("En proceso") || r.getAnio18().equals("En proceso") || r.getAnio19().equals("En proceso") || r.getAnio20().equals("En proceso") ||
				r.getAnio21().equals("En proceso") || r.getAnio22().equals("En proceso") || r.getAnio23().equals("En proceso") ) {
			estatus="En proceso";
		}else {
			estatus="Listo ";
		}
		
		
		return estatus;
		
	}
}
