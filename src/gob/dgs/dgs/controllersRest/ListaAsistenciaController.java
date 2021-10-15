package gob.dgs.dgs.controllersRest;

import java.io.File;
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

import gob.dgs.dgs.dao.ListaAsistenciaDao;
import gob.dgs.dgs.dao.PersonaDAO;
import gob.dgs.dgs.model.ListaAsistencia;
import gob.dgs.dgs.model.Persona;
import gob.dgs.dgs.security.PerfilDAO;
import gob.dgs.dgs.security.UsuarioDAO;
import gob.dgs.dgs.util.AsignadorDeCharset;
import gob.dgs.dgs.util.JsonConvertidor;
import gob.dgs.dgs.util.PdfLista;
import gob.dgs.dgs.util.Util;

@Controller
@RequestMapping(value = { "/listas" })
public class ListaAsistenciaController {
	
	@Autowired
	ListaAsistenciaDao ListaAsistenciasdao;
	
	@Autowired
	PersonaDAO personadao;
	
	@Autowired UsuarioDAO usuariodao;
	
	@Autowired PerfilDAO perfildao;
	
	@Autowired
	PersonaDAO personasdao;
	
	@RequestMapping(value = {"/add" }, method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public void add(HttpServletRequest re, HttpServletResponse rs, @RequestBody String json) throws IOException{
		if(Util.verificarPermiso(re, usuariodao, perfildao, 10, 11)){
			System.out.println("json:"+json);
			ListaAsistencia c= (ListaAsistencia) JsonConvertidor.fromJson(json, ListaAsistencia.class);
			ListaAsistenciasdao.guardar(c);
			rs.getWriter().println(JsonConvertidor.toJson(c));
		}else{
			rs.sendError(403);
		}
	}
	
	@RequestMapping(value = {
	"/find/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public void find(HttpServletRequest re, HttpServletResponse rs, @PathVariable String id) throws IOException{
		
	//	if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
		ListaAsistencia c= ListaAsistenciasdao.cargar(Long.parseLong(id));
		rs.getWriter().println(JsonConvertidor.toJson(c));
//		}else{
//			rs.sendError(403);
//		}
	}
	
	@RequestMapping(value = {
	"/delete/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public void delete(HttpServletRequest re, HttpServletResponse rs, @PathVariable Long id) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		System.out.println("id"+id);
		//	if(Util.verificarPermiso(re, usuariodao, perfildao, 11)){
		ListaAsistencia c= ListaAsistenciasdao.cargar(id);
		ListaAsistenciasdao.delete(c);
		rs.getWriter().println(JsonConvertidor.toJson(c));
//		}else{
//			rs.sendError(403);
//		}
	}
	
	@RequestMapping(value = {
	"/search/{search}" }, method = RequestMethod.GET, produces = "application/json")
	public void search(HttpServletRequest re, HttpServletResponse rs, @PathVariable String search) throws IOException{
		if(Util.verificarsesion(re)){
		List<Persona> lista= personadao.buscar(search);
		rs.getWriter().println(JsonConvertidor.toJson(lista));
		}else{
			rs.sendError(403);
		}
	}
	

	@RequestMapping(value = {
	"/findAll/{page}" }, method = RequestMethod.GET, produces = "application/json")
	public void findAll(HttpServletRequest re, HttpServletResponse rs, @PathVariable int page) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		if(Util.verificarsesion(re)){
		List<ListaAsistencia> lista= ListaAsistenciasdao.todos(page);
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
		List<ListaAsistencia> lista= ListaAsistenciasdao.todos();
		rs.getWriter().println(JsonConvertidor.toJson(lista));
		}else{
			rs.sendError(403);
		}
	}
	
	@RequestMapping(value = {
	"/personas/findAll/{page}" }, method = RequestMethod.GET, produces = "application/json")
	public void personasFindAll(HttpServletRequest re, HttpServletResponse rs, @PathVariable int page) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		if(Util.verificarsesion(re)){
		List<Persona> lista= personasdao.todos(page);
		rs.getWriter().println(JsonConvertidor.toJson(lista));
		}else{
			rs.sendError(403);
		}
	}
	
	@RequestMapping(value = {
	"/personas/findFull" }, method = RequestMethod.GET, produces = "application/json")
	public void personasFindFull(HttpServletRequest re, HttpServletResponse rs) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		if(Util.verificarsesion(re)){
		List<Persona> lista= personasdao.todos();
		rs.getWriter().println(JsonConvertidor.toJson(lista));
		}else{
			rs.sendError(403);
		}
	}

	
	@RequestMapping(value = {
	"/pages" }, method = RequestMethod.GET, produces = "application/json")
	public void pages(HttpServletRequest re, HttpServletResponse rs) throws IOException{
		AsignadorDeCharset.asignar(re, rs);
		rs.getWriter().print(ListaAsistenciasdao.pages());
	}
	
	  @RequestMapping(value = { "/pdf/{idLista}" },  method = RequestMethod.GET, produces = "application/pdf")
		public void generaPdf(HttpServletResponse response, HttpServletRequest request, @PathVariable Long idLista) throws IOException {
		  System.out.println("genera ticket con precio");
//	   if(SesionController.verificarPermiso2(request, usuarioDao, perfilDAO, 20, sessionDao,userName)){
		   response.setContentType("Application/Pdf");
		   
		   ListaAsistencia l= ListaAsistenciasdao.cargar(idLista);
		   System.out.println("genera ticket con precio");
		 // Envio e = envioDao.consult(idEnvio) ; 
		//   List<Detalle> dets= new ArrayList<Detalle>();
		  
				   
	        File newPdfFile = new File(idLista+".pdf");		 
	        if (!newPdfFile.exists()){
	            try {
	            	newPdfFile.createNewFile();
	            } catch (IOException ioe) {
	                System.out.println("(Error al crear el fichero nuevo ......)" + ioe);
	            }
	        }
	        
   
	        System.out.println("Empiezo a generar pdf..."+l.getId() );
	    	PdfLista gt = new PdfLista(l ,  response.getOutputStream());
	 
	    	  response.getOutputStream().flush();
		        response.getOutputStream().close();
	    	
//	   }else{
//			response.sendError(403);
//		}
	}

		
}
