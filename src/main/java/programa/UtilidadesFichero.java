/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programa;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author miguel
 */
public class UtilidadesFichero {

    public static ArrayList<String> leerFichero() {

        ArrayList<String> lines = new ArrayList<>();

        try {
            // Especifica la ruta del archivo que deseas leer
            String file = "usuarios.csv";

            // Lee todas las líneas del archivo y las almacena en una lista
            lines = (ArrayList<String>) Files.readAllLines(Paths.get(file));

            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public static void escribirFichero(ArrayList<String> fichero) {

        try {
            // Crear un BufferedWriter para escribir en el archivo
            BufferedWriter escritor = new BufferedWriter(new FileWriter("usuarios.csv"));

            // Escribir cada línea en el archivo
            for (String linea : fichero) {
                escritor.write(linea);
                escritor.newLine(); // Agregar un salto de línea después de cada línea
            }

            // Cerrar el BufferedWriter
            escritor.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al sobrescribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static boolean registrarUsuario(String user, char[] passw0, char[] passw1) {

        boolean registroCorrecto = true;

        ArrayList<String> lines = leerFichero();

        String passw0String = String.valueOf(passw0);
        String passw1String = String.valueOf(passw1);

        if (user.trim().isEmpty() || passw0String.isEmpty()
                || passw1String.isEmpty()) {

            JOptionPane.showMessageDialog(null,
                    "No puedes registrar un usuario con algun campo vacio.");
            return !registroCorrecto;
        }

        if (!passw0String.equals(passw1String)) {

            JOptionPane.showMessageDialog(null,
                    "Las contraseñas no coinciden.");
            return !registroCorrecto;
        } else {
            ArrayList<String> listaTmp = new ArrayList<>();

            for (String line : lines) {

                String[] parts = line.split(";");
                
                if (parts[0].equalsIgnoreCase(user)) {

                    String tmp = user + passw0String + "";
                    listaTmp.add(tmp);
                } else {

                    listaTmp.add(line);
                }
            }

            String userData = user + ";" + passw0String;
            lines.add(userData);
            escribirFichero(lines);
            return registroCorrecto;

        }
    }
}
