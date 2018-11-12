/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Errores.MisExcepciones;
import Interfaces.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author mallo
 */
public class Validaciones implements Leer, Fichero {

    public static int menu(int x, int y, int opc) throws IOException {

        int err;

        do {
            err = 0;
            try {
                opc = Integer.parseInt(read.readLine());
                if (opc > y || opc < x) {
                    err = 1;
                    System.out.println("\nERROR: Opción no disponible\n\nSeleccione otra opción:");
                }
            } catch (NumberFormatException e) {
                err = 1;
                System.out.println("\nERROR: Entrada no válida\n\nSeleccione otra opción:");
            }
        } while (err == 1);

        return err;
    }

    public static boolean validaRed(String ref, int registros) throws IOException, MisExcepciones {

        boolean error = false;
        RandomAccessFile lee = new RandomAccessFile(fichero, "r");
        try {
            if (ref.length() != 4) {
                throw new MisExcepciones("--Error en la longitud del campo. Se esperan 4 dígitos.");
            }
            try {
                for (int i = 0; i < registros; i++) {
                    lee.seek(i * Fichero.maxSize);
                    lee.readChar();
                    if (lee.readUTF().equalsIgnoreCase(ref)) {
                        throw new MisExcepciones("--Error, no debe haber duplicidades en el código de referencia.");
                    }
                }

            } catch (EOFException e) {
            }
        } catch (MisExcepciones e) {
            System.out.println(e.getError());
            error = true;
        }
        lee.close();
        return error;
    }
}
