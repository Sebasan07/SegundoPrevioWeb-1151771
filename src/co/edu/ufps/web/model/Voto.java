package co.edu.ufps.web.model;

import java.sql.Timestamp;

public class Voto {

	private Integer id;
	private Timestamp fechaCreacion;
	private Timestamp fechaVoto;
	private String uuid;
	private String enlace;
	private Integer estamento;
	private Integer candidato;
	private Integer votante;
	
	public Voto() {
		
	}

	public Voto(Integer id, Timestamp fechaCreacion, Timestamp fechaVoto, String uuid, String enlace, Integer estamento,
			Integer candidato, Integer votante) {
		super();
		this.id = id;
		this.fechaCreacion = fechaCreacion;
		this.fechaVoto = fechaVoto;
		this.uuid = uuid;
		this.enlace = enlace;
		this.estamento = estamento;
		this.candidato = candidato;
		this.votante = votante;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaVoto() {
		return fechaVoto;
	}

	public void setFechaVoto(Timestamp fechaVoto) {
		this.fechaVoto = fechaVoto;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public Integer getEstamento() {
		return estamento;
	}

	public void setEstamento(Integer estamento) {
		this.estamento = estamento;
	}

	public Integer getCandidato() {
		return candidato;
	}

	public void setCandidato(Integer candidato) {
		this.candidato = candidato;
	}

	public Integer getVotante() {
		return votante;
	}

	public void setVotante(Integer votante) {
		this.votante = votante;
	}

	@Override
	public String toString() {
		return "Voto [id=" + id + ", fechaCreacion=" + fechaCreacion + ", fechaVoto=" + fechaVoto + ", uuid=" + uuid
				+ ", enlace=" + enlace + ", estamento=" + estamento + ", candidato=" + candidato + ", votante="
				+ votante + "]";
	}
	
}
