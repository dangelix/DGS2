//package gob.dgs.dgs.controllersRest;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import gob.dgs.dgs.dao.AlertaDAO;
//
//import gob.dgs.dgs.security.PerfilDAO;
//import gob.dgs.dgs.security.UsuarioDAO;
//import gob.dgs.dgs.util.AsignadorDeCharset;
//import gob.dgs.dgs.util.JsonConvertidor;
//import gob.dgs.dgs.util.Util;
//
//@Controller
//@RequestMapping(value={"/alertas"})
//public class AlertaController {
////	@Autowired
////	AlertaDAO alertadao;
////	
//
//	@Autowired
//	UsuarioDAO usuariodao;
//	
//	@Autowired
//	PerfilDAO perfildao;
//
////	@RequestMapping(value = {
////	"/numAlertas" }, method = RequestMethod.GET, produces = "application/json")
////	public void numAlertas(HttpServletRequest re, HttpServletResponse rs) throws IOException{
////		if(Util.verificarsesion(re)){
////		AsignadorDeCharset.asignar(re, rs);
////		List<AlertaInventario> lista= alertadao.consultar();
////		rs.getWriter().print(lista.size());
////		}else{
////			rs.sendError(403);
////		}
////	}
//	
////	@RequestMapping(value = {
////	"/get" }, method = RequestMethod.GET, produces = "application/json")
////	public void search(HttpServletRequest re, HttpServletResponse rs) throws IOException{
////		if(Util.verificarPermiso(re, usuariodao, perfildao, 0,2))
////		AsignadorDeCharset.asignar(re, rs);
////		List<AlertaInventario> lista= alertadao.consultar();
////		rs.getWriter().println(JsonConvertidor.toJson(lista));
////	}
//	
//	
//}
