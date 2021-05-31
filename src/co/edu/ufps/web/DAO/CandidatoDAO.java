package co.edu.ufps.web.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.ufps.web.model.Candidato;
import co.edu.ufps.web.util.Conexion;

public class CandidatoDAO implements CrudDAO<Candidato,Integer> {
	private Conexion con;
	private Connection conection;
	private static final String INSERTAR_CANDIDATO_SQL = "INSERT INTO candidato(id,documento,nombre,apellido,eleccion,numero) VALUES (?,?,?,?,?,?)";
	private static final String ELIMINAR_CANDIDATO_SQL = "DELETE FROM candidato WHERE id=?";
	private static final String ACTUALIZAR_CANDIDATO_SQL = "UPDATE candidato SET documento=?,nombre=?,apellido=?,eleccion=?,numero=? WHERE id=?";
	private static final String BUSCAR_CANDIDATO_ID_SQL = "SELECT * FROM candidato WHERE id=?";
	private static final String LISTAR_CANDIDATOS_SQL = "SELECT * FROM candidato";

	public CandidatoDAO() throws SQLException {
		this.con = new Conexion();
	}

	@Override
	public boolean insertar(Candidato cl)throws SQLException {
		boolean rowInserted = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(INSERTAR_CANDIDATO_SQL);
		prepared.setInt(1, cl.getId());
		prepared.setString(2, cl.getDocumento());
		prepared.setString(3, cl.getNombre());
		prepared.setString(4, cl.getApellido());
		prepared.setInt(5, cl.getEleccion());
		prepared.setInt(6, cl.getNumero());

		rowInserted = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowInserted;
	}

	@Override
	public boolean actualizar(Candidato cl)  throws SQLException {
		boolean rowElimined = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(ACTUALIZAR_CANDIDATO_SQL);
		prepared.setString(1, cl.getDocumento());
		prepared.setString(2, cl.getNombre());
		prepared.setString(3, cl.getApellido());
		prepared.setInt(4, cl.getEleccion());
		prepared.setInt(5, cl.getNumero());
		prepared.setInt(6, cl.getId());

		rowElimined = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowElimined;
	}

	@Override
	public Candidato buscar(Integer id) throws SQLException {
		Candidato e = null;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(BUSCAR_CANDIDATO_ID_SQL);
		prepared.setInt(1, id);

		ResultSet rs = prepared.executeQuery();

		if (rs!=null && rs.next()) {
			
			e = new Candidato(id, rs.getString("documento"), rs.getString("nombre"), rs.getString("apellido"),
					rs.getInt("eleccion"),rs.getInt("numero"));
		}
		rs.close();
		this.con.desconectar();

		return e;
	}

	@Override
	public boolean eliminar(Integer id) throws SQLException {
		boolean rowElimined = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(ELIMINAR_CANDIDATO_SQL);
		prepared.setInt(1, id);

		rowElimined = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowElimined;
	}
	
	@Override
	public List<Candidato> list() throws SQLException {
		List<Candidato> list = new ArrayList<>();

		this.con.conectar();
		this.conection = this.con.conectar();

		Statement statement = this.conection.createStatement();
		ResultSet rs = statement.executeQuery(LISTAR_CANDIDATOS_SQL);

		while (rs.next()) {
			Integer id = rs.getInt("id");
			String documento = rs.getString("documento");
			String nombre = rs.getString("nombre");
			String apellido = rs.getString("apellido");
			Integer eleccion = rs.getInt("eleccion");
			Integer numero = rs.getInt("numero");

			Candidato cl = new Candidato(id, documento, nombre, apellido, eleccion, numero);
			list.add(cl);
		}

		this.con.desconectar();

		return list;
	}
	
}

