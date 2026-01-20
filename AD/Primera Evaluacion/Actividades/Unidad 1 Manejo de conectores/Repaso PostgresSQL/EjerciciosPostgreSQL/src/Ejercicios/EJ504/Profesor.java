package Ejercicios.EJ504;

public class Profesor {
    private String cedula;
    private String departamento;


    public Profesor(String info) {
        String[] info_split = info.substring(1, info.length() -1).split(",");
        this.cedula = info_split[0];
        this.departamento = info_split[1];
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "cedula='" + cedula + '\'' +
                ", departamento='" + departamento + '\'' +
                '}';
    }
}

