package co.edu.ufps.web.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import co.edu.ufps.web.model.TipoDocumento;
import co.edu.ufps.web.util.Conexion;

public class TipoDocumentoDAO implements CrudDAO<TipoDocumento,Integer> {
	private Conexion con;
	private Connection conection;
	private static final String INSERTAR_TIPODOCUMENTO_SQL = "INSERT INTO tipodocumento(id,descripcion) VALUES (?,?)";
	private static final String ELIMINAR_TIPODOCUMENTO_SQL = "DELETE FROM tipodocumento WHERE id=?";
	private static final String ACTUALIZAR_TIPODOCUMENTO_SQL = "UPDATE tipodocumento SET descripcion=? WHERE id=?";
	private static final String BUSCAR_TIPODOCUMENTO_ID_SQL = "SELECT * FROM tipodocumento WHERE id=?";
	private static final String LISTAR_TIPODOCUMENTOS_SQL = "SELECT * FROM tipodocumento";

	public TipoDocumentoDAO() throws SQLException {
		this.con = new Conexion();
	}

	@Override
	public boolean insertar(TipoDocumento el)throws SQLException {
		boolean rowInserted = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(INSERTAR_TIPODOCUMENTO_SQL);
		prepared.setInt(1, el.getId());
		prepared.setString(2, el.getDescripcion());

		rowInserted = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowInserted;
	}

	@Override
	public boolean actualizar(TipoDocumento el)  throws SQLException {
		boolean rowElimined = false;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared = this.conection.prepareStatement(ACTUALIZAR_TIPODOCUMENTO_SQL);
		prepared.setString(1, el.getDescripcion());
		prepared.setInt(2, el.getId());

		rowElimined = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowElimined;
	}

	@Override
	public TipoDocumento buscar(Integer id) throws SQLException {
		TipoDocumento e = null;

		this.con.conectar();
		this.conection = this.con.conectar();

		PreparedStatement prepared=this.conection.prepareStatement(BUSCAR_TIPODOCUMENTO_ID_SQL);
		prepared.setInt(1, id);

		ResultSet rs = prepared.executeQuery();

		if (rs!=null && rs.next()) {
			
			e = new TipoDocumento(id, rs.getString("descripcion"));
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

		PreparedStatement prepared = this.conection.prepareStatement(ELIMINAR_TIPODOCUMENTO_SQL);
		prepared.setInt(1, id);

		rowElimined = prepared.executeUpdate() > 0;
		prepared.close();
		this.con.desconectar();

		return rowElimined;
	}
	
	@Override
	public List<TipoDocumento> list() throws SQLException {
		List<TipoDocumento> list = new ArrayList<>();

		this.con.conectar();
		this.conection = this.con.conectar();

		Statement statement = this.conection.createStatement();
		ResultSet rs = statement.executeQuery(LISTAR_TIPODOCUMENTOS_SQL);

		while (rs.next()) {
			Integer id = rs.getInt("id");
			String descripcion = rs.getString("descripcion");

			TipoDocumento el = new TipoDocumento(id,descripcion);
			list.add(el);
		}

		this.con.desconectar();

		return list;
	}
	
}

