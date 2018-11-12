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
public abstract class Inmueble {

    private String referencia;
    private String propietario;
    private String fecha_recibo;
    private float cuota;
    private float aguaCaliente;
    private float calefaccion;
    private float total;
    private char tipo;

    public Inmueble(char tp, String ref, String pro, String fecha, float cuot, float agua, float calefa, float ttal) {
        tipo = tp;
        referencia = ref;
        propietario = pro;
        fecha_recibo = fecha;
        cuota = cuot;
        aguaCaliente = agua;
        calefaccion = calefa;
        total = ttal;
    }

    public String getFecha_recibo() {
        return fecha_recibo;
    }

    public void setFecha_recibo(String fecha_recibo) {
        this.fecha_recibo = fecha_recibo;
    }

    public float getAguaCaliente() {
        return aguaCaliente;
    }

    public void setAguaCaliente(float aguaCaliente) {
        this.aguaCaliente = aguaCaliente;
    }

    public char getTipo() {
        return tipo;
    }

    public float getCuota() {
        return cuota;
    }

    public float getAgua() {
        return aguaCaliente;
    }

    public float getCalefaccion() {
        return calefaccion;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getFecha() {
        return fecha_recibo;
    }

    public float getTotal() {
        return total;
    }

    public void setPropietario(String pro) {
        this.propietario = pro;
    }

    public void setCuota(float cuo) {
        this.cuota = cuo;
    }

    public void setTotal() {
        float total = 0;
        if (this instanceof Atico) {
            total = ((Atico) this).calcularTotal();
        } else if (this instanceof Duplex) {
            total = ((Duplex) this).calcularTotal();
        }
        this.total = total;
    }

    @Override
    public String toString() {
        return "Tipo: " + tipo + "  Referencia: " + referencia + "  Propietario: " + propietario + "   Fecha_recibo: " + fecha_recibo + "   Importe total: " + total + "€";
    }

    public abstract float calcularTotal();

    public abstract long getSize();
}
