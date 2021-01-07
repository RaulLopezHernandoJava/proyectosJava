package TestUsuarios;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.juegodemesa.logicanegocio.usuario.UsuarioLogica;
import com.juegodemesa.logicanegocio.usuario.UsuarioLogicaImpl;

import hthurow.tomcatjndi.TomcatJNDI;



class UsuariosLogicaTest {

	private static UsuarioLogica logica;

	// BeforeAll - Los metodos que llevan esta inicializacion inicializan una serie
	// de valores antes de que se ejecuten los
	// metodos de test

//	@Test
//	public void Test() {
//		fail("Not yet implemented");
//	}

	@BeforeAll
	static void setUp() {
		TomcatJNDI tomcatJNDI = new TomcatJNDI();
		tomcatJNDI.processContextXml(new File("WebContent/META-INF/context.xml"));
		tomcatJNDI.start();
		logica = UsuarioLogicaImpl.getInstancia();
	}

	@Test
	void validarUsuarioTest() {
		String email = "raullopezhernando@gmail.com";
		String emailEsperado = logica.obtenerPorEmail(email).getEmail();
		assertEquals(emailEsperado, email);
	}
}
