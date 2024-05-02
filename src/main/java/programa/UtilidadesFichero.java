/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programa;

import java.awt.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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
}
