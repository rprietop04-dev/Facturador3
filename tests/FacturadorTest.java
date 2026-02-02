import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

class FacturadorTest {
    
	@Test
    @DisplayName("Test constructor válido")
    public void testConstructorValido() {
		Facturador facturador = new Facturador();
		assertNotNull(facturador, "El constructor devuelve null.");
	}
	@Test
	@DisplayName("Test cabecera correcta")
	public void testCabeceraCorrecta() {
		PrintStream salidaConsola = System.out;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream salidaTest = new PrintStream(baos);
		System.setOut(salidaTest);
		
		try{
		
			Facturador.main(null);
		}
		catch(Exception e){
			fail("Excepcion inesperada");
		}
		
		System.setOut(salidaConsola);
		String salida = baos.toString();
		// Verificar cabecera y cliente
        assertTrue(salida.contains("FACTURA DE ACTUACIONES"), "Falta la cabecera de la factura");
        assertTrue(salida.contains("Cliente: Ayuntamiento de Badajoz"), "Falta el cliente");

        // Verificar base imponible, IVA y total
        assertTrue(salida.contains("BASE IMPONIBLE: 72800.0 euros"), "Base imponible incorrecta");
        assertTrue(salida.contains("IVA (21%): 15288,00 euros"), "IVA incorrecto");
        assertTrue(salida.contains("TOTAL FACTURA: 88088,00 euros"), "Total incorrecto");

        // Verificar créditos obtenidos
        assertTrue(salida.contains("Créditos obtenidos: 4108"), "Créditos obtenidos incorrectos");
	
	}
}