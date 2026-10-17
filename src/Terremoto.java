
public class Terremoto {
	private String consumidor;
    private String fecha;
    private double litros;
    
    public Terremoto(String consumidorInicial, String fechaInicial,
    	double litrosInicial) {

    	consumidor = consumidorInicial;
    	fecha = fechaInicial;
    	litros = litrosInicial;
    }
    
    public String getConsumidor() {
    	return consumidor;
    }
    public String getFecha() {
    	return fecha;
    }
    public double getLitros() {
    	return litros;
    }
    
    public double calcularKcal() {     //calcula las calorias usando los litros
    	return (litros * 10) / 3.0;
    } 
}
    
