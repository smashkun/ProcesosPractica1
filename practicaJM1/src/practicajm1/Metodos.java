package practicajm1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Daniel Marcos, Alvaro Gomez
 * @version 1.2 14/10/2015
 */
public class Metodos {

    /**
     * Lanza un proceso hijo que recorre la ubicacion donde se encuentran los
     * usuarios y almacena los nombres en un fichero de texto
     */
    public static void leerUsuarios() throws InterruptedException, IOException {

        // Declaramos el proceso con el comando a ejecutar
        ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "dir /b C:\\Users\\Alumnot\\Documents\\Usuarios");

        pb.redirectOutput(new File("C:\\Users\\Alumnot\\Documents\\Usuarios\\usuarios.txt")); // Ubicacion de destino del resultado del comando

        Process p = pb.start(); // Lanza el proceso
        p.waitFor(); // Espera a que acabe
    }

    /**
     * Metodo para contar los usuarios
     *
     * @return Numero de usuarios
     */
    public static int contarUsuarios() throws FileNotFoundException, IOException {
        // Abrimos el fichero creado anteriormente
        BufferedReader leer = new BufferedReader(new FileReader("C:\\Users\\Alumnot\\Documents\\Usuarios\\usuarios.txt"));
        // Variable donde se almacenara cada linea del fichero
        String linea = null;
        // Contador de usuarios
        int count = 1;

        // Si hay un registro de un archivo, que lleve un punto y una extension, la ignoramos
        while ((linea = leer.readLine()) != null) {
            if (!(linea.indexOf('.') >= 0)) {
                count++;
            }
        }
        leer.close(); // Cerramos el fichero

        return count;
    }
    
    public static void limpiarFicheros() throws FileNotFoundException, IOException {
        String contenido = null;
        BufferedReader leer = new BufferedReader(new FileReader("C:\\Users\\Alumnot\\Documents\\Usuarios\\usuarios.txt"));
        String user = null;

        while ((user = leer.readLine()) != null) {
            if (!(user.indexOf('.') >= 0)) {
                BufferedReader carpetaUsuario = new BufferedReader(new FileReader("C:\\Users\\Alumnot\\Documents\\Usuarios\\" + user + ".txt"));
                String linea = null;

                System.out.println("El Usuario " + user + " contiene en su carpeta los siguientes archivos:");
                while ((linea = carpetaUsuario.readLine()) != null) {
                    linea.replace("C:\\Users\\Alumnot\\Documents\\Usuarios\\", "");
                    contenido += linea + "\n";
                }
                carpetaUsuario.close();
                BufferedWriter ficheroUsuario = new BufferedWriter(new FileWriter("C:\\Users\\Alumnot\\Documents\\Usuarios\\" + user + ".txt"));
                ficheroUsuario.write(contenido);
                ficheroUsuario.close();
                contenido = null;
            }
        }
        leer.close();
    }

    /**
     * Metodo para leer el contenido de los ficheros de cada usuario
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void mostrarContenido() throws FileNotFoundException, IOException {
        BufferedReader leer = new BufferedReader(new FileReader("C:\\Users\\Alumnot\\Documents\\Usuarios\\usuarios.txt"));
        String user = null;

        while ((user = leer.readLine()) != null) {
            if (!(user.indexOf('.') >= 0)) {
                BufferedReader carpetaUsuario = new BufferedReader(new FileReader("C:\\Users\\Alumnot\\Documents\\Usuarios\\" + user + ".txt"));
                String linea = null;

                System.out.println("El Usuario " + user + " contiene en su carpeta los siguientes archivos:");
                while ((linea = carpetaUsuario.readLine()) != null) {
                    System.out.println(linea);
                }
                carpetaUsuario.close();
            }
        }
        leer.close();
    }
}
