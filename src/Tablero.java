public class Tablero {
    private Celula[][] grid;
    private int filas;
    private int columnas;
    private int generacion;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.grid = new Celula[filas][columnas];
        this.generacion = 0;
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                grid[i][j] = new Celula();
            }
        }
    }

    public void establecerCelula(int fila, int columna, boolean viva) {
        if (esValido(fila, columna)) {
            grid[fila][columna].setViva(viva);
            if (viva) {
                grid[fila][columna].nacer();
            }
        }
    }

    private boolean esValido(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    private int contarVecinosVivos(int fila, int columna) {
        int contador = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                int nuevaFila = (fila + i + filas) % filas;
                int nuevaColumna = (columna + j + columnas) % columnas;
                if (grid[nuevaFila][nuevaColumna].isViva()) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public void evolucionarGeneracion() {
        Celula[][] nuevoGrid = new Celula[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                nuevoGrid[i][j] = new Celula();
                int vecinosVivos = contarVecinosVivos(i, j);

                if (grid[i][j].isViva()) {
                    if (vecinosVivos == 2 || vecinosVivos == 3) {
                        nuevoGrid[i][j].nacer();
                        nuevoGrid[i][j].setViva(true);
                    } else {
                        nuevoGrid[i][j].morir();
                    }
                } else {
                    if (vecinosVivos == 3) {
                        nuevoGrid[i][j].nacer();
                    } else if (Math.random() < 0.1) {
                        nuevoGrid[i][j].nacer();
                    }
                }
            }
        }

        grid = nuevoGrid;
        generacion++;
    }

    public void mostrarTablero() {
        System.out.println("\n=== Generación " + generacion + " ===");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                System.out.print(grid[i][j].isViva() ? "■ " : "□ ");
            }
            System.out.println();
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public Celula getCelula(int fila, int columna) {
        if (esValido(fila, columna)) {
            return grid[fila][columna];
        }
        return null;
    }

    public int getGeneracion() {
        return generacion;
    }
}