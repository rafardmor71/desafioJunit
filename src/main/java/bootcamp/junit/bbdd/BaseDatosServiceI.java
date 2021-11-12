package bootcamp.junit.bbdd;

import bootcamp.junit.model.Articulo;

public interface BaseDatosServiceI {

	void initBD();

	Articulo findArticuloById(Integer id);

	String insertarArticulo(Articulo articulo);
	
	Integer lastIndex();

}
