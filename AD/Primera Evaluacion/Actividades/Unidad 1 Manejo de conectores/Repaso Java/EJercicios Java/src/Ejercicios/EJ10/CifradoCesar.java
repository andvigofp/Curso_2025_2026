package Ejercicios.EJ10;

public class CifradoCesar {
    // Método para cifrar un mensaje usando el cifrado César
    public static String cifrar(String mensaje, int desplazamiento) {
        StringBuilder cifrado = new StringBuilder();
        desplazamiento = desplazamiento % 26; // Asegurarse de que el desplazamiento esté dentro del rango 0-25

        for (char c : mensaje.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char nuevaLetra = (char) ((c - base + desplazamiento) % 26 + base);
                cifrado.append(nuevaLetra);
            } else {
                cifrado.append(c);
            }
        }

        return cifrado.toString();
    }

    // Método para descifrar un mensaje usando el cifrado César
    public static String descifrar(String mensaje, int desplazamiento) {
        // Descifrar es simplemente cifrar con el desplazamiento negativo
        return cifrar(mensaje, -desplazamiento);
    }
}
