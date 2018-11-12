/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author mallo
 */
public class Atico extends Inmueble {

    private float terraza;

    public Atico(char tp, String ref, String pro, String fecha, float cuot, float agua, float calefa, float ttal, float terraza) {
        super(tp, ref, pro, fecha, cuot, agua, calefa, ttal);
        this.terraza = terraza;
    }

    public float getTerraza() {
        return terraza;
    }

    public void setTerraza(float terraza) {
        this.terraza = terraza;
    }

    public String getUsos() {
        return "Fecha: " + super.getFecha() + "  Beneficios: " + super.getTotal() + "";
    }

    @Override
    public float calcularTotal() {
        float total = (super.getCuota() + super.getAgua() * 0.40f) + (super.getCalefaccion() * 0.70f) + (0.45f * terraza);
        return total;
    }

    @Override
    public long getSize() {
        return (Float.BYTES * 5) + (Character.BYTES * 1) + (super.getPropietario().length() * 2) + (super.getReferencia().length() * 2) + (super.getFecha().length() * 2);
    }

}
