package Ejercicios.EJ7;

public class Alumno {

    private int[]materias;
    private String nombre;
    private double media = 0;
    private int modulosCursados = 0;
    private boolean aprobadoTodo = true;

    public Alumno(String[] fila, int longitud) {
        this.materias = new int[longitud];
        this.nombre = fila[0];

        for (int i = 1; i < fila.length; i++) {
            try {
                this.materias[i - 1] = Integer.parseInt(fila[i].strip());

                if(this.materias[i-1] < 5)
                    this.aprobadoTodo = false;

                this.media += this.materias[i - 1];
                this.modulosCursados++;

            }catch (Exception e) {
                this.materias[i - 1] = -1;
            }
        }

        this.media = this.media / this.modulosCursados;
    }


    public String getNombre() {
        return nombre;
    }

    public double getNotaMedia() {
        return media;
    }

    public int getMateria(int pos) {
        return this.materias[pos];
    }

    public boolean getAprobadoTodo() {
        return this.aprobadoTodo;
    }

    public int getModulosCursados() {
        return modulosCursados;
    }

    public boolean haCursadoTodosLosModulos() {
        for (int i = 0; i < materias.length; i++) {
            if (materias[i] == -1) {  // Si alguna materia tiene un -1, no fue cursada
                return false;
            }
        }
        return true;
    }

}