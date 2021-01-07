package TestUsuarios;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.juegodemesa.logicanegocio.juegos.JuegosLogica;
import com.juegodemesa.logicanegocio.juegos.JuegosLogicaImpl;

public class JuegosLogicaTest {

	private static JuegosLogica logica;

	@BeforeAll
	static void setUp() {
		logica = JuegosLogicaImpl.getInstancia();
	}

	@Test
	void obtenerJuegoPorId() {
		Long idPrueba = 1L;
		Long idJuegoEsperado = logica.obtenerPorId(idPrueba).getId();
		assertEquals(idJuegoEsperado, idPrueba);

	}

}
