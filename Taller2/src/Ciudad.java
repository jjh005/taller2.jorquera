
public class Ciudad {
	private String nombre;
	private int id;
	
	/**
	 * @param nombre; nombre de la ciudad
	 * @param id; numero identificador de la ciudad
	 */
	
	public Ciudad(String nombre, int id) {
		super();
		this.nombre = nombre;
		this.id = id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
}
