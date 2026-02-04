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
    static Integer[][] actuacionesRealizadas = {
        {0, 2000}, {2, 1200}, {0, 950}, {3, 1140}
    };

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
        double totalFactura = 0d;
        int creditos = 0;

        System.out.println("FACTURA DE ACTUACIONES");
        System.out.println("Cliente: " + cliente);

        for (int i = 0; i < actuacionesRealizadas.length; i++) {
            int indiceConcierto = actuacionesRealizadas[i][0];
            String tipo = conciertos[indiceConcierto][1];
            int asistentes = actuacionesRealizadas[i][1];

            double importeActuacion = calcularImporteActuacion(tipo, asistentes);
            totalFactura += importeActuacion;

            creditos += calcularCreditos(tipo, asistentes);

            System.out.println("\tConcierto: " + conciertos[indiceConcierto][0]);
            System.out.println("\t\tAsistentes: " + asistentes);
        }

        System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
        System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * IVA);
        System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * TOTAL_MULTIPLICADOR);
        System.out.println("Créditos obtenidos: " + creditos);
    }

    // Calcula el importe de una actuación
    static double calcularImporteActuacion(String tipo, int asistentes) throws Exception {
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
    static int calcularCreditos(String tipo, int asistentes) {
        int creditos = 0;

        creditos += Math.max(asistentes - UMBRAL_HEAVY, 0);

        if (tipo.equals("heavy")) {
            creditos += asistentes / 5;
        }

        return creditos;
    }
}
