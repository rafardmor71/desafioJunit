package bootcamp.junit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import bootcamp.junit.bbdd.BaseDatosServiceImpl;
import bootcamp.junit.model.Articulo;

@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class CarritoCompraServiceImplTest {

	@Mock
	private BaseDatosServiceImpl bbdService;

	@Mock
	private CarritoCompraServiceImpl carrito;

	@Mock
	private List<Articulo> listArticulosMock;
	
	@Mock
	private List<Articulo> listArticulos;

	//List<Articulo> mockedListArticulos = mock();

	
	@BeforeEach
	void testinitBD() {
		carrito = new CarritoCompraServiceImpl();
		listArticulos = carrito.getArticulos();
	}
	
	
	//Hecho
	@Test
	@Order(value = 1)
	void testAddArticulo() {
		carrito.addArticulo(new Articulo("Nuevo Articulo", 20.01));
		assertFalse(listArticulos.isEmpty());
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
		when(bbdService.findArticuloById(1)).thenReturn(articulo);
		Double res = carrito.aplicarDescuento(1, 120.0);
		
		//verify(mockedList).size();
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
		int id = carrito.addArticuloBdAndList(articulo);
		assertEquals(id, 4);
		//assertFalse(listArticulos.isEmpty());
		//verify(list, atLeast(2)).someMethod("was called at least twice");
	}
	
	
	//Hecho
	@Test
	@Order(value = 8)
	void testLimpiarCesta() {
		carrito.limpiarCesta();
		assertTrue(listArticulos.isEmpty());
	}
	
	
	

}
