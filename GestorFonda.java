import java.io.File;
import java.io.IOException;
import java.util.Scanner;

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
}
