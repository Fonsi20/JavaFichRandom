/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Interfaces.Fecha;
import Interfaces.Fichero;
import static Interfaces.Fichero.fichero;
import Interfaces.Leer;
import static Interfaces.Leer.read;
import Objetos.Atico;
import Objetos.Duplex;
import Objetos.Inmueble;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;

/**
 *
 * @author mallo
 */
public class Modificaciones implements Fichero, Leer, Fecha {

    public static void modifica(int registros) throws FileNotFoundException, IOException, ParseException {

        char tipo;
        Object aux;
        String ref;
        byte opcion;
        boolean repite = false, encontrado = false;

        RandomAccessFile escribe = new RandomAccessFile(fichero, "rw");
        System.out.println("Introduzca REFERENCIA de factura a modificar.");
        ref = read.readLine();
        int b = 0;
        try {
            for (int i = 0; i < registros; i++) {
                escribe.seek(i * Fichero.maxSize);
                tipo = escribe.readChar();
                escribe.seek(i * Fichero.maxSize);
                if (tipo == 'A') {
                    aux = new Atico(escribe.readChar(), escribe.readUTF(), escribe.readUTF(), escribe.readUTF(), escribe.readFloat(), escribe.readFloat(), escribe.readFloat(), escribe.readFloat(), escribe.readFloat());
                } else {
                    aux = new Duplex(escribe.readChar(), escribe.readUTF(), escribe.readUTF(), escribe.readUTF(), escribe.readFloat(), escribe.readFloat(), escribe.readFloat(), escribe.readFloat(), escribe.readFloat());
                }

                if (((Inmueble) aux).getReferencia().equalsIgnoreCase(ref)) {
                    System.out.println(((Inmueble) aux).toString() + "\n"
                            + "¿Confirma que desea modificar esta factura?"
                            + "\n[1]- Si."
                            + "\n[0]- No.");
                    encontrado = true;
                    opcion = Byte.parseByte(read.readLine());

                    if (opcion == 1) {
                        do {
                            System.out.println("Seleccione campo a modificar:"
                                    + "\n[1] - Cuota base."
                                    + "\n[2] - Propietario."
                                    + "\n[3] - Fecha de emisión."
                                    + "\n[0] - Finalizar modificación.");
                            opcion = Byte.parseByte(read.readLine());

                            switch (opcion) {
                                case 1:
                                    System.out.println("Introduzca nuevo valor para CUOTA BASE:");
                                    ((Inmueble) aux).setCuota(new Float(Float.parseFloat(read.readLine())));
                                    break;

                                case 2:
                                    System.out.println("Introduzca nuevo valor para PROPIETARIO:");
                                    ((Inmueble) aux).setPropietario(new String(read.readLine()));
                                    break;

                                case 3:
                                    System.out.println("Introduzca nuevo valor para CUOTA BASE:");

                                    do {
                                        try {
                                            repite = false;
                                            String fechaAux = sdf.format(sdf.parse(read.readLine()));
                                        } catch (NumberFormatException e) {
                                            System.out.println("Error en el formato, reintroduzca la fecha.");
                                            repite = true;
                                        }
                                    } while (repite == true);
                                    break;

                                case 0:
                                    break;
                            }

                        } while (opcion != 0);
                    }
                    escribe.seek(i * Fichero.maxSize);
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
                }
            }
        } catch (EOFException e) {
        }

        escribe.close();

        if (encontrado == false) {
            System.out.println("--Error: No se ha encontrado ninguna factura con la referencia " + ref);
        }

    }

}
