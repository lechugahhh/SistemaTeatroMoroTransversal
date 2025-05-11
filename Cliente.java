package com.sistemateatromoro;

/**
 * Clase que representa un cliente del sistema de ventas del Teatro Moro.
 * Contiene información personal del cliente, como nombre, edad, tipo de cliente,
 * sexo y si es estudiante.
 */
public class Cliente {
    private String nombre;          // Nombre del cliente
    private int edad;               // Edad del cliente
    private String tipoCliente;     // Tipo de cliente: "niño", "adulto", "tercera edad"
    private String sexo;            // Sexo del cliente: "hombre", "mujer", "otro"
    private boolean esEstudiante;   // Indica si el cliente es estudiante

    /**
     * Constructor para inicializar un nuevo cliente.
     *
     * @param nombre       Nombre del cliente.
     * @param edad         Edad del cliente.
     * @param tipoCliente  Tipo de cliente (niño, adulto, tercera edad).
     * @param sexo         Sexo del cliente (hombre, mujer, otro).
     * @param esEstudiante Indica si el cliente es estudiante.
     */
    public Cliente(String nombre, int edad, String tipoCliente, String sexo, boolean esEstudiante) {
        this.nombre = nombre;
        this.edad = edad;
        this.tipoCliente = tipoCliente;
        this.sexo = sexo;
        this.esEstudiante = esEstudiante;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene la edad del cliente.
     *
     * @return La edad del cliente.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Obtiene el tipo de cliente (niño, adulto, tercera edad).
     *
     * @return El tipo de cliente.
     */
    public String getTipo() {
        return tipoCliente;
    }

    /**
     * Obtiene el sexo del cliente (hombre, mujer, otro).
     *
     * @return El sexo del cliente.
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Indica si el cliente es estudiante.
     *
     * @return true si el cliente es estudiante, false de lo contrario.
     */
    public boolean isEstudiante() {
        return esEstudiante;
    }
}