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
	private int [] costos;
	private int cantCiudades;
	
	/**
	 * Constructor de la clase Ruta
	 * @param nombreArchivo: el nombre del archivo que contiene todas
	 * las ciudades disponibles
	 * @param 
	 */
	public Ruta(String nombreArchivo,int numeroCiudades) {
		try {
			this.ciudades=crearLista(nombreArchivo,numeroCiudades);
			this.costoDeCaminos=crearMatrizCostos(numeroCiudades);
			this.costoTotal=0;
			this.ruta=new LinkedList<>();
			this.cantCiudades=this.ciudades.size();
			escribirMatriz();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		        str += "\t" + (w + 1);
		        //bw.write("\t" + Integer.toString(w + 1));
		      }
		      str += "\n";
		      //bw.write("\n");
		      
		      //guiones bajo los numeros
		      for (int w = 0; w < columns; w++) {
		        str += "\t-";
		        //bw.write("\t-");
		      }
		      
		      str += "\n";
		      //bw.write("\n");
		      
		      str += "1|\t";
		      //bw.write("1|\t");
		      for (int i = 0; i < rows; i++) {

		        for (int j = 0; j < columns; j++) {
		          str += m[i][j] + "\t";
		          //bw.write(Integer.toString(m[i][j]) + "\t");
		        }
		        //bw.write("|\n");
		        
		        
		        //System.out.println(str + "|");
		        bw.write(str+"\n");
		        str = (i + 2) + "|\t";
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
		
		this.ruta.addLast(startCity);
		if(this.ruta.size()<this.cantCiudades) {
			Ciudad nearestCity=obtenerCiudadHija(startCity);
			hillClimbing(nearestCity);
		}
		
	}
	
	private Ciudad obtenerCiudadHija(Ciudad ciudadActual) {
		int mejorCosto=9999;
		Ciudad mejorCiudad=null;
		
		for(int i=0;i<this.cantCiudades;i++) {
			if(!this.ruta.contains(this.ciudades.get(i))) {
				int coste_i_j=this.costoDeCaminos[ciudadActual.getId()][i];
				
				if(coste_i_j<mejorCosto) {
					mejorCosto=this.costoDeCaminos[ciudadActual.getId()][i];
					mejorCiudad=this.ciudades.get(i);
				}
				
			}
		}
		return mejorCiudad;
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
