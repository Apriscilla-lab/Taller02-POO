
public class Choripan {

	private String consumidor;
	private String fecha;
	private double largo;
	private double ancho;
	
	public Choripan(String consumidorInicial, String fechaInicial, double largoInicial, double anchoInicial) {
		consumidor = consumidorInicial;
		fecha = fechaInicial;
		largo = largoInicial;
		ancho = anchoInicial;
	}
	
	public String getConsumidor() {
		return consumidor;
	}
	
	public String getFecha() {
		return fecha;
	}
	public double getLargo() {
		return largo;
	}
	public double getAncho() {
		return ancho;
	}	
	
	public double calcularKcal() {
		return (largo * ancho) / 3.0; //calculamos las calorias usando las medidas del choripan
	}
	
}
