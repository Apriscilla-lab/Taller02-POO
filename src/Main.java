// Ma. Sofía Escalona -  26.965.672-7 - ITI
// Priscilla Álvarez Ugarte - 20.988.062-8 - ITI

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GestorFonda gestor = new GestorFonda();
        gestor.cargarDatos();

        Scanner teclado = new Scanner(System.in);
        int opcion = 0;

        while (opcion != 4 && opcion != -1) {
            System.out.println("\nUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUW");
            System.out.println("           LA FONDA DEL PAPU");
            System.out.println("UWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUW");
            System.out.println("\n    1. Ver registros");
            System.out.println("    2. Actualizar registros");
            System.out.println("    3. Calcular metricas");
            System.out.println("    4. Salir");
            System.out.println("\nUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUW");
            System.out.print("Seleccione una opcion: ");

            opcion = leerOpcion(teclado, 1, 4);

            if (opcion == 1) {
                verRegistros(teclado, gestor);
            }

            if (opcion == 2) {
                actualizarRegistros(teclado, gestor);
            }

            if (opcion == 3) {
                verMetricas(teclado, gestor);
            }
        }

        System.out.println("Hasta luego.");
        teclado.close();
    }

    private static int leerOpcion(Scanner teclado, int minimo, int maximo) {
        while (teclado.hasNextLine()) {
            try {
                int opcion = Integer.valueOf(teclado.nextLine());

                if (opcion >= minimo && opcion <= maximo) {
                    return opcion;
                }

                System.out.println("Elige un numero entre "
                        + minimo + " y " + maximo + ".");

            } catch (Exception e) {
                System.out.println("Debes escribir un numero entero.");
            }
        }

        return -1;
    }

    private static void verRegistros(Scanner teclado, GestorFonda gestor) {
        int opcion = 0;

        while (opcion != 3 && opcion != -1) {
            System.out.println("\nVER REGISTROS");
            System.out.println("1. Choripanes");
            System.out.println("2. Terremotos");
            System.out.println("3. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            opcion = leerOpcion(teclado, 1, 3);

            if (opcion == 1) {
                System.out.println(gestor.listarChoripanes());
            }

            if (opcion == 2) {
                System.out.println(gestor.listarTerremotos());
            }
        }
    }

    private static void verMetricas(Scanner teclado, GestorFonda gestor) {
        int opcion = 0;

        while (opcion != 7 && opcion != -1) {
            System.out.println("\nUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUW");
            System.out.println("           CALCULAR METRICAS");
            System.out.println("UWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUW");
            System.out.println("\n    1. Choripanes en total");
            System.out.println("    2. Terremotos en total");
            System.out.println("    3. Choripanes por usuario");
            System.out.println("    4. Terremotos por usuario");
            System.out.println("    5. KCal por usuario");
            System.out.println("    6. Dias ebrio por usuario");
            System.out.println("    7. Volver al menu principal");
            System.out.println("\nUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUW");
            System.out.print("Seleccione una opcion: ");

            opcion = leerOpcion(teclado, 1, 7);

            if (opcion >= 1 && opcion <= 6) {
                System.out.println(gestor.generarReporteMetrica(opcion));
            }
        }
    }

    private static double leerPositivo(Scanner teclado, String mensaje) {
        while (true) {
            System.out.print(mensaje);

            if (!teclado.hasNextLine()) {
                return -1;
            }

            try {
                double valor = Double.valueOf(teclado.nextLine());

                if (valor >= 0 && valor - valor == 0) {
                    return valor;
                }

                System.out.println("Escribe un valor mayor que 0, o 0 para cancelar.");

            } catch (Exception e) {
                System.out.println("Escribe un numero; usa punto para los decimales.");
            }
        }
    }

    private static String elegirConsumidor(Scanner teclado, GestorFonda gestor) {
        System.out.println("\nPARTICIPANTES");
        System.out.println(gestor.listarParticipantes());

        while (true) {
            System.out.print("Seleccione un participante (0 para cancelar): ");
            int numero = leerOpcion(teclado, 0, 200);

            if (numero == 0 || numero == -1) {
                return null;
            }

            String nombre = gestor.obtenerNombreParticipante(numero - 1);

            if (nombre != null) {
                return nombre;
            }

            System.out.println("No existe un participante con ese numero.");
        }
    }

    private static boolean fechaValida(String fecha) {
        try {
            String[] caracteres = fecha.split("");

            if (caracteres.length != 10) {
                return false;
            }

            if (!caracteres[4].equals("-") || !caracteres[7].equals("-")) {
                return false;
            }

            for (int i = 0; i < caracteres.length; i++) {
                if (i != 4 && i != 7) {
                    Integer.valueOf(caracteres[i]);
                }
            }

            String[] partes = fecha.split("-");
            int anio = Integer.valueOf(partes[0]);
            int mes = Integer.valueOf(partes[1]);
            int dia = Integer.valueOf(partes[2]);

            int diasDelMes = 31;

            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                diasDelMes = 30;
            }

            if (mes == 2) {
                diasDelMes = 28;

                if ((anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0) {
                    diasDelMes = 29;
                }
            }

            return anio > 0 && mes >= 1 && mes <= 12
                    && dia >= 1 && dia <= diasDelMes;

        } catch (Exception e) {
            return false;
        }
    }

    private static void encabezado(String titulo) {
        System.out.println("\nUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUW");
        System.out.println("           " + titulo);
        System.out.println("UWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUW\n");
    }

    private static void pedirOpcion() {
        System.out.println("\nUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUWUW");
        System.out.print("Seleccione una opcion: ");
    }

    private static void actualizarRegistros(Scanner teclado, GestorFonda gestor) {
        int opcion = 0;

        while (opcion != 4 && opcion != -1) {
            encabezado("ACTUALIZAR REGISTROS");
            System.out.println("    1. Agregar registro");
            System.out.println("    2. Editar registro");
            System.out.println("    3. Eliminar registro");
            System.out.println("    4. Volver al menu principal");
            pedirOpcion();

            opcion = leerOpcion(teclado, 1, 4);

            if (opcion >= 1 && opcion <= 3) {
                modificarRegistro(teclado, gestor, opcion);
            }
        }
    }

    private static void modificarRegistro(Scanner teclado, GestorFonda gestor, int operacion) {
        encabezado("TIPO DE REGISTRO");
        System.out.println("    1. Choripan");
        System.out.println("    2. Terremoto");
        System.out.println("    3. Cancelar");
        pedirOpcion();

        int tipo = leerOpcion(teclado, 1, 3);

        if (tipo == 3 || tipo == -1) {
            return;
        }

        int indice = -1;

        if (operacion == 2 || operacion == 3) {
            String listado;

            if (tipo == 1) {
                listado = gestor.listarChoripanes();
            } else {
                listado = gestor.listarTerremotos();
            }

            indice = elegirRegistro(teclado, listado);

            if (indice < 0) {
                return;
            }
        }

        boolean cambio = false;

        if (operacion == 3) {
            System.out.println("Eliminar el registro " + (indice + 1) + "?");
            System.out.println("1. Si\n2. No");

            if (leerOpcion(teclado, 1, 2) != 1) {
                System.out.println("Eliminacion cancelada.");
                return;
            }

            if (tipo == 1) {
                cambio = gestor.eliminarChoripan(indice);
            } else {
                cambio = gestor.eliminarTerremoto(indice);
            }

        } else {
            if (operacion == 2) {
                System.out.println("Ingrese los nuevos datos del registro seleccionado.");
            }

            if (gestor.obtenerNombreParticipante(0) == null) {
                System.out.println("No hay participantes cargados. Revise Participantes.txt.");
                return;
            }

            String consumidor = elegirConsumidor(teclado, gestor);

            if (consumidor == null) {
                return;
            }

            String fecha = leerFecha(teclado);

            if (fecha == null) {
                return;
            }

            if (tipo == 1) {
                double largo = leerPositivo(teclado, "Largo (0 para cancelar): ");

                if (largo <= 0) {
                    return;
                }

                double ancho = leerPositivo(teclado, "Ancho (0 para cancelar): ");

                if (ancho <= 0) {
                    return;
                }

                Choripan registro = new Choripan(consumidor, fecha, largo, ancho);

                if (operacion == 1) {
                    cambio = gestor.agregarChoripan(registro);
                } else {
                    cambio = gestor.editarChoripan(indice, registro);
                }

            } else {
                double litros = leerPositivo(teclado, "Litros (0 para cancelar): ");

                if (litros <= 0) {
                    return;
                }

                Terremoto registro = new Terremoto(consumidor, fecha, litros);

                if (operacion == 1) {
                    cambio = gestor.agregarTerremoto(registro);
                } else {
                    cambio = gestor.editarTerremoto(indice, registro);
                }
            }
        }

        if (cambio) {
            guardarCambio(teclado, gestor);
        } else {
            System.out.println("No se pudo realizar el cambio. Revise el limite de 200 consumos.");
        }
    }

    private static int elegirRegistro(Scanner teclado, String listado) {
        if (listado.equals("")) {
            System.out.println("No hay registros de este tipo.");
            return -1;
        }

        System.out.println(listado);
        String[] filas = listado.split("\n");

        System.out.print("Numero del registro (0 para cancelar): ");
        int numero = leerOpcion(teclado, 0, filas.length);

        if (numero <= 0) {
            return -1;
        }

        return numero - 1;
    }

    private static void guardarCambio(Scanner teclado, GestorFonda gestor) {
        while (!gestor.guardarDatos()) {
            System.out.println("El cambio esta en memoria, pero aun no se guardo en el archivo.");
            System.out.println("Reintentar guardado? 1. Si  2. No");

            if (leerOpcion(teclado, 1, 2) != 1) {
                System.out.println("El cambio se perdera al cerrar si no logra guardar despues.");
                return;
            }
        }

        System.out.println("Cambio realizado y guardado en Consumido.txt.");
    }

    private static String leerFecha(Scanner teclado) {
        while (true) {
            System.out.print("Fecha AAAA-MM-DD (0 para cancelar): ");

            if (!teclado.hasNextLine()) {
                return null;
            }

            String fecha = teclado.nextLine();

            if (fecha.equals("0")) {
                return null;
            }

            if (fechaValida(fecha)) {
                return fecha;
            }

            System.out.println("Fecha invalida. Ejemplo: 2026-09-16.");
        }
    }
}