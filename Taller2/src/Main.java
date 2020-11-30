
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {


	public static void main(String[] args) {
		
		//lineas de codigo que pida por consola el numero n de ciudades
		Scanner reader = new Scanner(System.in);
		System.out.println("Ingresa cantidad de ciudades: ");
		int n = reader.nextInt();
		
		Ruta cc=new Ruta("ciudades.txt",n);
		LinkedList<Ciudad> cities=cc.getCiudades();//son las ciudades que se usaran para crear la ruta
		
		
		//escoger una ciudad al azar de la lista "cities"
		
		Ciudad randCity=cities.get(getNumeroAleatorio(0,n-1));
		
		System.out.println("\nCiudad de Inicio Escogida: "+randCity.getNombre()+", id: "+randCity.getId());
		
		
		
		Date date = new Date();
	    //obtener la hora y salida por pantalla con formato, para mostrar el tiempo de ejecucion de Hill-Climbing
	    DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
	    System.out.println("Timpo de inicio Hill-Climbing: "+hourFormat.format(date));
	    
		
		
	    long tiempo;
		
	    long startTime = System.nanoTime();
	    
	    cc.hillClimbing(randCity);//***Aqui se ejecuta Hill-Climbing
	    
	    long endTime = System.nanoTime();
	    tiempo=(endTime-startTime);
	    //cc.getTiempoXIteracion().addFirst(Math.toIntExact(tiempo));
	    
		System.out.println("\nLA RUTA ES:");
		System.out.println(cc.generarStringRuta());
		System.out.println("COSTO TOTAL DE LA RUTA: "+cc.getCostoTotal()+"\n");
		
		System.out.println("Tiempo de finalizacion Hill-Climbing: "+hourFormat.format(date));
		if(tiempo<1000000000)
			System.out.printf("Tiempo de Ejecucion Hill-climbing: "+ tiempo+" NanoSegundos\n");
		else
			System.out.printf("Tiempo de Ejecucion Hill-climbing: "+ tiempo/1000000000.0+" Segundos\n");
		cc.escribirMatriz();
		cc.escribirRuta();
		
		
		/*
		 * LinkedList<Integer> tiempos=cc.getTiempoXIteracion(); for(int
		 * i=0;i<tiempos.size();i++) {
		 * 
		 * System.out.println(i+" "+tiempos.get(i)); }
		 */
		 
		
	}
	
	public static int getNumeroAleatorio(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	
}

