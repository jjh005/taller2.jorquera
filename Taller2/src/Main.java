
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {


	public static void main(String[] args) {
		//medir(350);
		
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
	    System.out.println("Tiempo de inicio Hill-Climbing: "+hourFormat.format(date));
	    
		
		
	    long tiempo;
		
	    long startTime = System.nanoTime();
	    
	    cc.hillClimbing(randCity);//***Aqui se ejecuta Hill-Climbing
	    
	    long endTime = System.nanoTime();
	    tiempo=(endTime-startTime);
	    date = new Date();
	    
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
		tiempoXIter(cc.getTiempoXIteracion());
		
		
		
		/*
		 * 
		 * LinkedList<Integer> tiempos=cc.getTiempoXIteracion(); 
		 * for(int i=0;i<tiempos.size();i++) {
		 * 
		 * System.out.println(i+" "+tiempos.get(i)); 
		 * }
		 */
		 
		
	}
	
	public static void medir(int numeroPruebas) {
		int npruebas=numeroPruebas+3;
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
		    String ruta = "pruebas.txt";
		    	
		    fw=new FileWriter(ruta);
		    bw=new BufferedWriter(fw);
		    System.out.println("Ejecutando...");
		    int porcentajeAnt=1;
		    int i=3;
		    
		    
		    for(i=3;i<npruebas;i++) {
		    	int x=(i*100)/npruebas;
		    	if(porcentajeAnt!=x) {
		    		System.out.println(x+"%");
		    	}
		    	porcentajeAnt=x;
		    	
				Ruta cc=new Ruta("ciudades.txt",i);
				LinkedList<Ciudad> cities=cc.getCiudades();
				Ciudad randCity=cities.get(getNumeroAleatorio(0,i-1));
				long tiempo;
				
			    long startTime = System.nanoTime();
			    
			    cc.hillClimbing(randCity);//***Aqui se ejecuta Hill-Climbing
			    
			    long endTime = System.nanoTime();
			    tiempo=(endTime-startTime);
			    bw.write(tiempo+"\n");
			}
		    System.out.println(porcentajeAnt+1+"%");
		    
		     } catch (Exception e) {
		    	 System.out.println("Matrix is empty!!");
		    	 }finally{
		    		 try {
		    			 if (bw != null)
		                 bw.close();
		    			 if (fw != null)
		                 fw.close();
		    			 }catch (IOException ex){
		    				 ex.printStackTrace();
		    				 }
		    		 }
		}
		
		
	
	public static int getNumeroAleatorio(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	public static void tiempoXIter(LinkedList<Integer> tiempos) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
		    String ruta = "tiemposxIter.txt";
		    	
		    fw=new FileWriter(ruta);
		    bw=new BufferedWriter(fw);
		    
		    for(int i=0;i<tiempos.size();i++) {
		    	bw.write(tiempos.get(i)+"\n");
		    	
		    }
		     } catch (Exception e) {
		    	 System.out.println("Matrix is empty!!");
		    	 }finally{
		    		 try {
		    			 if (bw != null)
		                 bw.close();
		    			 if (fw != null)
		                 fw.close();
		    			 }catch (IOException ex){
		    				 ex.printStackTrace();
		    				 }
		    		 }
		}
	}
	
	


