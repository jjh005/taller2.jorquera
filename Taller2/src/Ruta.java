import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Ruta {
	private LinkedList<Ciudad> ruta;
	private LinkedList<Ciudad> ciudades;
	private int [][] costoDeCaminos;
	private int costoTotal;
	private int [] costos;
	
	/**
	 * 
	 */
	public Ruta(String nombreArchivo,int numeroCiudades) {
		try {
			this.ciudades=crearLista(nombreArchivo,numeroCiudades);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private LinkedList<Ciudad> crearLista(String nombreArchivo,int numeroCiudades) throws IOException {
		
		  File file = new File(nombreArchivo); 
		  BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		  LinkedList<String> listCiudades=new LinkedList<>();
		  
		  String st; 
		  while ((st = br.readLine()) != null) {
			  listCiudades.add(st);
			  
		  }
		
		  List<String> ciudadesRandom;
		  Collections.shuffle(listCiudades);
		  ciudadesRandom=listCiudades.subList(0, numeroCiudades);
		  LinkedList<Ciudad> cities=new LinkedList<>();
		  
		  for (int i=0;i<ciudadesRandom.size();i++) {
			  Ciudad city=new Ciudad(ciudadesRandom.get(i),i);
			  cities.add(city);
			  
		  }
		  
		  return cities;
	}


	/**
	 * @return the costoTotal
	 */
	public int getCostoTotal() {
		return costoTotal;
	}


	/**
	 * @param costoTotal the costoTotal to set
	 */
	public void setCostoTotal(int costoTotal) {
		this.costoTotal = costoTotal;
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
	public int[] getCostos() {
		return costos;
	}
	
	
	
}
