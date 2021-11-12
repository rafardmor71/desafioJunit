package bootcamp.junit.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import bootcamp.junit.bbdd.BaseDatosServiceI;
import bootcamp.junit.model.Articulo;

@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)

class CarritoCompraServiceImplTest {

	@Mock
	private BaseDatosServiceI bbdService;

	@InjectMocks
	private CarritoCompraServiceImpl carrito;

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
		assertEquals(res, 1.05);
		
	}

	@Test
	@Order(value = 4)
	void testAplicadorDescuento() {
		Articulo articulo = new Articulo("Articulo nuevo", 66.35);
		when(bbdService.findArticuloById(1)).thenReturn(articulo);
		//verify(mockedList).size();
	}

	@Test
	@Order(value = 5)
	void testGetNumArticulo() {
		
	}

	@Test
	@Order(value = 6)
	void testGetArticulos() {
		Articulo articulo = new Articulo("Articulo nuevo", 66.35);
		List<Articulo> listArticuloPrueba= carrito.getArticulos();
		assertTrue(listArticuloPrueba.containsAll(listArticulos));
	}
	
	//Hecho
	@Test
	@Order(value = 7)
	void testLimpiarCesta() {
		carrito.limpiarCesta();
		assertTrue(listArticulos.isEmpty());
	}
	
	
	

}
