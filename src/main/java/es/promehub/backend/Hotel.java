package es.promehub.backend;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "hotel")
@XmlAccessorType(XmlAccessType.FIELD)
public class Hotel {

    @XmlElement(name = "habitacion")
    private final List<Habitacion> habitaciones;

    public Hotel() {
        habitaciones = new ArrayList<>();
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void insertarHabitacion(Habitacion habitacion){

        habitaciones.add(habitacion);
    }


}