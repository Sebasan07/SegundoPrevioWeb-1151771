package co.edu.ufps.web.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import co.edu.ufps.web.model.Eleccion;
import co.edu.ufps.web.util.Conexion;

public class EleccionDAO implements CrudDAO<Eleccion,Integer> {
	private Conexion con;
	private Connection conection;
	private static final String INSERTAR_ELECCION_SQL = "INSERT INTO eleccion(id,nombre,fechainicio,fechafin,cargo) VALUES (?,?,?,?,?)";
	private static final String ELIMINAR_ELECCION_SQL = "DELETE FROM eleccion WHERE id=?";
	private static final String ACTUALIZAR_ELECCION_SQL = "UPDATE eleccion SET nombre=?,fechainicio=?,fechafin=?,cargo=? WHERE id=?";
	private static final String BUSCAR_ELECCION_ID_SQL = "SELECT * FROM eleccion WHERE id=?";
	private static final String LISTAR_ELECCIONES_SQL = "SELECT * FROM eleccion";

	public EleccionDAO() throws SQLException {
		this.con = new Conexion();
	}

	@Override
	public boolean insertar(Eleccion cl)throws SQLException {
		boolean rowInserted = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(INSERTAR_ELECCION_SQL);
		prepared.setInt(1, cl.getId());
		prepared.setString(2, cl.getNombre());
		prepared.setTimestamp(3, cl.getFechaInicio());
		prepared.setTimestamp(4, cl.getFechaFin());
		prepared.setString(5, cl.getCargo());

		rowInserted = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowInserted;
	}

	@Override
	public boolean actualizar(Eleccion cl)  throws SQLException {
		boolean rowElimined = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(ACTUALIZAR_ELECCION_SQL);
		prepared.setString(1, cl.getNombre());
		prepared.setTimestamp(2, cl.getFechaInicio());
		prepared.setTimestamp(3, cl.getFechaFin());
		prepared.setString(4, cl.getCargo());
		prepared.setInt(5, cl.getId());

		rowElimined = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowElimined;
	}

	@Override
	public Eleccion buscar(Integer id) throws SQLException {
		Eleccion e = null;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(BUSCAR_ELECCION_ID_SQL);
		prepared.setInt(1, id);

		ResultSet rs = prepared.executeQuery();

		if (rs!=null && rs.next()) {
			
			e = new Eleccion(id, rs.getString("nombre"), Timestamp.valueOf(rs.getString("fechainicio")), Timestamp.valueOf(rs.getString("fechafin")),
					rs.getString("cargo"));
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

		PreparedStatement prepared = this.conection.prepareStatement(ELIMINAR_ELECCION_SQL);
		prepared.setInt(1, id);

		rowElimined = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowElimined;
	}
	
	@Override
	public List<Eleccion> list() throws SQLException {
		List<Eleccion> list = new ArrayList<>();

		this.con.conectar();
		this.conection = this.con.conectar();

		Statement statement = this.conection.createStatement();
		ResultSet rs = statement.executeQuery(LISTAR_ELECCIONES_SQL);

		while (rs.next()) {
			Integer id = rs.getInt("id");
			Timestamp fechaInicio = Timestamp.valueOf(rs.getString("fechainicio"));
			Timestamp fechaFin = Timestamp.valueOf(rs.getString("fechafin"));
			String nombre = rs.getString("nombre");
			String cargo = rs.getString("cargo");

			Eleccion cl = new Eleccion(id, nombre,fechaInicio,fechaFin,cargo);
			list.add(cl);
		}

		this.con.desconectar();

		return list;
	}
	
}

