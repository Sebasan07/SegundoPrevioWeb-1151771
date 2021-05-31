package co.edu.ufps.web.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.ufps.web.DAO.VotanteDAO;
import co.edu.ufps.web.model.Votante;

/**
 * Servlet implementation class VotanteServlet
 */
@WebServlet("/VotanteServlet")
public class VotanteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VotanteDAO votanteDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VotanteServlet() throws SQLException {
		super();
		votanteDAO= new VotanteDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Servlet Votante");
		String action = request.getParameter("action");
		System.out.println("action");

		try {
			switch (action) {
			case "index":
				index(request, response);
				break;
			case "nuevo":
				nuevo(request, response);
				break;
			case "register":
				registrar(request, response);
				break;
			case "mostrar":
				mostrar(request, response);
				break;
			case "showedit":
				showEditor(request, response);
				break;
			case "editar":
				editar(request, response);
				break;
			case "eliminar":
				eliminar(request, response);
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Servlet Votante");
		doGet(request, response);
	}

	private void index(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		// mostrar(request, response);
		RequestDispatcher dispatcher = request.getRequestDispatcher("indexVotantes.jsp");
		dispatcher.forward(request, response);
	}

	private void registrar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		Votante vt = new Votante(Integer.parseInt(request.getParameter("id")), request.getParameter("nombre"),request.getParameter("email"), request.getParameter("documento"),  
				Integer.parseInt(request.getParameter("tipodocumento")), Integer.parseInt(request.getParameter("eleccion")));
		votanteDAO.insertar(vt);
		RequestDispatcher dispatcher = request.getRequestDispatcher("indexVotantes.jsp");
		dispatcher.forward(request, response);
	}

	private void nuevo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("vistaVotantes/registro.jsp");
		dispatcher.forward(request, response);
	}

	private void mostrar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("vistaVotantes/mostrar.jsp");
		List<Votante> list = votanteDAO.list();
		request.setAttribute("lista", list);
		dispatcher.forward(request, response);
	}

	private void showEditor(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		Votante vt = votanteDAO.buscar(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("votante", vt);

		RequestDispatcher dispatcher = request.getRequestDispatcher("vistaVotantes/editar.jsp");
		dispatcher.forward(request, response);
	}

	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		Votante vt = new Votante(Integer.parseInt(request.getParameter("id")), request.getParameter("nombre"),request.getParameter("email"), request.getParameter("documento"),  
				Integer.parseInt(request.getParameter("tipodocumento")), Integer.parseInt(request.getParameter("eleccion")));
		votanteDAO.actualizar(vt);
		index(request, response);
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		Votante vt = votanteDAO.buscar(Integer.parseInt(request.getParameter("id")));
		votanteDAO.eliminar(vt.getId());
		RequestDispatcher dispatcher = request.getRequestDispatcher("indexVotantes.jsp");
		dispatcher.forward(request, response);

	}

}
