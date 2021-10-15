package gob.dgs.dgs;

import com.googlecode.objectify.ObjectifyService;



import gob.dgs.dgs.model.Persona;
import gob.dgs.dgs.model.Propuesta;
import gob.dgs.dgs.model.Proyecto;
import gob.dgs.dgs.model.Requerimiento;
import gob.dgs.dgs.model.Reunion;
import gob.dgs.dgs.model.Revision;
import gob.dgs.dgs.model.Usuario;
import gob.dgs.dgs.model.Analisis;
import gob.dgs.dgs.model.Apartado;
import gob.dgs.dgs.model.Cumplimiento;
import gob.dgs.dgs.model.Entregable;
import gob.dgs.dgs.model.ListaAsistencia;
import gob.dgs.dgs.model.Perfil;


public class Register {
	public Register(){
		ObjectifyService.register(Persona.class);

		ObjectifyService.register(Usuario.class);
		ObjectifyService.register(Perfil.class);
		ObjectifyService.register(ListaAsistencia.class);
		ObjectifyService.register(Analisis.class);
		ObjectifyService.register(Apartado.class);
		ObjectifyService.register(Cumplimiento.class);
		ObjectifyService.register(Entregable.class);
		ObjectifyService.register(Propuesta.class);
		ObjectifyService.register(Proyecto.class);
		ObjectifyService.register(Requerimiento.class);
		ObjectifyService.register(Reunion.class);
		ObjectifyService.register(Revision.class);

		
		

	}
}
