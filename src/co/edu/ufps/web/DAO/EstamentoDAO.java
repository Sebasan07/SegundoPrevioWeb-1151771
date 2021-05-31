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

import co.edu.ufps.web.model.Estamento;
import co.edu.ufps.web.util.Conexion;

public class EstamentoDAO implements CrudDAO<Estamento,Integer> {
	private Conexion con;
	private Connection conection;
	private static final String INSERTAR_ELECCION_SQL = "INSERT INTO eleccion(id,eleccion,descripcion) VALUES (?,?,?)";
	private static final String ELIMINAR_ELECCION_SQL = "DELETE FROM eleccion WHERE id=?";
	private static final String ACTUALIZAR_ELECCION_SQL = "UPDATE eleccion SET eleccion=?,descripcion=? WHERE id=?";
	private static final String BUSCAR_ELECCION_ID_SQL = "SELECT * FROM eleccion WHERE id=?";
	private static final String LISTAR_ELECCIONES_SQL = "SELECT * FROM eleccion";

	public EstamentoDAO() throws SQLException {
		this.con = new Conexion();
	}

	@Override
	public boolean insertar(Estamento el)throws SQLException {
		boolean rowInserted = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(INSERTAR_ELECCION_SQL);
		prepared.setInt(1, el.getId());
		prepared.setInt(2, el.getEleccion());
		prepared.setString(3, el.getDescripcion());

		rowInserted = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowInserted;
	}

	@Override
	public boolean actualizar(Estamento el)  throws SQLException {
		boolean rowElimined = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(ACTUALIZAR_ELECCION_SQL);
		prepared.setInt(1, el.getEleccion());
		prepared.setString(2, el.getDescripcion());
		prepared.setInt(3, el.getId());

		rowElimined = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowElimined;
	}

	@Override
	public Estamento buscar(Integer id) throws SQLException {
		Estamento e = null;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared=this.conection.prepareStatement(BUSCAR_ELECCION_ID_SQL);
		prepared.setInt(1, id);

		ResultSet rs = prepared.executeQuery();

		if (rs!=null && rs.next()) {
			
			e = new Estamento(id, rs.getInt("fechainicio"), rs.getString("descripcion"));
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
	public List<Estamento> list() throws SQLException {
		List<Estamento> list = new ArrayList<>();

		this.con.conectar();
		this.conection = this.con.conectar();

		Statement statement = this.conection.createStatement();
		ResultSet rs = statement.executeQuery(LISTAR_ELECCIONES_SQL);

		while (rs.next()) {
			Integer id = rs.getInt("id");
			Integer eleccion = rs.getInt("eleccion");
			String descripcion = rs.getString("descripcion");

			Estamento el = new Estamento(id, eleccion,descripcion);
			list.add(el);
		}

		this.con.desconectar();

		return list;
	}
	
}

