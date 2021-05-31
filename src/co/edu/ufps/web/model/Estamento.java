package co.edu.ufps.web.model;

public class Estamento {

	private Integer id;
	private Integer eleccion;
	private String descripcion;
	
	public Estamento() {
		
	}

	public Estamento(Integer id, Integer eleccion, String descripcion) {
		super();
		this.id = id;
		this.eleccion = eleccion;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEleccion() {
		return eleccion;
	}

	public void setEleccion(Integer eleccion) {
		this.eleccion = eleccion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Estamento [id=" + id + ", eleccion=" + eleccion + ", descripcion=" + descripcion + "]";
	}
	
}
