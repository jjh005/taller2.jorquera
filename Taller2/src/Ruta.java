import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Ruta {
	private LinkedList<Ciudad> ruta;
	private LinkedList<Ciudad> ciudades;
	private int [][] costoDeCaminos;
	private int costoTotal;
	private LinkedList<Integer> costos;
	private int cantCiudades;
	private String stringRuta;
	private LinkedList<Integer> tiempoXIteracion;
	
	/**
	 * Constructor de la clase Ruta
	 * @param nombreArchivo: el nombre del archivo que contiene todas
	 * las ciudades disponibles
	 * @param 
	 */
	
	public Ruta(String nombreArchivo,int numeroCiudades) {
		try {
			this.stringRuta="";
			this.ciudades=crearLista(nombreArchivo,numeroCiudades);
			this.costoDeCaminos=crearMatrizCostos(numeroCiudades);
			this.costoTotal=0;
			this.ruta=new LinkedList<>();
			this.cantCiudades=this.ciudades.size();
			this.costos=new LinkedList<>();
			this.tiempoXIteracion=new LinkedList<>();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int[][] crearMatrizCostos(int n) {
		int [][] matrizCosto=new int[n][n];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(i==j)
					matrizCosto[i][j]=0;
				else if(matrizCosto[i][j]==0)
					matrizCosto[i][j]=matrizCosto[j][i]=getNumeroAleatorio(1,100);
			}
		}
		return matrizCosto;
	}
	
	private int getNumeroAleatorio(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	public void escribirRuta() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			System.out.print("\nGenerando Archivo 'ruta.txt'");
		    String ruta = "ruta.txt";
		    	
		    fw=new FileWriter(ruta);
		    bw=new BufferedWriter(fw);
		     bw.write(this.stringRuta);
		     System.out.println("................. Listo!\n");
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
	
	public void escribirMatriz() {
		int [][] m=this.costoDeCaminos;
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			System.out.print("\nGenerando Archivo 'matriz_de_costos.txt'");
		    String ruta = "matriz_de_costos.txt";
		    	
		    fw=new FileWriter(ruta);
		    bw=new BufferedWriter(fw);
		        
	            
		    int rows = m.length;
		    int columns = m[0].length;
		    String str = "";
		    
		    for (int w = 0; w < columns; w++) {
		    	str += "\t" + (w);
		    	}
		    str += "\n";
		      
		    //guiones bajo los numeros
		    for (int w = 0; w < columns; w++) {
		    	str += "\t-";
		    	}
		      
		    str += "\n";
		      
		    str += "0|\t";
		    for (int i = 0; i < rows; i++) {
		    	for (int j = 0; j < columns; j++) {
		    		str += m[i][j] + "\t";
		    		}
		    	bw.write(str+"\n");
		        str = (i+1) + "|\t";
		        }
		    System.out.print("..... Listo!\n");
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
	
	private LinkedList<Ciudad> crearLista(String nombreArchivo,int numeroCiudades) throws IOException {
		
		  File file = new File(nombreArchivo); 
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  LinkedList<String> listCiudades=new LinkedList<>();//todas las ciudades disponibles
		  
		  String st;
		  while ((st = br.readLine()) != null) {//agregar nombre de ciudades
			  listCiudades.add(st);  
			  }
		  
		  List<String> ciudadesRandom;
		  Collections.shuffle(listCiudades);//aleatorizar lista 
		  ciudadesRandom=listCiudades.subList(0, numeroCiudades);//escoger n ciudades de entre todas las ciudades
		  
		  LinkedList<Ciudad> cities=new LinkedList<>();
		  
		  //este for agrega objetos Ciudades a la lista de n ciudades
		  for (int i=0;i<ciudadesRandom.size();i++) {
			  Ciudad city=new Ciudad(ciudadesRandom.get(i),i);
			  cities.add(city);  
		  }
		  br.close();
		  return cities;
	}

	public void hillClimbing(Ciudad startCity) {
		long tiempo;	
		long startTime = System.nanoTime();
		this.ruta.addLast(startCity);
		
		if(this.ruta.size()<this.cantCiudades) {
			Ciudad nearestCity=obtenerCiudadHija(startCity);
			
			hillClimbing(nearestCity);
			
		}
		long endTime = System.nanoTime();
	    tiempo=(endTime-startTime);
	    
	    int tiempoInt=(int)(tiempo);
	    this.tiempoXIteracion.addFirst(tiempoInt);
	}
	
	private Ciudad obtenerCiudadHija(Ciudad ciudadActual) {
		int mejorCosto=9999;
		Ciudad mejorCiudad=null;
		
		for(int i=0;i<this.cantCiudades;i++) {
			if(!this.ruta.contains(this.ciudades.get(i))) {
				int coste_i_j=this.costoDeCaminos[ciudadActual.getId()][i];
				
				if(coste_i_j<mejorCosto) {
					mejorCosto=coste_i_j;
					mejorCiudad=this.ciudades.get(i);
				}
			}
		}
		
		this.costos.addLast(mejorCosto);
		this.costoTotal+=mejorCosto;
		return mejorCiudad;
	}

	public String generarStringRuta() {
		
		int n=this.costos.size();
		for(int i=0;i<this.cantCiudades;i++) {
			Ciudad ci=this.ruta.get(i);
			this.stringRuta+="["+ci.getNombre()+" : "+ Integer.toString(ci.getId())+"]";
			if(n==i) {
				//se llego al limite de lista Costos
				//camino+="["+ci.getNombre()+" : "+Integer.toString(ci.getId())+"]";
				
			}else {
				this.stringRuta+=" --> "+Integer.toString(this.costos.get(i))+" --> ";
			}
		}
		
		return this.stringRuta;
	}
	
	/**
	 * @return the costoTotal
	 */
	public int getCostoTotal() {
		return costoTotal;
	}

	/**
	 * @return the ruta
	 */
	public LinkedList<Ciudad> getRuta() {
		return ruta;
	}


	/**
	 * @return the ciudades
	 */
	public LinkedList<Ciudad> getCiudades() {
		return ciudades;
	}


	/**
	 * @return the costoDeCaminos
	 */
	public int[][] getCostoDeCaminos() {
		return costoDeCaminos;
	}

	/**
	 * @return the costos
	 */
	public LinkedList<Integer> getCostos() {
		return costos;
	}


	/**
	 * @return the stringRuta
	 */
	public String getStringRuta() {
		return stringRuta;
	}

	/**
	 * @return the tiempoXIteracion
	 */
	public LinkedList<Integer> getTiempoXIteracion() {
		return tiempoXIteracion;
	}
	
	
}


