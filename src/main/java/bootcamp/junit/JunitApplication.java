package bootcamp.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import bootcamp.junit.bbdd.BaseDatosServiceI;
import bootcamp.junit.model.Articulo;

@SpringBootApplication
public class JunitApplication implements CommandLineRunner{
	
	@Autowired
	BaseDatosServiceI bdService ;
	
	public static void main(String[] args) {
		SpringApplication.run(JunitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Articulo articulo1 = new Articulo("Cinturon", 20.0);
		
		bdService.initBD();
		bdService.findArticuloById(2);
		bdService.insertarArticulo(articulo1);
		Articulo articulomostrar = bdService.findArticuloById(bdService.lastIndex());
		System.out.println(articulomostrar.toString());
	}

}
