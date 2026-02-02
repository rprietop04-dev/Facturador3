public class Facturador {

    // Repertorio de conciertos del grupo
    static String[][] repertorio = {
         {"Tributo Robe", "heavy"},
         {"Homaneje Queen", "pop"},
         {"Magia Knoppler", "rock"},
         {"Demonios Rojos", "heavy"}
    };

    // Actuaciones realizadas indicando el concierto ofrecido y audiencias obtenidas
    static Integer[][] actuaciones = {{0, 2000}, {2, 1200}, {0, 950}, {3, 1140}};

    static String cliente = "Ayuntamiento de Badajoz";

    public static void main(String[] args) throws Exception {
        Double totalFactura = 0d;
        Integer creditos = 0;

        System.out.println("FACTURA DE ACTUACIONES");
        System.out.println("Cliente: " + cliente);

        for (int i = 0; i < actuaciones.length; i++) {
            Integer iConcierto = actuaciones[i][0];
            String tipo = repertorio[iConcierto][1];
            int asistentes = actuaciones[i][1];

            double importeActuacion = calcularImporteActuacion(tipo, asistentes);
            totalFactura += importeActuacion;

            creditos += calcularCreditos(tipo, asistentes);

            System.out.println("\tConcierto: " + repertorio[iConcierto][0]);
            System.out.println("\t\tAsistentes: " + asistentes);
        }

        System.out.println("BASE IMPONIBLE: " + totalFactura + " euros");
        System.out.printf("IVA (21%%): %.2f euros\n", totalFactura * 0.21);
        System.out.printf("TOTAL FACTURA: %.2f euros\n", totalFactura * 1.21);
        System.out.println("Créditos obtenidos: " + creditos);
    }

    // Método para calcular el importe de una actuación según tipo y asistentes
    static double calcularImporteActuacion(String tipo, int asistentes) throws Exception {
        double importe = 0d;

        switch (tipo) {
            case "heavy":
                importe = 4000d;
                if (asistentes > 500)
                    importe += 20 * (asistentes - 500);
                break;
            case "rock":
                importe = 3000d;
                if (asistentes > 1000)
                    importe += 30 * (asistentes - 1000);
                break;
            default:
                throw new Exception("Tipo de concierto desconocido.");
        }

        return importe;
    }

    // Método para calcular créditos según tipo y asistentes
    static int calcularCreditos(String tipo, int asistentes) {
        int creditos = 0;

        creditos += Math.max(asistentes - 500, 0);

        if (tipo.equals("heavy")) {
            creditos += asistentes / 5;
        }

        return creditos;
    }
}
