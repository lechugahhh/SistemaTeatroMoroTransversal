package com.sistemateatromoro;

import java.util.Scanner;

/**
 * Clase principal que ejecuta el sistema de venta de entradas para el Teatro Moro.
 * Permite a los usuarios ver la disponibilidad de asientos, realizar ventas, 
 * gestionar entradas y salir del sistema.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VentaEntradas ventaEntradas = new VentaEntradas(100); // Sistema con 100 asientos
        int opcion;

        do {
            // Menú principal del sistema
            System.out.println("\n===== SISTEMA DE VENTA DE ENTRADAS =====");
            System.out.println("1. Ver disponibilidad de asientos");
            System.out.println("2. Realizar venta de entradas");
            System.out.println("3. Lista y gestión de entradas vendidas");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next(); // Limpiar entrada no válida
            }

            opcion = scanner.nextInt();

            // Opciones del menú
            switch (opcion) {
                case 1:
                    ventaEntradas.verDisponibilidadAsientos();
                    break;
                case 2:
                    realizarVenta(scanner, ventaEntradas);
                    break;
                case 3:
                    listarYGestionarEntradas(scanner, ventaEntradas);
                    break;
                case 4:
                    System.out.println("Saliendo del sistema. ¡Gracias por usar el sistema de venta de entradas!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción entre 1 y 4.");
            }
        } while (opcion != 4);

        scanner.close();
    }

    /**
     * Método para realizar la venta de una entrada.
     * Solicita datos del cliente, calcula el precio con descuentos y asigna un asiento.
     *
     * @param scanner       Objeto Scanner para entrada de datos.
     * @param ventaEntradas Objeto VentaEntradas para gestionar la venta.
     */
    private static void realizarVenta(Scanner scanner, VentaEntradas ventaEntradas) {
        scanner.nextLine(); // Limpiar buffer
        System.out.println("\n--- Venta de entradas ---");
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        int edad;
        while (true) { // Validar edad
            System.out.print("Ingrese su edad: ");
            if (scanner.hasNextInt()) {
                edad = scanner.nextInt();
                break;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next();
            }
        }

        String tipoCliente = (edad < 18) ? "niño" : (edad > 60) ? "tercera edad" : "adulto";

        String sexo = "";
        while (true) { // Validar sexo
            System.out.println("Seleccione su sexo:");
            System.out.println("1. Hombre");
            System.out.println("2. Mujer");
            System.out.println("3. Otro");
            System.out.print("Ingrese el número correspondiente a su selección: ");

            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                if (opcion == 1) {
                    sexo = "Hombre";
                    break;
                } else if (opcion == 2) {
                    sexo = "Mujer";
                    break;
                } else if (opcion == 3) {
                    sexo = "Otro";
                    break;
                } else {
                    System.out.println("Opción inválida. Por favor, ingrese 1, 2 o 3.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next();
            }
        }

        boolean esEstudiante = false;
        while (true) { // Validar si es estudiante
            System.out.println("¿Es estudiante?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            System.out.print("Ingrese el número correspondiente a su selección: ");

            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                if (opcion == 1) {
                    esEstudiante = true;
                    break;
                } else if (opcion == 2) {
                    esEstudiante = false;
                    break;
                } else {
                    System.out.println("Opción inválida. Por favor, ingrese 1 o 2.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next();
            }
        }

        Cliente cliente = new Cliente(nombre, edad, tipoCliente, sexo, esEstudiante);

        String seccion = "";
        while (true) { // Selección de sección
            System.out.println("Seleccione la sección del asiento:");
            System.out.println("1. VIP ($30,000)");
            System.out.println("2. Platea baja ($20,000)");
            System.out.println("3. Platea alta ($15,000)");
            System.out.println("4. Galería ($10,000)");
            System.out.print("Ingrese el número correspondiente a su selección: ");
            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                if (opcion == 1) {
                    seccion = "VIP";
                    break;
                } else if (opcion == 2) {
                    seccion = "Platea baja";
                    break;
                } else if (opcion == 3) {
                    seccion = "Platea alta";
                    break;
                } else if (opcion == 4) {
                    seccion = "Galería";
                    break;
                } else {
                    System.out.println("Opción inválida. Por favor, seleccione un número entre 1 y 4.");
                }
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.next();
            }
        }

        // Calcular precio final con descuentos
        VentaEntradas.ResultadoDescuento resultado = ventaEntradas.calcularPrecioFinal(cliente, seccion);

        System.out.println("\nCliente creado exitosamente: " + cliente.getNombre());
        System.out.println("Categoría: " + tipoCliente);
        System.out.println("Sexo: " + sexo);
        System.out.println("Estudiante: " + (esEstudiante ? "Sí" : "No"));
        System.out.println("Porcentaje de descuento aplicado: " + resultado.getPorcentajeDescuento() + "%");
        System.out.println("Precio final: $" + resultado.getPrecioFinal());

        // Asignar asiento y generar boleta
        if (ventaEntradas.asignarAsiento(cliente, seccion)) {
            ventaEntradas.imprimirBoleta(cliente, resultado.getPrecioFinal(), seccion, resultado.getPorcentajeDescuento());
        } else {
            System.out.println("No se pudo asignar un asiento en la sección seleccionada.");
        }
    }

    /**
     * Método para listar entradas vendidas y gestionar eliminaciones.
     *
     * @param scanner       Objeto Scanner para entrada de datos.
     * @param ventaEntradas Objeto VentaEntradas para gestionar las entradas.
     */
    private static void listarYGestionarEntradas(Scanner scanner, VentaEntradas ventaEntradas) {
        System.out.println("\n----- Registro de Entradas Vendidas -----");
        if (ventaEntradas.getEntradasVendidas().isEmpty()) {
            System.out.println("No hay entradas vendidas.");
        } else {
            int index = 1;
            for (String entrada : ventaEntradas.getEntradasVendidas()) {
                System.out.println(index + ". " + entrada);
                index++;
            }

            System.out.println("\n¿Desea eliminar alguna entrada?");
            System.out.println("1. Sí");
            System.out.println("2. No");
            System.out.print("Ingrese su opción: ");

            if (scanner.hasNextInt() && scanner.nextInt() == 1) {
                System.out.print("Ingrese el número de la entrada que desea eliminar: ");
                if (scanner.hasNextInt()) {
                    int entradaAEliminar = scanner.nextInt();
                    if (entradaAEliminar > 0 && entradaAEliminar <= ventaEntradas.getEntradasVendidas().size()) {
                        String eliminada = ventaEntradas.eliminarEntrada(entradaAEliminar - 1);
                        System.out.println("Entrada eliminada exitosamente: " + eliminada);
                    } else {
                        System.out.println("Número de entrada inválido.");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    scanner.next();
                }
            }
        }
        System.out.println("-----------------------------------------");
    }
}