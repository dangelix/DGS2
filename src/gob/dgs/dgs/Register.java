package gob.dgs.dgs;

import com.googlecode.objectify.ObjectifyService;



import gob.dgs.dgs.model.Persona;
import gob.dgs.dgs.model.Usuario;
import gob.dgs.dgs.model.Perfil;


public class Register {
	public Register(){
		ObjectifyService.register(Persona.class);

		ObjectifyService.register(Usuario.class);
		ObjectifyService.register(Perfil.class);
		
		

	}
}
