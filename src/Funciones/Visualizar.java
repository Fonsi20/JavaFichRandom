/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Interfaces.*;
import Objetos.*;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author mallo
 */
public class Visualizar implements Fichero, Leer, Fecha {

    public static void verTodo(int registros) throws FileNotFoundException, IOException {
        char tipo;
        Object aux;
        int n = 0;

        RandomAccessFile lee = new RandomAccessFile(fichero, "r");
        try {
            for (int i = 0; i < registros; i++) {
                lee.seek(i * Fichero.maxSize);
                tipo = lee.readChar();
                lee.seek(i * Fichero.maxSize);
                if (tipo == 'A') {
                    aux = new Atico(lee.readChar(), lee.readUTF(), lee.readUTF(), lee.readUTF(), lee.readFloat(), lee.readFloat(), lee.readFloat(), lee.readFloat(), lee.readFloat());
                } else {
                    aux = new Duplex(lee.readChar(), lee.readUTF(), lee.readUTF(), lee.readUTF(), lee.readFloat(), lee.readFloat(), lee.readFloat(), lee.readFloat(), lee.readFloat());
                }
                ((Inmueble) aux).setTotal();
                System.out.println(aux.toString());
                n++;
            }
        } catch (EOFException e) {
        }

        lee.close();

        if (n != 0) {
            System.out.println("---------------------------------------------------------------------------------------------------\n"
                    + "Total de facturas almacenadas: " + n + "\n"
                    + "---------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("--Error: No hay ninguna factura almacenadas--");
        }
    }

}
