package com.sistemateatromoro;

import java.util.ArrayList;

/**
 * Clase principal para gestionar la venta de entradas en el Teatro Moro.
 * Permite realizar operaciones como asignar asientos, aplicar descuentos,
 * imprimir boletas y listar las entradas vendidas.
 */
public class VentaEntradas {
    private Asiento[] asientos;             // Arreglo de asientos disponibles
    private ArrayList<String> entradasVendidas; // Lista dinámica de entradas vendidas

    /**
     * Constructor para inicializar el sistema de venta de entradas con una cantidad específica de asientos.
     *
     * @param cantidadAsientos Número total de asientos disponibles.
     */
    public VentaEntradas(int cantidadAsientos) {
        this.asientos = new Asiento[cantidadAsientos];
        this.entradasVendidas = new ArrayList<>();
        inicializarAsientos();
    }

    /**
     * Inicializa los asientos del teatro dividiéndolos en secciones.
     */
    private void inicializarAsientos() {
        for (int i = 0; i < asientos.length; i++) {
            String seccion = determinarSeccion(i);
            asientos[i] = new Asiento(seccion, i + 1);
        }
    }

    /**
     * Determina la sección del asiento según su índice.
     *
     * @param indice Índice del asiento.
     * @return Nombre de la sección correspondiente.
     */
    private String determinarSeccion(int indice) {
        if (indice < 10) return "VIP";
        if (indice < 30) return "Platea baja";
        if (indice < 50) return "Platea alta";
        return "Galería";
    }

    /**
     * Muestra la disponibilidad de los asientos en el teatro.
     */
    public void verDisponibilidadAsientos() {
        System.out.println("----- Disponibilidad de Asientos -----");
        for (Asiento asiento : asientos) {
            String estado = asiento.isDisponible() ? "Disponible" : "Ocupado";
            System.out.println("Sección: " + asiento.getSeccion() + ", Número: " + asiento.getNumero() + " (" + estado + ")");
        }
        System.out.println("--------------------------------------");
    }

    /**
     * Lista todas las entradas vendidas.
     */
    public void listarEntradasVendidas() {
        System.out.println("----- Registro de Entradas Vendidas -----");
        if (entradasVendidas.isEmpty()) {
            System.out.println("No hay entradas vendidas.");
        } else {
            for (String entrada : entradasVendidas) {
                System.out.println(entrada);
            }
        }
        System.out.println("-----------------------------------------");
    }

    /**
     * Asigna un asiento disponible a un cliente en una sección específica.
     *
     * @param cliente Objeto Cliente que solicita el asiento.
     * @param seccion Sección deseada.
     * @return true si se asignó el asiento exitosamente, false de lo contrario.
     */
    public boolean asignarAsiento(Cliente cliente, String seccion) {
        for (Asiento asiento : asientos) {
            if (asiento.getSeccion().equalsIgnoreCase(seccion) && asiento.isDisponible()) {
                asiento.setDisponible(false);
                String entrada = "Cliente: " + cliente.getNombre() + ", Sección: " + asiento.getSeccion() + ", Número: " + asiento.getNumero();
                entradasVendidas.add(entrada);
                return true;
            }
        }
        return false;
    }

    /**
     * Imprime la boleta de una entrada comprada.
     *
     * @param cliente             Objeto Cliente que realizó la compra.
     * @param precioFinal         Precio final con descuento aplicado.
     * @param seccion             Sección del asiento comprado.
     * @param porcentajeDescuento Porcentaje de descuento aplicado.
     */
    public void imprimirBoleta(Cliente cliente, double precioFinal, String seccion, double porcentajeDescuento) {
        System.out.println("\n========== BOLETA ==========");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Edad: " + cliente.getEdad());
        System.out.println("Sección: " + seccion.toUpperCase());
        System.out.printf("Precio final: $%.2f\n", precioFinal);
        System.out.printf("Descuento aplicado: %.0f%%\n", porcentajeDescuento);
        System.out.println("=============================");
    }

    /**
     * Clase interna para almacenar el resultado del cálculo de precio final.
     */
    public static class ResultadoDescuento {
        private double precioFinal;           // Precio final después del descuento
        private double porcentajeDescuento;  // Porcentaje de descuento aplicado

        /**
         * Constructor para inicializar un resultado de descuento.
         *
         * @param precioFinal         Precio final después del descuento.
         * @param porcentajeDescuento Porcentaje de descuento aplicado.
         */
        public ResultadoDescuento(double precioFinal, double porcentajeDescuento) {
            this.precioFinal = precioFinal;
            this.porcentajeDescuento = porcentajeDescuento;
        }

        public double getPrecioFinal() {
            return precioFinal;
        }

        public double getPorcentajeDescuento() {
            return porcentajeDescuento;
        }
    }

    /**
     * Calcula el precio final de una entrada aplicando los descuentos correspondientes.
     *
     * @param cliente Objeto Cliente que realiza la compra.
     * @param seccion Sección seleccionada.
     * @return Objeto ResultadoDescuento con el precio final y el porcentaje de descuento.
     */
    public ResultadoDescuento calcularPrecioFinal(Cliente cliente, String seccion) {
        double descuento = 0.0;
        double precioBase;

        // Normalizar la sección eliminando tildes y convirtiendo a mayúsculas
        seccion = eliminarTildes(seccion).toUpperCase();

        // Asignar el precio base según la sección seleccionada
        switch (seccion) {
            case "VIP":
                precioBase = 30000.0; // Precio para VIP
                break;
            case "PLATEA BAJA":
                precioBase = 20000.0; // Precio para Platea Baja
                break;
            case "PLATEA ALTA":
                precioBase = 15000.0; // Precio para Platea Alta
                break;
            case "GALERIA":
                precioBase = 10000.0; // Precio para Galería
                break;
            default:
                throw new IllegalArgumentException("Sección no válida: " + seccion);
        }

        // Aplicar descuentos según las características del cliente
        if (cliente.getEdad() < 18) { // Niños
            descuento = 0.1;
        } else if (cliente.isEstudiante()) { // Estudiantes
            descuento = 0.15;
        } else if (cliente.getEdad() > 60) { // Personas de tercera edad
            descuento = 0.25;
        } else if (cliente.getSexo().equalsIgnoreCase("mujer")) { // Mujeres
            descuento = 0.2;
        }

        double precioFinal = precioBase * (1 - descuento);
        return new ResultadoDescuento(precioFinal, descuento * 100); // Retornar el porcentaje de descuento
    }

    /**
     * Elimina tildes de un texto y lo convierte a mayúsculas.
     *
     * @param texto Texto original.
     * @return Texto sin tildes y en mayúsculas.
     */
    private String eliminarTildes(String texto) {
        return texto
            .replace("á", "a")
            .replace("é", "e")
            .replace("í", "i")
            .replace("ó", "o")
            .replace("ú", "u")
            .replace("Á", "A")
            .replace("É", "E")
            .replace("Í", "I")
            .replace("Ó", "O")
            .replace("Ú", "U");
    }

    /**
     * Obtiene la lista de entradas vendidas.
     *
     * @return Lista de entradas vendidas.
     */
    public ArrayList<String> getEntradasVendidas() {
        return entradasVendidas;
    }

    /**
     * Elimina una entrada vendida por su índice.
     *
     * @param index Índice de la entrada a eliminar.
     * @return Entrada eliminada.
     * @throws IndexOutOfBoundsException Si el índice es inválido.
     */
    public String eliminarEntrada(int index) {
        if (index >= 0 && index < entradasVendidas.size()) {
            return entradasVendidas.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Índice inválido para eliminar entrada.");
        }
    }
}