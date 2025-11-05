public class Simulador {
    private Tablero tablero;
    private int numGeneraciones;

    public Simulador(int filas, int columnas, int numGeneraciones) {
        this.tablero = new Tablero(filas, columnas);
        this.numGeneraciones = numGeneraciones;
    }

    public void inicializarPatronAleatorio(double densidad) {
        for (int i = 0; i < tablero.getFilas(); i++) {
            for (int j = 0; j < tablero.getColumnas(); j++) {
                if (Math.random() < densidad) {
                    tablero.establecerCelula(i, j, true);
                }
            }
        }
    }

    public void simular() {
        tablero.mostrarTablero();

        for (int g = 0; g < numGeneraciones; g++) {
            tablero.evolucionarGeneracion();
            tablero.mostrarTablero();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Tablero getTablero() {
        return tablero;
    }
}