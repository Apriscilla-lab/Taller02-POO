import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class GestorFonda {

	private Participante[] participantes;
    private Choripan[] choripanes;
    private Terremoto[] terremotos;
    private int cantidadParticipantes;
    private int cantidadChoripanes;
    private int cantidadTerremotos;
    
    public GestorFonda() {                        //para los arreglos y los contadores 
        participantes = new Participante[200];
        choripanes = new Choripan[200];
        terremotos = new Terremoto[200];
        cantidadParticipantes = 0;
        cantidadChoripanes = 0;
        cantidadTerremotos = 0;
    }
    // lee el archivo de participante y los guarda en el arreglo
    public void cargarDatos() {
        cantidadParticipantes = 0;
        cantidadChoripanes = 0;
        cantidadTerremotos = 0;
        
        try {
            File archivo = new File("Participantes.txt");
            Scanner lector = new Scanner(archivo);

            while (lector.hasNextLine()) {
                String nombre = lector.nextLine();

                if (!nombre.equals("")) {
                    if (cantidadParticipantes < participantes.length) {
                        participantes[cantidadParticipantes] =
                                new Participante(nombre);

                        cantidadParticipantes++;
                    } else {
                        System.out.println("Se alcanzo el limite de participantes.");
                        break;
                    }
                }
            }

            lector.close();

        } catch (IOException e) {
            System.out.println("No se pudo abrir Participantes.txt.");
        }
        try {
            File archivo = new File("Consumido.txt");
            Scanner lector = new Scanner(archivo);

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();

                if (!linea.equals("")) {
                	String[] datos = linea.split(";");

                	if (cantidadChoripanes + cantidadTerremotos >= 200) {
                	    System.out.println("Se alcanzo el limite de consumos.");
                	    break;
                	}

                	if (datos.length == 5 && datos[0].equals("Choripan")) {
                        try {
                            double largo = Double.valueOf(datos[2]);
                            double ancho = Double.valueOf(datos[3]);

                            choripanes[cantidadChoripanes] =
                                    new Choripan(datos[1], datos[4], largo, ancho);

                            cantidadChoripanes++;

                        } catch (Exception e) {
                            System.out.println("No se pudo cargar este registro: " + linea);
                        }
                    }
                	if (datos.length == 4 && datos[0].equals("Terremoto")) {
                        try {
                            double litros = Double.valueOf(datos[2]);

                            terremotos[cantidadTerremotos] =
                                    new Terremoto(datos[1], datos[3], litros);

                            cantidadTerremotos++;

                        } catch (Exception e) {
                            System.out.println("No se pudo cargar este registro: " + linea);
                        }
                    }
                }
            }

            lector.close();

        } catch (IOException e) {
            System.out.println("No se pudo abrir Consumido.txt.");
        }    
    }
    //para devolver el texto en un lista 
    public String listarParticipantes() {
        String resultado = "";

        for (int i = 0; i < cantidadParticipantes; i++) {
            resultado = resultado + (i + 1) + ". "
                    + participantes[i].getNombre() + "\n";
        }

        return resultado;
    }
    
    public String listarChoripanes() { //lista de choripanes guardados
        String resultado = "";

        for (int i = 0; i < cantidadChoripanes; i++) {
            resultado = resultado + (i + 1) + ". "
                    + choripanes[i].getConsumidor()
                    + " | Fecha: " + choripanes[i].getFecha()
                    + " | Largo: " + choripanes[i].getLargo()
                    + " | Ancho: " + choripanes[i].getAncho() + "\n";
        }

        return resultado;
    }
    public String listarTerremotos() {    //lista de terremotos guardados
        String resultado = "";

        for (int i = 0; i < cantidadTerremotos; i++) {
            resultado = resultado + (i + 1) + ". "
                    + terremotos[i].getConsumidor()
                    + " | Fecha: " + terremotos[i].getFecha()
                    + " | Litros: " + terremotos[i].getLitros() + "\n";
        }

        return resultado;
    }
    
    public boolean agregarChoripan(Choripan registro) {
        if (registro == null ||
                cantidadChoripanes + cantidadTerremotos >= 200) {
            return false;
        }

        choripanes[cantidadChoripanes] = registro;
        cantidadChoripanes++;

        return true;
    }
    public boolean agregarTerremoto(Terremoto registro) {
        if (registro == null ||
                cantidadChoripanes + cantidadTerremotos >= 200) {
            return false;
        }

        terremotos[cantidadTerremotos] = registro;
        cantidadTerremotos++;

        return true;
    }
    
    public boolean editarChoripan(int indice, Choripan registro) {
        if (indice < 0 || indice >= cantidadChoripanes ||
                registro == null) {
            return false;
        }

        choripanes[indice] = registro;
        return true;
    }
    
    public boolean editarTerremoto(int indice, Terremoto registro) {
        if (indice < 0 || indice >= cantidadTerremotos ||
                registro == null) {
            return false;
        }

        terremotos[indice] = registro;
        return true;
    }
    
    public boolean eliminarChoripan(int indice) {
        if (indice < 0 || indice >= cantidadChoripanes) {
            return false;
        }

        for (int i = indice; i < cantidadChoripanes - 1; i++) {
            choripanes[i] = choripanes[i + 1];
        }

        cantidadChoripanes--;
        choripanes[cantidadChoripanes] = null;

        return true;
    }
    
    public boolean eliminarTerremoto(int indice) {
        if (indice < 0 || indice >= cantidadTerremotos) {
            return false;
        }

        for (int i = indice; i < cantidadTerremotos - 1; i++) {
            terremotos[i] = terremotos[i + 1];
        }

        cantidadTerremotos--;
        terremotos[cantidadTerremotos] = null;

        return true;
    }
    
    public String generarReporteMetrica(int opcion) {         //devuelve el reporte de la metrica seleccionada 
        if (opcion == 1) {
            return "Total de choripanes: " + cantidadChoripanes;
        }

        if (opcion == 2) {
            double litrosTotales = 0;

            for (int i = 0; i < cantidadTerremotos; i++) {
                litrosTotales = litrosTotales
                        + terremotos[i].getLitros();
            }

            return "Total de terremotos: " + cantidadTerremotos
                    + " | Litros totales: " + litrosTotales;
        }

        if (opcion == 3) {
            String resultado = "Choripanes por usuario:\n";

            for (int i = 0; i < cantidadParticipantes; i++) {
                String nombre = participantes[i].getNombre();
                int cantidad = 0;

                for (int j = 0; j < cantidadChoripanes; j++) {
                    if (choripanes[j].getConsumidor().equals(nombre)) {
                        cantidad++;
                    }
                }

                resultado = resultado + nombre + ": " + cantidad + "\n";
            }

            return resultado;
        }
        if (opcion == 4) {
            String resultado = "Terremotos por usuario:\n";

            for (int i = 0; i < cantidadParticipantes; i++) {
                String nombre = participantes[i].getNombre();
                int cantidad = 0;
                double litros = 0;

                for (int j = 0; j < cantidadTerremotos; j++) {
                    if (terremotos[j].getConsumidor().equals(nombre)) {
                        cantidad++;
                        litros = litros + terremotos[j].getLitros();
                    }
                }

                resultado = resultado + nombre + ": " + cantidad
                        + " | Litros: " + litros + "\n";
            }

            return resultado;
        }
        if (opcion == 5) {
            String resultado = "KCal por usuario:\n";

            for (int i = 0; i < cantidadParticipantes; i++) {
                String nombre = participantes[i].getNombre();
                double kcalChoripanes = 0;
                double kcalTerremotos = 0;

                for (int j = 0; j < cantidadChoripanes; j++) {
                    if (choripanes[j].getConsumidor().equals(nombre)) {
                        kcalChoripanes = kcalChoripanes
                                + choripanes[j].calcularKcal();
                    }
                }

                for (int j = 0; j < cantidadTerremotos; j++) {
                    if (terremotos[j].getConsumidor().equals(nombre)) {
                        kcalTerremotos = kcalTerremotos
                                + terremotos[j].calcularKcal();
                    }
                }

                double total = kcalChoripanes + kcalTerremotos;

                resultado = resultado + nombre
                        + " | Choripanes: " + kcalChoripanes
                        + " | Terremotos: " + kcalTerremotos
                        + " | Total: " + total + "\n";
            }

            return resultado;
        }
        if (opcion == 6) {
            String resultado = "Dias ebrio por usuario:\n";

            for (int i = 0; i < cantidadParticipantes; i++) {
                String nombre = participantes[i].getNombre();
                int dias = 0;

                for (int j = 0; j < cantidadTerremotos; j++) {
                    if (terremotos[j].getConsumidor().equals(nombre)) {
                        String fecha = terremotos[j].getFecha();
                        boolean fechaRevisada = false;

                        for (int k = 0; k < j; k++) {
                            if (terremotos[k].getConsumidor().equals(nombre)
                                    && terremotos[k].getFecha().equals(fecha)) {
                                fechaRevisada = true;
                            }
                        }

                        if (!fechaRevisada
                                && sumarLitrosDelDia(nombre, fecha) >= 2) {
                            dias++;
                        }
                    }
                }

                resultado = resultado + nombre + ": " + dias + "\n";
            }

            return resultado;
        }
        return "Opcion de metrica invalida.";
    }
    private double sumarLitrosDelDia(String nombre, String fecha) {
        double total = 0;

        for (int i = 0; i < cantidadTerremotos; i++) {
            if (terremotos[i].getConsumidor().equals(nombre)
                    && terremotos[i].getFecha().equals(fecha)) {
                total = total + terremotos[i].getLitros();
            }
        }

        return total;
    }
    public boolean guardarDatos() {
        String contenido = "";

        for (int i = 0; i < cantidadChoripanes; i++) {
            contenido = contenido + "Choripan;"
                    + choripanes[i].getConsumidor() + ";"
                    + choripanes[i].getLargo() + ";"
                    + choripanes[i].getAncho() + ";"
                    + choripanes[i].getFecha() + "\n";
        }

        for (int i = 0; i < cantidadTerremotos; i++) {
            contenido = contenido + "Terremoto;"
                    + terremotos[i].getConsumidor() + ";"
                    + terremotos[i].getLitros() + ";"
                    + terremotos[i].getFecha() + "\n";
        }

        try {
            FileWriter escritor = new FileWriter("Consumido.txt");

            try {
                escritor.write(contenido);
            } finally {
                escritor.close();
            }

            return true;

        } catch (IOException e) {
            System.out.println("No se pudo guardar Consumido.txt.");
            return false;
        }
    }
    public String obtenerNombreParticipante(int indice) {
        if (indice < 0 || indice >= cantidadParticipantes) {
            return null;
        }

        return participantes[indice].getNombre();
    }
    
}
