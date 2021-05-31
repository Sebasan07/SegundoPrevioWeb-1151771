package co.edu.ufps.web.model;

public class TipoDocumento {

	private Integer id;
	private String descripcion;
	
	public TipoDocumento() {
		
	}

	public TipoDocumento(Integer id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "TipoDocumento [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
}
