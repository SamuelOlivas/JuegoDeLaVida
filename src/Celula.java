public class Celula {
    private boolean viva;
    private int edad;

    public Celula() {
        this.viva = false;
        this.edad = 0;
    }

    public Celula(boolean viva) {
        this.viva = viva;
        this.edad = viva ? 1 : 0;
    }

    public boolean isViva() {
        return viva;
    }

    public void setViva(boolean viva) {
        this.viva = viva;
    }

    public int getEdad() {
        return edad;
    }

    public void incrementarEdad() {
        if (viva) {
            edad++;
        }
    }

    public void resetearEdad() {
        edad = 0;
    }

    public void nacer() {
        this.viva = true;
        this.edad = 1;
    }

    public void morir() {
        this.viva = false;
        this.edad = 0;
    }
}
