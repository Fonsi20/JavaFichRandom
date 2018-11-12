/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.io.EOFException;
import java.io.File;
import java.io.RandomAccessFile;
import Interfaces.*;
import Objetos.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author mallo
 */
public class Bajas implements Fichero, Leer, Fecha {

    public static int borrados(int registros) throws FileNotFoundException, IOException {
        char tipo;
        Object aux;
        String ref;
        byte opcion;
        boolean borrado = false, encontrado = false;

        File temporal = new File("temporal");
        RandomAccessFile lee = new RandomAccessFile(fichero, "r");
        RandomAccessFile escribe = new RandomAccessFile(temporal, "rw");
        System.out.println("Introduzca REFERENCIA de factura a eliminar.");
        ref = read.readLine();
        int b = 0;
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

                if (!((Inmueble) aux).getReferencia().equalsIgnoreCase(ref)) {
                    lee.seek(b * Fichero.maxSize);
                    escribe.writeChar(((Inmueble) aux).getTipo());
                    escribe.writeUTF(((Inmueble) aux).getReferencia());
                    escribe.writeUTF(((Inmueble) aux).getPropietario());
                    escribe.writeUTF(((Inmueble) aux).getFecha());
                    escribe.writeFloat(((Inmueble) aux).getCuota());
                    escribe.writeFloat(((Inmueble) aux).getAgua());
                    escribe.writeFloat(((Inmueble) aux).getCalefaccion());
                    if (((Inmueble) aux).getTipo() == 'A') {
                        escribe.writeFloat(((Atico) aux).getTerraza());
                    } else {
                        escribe.writeFloat(((Duplex) aux).getExtra());
                    }
                    escribe.writeFloat(((Inmueble) aux).getTotal());
                    b++;
                } else {
                    System.out.println(((Inmueble) aux).toString() + "\n"
                            + "¿Confirma que desea eliminar esta factura del fichero?"
                            + "\n[1]- Si."
                            + "\n[0]- No.");
                    encontrado = true;
                    opcion = Byte.parseByte(read.readLine());
                    if (opcion == 1) {
                        borrado = true;
                    } else {
                        ref = null;
                        i--;
                        b--;
                    }
                }
            }
        } catch (EOFException e) {
        }
        lee.close();
        escribe.close();
        fichero.delete();
        temporal.renameTo(fichero);
        if (borrado == true) {
            System.out.println("Se ha eliminado la factura con ref " + ref);
        } else if (encontrado == false) {
            System.out.println("--Error: No se ha podido localizar la factura con ref " + ref);
        }
        return registros;
    }

}
