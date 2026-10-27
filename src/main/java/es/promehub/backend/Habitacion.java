package es.promehub.backend;

import jakarta.xml.bind.annotation.*;

//@XmlRootElement(name = "habitacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class Habitacion {

    @XmlAttribute
    private int numero;

    @XmlAttribute
    private String estado;

    @XmlAttribute
    private int planta;

    @XmlElement
    private String tipo;

    @XmlElement
    private int capacidad;

    @XmlElement
    private int precioNoche;

    @XmlElement
    private String vista;

    @XmlElement
    private String accesible;


    public Habitacion(int numero, String estado, int planta, String tipo, int capacidad, int precioNoche, String vista, String accesible) {
        this.numero = numero;
        this.estado = estado;
        this.planta = planta;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
        this.vista = vista;
        this.accesible = accesible;
    }

    public int getNumero() {
        return numero;
    }

    public String getEstado() {
        return estado;
    }

    public int getPlanta() {
        return planta;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getPrecioNoche() {
        return precioNoche;
    }

    public String getVista() {
        return vista;
    }

    public String getAccesible() {
        return accesible;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "numero=" + numero +
                ", estado='" + estado + '\'' +
                ", planta=" + planta +
                ", tipo='" + tipo + '\'' +
                ", capacidad=" + capacidad +
                ", precioNoche=" + precioNoche +
                ", vista='" + vista + '\'' +
                ", accesible='" + accesible + '\'' +
                '}';
    }
}
