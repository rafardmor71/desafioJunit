package bootcamp.junit.service;

import java.util.List;

import bootcamp.junit.model.Articulo;

public interface CarritoCompraService {
	void limpiarCesta();
	void addArticulo(Articulo articulo);
	Integer getNumArticulo();
	List<Articulo> getArticulos();
	Double totalPrice();
	Double calculadorDescuento(Double precio, Double porcentajeDescuento);
	Double aplicarDescuento(Integer idArticulo, Double porcentaje);
}
