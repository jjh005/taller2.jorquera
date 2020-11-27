
import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		Ruta cc=new Ruta("ciudades.txt",10);
		LinkedList<Ciudad> cities=cc.getCiudades();
		
		for(int i=0;i<cities.size();i++) {
			Ciudad city=cities.get(i);
			String cityName=city.getNombre();
			int id=city.getId();
			System.out.println(id+" "+cityName);
		}
	}
}

