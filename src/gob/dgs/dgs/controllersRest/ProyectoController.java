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

import gob.dgs.dgs.dao.PersonaDAO;
import gob.dgs.dgs.dao.ProyectoDao;
import gob.dgs.dgs.model.ListaAsistencia;
import gob.dgs.dgs.model.Persona;
import gob.dgs.dgs.model.Proyecto;
import gob.dgs.dgs.security.PerfilDAO;
import gob.dgs.dgs.security.UsuarioDAO;
import gob.dgs.dgs.util.AsignadorDeCharset;
import gob.dgs.dgs.util.JsonConvertidor;
import gob.dgs.dgs.util.Util;

@Controller
@RequestMapping(value = { "/proyectos" })
public class ProyectoController {

	@Autowired
	ProyectoDao proyectodao;
	
	@Autowired UsuarioDAO usuariodao;
	
	@Autowired PerfilDAO perfildao;
	
	
	@RequestMapping(value = {
	"/add" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public void add(HttpServletRequest re, HttpServletResponse rs, @RequestBody String json) throws IOException{
		if(Util.verificarPermiso(re, usuariodao, perfildao, 10, 11)){
			System.out.println("siiis");
			Proyecto c= (Proyecto) JsonConvertidor.fromJson(json, Proyecto.class);
			proyectodao.guardar(c);
			rs.getWriter().println(JsonConvertidor.toJson(c));
		}else{
			rs.sendError(403);
		}
	}
	
	@RequestMapping(value = {
	"/find/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public void find(HttpServletRequest re, HttpServletResponse rs, @PathVariable String id) throws IOException{
		
		if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
			Proyecto c= proyectodao.cargar(Long.parseLong(id));
		rs.getWriter().println(JsonConvertidor.toJson(c));
		}else{
			rs.sendError(403);
		}
	}
	
//	@RequestMapping(value = {
//	"/search/{search}" }, method = RequestMethod.GET, produces = "application/json")
//	public void search(HttpServletRequest re, HttpServletResponse rs, @PathVariable String search) throws IOException{
//		if(Util.verificarsesion(re)){
//		List<Proyecto> lista= proyectodao.buscar(search);
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
		List<Proyecto> lista= proyectodao.todos(page);
		rs.getWriter().println(JsonConvertidor.toJson(lista));
		}else{
			rs.sendError(403);
		}
	}
	
	@RequestMapping(value = {
	"/findFull" }, method = RequestMethod.GET, produces = "application/json")
	public void todos(HttpServletRequest re, HttpServletResponse rs) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		System.out.println("ssssi");
		if(Util.verificarsesion(re)){
		List<Proyecto> lista= proyectodao.todos();
		rs.getWriter().println(JsonConvertidor.toJson(lista));
		}else{
			rs.sendError(403);
		}
	}
	
	@RequestMapping(value = {
	"/pages" }, method = RequestMethod.GET, produces = "application/json")
	public void pages(HttpServletRequest re, HttpServletResponse rs) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		rs.getWriter().print(proyectodao.pages());
	}
	
	@RequestMapping(value = {"/delete/" }, method = RequestMethod.POST, produces = "application/json")
	public void delete(HttpServletRequest re, HttpServletResponse rs, @RequestBody String json ) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
	//	System.out.println("id"+id);
		Proyecto c= (Proyecto) JsonConvertidor.fromJson(json, Proyecto.class);
		//	if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
		//Proyecto c=proyectodao.cargar(id);
		proyectodao.delete(c);
		rs.getWriter().println(JsonConvertidor.toJson(c));
//		}else{
//			rs.sendError(403);
//		}
	}
	
		
}
