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
public class Duplex extends Inmueble {

    private float extra;

    public Duplex(char tp, String ref, String pro, String fecha, float cuota, float agua, float calefa, float extra, float ttl) {
        super(tp, ref, pro, fecha, cuota, agua, calefa, ttl);
        this.extra = extra;
    }

    public float getExtra() {
        return extra;
    }

    public void setExtra(float extra) {
        this.extra = extra;
    }

    @Override
    public float calcularTotal() {
        float total = (super.getCuota() + super.getAgua() * 0.40f) + (super.getCalefaccion() * 0.70f) + extra;
        return total;
    }

    @Override
    public long getSize() {
        return (Float.BYTES * 5) + (Character.BYTES * 1) + (super.getPropietario().length() * 2) + (super.getReferencia().length() * 2) + (super.getFecha().length() * 2);
    }

}
