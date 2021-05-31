package co.edu.ufps.web.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.ufps.web.model.Votante;
import co.edu.ufps.web.util.Conexion;

public class VotanteDAO implements CrudDAO<Votante,Integer> {
	private Conexion con;
	private Connection conection;
	private static final String INSERTAR_VOTANTE_SQL = "INSERT INTO votante(id,nombre,email,documento,tipodocumento,eleccion) VALUES (?,?,?,?,?,?)";
	private static final String ELIMINAR_VOTANTE_SQL = "DELETE FROM votante WHERE id=?";
	private static final String ACTUALIZAR_VOTANTE_SQL = "UPDATE votante SET nombre=?,email=?,documento=?,tipodocumento=?,eleccion=? WHERE id=?";
	private static final String BUSCAR_VOTANTE_ID_SQL = "SELECT * FROM votante WHERE id=?";
	private static final String BUSCAR_VOTANTE_DOCUMENTO_SQL = "SELECT * FROM votante WHERE documento=?";
	private static final String BUSCAR_ULTIMO_ID_SQL = "SELECT MAX(id) FROM votante";
	private static final String LISTAR_VOTANTES_SQL = "SELECT * FROM votante";

	public VotanteDAO() throws SQLException {
		this.con = new Conexion();
	}

	@Override
	public boolean insertar(Votante vt)throws SQLException {
		boolean rowInserted = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(INSERTAR_VOTANTE_SQL);
		prepared.setInt(1, vt.getId());
		prepared.setString(2, vt.getNombre());
		prepared.setString(3, vt.getEmail());
		prepared.setString(4, vt.getDocumento());
		prepared.setInt(5, vt.getTipoDocumento());
		prepared.setInt(6, vt.getEleccion());

		rowInserted = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowInserted;
	}

	@Override
	public boolean actualizar(Votante vt)  throws SQLException {
		boolean rowElimined = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(ACTUALIZAR_VOTANTE_SQL);
		prepared.setString(1, vt.getNombre());
		prepared.setString(2, vt.getEmail());
		prepared.setString(3, vt.getDocumento());
		prepared.setInt(4, vt.getTipoDocumento());
		prepared.setInt(5, vt.getEleccion());
		prepared.setInt(6, vt.getId());

		rowElimined = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowElimined;
	}

	@Override
	public Votante buscar(Integer id) throws SQLException {
		Votante v = null;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(BUSCAR_VOTANTE_ID_SQL);
		prepared.setInt(1, id);

		ResultSet rs = prepared.executeQuery();

		if (rs!=null && rs.next()) {
			
			v = new Votante(id, rs.getString("nombre"), rs.getString("email"), rs.getString("documento"),
					rs.getInt("tipodocumento"),rs.getInt("eleccion"));
		}
		rs.close();
		this.con.desconectar();

		return v;
	}

	public Votante buscarPorDocumento(String doc) throws SQLException {
		Votante v = null;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(BUSCAR_VOTANTE_DOCUMENTO_SQL);
		prepared.setString(1, doc);

		ResultSet rs = prepared.executeQuery();

		if (rs!=null && rs.next()) {
			
			v = new Votante(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("documento"),
					rs.getInt("tipodocumento"),rs.getInt("eleccion"));
		}
		rs.close();
		this.con.desconectar();

		return v;
	}
	
	public Integer buscarUltimoID() throws SQLException {
		Integer id=0;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(BUSCAR_ULTIMO_ID_SQL);

		ResultSet rs = prepared.executeQuery();

		if (rs!=null && rs.next()) {
			if(rs.getString(1)!=null) {
			id=Integer.parseInt(rs.getString(1));
			}
		}
		rs.close();
		this.con.desconectar();

		return id;
	}
	
	@Override
	public boolean eliminar(Integer id) throws SQLException {
		boolean rowElimined = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(ELIMINAR_VOTANTE_SQL);
		prepared.setInt(1, id);

		rowElimined = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowElimined;
	}
	
	@Override
	public List<Votante> list() throws SQLException {
		List<Votante> list = new ArrayList<>();

		this.con.conectar();
		this.conection = this.con.conectar();

		Statement statement = this.conection.createStatement();
		ResultSet rs = statement.executeQuery(LISTAR_VOTANTES_SQL);

		while (rs.next()) {
			Integer id = rs.getInt("id");
			String documento = rs.getString("documento");
			String nombre = rs.getString("nombre");
			String email = rs.getString("email");
			Integer eleccion = rs.getInt("eleccion");
			Integer tipoDocumento = rs.getInt("tipodocumento");

			Votante vt = new Votante(id,nombre, email, documento, tipoDocumento, eleccion);
			list.add(vt);
		}

		this.con.desconectar();

		return list;
	}
	
}

