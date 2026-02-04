import java.util.List;
import java.util.ArrayList;
public class Facturador {
	
	 enum TipoConcierto {
        HEAVY,
        ROCK
    }

    // Conciertos disponibles del grupo
    static String[][] conciertos = {
         {"Tributo Robe", "heavy"},
         {"Homaneje Queen", "pop"},
         {"Magia Knoppler", "rock"},
         {"Demonios Rojos", "heavy"}
    };

    // Actuaciones realizadas indicando el concierto ofrecido y asistentes
    static Integer[][] datosActuaciones = {{0, 2000}, {2, 1200}, {0, 950}, {3, 1140}};
	 
	
    static String cliente = "Ayuntamiento de Badajoz";

    // Constantes para cálculos
    static final double BASE_HEAVY = 4000d;
    static final double BASE_ROCK = 3000d;

    static final int UMBRAL_HEAVY = 500;
    static final int UMBRAL_ROCK = 1000;

    static final double EXTRA_HEAVY = 20d;
    static final double EXTRA_ROCK = 30d;

    static final double IVA = 0.21;
    static final double TOTAL_MULTIPLICADOR = 1.21;

    public static void main(String[] args) throws Exception {
        Double totalFactura = 0d;
        Integer creditos = 0;

        System.out.println("FACTURA DE ACTUACIONES");
        System.out.println("Cliente: " + cliente);
		
		List<Actuacion> listaActuaciones = crearListaActuaciones(datosActuaciones);

        for (Actuacion actuacion : listaActuaciones) {
            Integer indiceConcierto = actuacion.indiceConcierto();
            Integer asistentes = actuacion.asistentes();
			
			 String tipoActuacion = repertorio[IndiceConcierto][1];

			
            totalFactura += calcularImporteActuacion(tipoActuacion, asistentes);
            creditos += calcularCreditos(tipoActuacion, asistentes);

            System.out.println("\tConcierto: " + repertorio[actuacion.getIndiceConcierto()][0]);
            System.out.println("\t\tAsistentes: " + asistentes);
        }

        System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
        System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * IVA);
        System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * TOTAL_MULTIPLICADOR);
        System.out.println("Créditos obtenidos: " + creditos);
    }
	
	public static List<Actuacion> crearListaActuaciones(Integer[][] datosActuaciones){
		List<Actuacion> resultado = new ArrayList<>();
		for( Integer[] datosActuacion : datosActuaciones)
			Integer indiceConcierto = datosActuacion[0];
			Integer asistentes = datosActuacion[1];
			Actuacion actuacion = (indiceConcierto, asistentes);
			actuaciones.add(actuacion);
		}
		return resultado;
	}

    // Calcula el importe de una actuación
    public static Double calcularImporteActuacion(String tipo, int asistentes) throws Exception {
        double importe = 0d;
		TipoConcierto tipoConcierto = TipoConcierto.valueOf(tipo.trim().toUpperCase());
        switch (tipo) {
            case "heavy":
                importe = BASE_HEAVY;
                if (asistentes > UMBRAL_HEAVY)
                    importe += EXTRA_HEAVY * (asistentes - UMBRAL_HEAVY);
                break;
            case "rock":
                importe = BASE_ROCK;
                if (asistentes > UMBRAL_ROCK)
                    importe += EXTRA_ROCK * (asistentes - UMBRAL_ROCK);
                break;
            default:
                throw new Exception("Tipo de concierto desconocido.");
        }

        return importe;
    }

    // Calcula los créditos obtenidos
    public static Integer calcularCreditos(String tipo, int asistentes) {
        int creditos = 0;

        creditos += Math.max(asistentes - UMBRAL_HEAVY, 0);

        if (tipo.equals("heavy")) {
            creditos += asistentes / 5;
        }

        return creditos;
    }
}

 record Actuacion(Integer indiceConcierto, Integer asistentes){}
