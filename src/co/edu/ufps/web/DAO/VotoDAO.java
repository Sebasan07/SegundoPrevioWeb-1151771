package co.edu.ufps.web.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import co.edu.ufps.web.model.Voto;
import co.edu.ufps.web.util.Conexion;

public class VotoDAO implements CrudDAO<Voto,Integer> {
	private Conexion con;
	private Connection conection;
	private static final String INSERTAR_VOTO_SQL = "INSERT INTO voto(id,fechacreacion,fechavoto,uuid,enlace,estamento,candidato,votante) VALUES (?,?,?,?,?,?,?,?)";
	private static final String ELIMINAR_VOTO_SQL = "DELETE FROM voto WHERE id=?";
	private static final String ACTUALIZAR_VOTO_SQL = "UPDATE voto SET fechacreacion=?,fechavoto=?,uuid=?,enlace=?,estamento=?,candidato=?,votante=? WHERE id=?";
	private static final String BUSCAR_VOTO_ID_SQL = "SELECT * FROM voto WHERE id=?";
	private static final String LISTAR_VOTOS_SQL = "SELECT * FROM voto";

	public VotoDAO() throws SQLException {
		this.con = new Conexion();
	}

	@Override
	public boolean insertar(Voto vt)throws SQLException {
		boolean rowInserted = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(INSERTAR_VOTO_SQL);
		prepared.setInt(1, vt.getId());
		prepared.setTimestamp(2, vt.getFechaCreacion());
		prepared.setTimestamp(3, vt.getFechaVoto());
		prepared.setString(4, vt.getUuid());
		prepared.setString(5, vt.getEnlace());
		prepared.setInt(6, vt.getEstamento());
		prepared.setInt(7, vt.getCandidato());
		prepared.setInt(8, vt.getVotante());

		rowInserted = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowInserted;
	}

	@Override
	public boolean actualizar(Voto vt)  throws SQLException {
		boolean rowElimined = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(ACTUALIZAR_VOTO_SQL);
		prepared.setTimestamp(1, vt.getFechaCreacion());
		prepared.setTimestamp(2, vt.getFechaVoto());
		prepared.setString(3, vt.getUuid());
		prepared.setString(4, vt.getEnlace());
		prepared.setInt(5, vt.getEstamento());
		prepared.setInt(6, vt.getCandidato());
		prepared.setInt(7, vt.getVotante());
		prepared.setInt(8, vt.getId());

		rowElimined = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowElimined;
	}

	@Override
	public Voto buscar(Integer id) throws SQLException {
		Voto v = null;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(BUSCAR_VOTO_ID_SQL);
		prepared.setInt(1, id);

		ResultSet rs = prepared.executeQuery();

		if (rs!=null && rs.next()) {
			
			v = new Voto(id, Timestamp.valueOf(rs.getString("fechacreacion")), Timestamp.valueOf(rs.getString("fechavoto")), rs.getString("uuid"),
					rs.getString("enlace"),rs.getInt("estamento"),rs.getInt("candidato"),rs.getInt("votante"));
		}
		rs.close();
		this.con.desconectar();

		return v;
	}

	@Override
	public boolean eliminar(Integer id) throws SQLException {
		boolean rowElimined = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(ELIMINAR_VOTO_SQL);
		prepared.setInt(1, id);

		rowElimined = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowElimined;
	}
	
	@Override
	public List<Voto> list() throws SQLException {
		List<Voto> list = new ArrayList<>();

		this.con.conectar();
		this.conection = this.con.conectar();

		Statement statement = this.conection.createStatement();
		ResultSet rs = statement.executeQuery(LISTAR_VOTOS_SQL);

		while (rs.next()) {
			Integer id = rs.getInt("id");
			Timestamp fechaCreacion =Timestamp.valueOf(rs.getString("fechacreacion"));
			Timestamp fechaVoto =Timestamp.valueOf(rs.getString("fechaVoto"));
			String uuid = rs.getString("uuid");
			String enlace = rs.getString("enlace");
			Integer estamento = rs.getInt("estamento");
			Integer candidato = rs.getInt("candidato");
			Integer votante = rs.getInt("votante");

			Voto vt = new Voto(id, fechaCreacion, fechaVoto, uuid, enlace, estamento, candidato, votante);
			list.add(vt);
		}

		this.con.desconectar();

		return list;
	}
	
}

