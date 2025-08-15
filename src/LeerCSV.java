import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LeerCSV {
    public static void main(String[] args) {
        //ruta de los codigos postales 
        String rutaArchivo = "archivos/codigos_postales_hmo.csv";

        // el hash map sirve pa guardar los conteos de asentamientos 
        HashMap<String, Integer> conteoCodigos = new HashMap<>();

        // EL BufferedReader br = new BufferedReader(new FileReader sirve pa leer linea por linea el archivo y llama a la variable ruta archivo que ahi esta
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                // Aqui sirve para separar las partes en comas como esta el archivo para poder separar y sacar lo que necesitamos 
                String[] partes = linea.split(",");

                // Validamos que tenga al menos 2 columnas (código postal y asentamiento)
                if (partes.length < 2) {
                    continue; // Si no se ignora y se deja pasar
                }

                String codigoPostal = partes[0].trim(); // Quitamos los  espacios innecesarios 
                String asentamiento = partes[1].trim();

                // Aquí actualizamos el conteo en el HashMap para que si encuentra uno nuevo se sume 
                if (conteoCodigos.containsKey(codigoPostal)) {
                    // Si ya existe, sumamos 1 al valor actual y asi hasta que termine
                    int valorActual = conteoCodigos.get(codigoPostal);
                    conteoCodigos.put(codigoPostal, valorActual + 1);
                } else {
                    // y si no hay ninguno se le colocaa el valor de 1 
                    conteoCodigos.put(codigoPostal, 1);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Como solo se nos pidio el numero de asentamientos por codigo postal eso se va a impimir en orden 
      System.out.println("""
=== Manejo de archivos de datos ===
Version 0.1
Hecho por Marco Sebastian Ortega Molina 
""");
System.out.println("=== Conteo de asentamientos por código postal ===");

// Recorremos todos los códigos postales que tenemos en el HashMap
for (String codigo : conteoCodigos.keySet()) {

    // Tomamos el número de asentamientos que tiene este código postal
    int numeroAsentamientos = conteoCodigos.get(codigo);

    // Imprimimos el código postal y cuántos asentamientos tiene
    System.out.println("Código postal: " + codigo + " - Número de asentamientos: " + numeroAsentamientos);
}

// El for se cierra aquí, significa que ya recorrimos todos los códigos


    }}