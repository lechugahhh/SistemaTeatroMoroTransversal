package com.sistemateatromoro;

/**
 * Clase que representa un asiento en el Teatro Moro.
 * Cada asiento pertenece a una sección y tiene un número de asiento único.
 * Además, se puede marcar como disponible o no disponible para su asignación.
 */
public class Asiento {
    private String seccion;   // Sección a la que pertenece el asiento (ejemplo: "vip", "platea baja")
    private int numero;       // Número único del asiento dentro de la sección
    private boolean disponible; // Indica si el asiento está disponible para asignación

    /**
     * Constructor para inicializar un asiento con su sección y número.
     * Por defecto, el asiento está disponible.
     *
     * @param seccion La sección a la que pertenece el asiento.
     * @param numero  El número único del asiento.
     */
    public Asiento(String seccion, int numero) {
        this.seccion = seccion;
        this.numero = numero;
        this.disponible = true; // El asiento está disponible por defecto
    }

    /**
     * Verifica si el asiento está disponible.
     *
     * @return true si el asiento está disponible, false si está ocupado.
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Establece el estado de disponibilidad del asiento.
     *
     * @param disponible true si el asiento debe estar disponible, false si debe estar ocupado.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Obtiene la sección del asiento.
     *
     * @return La sección a la que pertenece el asiento.
     */
    public String getSeccion() {
        return seccion;
    }

    /**
     * Obtiene el número del asiento.
     *
     * @return El número único del asiento.
     */
    public int getNumero() {
        return numero;
    }
}