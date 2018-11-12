/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficherorandom1;

import Errores.MisExcepciones;
import Funciones.*;
import Interfaces.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

/**
 *
 * @author mallo
 */
public class FicheroRandom1 implements Fichero, Leer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, MisExcepciones, ParseException {

        int opcion = 0, err = 0;
        int registros = Registros.rRegistros(fichero);
        do {
            System.out.println(""
                    + "\n---------MENÚ-----------"
                    + "\n[1]- Cargar facturas."
                    + "\n[2]- Eliminar facturas."
                    + "\n[3]- Modificaciones."
                    + "\n[4]- Visualizar todo."
                    + "\n[5]- Consultar historial."
                    + "\n[6]- Consultar propietario."
                    + "\n[0]- Salir.\n");

            opcion = Integer.parseInt(read.readLine());
            //err = Validaciones.menu(0,6,opcion);

            switch (opcion) {
                case 0:
                    break;
                case 1:
                    registros = Altas.nuevaFactura(registros);
                    break;
                case 2:
                    registros = Bajas.borrados(registros);
                    break;
                case 3:
                    Modificaciones.modifica(registros);
                    break;
                case 4:
                    Visualizar.verTodo(registros);
                    break;
                case 5:
                    //Consultas.consultas(registros);
                    break;
                case 6:
                    //Consultas.consultas2(registros);
                    break;
            }
        } while (opcion != 0);
    }
}
