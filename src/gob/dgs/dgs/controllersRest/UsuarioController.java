package gob.dgs.dgs.controllersRest;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gob.dgs.dgs.model.Perfil;
import gob.dgs.dgs.model.Usuario;
import gob.dgs.dgs.security.PerfilDAO;
import gob.dgs.dgs.security.UsuarioDAO;
import gob.dgs.dgs.util.AsignadorDeCharset;

import gob.dgs.dgs.util.JsonConvertidor;
import gob.dgs.dgs.util.Util;

@Controller
@RequestMapping(value = { "/usuario" })
public class UsuarioController {

	@Autowired
	UsuarioDAO usuariodao;
	
	@Autowired
	PerfilDAO perfildao;

	@RequestMapping(value = { "/registro" }, method = RequestMethod.POST, consumes = "Application/Json")
	public void crearUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody String json)
			throws IOException {
		if(ServicioSesion.verificarPermiso(request, usuariodao, perfildao, 4)){
			AsignadorDeCharset.asignar(request, response);
			Usuario usuario = (Usuario) JsonConvertidor.fromJson(json, Usuario.class);
			usuario.setPass(UsuarioController.otroMetodo(usuario.getPass()));
			if (usuario.getUsername() == null || usuario.getPassword() == null || usuario.getEmail() == null) {
				response.sendError(400);
			} else {
				// System.out.println(usuario.getUsername()+"YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYy");
				if (!usuariodao.crearUsuario(usuario)) {
					response.sendError(400);
				}
			}
		}else{
			response.sendError(403);
		}
	}

	@RequestMapping(value = { "/consultarTodos" }, method = RequestMethod.GET, produces = "application/json")
	public void consultarUsuarios(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(Util.verificarPermiso(request, usuariodao, perfildao, 4,5)){
			AsignadorDeCharset.asignar(request, response);
			List<Usuario> lista = usuariodao.consultarUsuarios();
			response.getWriter().println(JsonConvertidor.toJson(lista));
		}else{
			response.sendError(403);
		}
	}

	@RequestMapping(value = { "/actualiza" }, method = RequestMethod.POST, consumes = "Application/Json")
	public void actualizarUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody String json)
			throws IOException {
		if(Util.verificarPermiso(request, usuariodao, perfildao, 5)){
		AsignadorDeCharset.asignar(request, response);
		Usuario usuario = (Usuario) JsonConvertidor.fromJson(json, Usuario.class);
		usuariodao.actualizarUsuario(usuario);
		}else{
			response.sendError(403);
		}
	}

	@RequestMapping(value = { "/elimina" }, method = RequestMethod.POST, consumes = "Application/Json")
	public void eliminarUsuario(HttpServletRequest request, HttpServletResponse response, @RequestBody String json)
			throws IOException {
		if(Util.verificarPermiso(request, usuariodao, perfildao, 4, 5)){
		AsignadorDeCharset.asignar(request, response);
		Usuario usuario = (Usuario) JsonConvertidor.fromJson(json, Usuario.class);
		usuariodao.eliminarUsuario(usuario);
		}else{
			response.sendError(403);
		}
	}

	@RequestMapping(value = { "/reset" }, method = RequestMethod.POST, consumes = "Application/Json")
	public void resetearPass(HttpServletRequest request, HttpServletResponse response, @RequestBody String email)
			throws IOException {
		
		AsignadorDeCharset.asignar(request, response);
		String correo = (String) JsonConvertidor.fromJson(email, String.class);
		Usuario usuario = usuariodao.consultarPorEmail(correo);
		//System.out.println("Printf de UsuarioController = " + usuario.getUsername());
		String user= usuario.getUsername();
		String mail= usuario.getEmail();
		if(usuario.getUsername()==null){
			response.sendError(400);
		}else{
			//EmailSender sender = new EmailSender();
			String nuevoPass = Util.randomString(10);
			//sender.enviaEmail(mail, user, nuevoPass);
			usuario.setPass(nuevoPass);
			usuariodao.actualizarUsuario(usuario);
			//System.out.println("Si mando el correo :*");
			
		}
	}
	
		static String otroMetodo(String cadena){
		String password = cadena;
		String algorithm = "SHA";
		
		byte[] plainText = password.getBytes();
		
		MessageDigest md = null;
		
		try {		
			md = MessageDigest.getInstance(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		md.reset();		
		md.update(plainText);
		byte[] encodedPassword = md.digest();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				sb.append("0");
			}
			
			sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

//		System.out.println("Plain    : " + password);
//		System.out.println("Encrypted: " + sb.toString());
		return sb.toString();
	}
		
		@RequestMapping(value = {"/check"}, method = RequestMethod.GET)
		public void consultarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException{
			HttpSession s = request.getSession();
			String user = (String)s.getAttribute("userName");
			if(user == null){
				response.sendError(403);
			}
		}
		
		@RequestMapping(value = {"/cerrarSesion"}, method = RequestMethod.GET)
		public void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws IOException{
			HttpSession session = request.getSession(false);
			if (session != null) {
			    session.invalidate();
			}
		}
		
		
		
		@RequestMapping(value = { "/TodoPoderoso" }, method = RequestMethod.GET)
		public void crearUsuarioUnico(HttpServletRequest request, HttpServletResponse response){
			
				Usuario usuario = new Usuario();
				usuario.setEmail("root@root.com");
				usuario.setPass("357ac957f910dec66873e2bb9b2e4fab7dd96389");
				usuario.setPerfil("Administrador");
				usuario.setUsername("root");
				
				usuariodao.crearUsuario(usuario);
				
				Perfil perfil = new Perfil();
				perfil.setTipo("Administrador");
				boolean[] arreglo = new boolean[11];
				for(int i=0; i < arreglo.length; i++){
					arreglo[i] = true;
				}
				
				perfil.setPermisos(arreglo);
				perfildao.crearPerfil(perfil);
						
		}

}
