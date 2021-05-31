package co.edu.ufps.web.model;

public class Votante {

	private Integer id;
	private String nombre;
	private String email;
	private String documento;
	private Integer tipoDocumento;
	private Integer eleccion;
	
	public Votante() {
		
	}
	
	public Votante(Integer id, String nombre, String email, String documento, Integer tipoDocumento, Integer eleccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.documento = documento;
		this.tipoDocumento = tipoDocumento;
		this.eleccion = eleccion;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public Integer getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(Integer tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public Integer getEleccion() {
		return eleccion;
	}
	public void setEleccion(Integer eleccion) {
		this.eleccion = eleccion;
	}
	
	@Override
	public String toString() {
		return "Votante [id=" + id + ", nombre=" + nombre + ", email=" + email + ", documento=" + documento
				+ ", tipoDocumento=" + tipoDocumento + ", eleccion=" + eleccion + "]";
	}

}
