package bootcamp.junit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bootcamp.junit.bbdd.BaseDatosServiceI;
import bootcamp.junit.model.Articulo;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraService {
	@Autowired
	private BaseDatosServiceI bdService;

	private List<Articulo> listaArticulos = new ArrayList<>();

	@Override
	public void limpiarCesta() {
		listaArticulos.clear();
	}

	@Override
	public void addArticulo(Articulo articulo) {
		listaArticulos.add(articulo);
	}

	@Override
	public Integer getNumArticulo() {
		return listaArticulos.size();
	}

	@Override
	public List<Articulo> getArticulos() {
		return listaArticulos;
	}

	@Override
	public Double totalPrice() {
		Double total = 0.0;
		for (Articulo articulo : listaArticulos) {
			total = total + articulo.getPrecio();
		}
		return total;
	}

	@Override
	public Double calculadorDescuento(Double precio, Double porcentajeDescuento) {
		return precio = precio * porcentajeDescuento / 100;
	}

	@Override
	public Double aplicarDescuento(Integer idArticulo, Double porcentaje) {
		Double resultado = null;
		Articulo articulo = bdService.findArticuloById(idArticulo);
		if (articulo != null) {
			resultado = calculadorDescuento(articulo.getPrecio(), porcentaje);
		} else {
			System.out.println("No e ha encontrado ningun articulo con ID: " + idArticulo);
		}

		return resultado;
	}

}
