package gob.dgs.dgs.dao;

import java.util.List;

import gob.dgs.dgs.model.AlertaInventario;

public interface AlertaDAO {

	void add(AlertaInventario a);
	
	List<AlertaInventario> consultar();
	
	void delete(AlertaInventario a);
	
	AlertaInventario consultar(Long id);
}
