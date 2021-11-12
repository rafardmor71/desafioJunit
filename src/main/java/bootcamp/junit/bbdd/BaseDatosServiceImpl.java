package bootcamp.junit.bbdd;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import bootcamp.junit.model.Articulo;

@Service
public class BaseDatosServiceImpl implements BaseDatosServiceI{

	private Map<Integer, Articulo> storage; 
	
	@Override
	public void initBD() {
		storage = new HashMap<>();
		storage.put(1, new Articulo("Camiseta",18.95));
		storage.put(2, new Articulo("Pantalon",20.95));
		storage.put(3, new Articulo("Sudadera",50.20));
		storage.put(4, new Articulo("Chaqueton",10.99));
		storage.put(5, new Articulo("Jersey",12.99));
		storage.put(6, new Articulo("Chandal" ,15.99));
	}

	@Override
	public Articulo findArticuloById(Integer id) {
		System.out.println("Buscando el articulo con ID: " + id);
		return storage.get(id);
	}

	@Override
	public String insertarArticulo(Articulo articulo) {
		System.out.println("Insertando el articulo con nombre: " + articulo.getNombre());
		storage.put(storage.size() + 1, articulo);
		return null;
	}
	public Integer lastIndex() {
		return storage.size();
	}

}
