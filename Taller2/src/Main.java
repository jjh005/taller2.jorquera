
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		
		//lienas de codigo que pida por consola el numero n de ciudades
		
		int n=3;
		Ruta cc=new Ruta("ciudades.txt",n);
		LinkedList<Ciudad> cities=cc.getCiudades();//son las ciudades que se usaran para crear la ruta
		
		
		//escoger una ciudad al azar de la lista "cities"
		
		Ciudad randCity=cities.get(getNumeroAleatorio(0,n-1));
		
		System.out.println("\nCiudad de Inicio Escogida: "+randCity.getNombre()+" id: "+randCity.getId());
		
		cc.hillClimbing(randCity);
		LinkedList<Ciudad> rutaf=cc.getRuta();
		
		System.out.println(" dim ruta: "+cc.getRuta().size());
	
		System.out.println("La ruta es: ");
		for(int i=0;i<rutaf.size();i++) {
			Ciudad c=rutaf.get(i);
			System.out.println(c.getId());
		}
	}
	
	public static int getNumeroAleatorio(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	
}

