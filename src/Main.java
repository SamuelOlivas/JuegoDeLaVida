import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║      JUEGO DE LA VIDA - John Conway (1970)                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        System.out.println("DESCRIPCIÓN:");
        System.out.println("El Juego de la Vida es un autómata celular que simula la evolución");
        System.out.println("de células en un mundo esférico (matriz toroidal) mediante reglas simples.\n");

        System.out.println("REGLAS DEL JUEGO:");
        System.out.println("1. Nacimiento: Una célula muerta con exactamente 3 vecinos vivos nace");
        System.out.println("2. Supervivencia: Una célula viva con 2-3 vecinos vivos sobrevive");
        System.out.println("3. Muerte por aislamiento: Célula viva con < 2 vecinos muere");
        System.out.println("4. Muerte por sobrepoblación: Célula viva con > 4 vecinos muere");
        System.out.println("5. Génesis espontánea: 10% de probabilidad de nacimiento aleatorio\n");

        System.out.println("SIMBOLOGÍA:");
        System.out.println("■ = Célula viva");
        System.out.println("□ = Célula muerta\n");

        System.out.println("─".repeat(60) + "\n");

        // Obtener parámetros del usuario
        Scanner scanner = new Scanner(System.in);

        int filas = obtenerEnteroPositivo(scanner, "Introduce el número de filas (mín 4, máx 20): ", 4, 20);
        int columnas = obtenerEnteroPositivo(scanner, "Introduce el número de columnas (mín 4, máx 20): ", 4, 20);
        int generaciones = obtenerEnteroPositivo(scanner, "Introduce el número de generaciones (mín 1, máx 50): ", 1, 50);

        System.out.print("\nIntroduce la densidad inicial de células (0.0 a 1.0): ");
        double densidad = obtenerDoble(scanner, 0.0, 1.0);

        scanner.close();

        System.out.println("\n" + "─".repeat(60));
        System.out.println("PARÁMETROS CONFIGURADOS:");
        System.out.println("   Dimensiones: " + filas + "x" + columnas);
        System.out.println("   Generaciones: " + generaciones);
        System.out.println("   Densidad inicial: " + String.format("%.1f%%", densidad * 100));
        System.out.println("─".repeat(60) + "\n");

        // Crear simulador con los parámetros ingresados
        Simulador simulador = new Simulador(filas, columnas, generaciones);

        // Inicializar con patrón aleatorio según la densidad
        simulador.inicializarPatronAleatorio(densidad);

        // Ejecutar la simulación
        simulador.simular();

        System.out.println("\n" + "─".repeat(60));
        System.out.println("FIN DE LA SIMULACIÓN");
        System.out.println("─".repeat(60));
    }

    /**
     * Obtiene un número entero positivo del usuario dentro de un rango
     */
    private static int obtenerEnteroPositivo(Scanner scanner, String mensaje, int minimo, int maximo) {
        int valor = 0;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensaje);
                valor = Integer.parseInt(scanner.nextLine());

                if (valor >= minimo && valor <= maximo) {
                    valido = true;
                } else {
                    System.out.println("Error: Introduce un número entre " + minimo + " y " + maximo);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes introducir un número entero");
            }
        }

        return valor;
    }

    /**
     * Obtiene un número decimal (double) del usuario dentro de un rango
     */
    private static double obtenerDoble(Scanner scanner, double minimo, double maximo) {
        double valor = 0;
        boolean valido = false;

        while (!valido) {
            try {
                valor = Double.parseDouble(scanner.nextLine());

                if (valor >= minimo && valor <= maximo) {
                    valido = true;
                } else {
                    System.out.println("Error: Introduce un número entre " + minimo + " y " + maximo);
                    System.out.print("Introduce la densidad inicial de células (0.0 a 1.0): ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes introducir un número decimal (usa punto: 0.5)");
                System.out.print("Introduce la densidad inicial de células (0.0 a 1.0): ");
            }
        }

        return valor;
    }
}