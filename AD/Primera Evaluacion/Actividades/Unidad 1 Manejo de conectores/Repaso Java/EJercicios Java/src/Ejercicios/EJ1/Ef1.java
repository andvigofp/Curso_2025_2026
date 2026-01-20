package Ejercicios.EJ1;

/**
 * Realiza un programa en Java que usando una variable estática
 *
 * String texto = “4;5;7;8;4”;
 *
 * copiar rápido
 * Para copiar el texto anterior podéis pulsar en el botón que aparece en la parte derecha del cuadro anterior.
 *
 * Separe los elementos por el carácter ; y los sume.
 *
 * Devolver el resultado por pantalla.
 */
public class Ef1 {
    static String texto = "4;5;7;8;4";

    public static void main(String[] args) {
        int suma = 0;
        String[] separados = texto.split(";");

        for (String s : separados) {
            suma += Integer.parseInt(s);
        }

        System.out.printf("EL total es: " + suma);
    }
}
