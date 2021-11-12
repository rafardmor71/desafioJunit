package bootcamp.junit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import bootcamp.junit.bbdd.BaseDatosServiceImpl;
import bootcamp.junit.model.Articulo;


@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceImplTest {

	@Mock
	private BaseDatosServiceImpl bbdService;

	@InjectMocks
	private CarritoCompraServiceImpl carrito = new CarritoCompraServiceImpl();


	private List<Articulo> listArticulos;

	//List<Articulo> mockedListArticulos = mock();

	
	@BeforeEach
	void testinitBD() {
		//carrito = new CarritoCompraServiceImpl();
		listArticulos = carrito.getArticulos();
	}
	

	//Hecho
	@Test
	@Order(value = 1)
	void testAddArticulo() {
		Articulo articulo1 = new Articulo("Nuevo Articulo", 20.01);
		carrito.addArticulo(articulo1);
		assertTrue(carrito.getArticulos().contains(articulo1));
	}

	//Hecho
	@Test
	@Order(value = 2)
	void testTotalPrice() {
		carrito.addArticulo(new Articulo("Nuevo Articulo", 20.01));
		carrito.addArticulo(new Articulo("Nuevo Articulo", 20.01));
		carrito.addArticulo(new Articulo("Nuevo Articulo", 20.01));
		Double res = carrito.totalPrice();
		assertEquals(res, 60.03);
	}

	//Hecho
	@Test
	@Order(value = 3)
	void testCalculadorDescuento() {
		Articulo articulo = new Articulo ("Pantalon", 10.50);
		Double res = carrito.calculadorDescuento(articulo.getPrecio(), 10.00);
		assertEquals(1.05, res);
		
	}

	@Test
	@Order(value = 4)
	void testAplicadorDescuento() {
		Articulo articulo = new Articulo("Articulo nuevo", 66.35);
		when(bbdService.findArticuloById(any(Integer.class))).thenReturn(articulo);
		Double res = carrito.aplicarDescuento(1, 10.0);
		assertEquals(6.635, res);
	}

	@Test
	@Order(value = 5)
	void testGetNumArticulo() {
		Integer res = carrito.getNumArticulo();
		assertEquals(res, listArticulos.size());
	}

	@Test
	@Order(value = 6)
	void testGetArticulos() {
		Articulo articulo = new Articulo("Articulo nuevo", 66.35);
		List<Articulo> listArticuloPrueba= carrito.getArticulos();
		assertTrue(listArticuloPrueba.containsAll(listArticulos));
	}
	@Test
	@Order(value = 7)
	void testAddArticuloBdAndList() {
		Articulo articulo = new Articulo("", 50.00);
		int id = 0;
		id = carrito.addArticuloBdAndList(articulo);
		assertEquals(id, 0);
		
		assertTrue(carrito.getArticulos().contains(articulo));
		//assertFalse(listArticulos.isEmpty());
		//verify();
	}
	
	
	//Hecho
	@Test
	@Order(value = 8)
	void testLimpiarCesta() {
		carrito.limpiarCesta();
		assertTrue(listArticulos.isEmpty());
	}
	
	
	

}
