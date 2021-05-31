package co.edu.ufps.web.util;

import java.sql.SQLException;

import co.edu.ufps.web.DAO.*;

public class Prueba {

	public static void main(String[] args) {
		
		VotanteDAO cDAO;
		try {
			cDAO = new VotanteDAO();
			System.out.println(cDAO.buscarUltimoID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
