/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Errores.MisExcepciones;
import Interfaces.*;
import Objetos.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;

/**
 *
 * @author mallo
 */
public class Altas implements Fichero, Leer, Fecha {

    public static int nuevaFactura(int registros) throws FileNotFoundException, IOException, MisExcepciones {
        byte opcion;
        boolean repite, abortar = false;
        Atico auxA;
        Duplex auxD;
        char tipo;
        String ref, pro, fecha = null;
        float cuota, calefaccion, agua, extra = 0, terraza = 0, total = 0;
        long sizeAux = 0;

        do {

            RandomAccessFile raf = new RandomAccessFile(fichero, "rw");

            System.out.println("Introduzca REFERENCIA de nueva factura:");
            do {
                repite = false;
                ref = read.readLine();
                repite = Validaciones.validaRed(ref, registros);
            } while (repite == true);

            System.out.println("Introduzca FECHA de facturación:"
                    + "\n(Formato: dd/mm/aaaa)");
            do {
                try {
                    repite = false;
                    fecha = sdf.format(sdf.parse(read.readLine()));
                } catch (ParseException e) {
                    System.out.println("--Error en el formato de fecha. Respete el formato indicado.");
                    repite = true;
                }
            } while (repite == true);

            System.out.println("Introduzca nombre del PROPIETARIO del inmueble:");
            pro = read.readLine();
            System.out.println("Introduzca TIPO de piso:"
                    + "\n[1]- Ático."
                    + "\n[0]- Dúplex.");
            opcion = Byte.parseByte(read.readLine());
            if (opcion == 1) {
                tipo = 'A';
            } else {
                tipo = 'D';
            }
            System.out.println("Introduzca CUOTA BASE:");
            cuota = Float.parseFloat(read.readLine());
            System.out.println("Introduzca gastos de CALEFACCIÓN:");
            calefaccion = Float.parseFloat(read.readLine());
            System.out.println("Introduzca gastos de AGUA CALIENTE:");
            agua = Float.parseFloat(read.readLine());

            if (tipo == 'A') {
                System.out.println("Introduzca gastos derivados de la TERRAZA:");
                terraza = Float.parseFloat(read.readLine());
                auxA = new Atico(tipo, ref, pro, fecha, cuota, agua, calefaccion, terraza, 0);
                sizeAux = auxA.getSize();
                auxA.setTotal();
                total = auxA.getTotal();
            } else if (tipo == 'D') {
                System.out.println("Introduzca gastos EXTRA por DUPLEX:");
                extra = Float.parseFloat(read.readLine());
                auxD = new Duplex(tipo, ref, pro, fecha, cuota, agua, calefaccion, extra, 0);
                sizeAux = auxD.getSize();
                auxD.setTotal();
                total = auxD.getTotal();
            }

            if (sizeAux > Fichero.maxSize) {
                System.out.println("--ERROR, la longitud del objeto excede la permitida."
                        + "\nFactura actual: " + sizeAux + "    Máximo:" + Fichero.maxSize + ""
                        + "\nEl objeto no puede ser guardado en su estado actual."
                        + "\n¿Desea crear una nueva factura?"
                        + "\n[1]- Si."
                        + "\n[0]- No, regresar al menú principal.");
                abortar = true;
                opcion = Byte.parseByte(read.readLine());
            }

            if (abortar == false) {
                System.out.println("Tamaño en fichero: " + sizeAux + "/" + Fichero.maxSize
                        + "\nEl importe TOTAL de esta factura es:" + total);

                raf.seek(registros * Fichero.maxSize);
                raf.writeChar(tipo);
                raf.writeUTF(ref);
                raf.writeUTF(pro);
                raf.writeUTF(fecha);
                raf.writeFloat(cuota);
                raf.writeFloat(agua);
                raf.writeFloat(calefaccion);
                if (tipo == 'A') {
                    raf.writeFloat(terraza);
                } else {
                    raf.writeFloat(extra);
                }
                raf.writeFloat(0);
                registros++;
                raf.close();
                System.out.println("--La factura ha sido creada satisfactoriamente y se ha guardado en el fichero."
                        + "\n¿Desea crear una nueva factura?"
                        + "\n[1]- Si."
                        + "\n[0]- No, regresar al menú principal.");
                opcion = Byte.parseByte(read.readLine());
            }
        } while (opcion == 1);

        return registros;
    }

}
