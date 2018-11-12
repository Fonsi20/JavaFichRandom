/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Interfaces.Fichero;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author mallo
 */
public class Registros implements Fichero {

    public static int rRegistros(File fichero) throws IOException {

        int numRegs = 0;

        if (fichero.exists()) {
            RandomAccessFile leeRegistros = new RandomAccessFile(fichero, "r");
            numRegs = (int) Math.ceil((float) leeRegistros.length() / maxSize);
            leeRegistros.close();
        }

        return numRegs;
    }
}
